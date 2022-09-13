package com.eosa.admin.controller;

import com.eosa.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * packageName    : com.eosa.admin.controller
 * fileName       : UserController
 * author         : Jihun Kim
 * date           : 2022-09-13
 * description    : 회원 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-13        Jihun Kim       최초 생성
 */
@Controller
@RequestMapping("/admin/manage/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 회원 목록 조회 컨트롤러
     *
     * @param model
     * @param role
     * @param sort
     * @param search
     * @param page
     * @return String
     */
    @GetMapping("/list")
    public String userList(Model model,
                           @RequestParam(defaultValue = "DETECTIVE") String role,
                           @RequestParam(defaultValue = "id") String sort,
                           @RequestParam(defaultValue = "") String search,
                           @RequestParam(defaultValue = "1") int page) {

        return userService.userList(model, role, sort, search, page);
    }

    /**
     * 회원 상세 조회 컨트롤러
     *
     * @param model
     * @param usersIdx
     * @return String
     */
    @GetMapping("/details")
    public String userDetails(Model model, @RequestParam long usersIdx) {

        return userService.userDetails(model, usersIdx);
    }

}
