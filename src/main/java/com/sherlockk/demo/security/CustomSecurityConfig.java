package com.sherlockk.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {
 
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
            // 모든 요청에 대한 권한 해제
            .authorizeRequests()
                .anyRequest().permitAll();
            // .authorizeRequests()
            // .antMatchers(PERMIT_URL_ARRAY)
            //     .hasAnyAuthority("ADMIN")        
            // .and()
            // .formLogin()
            //     .loginPage("/admin")
            //     .usernameParameter("id")
            //     .passwordParameter("pwd")
            //     .loginProcessingUrl("/admin/login.do")
            //     // .defaultSuccessUrl("/admin/project/list") // login에 성공했을 때 이동할 페이지
            //     .successForwardUrl("/admin/login_success_handler") // login에 성공했을 때 처리할 핸들러 메서드URL을 매개변수로 사용
            //     .failureHandler(new CustomLoginFailHandler())
            //     // .failureUrl("/admin/login_failure") // login에 실패했을때 전달 URL                
            //     .permitAll()
            // .and()
            // .logout()
            //     .logoutUrl("/admin/logout.do")
            //     // .logoutSuccessUrl("/") // 로그아웃에 성공한 사용자에게 보여줄 페이지의 URL을 매개변수로 사용
            //     .permitAll()
            // .and()
            // // // 권한외의 접근을 시도할 때 출력할 페이지
            // // .exceptionHandling()
            // //     .accessDeniedPage("/admin/");
            // .exceptionHandling()
            //     .accessDeniedHandler(customAccessDeniedHandler);        
    }

}
