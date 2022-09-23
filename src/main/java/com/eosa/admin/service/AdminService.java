package com.eosa.admin.service;

import com.eosa.admin.dto.UsersDTO;
import com.eosa.admin.mapper.AdminMapper;
import com.eosa.admin.pagination.Pagination;
import com.eosa.security.CustomPrincipalDetails;
import com.eosa.web.users.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName    : com.eosa.admin.service
 * fileName       : AdminService
 * author         : Jihun Kim
 * date           : 2022-09-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-08        Jihun Kim       최초 생성
 */
@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 관리자 로그인 폼 서비스
     *
     * @param model
     * @return String
     */
    public String adminMain(Model model) {

        model.addAttribute("error", false);

        return "admin/index";
    }

    /**
     * 관리자 정보 수정 폼 서비스
     *
     * @param model
     * @param principalDetails
     * @return String
     */
    public String adminInfo(Model model, CustomPrincipalDetails principalDetails) {


        UsersDTO details = adminMapper.selectAdminDetails(principalDetails.getUsers().getUsersIdx());

        model.addAttribute("details", details);

        return "admin/admin/mypage";
    }

    /**
     * 관리자 관리 폼 서비스
     *
     * @param model
     * @param page
     * @return String
     */
    public String adminList(Model model, int page) {

        int count = adminMapper.countAdmin();

        Pagination pagination = new Pagination(count, page);

        Map<String, Object> map = new HashMap<>();
        map.put("startIndex", pagination.getStartIndex());
        map.put("pageSize", pagination.getPageSize());

        List<UsersDTO> list = adminMapper.selectAdmin(map);

        model.addAttribute("adminList", list);
        model.addAttribute("pagination", pagination);
        model.addAttribute("count", count);

        return "admin/admin/list";
    }

    /**
     * 관리자 등록 폼 서비스
     *
     * @return String
     */
    public String adminRegister() {

        return "admin/admin/register";
    }

    /**
     * 관리자 등록 서비스
     *
     * @param usersDTO
     * @return int
     */
    public int insertAdmin(UsersDTO usersDTO) {

        // 비밀번호 인코딩
        String rawPassword = usersDTO.getUsersPass();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        usersDTO.setUsersPass(encPassword);

        return adminMapper.insertAdmin(usersDTO);
    }

    /**
     * 관리자 상세 조회 서비스
     *
     * @param model
     * @param usersIdx
     * @return String
     */
    public String adminDetails(Model model, long usersIdx) {

        UsersDTO details = adminMapper.selectAdminDetails(usersIdx);

        model.addAttribute("details", details);

        return "admin/admin/details";
    }

    /**
     * 관리자 비밀번호 초기화 서비스
     *
     * @param usersDTO
     * @return int
     */
    public int resetPwd(UsersDTO usersDTO) {

        // 비밀번호 인코딩
        String rawPassword = usersDTO.getUsersAccount();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        usersDTO.setUsersPass(encPassword);

        return adminMapper.updatePwd(usersDTO);
    }

    /**
     * 관리자 수정 서비스
     *
     * @param usersDTO
     * @return int
     */
    public int updateAdmin(UsersDTO usersDTO) {

        return adminMapper.updateAdmin(usersDTO);
    }

    /**
     * 아이디 중복검사 서비스
     *
     * @param usersAccount
     * @return int
     */
    public int accountCheck(String usersAccount) {

        return adminMapper.countAccount(usersAccount);
    }

    /**
     * 아이디 중복검사 서비스
     *
     * @param usersPhone
     * @return int
     */
    public int phoneCheck(String usersPhone) {

        return adminMapper.countPhone(usersPhone);
    }

    /**
     * 비밀번호 확인 서비스
     *
     * @param usersDTO
     * @return int
     */
    public int selectPassword(UsersDTO usersDTO) {

        String nowPassword = adminMapper.selectPassword(usersDTO.getUsersIdx());

        if (bCryptPasswordEncoder.matches(usersDTO.getUsersPass(), nowPassword)) {
            return 1;
        } else {
            return 0;
        }

    }

    /**
     * 관리자 마이페이지 수정 서비스
     *
     * @param usersDTO
     * @return int
     */
    public int updateMypage(UsersDTO usersDTO) {

        // 비밀번호 인코딩
        String rawPassword = usersDTO.getUsersPass();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        usersDTO.setUsersPass(encPassword);

        return adminMapper.updateMypage(usersDTO);
    }

}
