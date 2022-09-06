package com.eosa.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eosa.web.users.entity.Users;
import com.eosa.web.users.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class IndexController {
    
    @Autowired UsersService usersService;

    @GetMapping({"", "/"})
    public String showIndexPage(
        HttpServletRequest request, HttpServletResponse response
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null) {
            log.info("{} Request /admin", auth.getName());
            Users user = usersService.findByUsersAccount(auth.getName());
            request.setAttribute("user", user);
            return "admin/index";
        } else {
            log.info("someone Request /admin/");
            return "admin/index";
        }
    }

}
