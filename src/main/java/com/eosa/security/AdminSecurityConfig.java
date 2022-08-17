package com.eosa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(2)
public class AdminSecurityConfig {

    @Autowired private CustomLogoutSuccessHandler customLogoutSuccessHandler; 

    private String[] PERMIT_URL = {
        "/assets/**", "/js/**", "/css/**", "/webjars/**", 
        "/admin/sign/**"
    };
    
    @Bean
    public SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {
        http
            .cors().disable()
            .csrf().disable()
            .antMatcher("/admin/**")
            .authorizeRequests()
                .antMatchers(PERMIT_URL).permitAll()
                // .anyRequest().hasAnyAuthority("ADMIN", "SUPER_ADMIN")
                .anyRequest().permitAll()
        .and()
            .formLogin()
                .loginPage("/admin/sign/signIn")
                    .loginProcessingUrl("/admin/sign/signIn.do")
                    .usernameParameter("usersAccount").passwordParameter("usersPass")                       
                    .successForwardUrl("/admin/sign/signIn.success")
                    .failureForwardUrl("/admin/sign/signIn.failure")
        .and()
            .logout()
                    .logoutUrl("/api/user/signOut.do")
                        .logoutSuccessHandler(customLogoutSuccessHandler);

        return http.build();
    }

}
