package com.eosa.web.security.jwt.javajwt;

import java.io.IOException;
import java.util.Date;

import javax.naming.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.eosa.web.security.oauth2.entity.JwtPrincipalDetails;
import com.eosa.web.users.Users;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CustomJwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    
    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        // request의 username과 password를 갖고와 java 객체로 받는다.
        ObjectMapper om = new ObjectMapper();
        Users users = null;

        try {
            users = om.readValue(request.getInputStream(), Users.class);
        }
        catch(Exception e) {
            log.error("## [ERROR] {}", e);
        }

        log.info("## [INFO] JwtAuthenticationFilter: {}", users);

        // 사용자네임패스워드 토큰 생성
        UsernamePasswordAuthenticationToken authenticationToken = 
        new UsernamePasswordAuthenticationToken(
            users.getUsersAccount(),
            users.getUsersPass()
        );

        log.info("## [INFO] Token Generated!!");

        // authenticate() 함수가 호출 되면 인증 프로바이더가 유저 디테일 서비스의
		// loadUserByUsername(토큰의 첫번째 파라메터) 를 호출하고
		// UserDetails를 리턴받아서 토큰의 두번째 파라메터(credential)과
		// UserDetails(DB값)의 getPassword()함수로 비교해서 동일하면
		// Authentication 객체를 만들어서 필터체인으로 리턴해준다.
		
		// Tip: 인증 프로바이더의 디폴트 서비스는 UserDetailsService 타입
		// Tip: 인증 프로바이더의 디폴트 암호화 방식은 BCryptPasswordEncoder
		// 결론은 인증 프로바이더에게 알려줄 필요가 없음.
		Authentication authentication = 
				authenticationManager.authenticate(authenticationToken);
		
		JwtPrincipalDetails principalDetailis = (JwtPrincipalDetails) authentication.getPrincipal();
		System.out.println("Authentication : "+principalDetailis.getUsers().getUsersAccount());
		return authentication;
    }

    // JWT Token 생성해서 response에 담아주기
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		JwtPrincipalDetails principalDetailis = (JwtPrincipalDetails) authResult.getPrincipal();
		
		String jwtToken = JWT.create()
				.withSubject(principalDetailis.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+CustomJwtProperties.EXPIRATION_TIME))
				.withClaim("usersAccount", principalDetailis.getUsers().getUsersAccount())
				.withClaim("usersNick", principalDetailis.getUsers().getUsersNick())
				.sign(Algorithm.HMAC512(CustomJwtProperties.SECRET_KEY));
		
		response.addHeader(CustomJwtProperties.HEADER_STRING, CustomJwtProperties.TOKEN_PREFIX+jwtToken);
	}

}
