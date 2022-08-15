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
        log.info("someone Request /admin/");
        ModelAndView mv = new ModelAndView();
        Users user = usersService.findByUsersAccount("superadmin88");

        if(user == null) {
            user = new Users();
            user.setUsersAccount("");
            user.setUsersName("");
            user.setUsersPhone("");
            user.setUsersEmail("");
        }

        mv.setViewName("admin/index");
        mv.addObject("user", user);
        return mv;
    }

}
