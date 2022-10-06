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
        String usersIp = request.getLocalAddr();        
        log.info("[OK] {} signOut Success FROM {}",authentication.getName(),  usersIp);
        response.sendRedirect("/admin");
    }
    
}
