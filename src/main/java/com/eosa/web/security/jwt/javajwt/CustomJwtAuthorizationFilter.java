package com.eosa.web.security.jwt.javajwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.eosa.web.security.oauth2.entity.JwtPrincipalDetails;
import com.eosa.web.users.Users;
import com.eosa.web.users.UsersRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomJwtAuthorizationFilter extends BasicAuthenticationFilter {
    
    private UsersRepository usersRepository;

    public CustomJwtAuthorizationFilter(AuthenticationManager authenticationManager, UsersRepository usersRepository) {
        super(authenticationManager);
        this.usersRepository = usersRepository;
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request, HttpServletResponse response, FilterChain chain)
    throws IOException, ServletException {
        String header = request.getHeader(CustomJwtProperties.HEADER_STRING);
        if(header == null || !header.startsWith(CustomJwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        log.debug("## [INFO] header: {}", header);
        String token = request.getHeader(CustomJwtProperties.HEADER_STRING)
            .replace(CustomJwtProperties.TOKEN_PREFIX, "");

        String usersAccount = JWT.require(Algorithm.HMAC512(CustomJwtProperties.HEADER_STRING)).build().verify(token)
            .getClaim("usersAccount").asString();
        
        if(usersAccount != null) {	
            Users user = usersRepository.findByUsersAccount(usersAccount);
            
            // 인증은 토큰 검증시 끝. 인증을 하기 위해서가 아닌 스프링 시큐리티가 수행해주는 권한 처리를 위해 
            // 아래와 같이 토큰을 만들어서 Authentication 객체를 강제로 만들고 그걸 세션에 저장!
            JwtPrincipalDetails principalDetails = new JwtPrincipalDetails(user);
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(
                            principalDetails, //나중에 컨트롤러에서 DI해서 쓸 때 사용하기 편함.
                            null, // 패스워드는 모르니까 null 처리, 어차피 지금 인증하는게 아니니까!!
                            principalDetails.getAuthorities());
            
            // 강제로 시큐리티의 세션에 접근하여 값 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }    
        chain.doFilter(request, response);
    }
}