package com.eosa.security.handler.adminsite;

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
public class CustomAdminLogoutSuccessHandler implements LogoutSuccessHandler {

    
    /** 
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onLogoutSuccess(
        HttpServletRequest request, HttpServletResponse response, Authentication authentication
    ) throws IOException, ServletException {
        if(authentication != null) { 
            log.info("{} signOut Success", authentication.getName());
            response.sendRedirect("/admin");
        }
        else {
            log.info("[관리자페이지] 이미 로그아웃 되어있습니다.");
            response.sendRedirect("/admin");
        }
    }
    
}
