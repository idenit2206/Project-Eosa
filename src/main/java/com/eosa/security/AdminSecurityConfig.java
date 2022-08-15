package com.eosa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(1)
public class AdminSecurityConfig {

    @Autowired private CustomLogoutSuccessHandler customLogoutSuccessHandler; 
    
    @Bean
    public SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {
        http
            .cors().disable()
            .csrf().disable()
            .antMatcher("/admin/**")
            .authorizeRequests()
                .antMatchers("/admin/singIn", "/admin/signUp", "/admin/test01").permitAll()
                .anyRequest().hasAnyAuthority("ADMIN")
                // .anyRequest().permitAll()
        .and()
            .formLogin()
                .loginPage("/admin/signIn")
                    .loginProcessingUrl("/admin/signIn.do")
                    // .usernameParameter("usersAccount").passwordParameter("usersPass")                       
                    // .successForwardUrl("/api/user/signIn.success")
                    // .failureForwardUrl("/api/user/signIn.failure")
        .and()
            .logout()
                    .logoutUrl("/api/user/signOut.do")
                        .logoutSuccessHandler(customLogoutSuccessHandler);

        return http.build();
    }

}
