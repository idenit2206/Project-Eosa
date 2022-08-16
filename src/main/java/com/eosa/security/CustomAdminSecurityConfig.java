package com.eosa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomAdminSecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    private String[] ANYONE_PERMIT = {
        "/assets/**", "/js/**", "/css/**", "/webjars/**",
        "/admin/sign/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .antMatcher("/admin/**")
            .authorizeRequests().anyRequest().authenticated()
                // .antMatchers(ANYONE_PERMIT).permitAll()
                // .anyRequest().hasAnyAuthority("ADMIN, SUPER_ADMIN")                
        .and()
            .formLogin()
                .loginPage("/admin/sign/signIn")
                    .loginProcessingUrl("/admin/sign/signIn.do")
                    .usernameParameter("usersAccount").passwordParameter("usersPass")                       
                    .successForwardUrl("/admin/sign/signIn.success")
                    .failureForwardUrl("/admin/sign/signIn.failure")            
        .and()
            .logout();
            
        return http.build();
    }
}