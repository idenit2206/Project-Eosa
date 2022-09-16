package com.eosa.security.handler.servicesite;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(
        HttpServletRequest request, HttpServletResponse response, Authentication authentication
    ) throws IOException, ServletException {
        String usersIp = request.getLocalAddr();
        if(authentication != null) {
            log.info("[onLogoutSuccess] 사용자: {} 로그아웃 성공 ",authentication.getName());
        }
        else {
            log.info("[onLogoutSuccess] 이미 로그아웃이 되어있는 사용자 입니다.");
        }

    }
    
}
