package com.eosa.admin.adminuser;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.buf.StringCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eosa.web.security.CustomPrincipalDetails;
import com.eosa.web.users.Users;
import com.eosa.web.users.UsersService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired private AdminUserService adminUserService;
    @Autowired UsersService usersService;

    @GetMapping("/test01")
    @ResponseBody
    public String test01() {
        return "/admin/test01";
    }

    @GetMapping("/signUp")
    public String adminSignUpForm() {
        return "/signin/SignUp";
    }

    @PostMapping("/signUp.do")
    public String adminSignUpDo(Users adminUser) {
        adminUserService.adminRegist(adminUser);
        return "/signin/SignIn";
    }

    @Operation(summary="관리자페이지 로그인 view", description="관리자 페이지의 로그인을 위한 Form 페이지")
    @GetMapping("/signIn")
    public String adminSignInForm() {
        // log.info("## Someone Request /signInForm");
        return "/admin/signin/SignIn";
    }

    @PostMapping(value="/signIn.success")
    public ModelAndView adminSignInSuceess(
        @AuthenticationPrincipal CustomPrincipalDetails users, 
        @RequestParam("usersAccount") String usersAccount
    ) {
        ModelAndView mv = new ModelAndView();
        String author = users.customGetAuthorities();
        if(author.matches("ADMIN") || author.matches("SUPER_ADMIN")) {
            log.info("## 환영합니다 {} 님.", usersAccount);
            Users user = usersService.findByUsersAccount(usersAccount);

            mv.setViewName("/admin/index");
            mv.addObject("user", user);
            
            return mv;
        }
        else {
            log.info("## 권한이 없는 사용자 입니다. 사용자명: {}.", usersAccount);
            mv.setViewName("/admin/signin/SignIn");
            return mv;
        }        
    }

    @PostMapping(value="/signIn.failure")
    public void adminSignInFailure(
        HttpServletRequest request, HttpServletResponse response,
        @RequestParam("usersAccount") String usersAccount,
        Model model
    ) throws IOException {
        log.error("## {} 님이 관리자 페이지 로그인에 실패했습니다.", usersAccount);
        model.addAttribute("msg", "Failed to SignIn");
        response.sendRedirect("/admin/signInForm");
    }
    
    @PostMapping(value="/adminUpdate")
    @ResponseBody
    public String adminUpdate(
        Users param
    ) {
        // ModelAndView mv = new ModelAndView();
        int transaction = usersService.updateAdminUserInfo(param);
        log.info("# transaction {}", transaction);
        Users user = usersService.findByUsersAccount(param.getUsersAccount());

        // mv.setViewName("/adminIndex");
        // mv.addObject("user", user);

        return user.toString();
    }
}
