package com.eosa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.eosa.security.handler.servicesite.CustomLogoutSuccessHandler;
import com.eosa.security.oauth2.CustomPrincipalOAuth2UserService;

@Configuration
public class CustomSecurityConfig {

    @Autowired private CustomLogoutSuccessHandler customLogoutSuccessHandler; 
    @Autowired private CustomPrincipalOAuth2UserService customPrincipalOAuth2UserService;    
    @Value("${my.service.domain}") private String myDomain;

    
    /** 
     * @return BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    
    /** 
     * @return CorsConfigurationSource
     */
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
        "/oauth2/**", "/api/user/**", "/api/mail/**",
    };

    

    
    /** 
     * @param http
     * @return SecurityFilterChain
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors().disable()
            .cors().configurationSource(customConfigurationSource())
        .and()
            .csrf().disable()       
            .authorizeRequests()
                .antMatchers(ANYONE_PERMIT).permitAll()
                .antMatchers("/api/**")
                    // .hasAnyAuthority("CLIENT", "DETECTIVE")
                    .permitAll()
        .and()
            .formLogin()
                .loginPage("http://" + myDomain + "/user/signin")
                    .loginProcessingUrl("/api/user/sign/signIn.do")
                    .usernameParameter("usersAccount").passwordParameter("usersPass")                       
                    .successForwardUrl("/api/user/sign/signIn.success")
                    // .successHandler(new FormLoginSuccessHandler())
                    .failureForwardUrl("/api/user/sign/signIn.failure")            
        .and()
            .oauth2Login()
                .loginPage("http://" + myDomain + ":3000/user/signin")
                    .defaultSuccessUrl("/api/user/sign/oauth2SignIn.success")
                    .failureUrl("/api/user/sign/oauth2SignIn.failure")
                    
        .and()
            .logout()
                    .logoutUrl("/api/user/signOut")
                        .logoutSuccessHandler(customLogoutSuccessHandler)
                        .deleteCookies("JSESSIONID")
                        .permitAll();
        return http.build();
    }
}
