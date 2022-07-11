package com.sherlockk.demo.security.oauth2.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sherlockk.demo.security.jwt.Token;
import com.sherlockk.demo.security.jwt.TokenService;
import com.sherlockk.demo.users.Users;
import com.sherlockk.demo.users.UsersRepository;
import com.sherlockk.demo.users.UsersService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    
    @Autowired
    private TokenService tokenSerivce;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication authentication) throws IOException, ServletException {
        /**
         * Name: [email],
         * Granted Authorities: [[USER]]
         */
        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
        Users users = usersRepository.findByUsersEmail(oAuth2User.getName());
        log.info("## users: {}", users.getProvider());

        String targetUrl;
        
        if(users != null) {
            final String usersRole ="CLIENT";
            final String usersEmail = users.getUsersEmail();
            
            log.info("## Token issue {}, {}", usersEmail, usersRole);        

            Token token = tokenSerivce.generateToken(usersEmail, usersRole);
            log.info("## Token: {}", token);
            targetUrl = UriComponentsBuilder.fromUriString("/")
                .queryParam("token", token)
                .build().toString();
            getRedirectStrategy().sendRedirect(req, res, targetUrl);
        }
        else {
            /**
             * SNS로그인을 시도했지만 회원정보가 존재하지 않는경우(신규 회원)
             * sendRedirect(요청, 응답, 리다이렉트할 페이지 경로)
             */
            getRedirectStrategy().sendRedirect(req, res, "http://localhost:3000/");
        }
    }
}
