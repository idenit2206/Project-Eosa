package com.eosa.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.eosa.web.users.Users;
import com.eosa.web.users.UsersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class IndexController {
    
    @Autowired UsersService usersService;

    @GetMapping("/")
    public ModelAndView showIndexPage(
    ) {
        ModelAndView mv = new ModelAndView();
        Users user = usersService.findByUsersAccount("superadmin88");

        mv.setViewName("/adminIndex");
        mv.addObject("user", user);
        return mv;
    }

}
