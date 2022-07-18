package com.eosa.web.security;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import com.eosa.web.security.jwt.JwtAccessDeniedHandler;
import com.eosa.web.security.jwt.JwtAuthenticationEntryPoint;
import com.eosa.web.security.jwt.JwtSecurityConfig;
import com.eosa.web.security.jwt.TokenProvider;
import com.eosa.web.users.UsersRepository;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class CustomSecurityConfig {

    @Autowired private UsersRepository usersRepository;

    @Autowired private TokenProvider tokenProvider;
    @Autowired private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired private JwtAccessDeniedHandler jwtAccessDeniedHandler;    
    @Autowired private CorsFilter corsFilter;
    // private final CorsFilter corsFilter;

    // OAuth2를 통한 SNS로그인에서 활용
    // @Autowired private CustomOAuth2UserService customOAuth2UserService;
    // @Autowired private OAuth2SuccessHandler oAuth2SuccessHandler;
    // @Autowired private TokenService tokenService;
 
    // Spring Security를 이용한 로그인에서 활용
    // @Bean
    // public UserDetailsService userDetailsService() {
    //     return new CustomUserDetailsService();
    // }

    // @Bean
    // public DaoAuthenticationProvider authenticationProvider() {
    //     DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    //     authProvider.setUserDetailsService(userDetailsService());
    //     authProvider.setPasswordEncoder(passwordEncoder());
    //     return authProvider;
    // }

    // public CustomSecurityConfig(       
    //     TokenProvider tokenProvider,
    //     JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
    //     JwtAccessDeniedHandler jwtAccessDeniedHandler,
    //     CorsFilter corsFilter
    // ) {       
    //     this.tokenProvider = tokenProvider;
    //     this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    //     this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    //     this.corsFilter = corsFilter;
    // }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }   

    private final String[] PERMIT_ADMIN_URL = {
        "/swagger-ui/index.html"
    };

    private final String[] NO_AUTHENTICATION_URL = {
        "/", "/api/user/signIn.do", "/login/oauth2/code/kakao"
    };
   
    public void configure(WebSecurity web) {
        web.ignoring()
            .antMatchers(
                "/error"
            );
    }

    // After WebSecurityConfigureAdapter Depreacted
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http            
            .csrf().disable()
            .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
        .and()
             // JWT Token인증을 활용하기 때문에 세션을 stateless 하도록 설정
             .sessionManagement()
             .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .authorizeRequests()
                .antMatchers(NO_AUTHENTICATION_URL).permitAll()
                .anyRequest().authenticated()
            //     .antMatchers("/swagger-ui/**")
            //     .permitAll()                             
            // .authorizeRequests()
            //     .antMatchers("/api/**")
            //     // .access("hasRole('CLIENT') or hasRole('DETECTIVE')")
            //     .hasAnyAuthority("CLIENT", "DETECTIVE", "ADMIN", "SUPER_ADMIN")
            // .antMatchers(PERMIT_ADMIN_URL)
            //     .hasAnyAuthority("ADMIN", "SUPER_ADMIN")            
            // .and()
            //     .formLogin()
            //         .loginPage("http://localhost:3000/user/signin")
            //             .usernameParameter("usersAccount").passwordParameter("usersPass")
            //             .loginProcessingUrl("/api/user/signIn.do")
            //                 .successForwardUrl("/api/user/signIn.success")
            //                 .failureForwardUrl("/api/user/signIn.failure")
            //                 .permitAll()
            // .and()
            //     .logout()
            //         .logoutUrl("/api/user/signOut.do")
            //         .permitAll();
        .and()
            .apply(new JwtSecurityConfig(tokenProvider));
        return http.build();
    }
    // extends WebSecurityConfigurerAdapter 활용시
    // Spring Security & OAuth2를 활용 (JWT 미반영)
    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.authenticationProvider(authenticationProvider());
    // }
    
//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http
//             .csrf().disable()
//             .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//         .and()           
//             // .authorizeRequests().antMatchers("/token/**").permitAll()           
//             // .addFilter(new CustomJwtAuthenticationFilter(authenticationManager()))
//             // .addFilter(new CustomJwtAuthorizationFilter(authenticationManager(), usersRepository))
//             // .addFilterBefore(new JwtAuthFilter(tokenService), UsernamePasswordAuthenticationFilter.class)
//             .authorizeRequests()
//             .antMatchers("/api/**")
//             .hasAnyAuthority("CLIENT", "DETECTIVE")
//             .anyRequest().permitAll()
//         .and()
//             .formLogin()
//                 .loginPage("http://localhost:3000/user/signin")
//                 .usernameParameter("usersAccount")
//                 .passwordParameter("usersPass")
//                 .loginProcessingUrl("/api/user/signIn.do")
//                 .successForwardUrl("/api/user/signIn.success") // login에 성공했을 때 연결할 URL을 매개변수로 사용
//                 .failureForwardUrl("/api/user/signIn.failure")
//                 .permitAll()            
//             .and()
//             .logout()
//                 .logoutUrl("/api/user/signOut.do")
//                 .logoutSuccessUrl("http://localhost:3000") // 로그아웃에 성공한 사용자에게 보여줄 페이지의 URL을 매개변수로 사용
//                 .permitAll();           
//     }
}