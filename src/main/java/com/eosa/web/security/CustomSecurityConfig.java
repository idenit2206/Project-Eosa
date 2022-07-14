package com.eosa.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.eosa.web.security.jwt.JwtAuthFilter;
import com.eosa.web.security.jwt.TokenService;
import com.eosa.web.security.oauth2.service.CustomOAuth2UserService;
import com.eosa.web.security.oauth2.service.OAuth2SuccessHandler;

@Configuration
@EnableWebSecurity
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired private CustomOAuth2UserService customOAuth2UserService;
    @Autowired private OAuth2SuccessHandler oAuth2SuccessHandler;
    @Autowired private TokenService tokenService;
 
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    private static final String[] PERMIT_URL_ARRAY = {
        "/swagger-ui/index.html"        
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()           
            .authorizeRequests().antMatchers("/token/**").permitAll()
             // 모든 요청에 대한 권한 해제
            .anyRequest().permitAll()
            // .authorizeRequests()
                // .anyRequest()
            // .antMatchers(PERMIT_URL_ARRAY)
                // .hasAnyAuthority("ADMIN")        
            .and()
            .formLogin()
                .loginPage("http://localhost:3000/")
                .usernameParameter("usersAccount")
                .passwordParameter("usersPass")
                .loginProcessingUrl("/api/user/signIn.do")
                // .defaultSuccessUrl("http://localhost:3000") // login에 성공했을 때 이동할 페이지
                .successForwardUrl("/api/user/signIn.success") // login에 성공했을 때 연결할 URL을 매개변수로 사용
                .failureForwardUrl("/api/user/signIn.failure")
                .permitAll()            
            .and()
            .logout()
                .logoutUrl("/admin/logout.do")
                .logoutSuccessUrl("/") // 로그아웃에 성공한 사용자에게 보여줄 페이지의 URL을 매개변수로 사용
                .permitAll()
            .and()          
            .addFilterBefore(new JwtAuthFilter(tokenService), UsernamePasswordAuthenticationFilter.class)
            .oauth2Login().loginPage("/token/expired")
            .successHandler(oAuth2SuccessHandler)
            .userInfoEndpoint().userService(customOAuth2UserService);

        http.addFilterBefore(new JwtAuthFilter(tokenService), UsernamePasswordAuthenticationFilter.class);
            // .oauth2Login()
            //     .loginPage("/http://localhost:3000/")
            //     .userInfoEndpoint()
            //         // parameter로 CustomOAuth2UserService
            //         .userService(customOAuth2UserService);         
            // .and()
            // // // 권한외의 접근을 시도할 때 출력할 페이지
            // // .exceptionHandling()
            // //     .accessDeniedPage("/admin/");
            // .exceptionHandling()
            //     .accessDeniedHandler(customAccessDeniedHandler);
    }

}
