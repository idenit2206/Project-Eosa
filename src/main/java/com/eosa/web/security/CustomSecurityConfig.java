package com.eosa.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.eosa.web.security.oauth2.CustomPrincipalOAuth2UserService;

@EnableWebSecurity
public class CustomSecurityConfig {

    private CustomPrincipalOAuth2UserService principalOAuth2UserService;
    
    private String[] ANYONE_PERMIT = {
       "/", "/api/user/**"
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
        .and()
            .oauth2Login()
                .loginPage("http://localhost:3000/user/signin")
                    .defaultSuccessUrl("http://localhost:3000/")
                    .failureUrl("http://localhost:3000/user/register");

        return http.build();
    }


}
