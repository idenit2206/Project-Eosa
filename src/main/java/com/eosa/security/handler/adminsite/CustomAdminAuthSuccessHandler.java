package com.eosa.security.handler.adminsite;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import com.eosa.web.users.entity.GetUsersInfoByUsersAccountEntity;
import com.eosa.web.users.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomAdminAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired private UsersService usersService;

    
    /** 
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request, HttpServletResponse response,
        Authentication authentication)
    throws IOException, ServletException {
        log.info("Welcom Admin: {}", authentication.getName());
        GetUsersInfoByUsersAccountEntity user = usersService.getUsersInfoByUsersAccount(authentication.getName());

        if(user.getUsersRole().equals("ADMIN") || user.getUsersRole().equals("SUPER_ADMIN")) {
            response.sendRedirect("/admin/manage/user/list");
        }
        else {
            response.sendRedirect("/admin");
        }
        
       
    }
}
