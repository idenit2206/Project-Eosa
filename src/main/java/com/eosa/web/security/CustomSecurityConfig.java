package com.eosa.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.eosa.web.users.UsersRepository;

@Configuration
@EnableWebSecurity
public class CustomSecurityConfig {

    @Autowired private UsersRepository usersRepository;
    @Autowired private CorsConfig corsConfig;

    // @Autowired private CustomOAuth2UserService customOAuth2UserService;
    // @Autowired private OAuth2SuccessHandler oAuth2SuccessHandler;
    // @Autowired private TokenService tokenService;
 
    // @Bean
    // public UserDetailsService userDetailsService() {
    //     return new CustomUserDetailsService();
    // }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean
    // public DaoAuthenticationProvider authenticationProvider() {
    //     DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    //     authProvider.setUserDetailsService(userDetailsService());
    //     authProvider.setPasswordEncoder(passwordEncoder());
    //     return authProvider;
    // }

    private static final String[] PERMIT_ADMIN_URL = {
        "/swagger-ui/index.html"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {       

        http
            // Http Basic Auth 기반의 로그인 화면을 출력하지 않는다.
            // .httpBasic().disable()
            .csrf().disable()
            // JWT Token인증을 활용하기 때문에 세션을 stateless 하도록 설정
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            // 모든 접속을 아무나 허용
            // .authorizeRequests().anyRequest().permitAll();
            // .authorizeRequests().antMatchers("/token/**").permitAll()
            // .formLogin().disable()
            // .httpBasic().disable()
            // .addFilter(new CustomJwtAuthenticationFilter(authenticationManager()))
            // .addFilter(new CustomJwtAuthorizationFilter(authenticationManager(), usersRepository))
            .authorizeRequests()
                .antMatchers("/api/**")
                // .access("hasRole('CLIENT') or hasRole('DETECTIVE')")
                .hasAnyAuthority("CLIENT", "DETECTIVE")
            // .antMatchers(PERMIT_ADMIN_URL)
            //     .hasAnyAuthority("ADMIN", "SUPER_ADMIN")
        .and()
            .formLogin()
                .loginPage("http://localhost:3000/user/signin")
                    .usernameParameter("usersAccount").passwordParameter("usersPass")
                    .loginProcessingUrl("/api/user/signIn.do")
                        .successForwardUrl("/api/user/signIn.success")
                        .failureForwardUrl("/api/user/signIn.failure")
                        .permitAll()
                    .and()
                    .logout()
                        .logoutUrl("/api/user/signOut.do")
                        .permitAll();
                    
        return http.build();
    }    

}

    // extends WebSecurityConfigurerAdapter 활용시
    // Spring Security & OAuth2를 활용 (JWT 미반영)
    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.authenticationProvider(authenticationProvider());
    // }
    //
    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    //     http.httpBasic().disable()
    //         .csrf().disable()
    //         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    //         .and()           
    //         .authorizeRequests().antMatchers("/token/**").permitAll()
    //          // 모든 요청에 대한 권한 해제
    //         .anyRequest().permitAll()
    //         // .authorizeRequests()
    //             // .anyRequest()
    //         // .antMatchers(PERMIT_ADMIN_URL)
    //             // .hasAnyAuthority("ADMIN")        
    //         .and()
    //         .formLogin()
    //             .loginPage("http://localhost:3000/")
    //             .usernameParameter("usersAccount")
    //             .passwordParameter("usersPass")
    //             .loginProcessingUrl("/api/user/signIn.do")
    //             // .defaultSuccessUrl("http://localhost:3000") // login에 성공했을 때 이동할 페이지
    //             .successForwardUrl("/api/user/signIn.success") // login에 성공했을 때 연결할 URL을 매개변수로 사용
    //             .failureForwardUrl("/api/user/signIn.failure")
    //             .permitAll()            
    //         .and()
    //         .logout()
    //             .logoutUrl("/admin/logout.do")
    //             .logoutSuccessUrl("/") // 로그아웃에 성공한 사용자에게 보여줄 페이지의 URL을 매개변수로 사용
    //             .permitAll()
    //         .and()          
    //         .addFilterBefore(new JwtAuthFilter(tokenService), UsernamePasswordAuthenticationFilter.class)
    //         .oauth2Login().loginPage("/token/expired")
    //         .successHandler(oAuth2SuccessHandler)
    //         .userInfoEndpoint().userService(customOAuth2UserService);

    //     http.addFilterBefore(new JwtAuthFilter(tokenService), UsernamePasswordAuthenticationFilter.class);
    //         // .oauth2Login()
    //         //     .loginPage("/http://localhost:3000/")
    //         //     .userInfoEndpoint()
    //         //         // parameter로 CustomOAuth2UserService
    //         //         .userService(customOAuth2UserService);         
    //         // .and()
    //         // // // 권한외의 접근을 시도할 때 출력할 페이지
    //         // // .exceptionHandling()
    //         // //     .accessDeniedPage("/admin/");
    //         // .exceptionHandling()
    //         //     .accessDeniedHandler(customAccessDeniedHandler);
    // }