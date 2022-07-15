package com.eosa.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.eosa.web.security.jwt.javajwt.CustomJwtAuthenticationFilter;
import com.eosa.web.security.jwt.javajwt.CustomJwtAuthorizationFilter;
import com.eosa.web.security.jwt.nimbus.JwtAuthFilter;
import com.eosa.web.security.jwt.nimbus.TokenService;
import com.eosa.web.users.UsersRepository;

@Configuration
@EnableWebSecurity
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired private UsersRepository usersRepository;
    @Autowired private CorsConfig corsConfig;

    // @Autowired private CustomOAuth2UserService customOAuth2UserService;
    // @Autowired private OAuth2SuccessHandler oAuth2SuccessHandler;
    @Autowired private TokenService tokenService;
 
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
    // extends WebSecurityConfigurerAdapter 활용시
    // Spring Security & OAuth2를 활용 (JWT 미반영)
    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.authenticationProvider(authenticationProvider());
    // }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()           
            // .authorizeRequests().antMatchers("/token/**").permitAll()           
            // .addFilter(new CustomJwtAuthenticationFilter(authenticationManager()))
            // .addFilter(new CustomJwtAuthorizationFilter(authenticationManager(), usersRepository))
            .addFilterBefore(new JwtAuthFilter(tokenService), UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests()
            .antMatchers("/api/**")
            .hasAnyAuthority("CLIENT", "DETECTIVE")
            .anyRequest().permitAll()
        .and()
            .formLogin()
                .loginPage("http://localhost:3000/user/signin")
                .usernameParameter("usersAccount")
                .passwordParameter("usersPass")
                .loginProcessingUrl("/api/user/signIn.do")
                .successForwardUrl("/api/user/signIn.success") // login에 성공했을 때 연결할 URL을 매개변수로 사용
                .failureForwardUrl("/api/user/signIn.failure")
                .permitAll()            
            .and()
            .logout()
                .logoutUrl("/api/user/signOut.do")
                .logoutSuccessUrl("http://localhost:3000") // 로그아웃에 성공한 사용자에게 보여줄 페이지의 URL을 매개변수로 사용
                .permitAll();           
    }
}

    
    // After WebSecurityConfigureAdapter Depreacted
    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //     http
    //         // Http Basic Auth 기반의 로그인 화면을 출력하지 않는다.
    //         // .httpBasic().disable()
    //         .csrf().disable()
    //         // JWT Token인증을 활용하기 때문에 세션을 stateless 하도록 설정
    //         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    //     .and()
    //         // 모든 접속을 아무나 허용
    //         // .authorizeRequests().anyRequest().permitAll();
    //         // .authorizeRequests().antMatchers("/token/**").permitAll()
    //         // .formLogin().disable()
    //         // .httpBasic().disable()
    //         // .addFilter(new CustomJwtAuthenticationFilter(authenticationManager()))
    //         // .addFilter(new CustomJwtAuthorizationFilter(authenticationManager(), usersRepository))            
    //         .authorizeRequests()
    //             .antMatchers("/api/**")
    //             // .access("hasRole('CLIENT') or hasRole('DETECTIVE')")
    //             .hasAnyAuthority("CLIENT", "DETECTIVE", "ADMIN", "SUPER_ADMIN")
    //         .antMatchers(PERMIT_ADMIN_URL)
    //             .hasAnyAuthority("ADMIN", "SUPER_ADMIN")
    //     .and()
    //         .formLogin()
    //             .loginPage("http://localhost:3000/user/signin")
    //                 .usernameParameter("usersAccount").passwordParameter("usersPass")
    //                 .loginProcessingUrl("/api/user/signIn.do")
    //                     .successForwardUrl("/api/user/signIn.success")
    //                     .failureForwardUrl("/api/user/signIn.failure")
    //                     .permitAll()
    //                 .and()
    //                 .logout()
    //                     .logoutUrl("/api/user/signOut.do")
    //                     .permitAll()
    //     .and()
    //         .apply(MyCustomDsl.customDsl());             
    //     return http.build();
    // }