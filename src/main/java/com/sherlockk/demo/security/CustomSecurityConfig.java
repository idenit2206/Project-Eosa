package com.sherlockk.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sherlockk.demo.oauth2.PrincipalOauth2UserService;

@Configuration
@EnableWebSecurity
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired 
    private PrincipalOauth2UserService principalOauth2UserService;
 
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
        http
            .csrf().disable()
            // 모든 요청에 대한 권한 해제
            .authorizeRequests()
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
                .oauth2Login()
                .loginPage("http://localhost:3000/")
                .defaultSuccessUrl("http://localhost:3000/")
                .failureUrl("http://localhost:3000/")
                .userInfoEndpoint() // 로그인 성공 후  사용자 정보를 가져옵니다.
                .userService(principalOauth2UserService);            
            // .and()
            // // // 권한외의 접근을 시도할 때 출력할 페이지
            // // .exceptionHandling()
            // //     .accessDeniedPage("/admin/");
            // .exceptionHandling()
            //     .accessDeniedHandler(customAccessDeniedHandler);        
    }

}
