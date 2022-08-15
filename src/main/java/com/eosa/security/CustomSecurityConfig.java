package com.eosa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@Order(1)
public class CustomSecurityConfig {

    // @Autowired private CustomPrincipalOAuth2UserService customPrincipalOAuth2UserService;
    @Autowired private CustomLogoutSuccessHandler customLogoutSuccessHandler; 

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource customConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    private String[] ANYONE_PERMIT = {
        // Admin Page
        "/assets/**", "/js/**", "/css/**", "/webjars/**", "/admin/signIn", "/admin/**",       
        "/api/user/**", "/api/mail/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .cors().configurationSource(customConfigurationSource())
        .and()
            .antMatcher("/api/**")
            .authorizeRequests()
                .antMatchers(ANYONE_PERMIT).permitAll()
                .anyRequest().hasAnyAuthority("CLIENT, DETECTIVE")
        .and()
            .formLogin()
                .loginPage("http://localhost:3000/user/signin")
                    .loginProcessingUrl("/api/user/signIn.do")
                    .usernameParameter("usersAccount").passwordParameter("usersPass")                       
                    .successForwardUrl("/api/user/signIn.success")
                    .failureForwardUrl("/api/user/signIn.failure")            
        .and()
            .oauth2Login()
                .loginPage("http://localhost:3000/user/signin")
                    .defaultSuccessUrl("/api/user/oauth2SignIn.success")
                    // .defaultSuccessUrl("/api/user2/oauth2SignIn.success")
                    .failureUrl("/api/user/oauth2SignIn.failure")
                    // .userInfoEndpoint()
                    // .userService(customPrincipalOAuth2UserService);
        .and()
            .logout()
                    .logoutUrl("/api/user/signOut.do")
                        .logoutSuccessHandler(customLogoutSuccessHandler);

        return http.build();
    }
}
