package com.eosa.demo.security.jwt;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.eosa.demo.users.Users;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthFilter extends GenericFilterBean{
    @Autowired private TokenService tokenService;
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException {
        String token = ((HttpServletRequest) request).getHeader("Auth");

        if(token != null && tokenService.verifyToken(token)) {
            String email = tokenService.getUid(token);

            Users user = Users.builder()
                .usersEmail(email)
                .usersName("usersName")
                .picture("picture")
                .build();
            
            Authentication auth = getAuthentication(user);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        chain.doFilter(request, response);
    }

    public Authentication getAuthentication(Users user) {
        return new UsernamePasswordAuthenticationToken(
            user, "", Arrays.asList(new SimpleGrantedAuthority("CLIENT"))
        );
    }

    public JwtAuthFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }
    
}
