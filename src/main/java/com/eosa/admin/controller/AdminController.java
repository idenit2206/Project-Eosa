package com.eosa.admin.controller;

import com.eosa.admin.dto.UsersDTO;
import com.eosa.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 관리자 로그인 폼 컨트롤러
     *
     * @param model
     * @return String
     */
    @GetMapping({"", "/"})
    public String adminMain(Model model) {

        return adminService.adminMain(model);
    }

    /**
     * 관리자 정보 수정 폼 컨트롤러
     *
     * @return String
     */
    @GetMapping("/manage/info")
    public String adminInfo() {

        return adminService.adminInfo();
    }

    /**
     * 관리자 관리 폼 컨트롤러
     *
     * @param model
     * @param page
     * @return String
     */
    @GetMapping("/manage/list")
    public String adminList(Model model, @RequestParam(defaultValue = "1") int page) {

        return adminService.adminList(model, page);
    }

    /**
     * 관리자 등록 폼 컨트롤러
     *
     * @return String
     */
    @GetMapping("/manage/register")
    public String adminRegister() {

        return adminService.adminRegister();
    }

    /**
     * 관리자 등록 컨트롤러
     *
     * @param usersDTO
     * @return int
     */
    @ResponseBody
    @PostMapping("/manage/insert")
    public int insertAdmin(UsersDTO usersDTO) {

        return adminService.insertAdmin(usersDTO);
    }

    /**
     * 관리자 상세 조회 컨트롤러
     *
     * @param model
     * @param usersIdx
     * @return String
     */
    @GetMapping("/manage/details")
    public String adminDetails(Model model, @RequestParam long usersIdx) {

        return adminService.adminDetails(model, usersIdx);
    }

    /**
     * 관리자 비밀번호 초기화 컨트롤러
     *
     * @param usersDTO
     * @return int
     */
    @ResponseBody
    @PostMapping("/manage/reset/password")
    public int resetPwd(UsersDTO usersDTO) {

        return adminService.resetPwd(usersDTO);
    }

    /**
     * 관리자 수정 컨트롤러
     *
     * @param usersDTO
     * @return int
     */
    @ResponseBody
    @PostMapping("/manage/update")
    public int updateAdmin(UsersDTO usersDTO) {

        return adminService.updateAdmin(usersDTO);
    }

    /**
     * 아이디 중복검사 컨트롤러
     *
     * @param usersAccount
     * @return int
     */
    @ResponseBody
    @PostMapping("/manage/account/check")
    public int accountCheck(@RequestParam String usersAccount) {

        return adminService.accountCheck(usersAccount);
    }

    /**
     * 연락처 중복검사 컨트롤러
     *
     * @param usersPhone
     * @return int
     */
    @ResponseBody
    @PostMapping("/manage/phone/check")
    public int phoneCheck(@RequestParam String usersPhone) {

        return adminService.phoneCheck(usersPhone);
    }

}
