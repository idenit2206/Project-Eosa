package com.eosa.admin.adminuser.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.eosa.web.users.entity.Users;
import com.eosa.web.users.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/admin")
public class AdminUserManageController {

    @Autowired private UsersService usersService;
    
    @GetMapping("/adminAdd")
    public String viewAdminAdd() {
        return "admin/adminmanage/adminAddForm";
    }

    @PostMapping("/adminAddProcess")
    public ModelAndView adminAddProcess(
        Users user
    ) throws IOException {
        ModelAndView mv = new ModelAndView();
        // log.debug("user: {}", user.toString());
        user.setUsersNick(user.getUsersAccount());
        int addProcessResult = usersService.userSave(user);
        if(addProcessResult != 0) {
            mv.addObject("alert", "새로운 운영자를 추가했습니다.");
            mv.setViewName("redirect:/admin/adminList");
            return mv;
            // return "redirect:/admin/adminList";
        }
        else {
            mv.setViewName("redirect:/admin/adminAdd");
            return mv;
        }

        
    }

}
