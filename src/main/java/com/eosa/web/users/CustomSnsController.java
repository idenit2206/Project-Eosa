package com.eosa.web.users;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eosa.security.CustomPrincipalDetails;
import com.eosa.web.util.CustomResponseData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/api/user2/")
public class CustomSnsController {
    
    /**
     * OAuth2를 활용한 SNS로그인 성공시 메서드 
     * @param principalUserDetails
     * @return
     * @throws IOException
     * @throws ServletException
     */
    @GetMapping("/oauth2SignIn.success")
    public String oauth2SignInSuccess(
        HttpServletRequest request, HttpServletResponse response, HttpSession session,
        @AuthenticationPrincipal CustomPrincipalDetails principalUserDetails
    ) throws IOException, ServletException {
        CustomResponseData result = new CustomResponseData();
        Map<String, Object> items = new HashMap<>();
            
        String sns = principalUserDetails.getProvider();
        String usersEmail = principalUserDetails.getUsers().getUsersEmail();
        String usersRole = principalUserDetails.getUsers().getUsersRole();
        // log.debug("# sns:{}, usersEmail: {}, usersRole: {}",sns, usersEmail, usersRole);
        Users user = new UsersService().selectByUsersEmail(usersEmail);
        String existUsersEmail = "";
        if(user != null) {
            existUsersEmail = user.getUsersEmail();            
            items.put("sns", sns);
            items.put("usersEmail", existUsersEmail);
            items.put("usersRole", usersRole);   
        }
        else {
            items.put("sns", sns);
            items.put("usersEmail", usersEmail);
            items.put("usersRole", null);
            items.put("message", "해당 회원은 신규회원입니다.");
        }

        result.setStatusCode(HttpStatus.OK.value());
        result.setResultItem(items);
        result.setResponseDateTime(LocalDateTime.now());

        session.setAttribute("items", items);
        log.info("session {}", session.getId());

        return "http://localhost:3000";
    }

}