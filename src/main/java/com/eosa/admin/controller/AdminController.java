package com.eosa.admin.controller;

import com.eosa.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * packageName    : com.eosa.admin.controller
 * fileName       : AdminController
 * author         : Jihun Kim
 * date           : 2022-09-08
 * description    : 관리자 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-08        Jihun Kim       최초 생성
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping({"", "/"})
    public String adminMain(Model model) {

        return adminService.adminMain(model);
    }

}
