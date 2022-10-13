package com.eosa.admin.controller;

import com.eosa.admin.dto.UsersDTO;
import com.eosa.admin.dto.UsersTerminateDTO;
import com.eosa.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 회원 수정 컨트롤러
     *
     * @param usersDTO
     * @return int
     */
    @ResponseBody
    @PostMapping("/update")
    public int updateUser(UsersDTO usersDTO) {

        return userService.updateUser(usersDTO);
    }    

    /**
     * 탈퇴 회원 목록 조회 컨트롤러
     *
     * @param model
     * @param sort
     * @param search
     * @param page
     * @return String
     */
    @GetMapping("/terminate/list")
    public String terminateList(Model model,
                                @RequestParam(defaultValue = "id") String sort,
                                @RequestParam(defaultValue = "") String search,
                                @RequestParam(defaultValue = "1") int page) {

        return userService.terminateList(model, sort, search, page);
    }

    /**
     * 회원 탈퇴 컨트롤러
     *
     * @param usersTerminateDTO
     * @return int
     */
    @ResponseBody
    @PostMapping("/terminate/insert")
    public int insertTerminate(UsersTerminateDTO usersTerminateDTO) {

        return userService.insertTerminate(usersTerminateDTO);
    }

    /**
     * 회원 탈퇴 취소 컨트롤러
     *
     * @param usersIdx
     * @return int
     */
    @ResponseBody
    @PostMapping("/terminate/delete")
    public int deleteTerminate(@RequestParam long usersIdx) {

        return userService.deleteTerminate(usersIdx);
    }

    /**
     * 회원 삭제 컨트롤러
     *
     * @param usersIdx
     * @return int
     */
    @ResponseBody
    @PostMapping("/delete")
    public int deleteUser(@RequestParam long usersIdx) {

        return userService.deleteUser(usersIdx);
    }

    /**
     * 비회원 목록 조회 컨트롤러
     *
     * @param model
     * @param page
     * @return String
     */
    @GetMapping("/temp/list")
    public String tempList(Model model, @RequestParam(defaultValue = "1") int page) {

        return userService.tempList(model, page);
    }

    /**
     * 비회원 삭제 컨트롤러
     *
     * @param tempUserIdx
     * @return int
     */
    @ResponseBody
    @PostMapping("/temp/delete")
    public int deleteTemp(@RequestParam String tempUserIdx) {

        return userService.deleteTemp(tempUserIdx);
    }

    /**
     * 회원 등록 폼 컨트롤러
     *
     * @return String
     */
    @GetMapping("/register")
    public String registerUser() {

        return userService.registerUser();
    }

    /**
     * 회원 등록 컨트롤러
     *
     * @param usersDTO
     * @return int
     */
    @ResponseBody
    @PostMapping("/insert")
    public int insertUsers(UsersDTO usersDTO) {

        return userService.insertUsers(usersDTO);
    }

}
