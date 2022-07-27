package com.eosa.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.eosa.web.security.oauth2.CustomPrincipalOAuth2UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class CustomSecurityConfig {

    // @Autowired private CustomPrincipalOAuth2UserService customPrincipalOAuth2UserService;
    
    private String[] ANYONE_PERMIT = {
       "/", "/api/user/**", "/api/mail/**"
    };

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors().disable()
            .csrf().disable()
            .authorizeRequests()
                .antMatchers(ANYONE_PERMIT).permitAll()
                .anyRequest().authenticated()
                // .anyRequest().hasAnyAuthority("ADMIN, SUPER_ADMIN")
        .and()
            .formLogin()
                .loginPage("http://localhost:3000/user/signin")
                    // .loginProcessingUrl("/api/user/signIn.do")
                        .loginProcessingUrl("/api/user/signIn.do")
                        .usernameParameter("usersAccount").passwordParameter("usersPass")                       
                        .successForwardUrl("/api/user/signIn.success")
                        .failureForwardUrl("/api/user/signIn.failure")
                        .permitAll()
        .and()
            .oauth2Login()
                .loginPage("http://localhost:3000/user/signin")  
                    .defaultSuccessUrl("/api/user/oauth2SignIn.success")
                    .failureUrl("http://localhost:3000/user/register");
                    // .userInfoEndpoint()
                    // .userService(customPrincipalOAuth2UserService);
        return http.build();
    }


}
