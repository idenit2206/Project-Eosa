package com.eosa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.eosa.security.handler.adminsite.CustomAdminAuthFailureHandler;
import com.eosa.security.handler.adminsite.CustomAdminAuthSuccessHandler;
import com.eosa.security.handler.adminsite.CustomAdminLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class AdminSecurityConfig {

    @Autowired private CustomAdminLogoutSuccessHandler customAdminLogoutSuccessHandler;
    @Autowired private CustomAdminAuthSuccessHandler customAdminAuthSuccessHandler;
    private CustomAdminAuthFailureHandler customAdminAuthFailureHandler;

    private String[] PERMIT_URL = {
        "/assets/**", "/js/**", "/css/**", "/webjars/**",
        "/admin", "/admin/sign/**"
    };

    
    /** 
     * @param http
     * @return SecurityFilterChain
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http
            .antMatcher("/admin/**")
            .authorizeRequests()
                .antMatchers(PERMIT_URL).permitAll()              
                // .anyRequest().hasAnyAuthority("ADMIN", "SUPER_ADMIN")              
                .anyRequest().permitAll()
        .and()
            .formLogin()
                .loginPage("/admin")
                    .usernameParameter("usersAccount").passwordParameter("usersPass")
                    .loginProcessingUrl("/admin/sign/signIn.do")
                    // .defaultSuccessUrl("/admin/sign/signIn.success")
                    .successHandler(customAdminAuthSuccessHandler)
                    .failureHandler(customAdminAuthFailureHandler)
        .and()
            .logout()
                    .logoutUrl("/admin/sign/signOut")
                        .logoutSuccessHandler(customAdminLogoutSuccessHandler);

        return http.build();
    }

    @Bean
    public SecurityFilterChain swaggerFilterChain(HttpSecurity http) throws Exception {
        
        http.csrf().disable();

        http
            .antMatcher("/swagger-ui/**")
            .authorizeRequests()            
                .anyRequest().hasAnyAuthority("ADMIN", "SUPER_ADMIN")                
                // .anyRequest().permitAll()
        .and()
            .formLogin()
                .loginPage("/admin")
                    .usernameParameter("usersAccount").passwordParameter("usersPass")
                    .loginProcessingUrl("/admin/sign/signIn.do")
                    .defaultSuccessUrl("/swagger-ui/index.html")
                    // .successHandler(customAdminAuthSuccessHandler)
                    .failureHandler(customAdminAuthFailureHandler);


        return http.build();
    }

}
