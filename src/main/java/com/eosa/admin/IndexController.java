package com.eosa.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eosa.web.users.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class IndexController {
    
    @Autowired UsersService usersService;

    @GetMapping({"", "/"})
    public String showIndexPage(
    ) {
        log.info("someone Request /admin/");
        return "admin/index";
    }

}
