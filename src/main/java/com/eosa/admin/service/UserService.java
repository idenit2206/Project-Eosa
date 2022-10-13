package com.eosa.admin.service;

import com.eosa.admin.dto.TempUserDTO;
import com.eosa.admin.dto.UsersDTO;
import com.eosa.admin.dto.UsersTerminateDTO;
import com.eosa.admin.mapper.UserMapper;
import com.eosa.admin.pagination.Pagination;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName    : com.eosa.admin.service
 * fileName       : UserService
 * author         : Jihun Kim
 * date           : 2022-09-13
 * description    : 회원 서비스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-13        Jihun Kim       최초 생성
 */
@Slf4j
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 회원 목록 조회 서비스
     *
     * @param model
     * @param role
     * @param sort
     * @param search
     * @param page
     * @return String
     */
    public String userList(Model model, String role, String sort, String search, int page) {

        Map<String, Object> map = new HashMap<>();

        map.put("usersRole", role);

        // 필터 검색 조건
        if (search != "" && !search.equals("")) {
            if (sort.equals("id")) {
                map.put("id", sort);
            } else if (sort.equals("name")) {
                map.put("name", sort);
            } else if (sort.equals("phone")) {
                map.put("phone", sort);
            }
            map.put("search", search);
        }

        int count = userMapper.countUsersList(map);

        Pagination pagination = new Pagination(count, page);

        map.put("startIndex", pagination.getStartIndex());
        map.put("pageSize", pagination.getPageSize());

        List<UsersDTO> list = userMapper.selectUsersList(map);

        model.addAttribute("usersList", list);
        model.addAttribute("pagination", pagination);
        model.addAttribute("count", count);
        model.addAttribute("role", role);
        model.addAttribute("sort", sort);
        model.addAttribute("search", search);

        return "admin/user/list";
    }

    /**
     * 회원 상세 조회 서비스
     *
     * @param model
     * @param usersIdx
     * @return String
     */
    public String userDetails(Model model, long usersIdx) {

        UsersTerminateDTO details = userMapper.selectUsersDetails(usersIdx);

        model.addAttribute("details", details);

        return "admin/user/details";
    }

    /**
     * 회원 수정 서비스
     *
     * @param usersDTO
     * @return int
     */
    public int updateUser(UsersDTO usersDTO) {

        return userMapper.updateUsers(usersDTO);
    }

    /**
     * 탈퇴 회원 목록 조회 서비스
     *
     * @param model
     * @param sort
     * @param search
     * @param page
     * @return String
     */
    public String terminateList(Model model, String sort, String search, int page) {

        Map<String, Object> map = new HashMap<>();

        // 필터 검색 조건
        if (search != "" && !search.equals("")) {
            if (sort.equals("id")) {
                map.put("id", sort);
            } else if (sort.equals("name")) {
                map.put("name", sort);
            } else if (sort.equals("phone")) {
                map.put("phone", sort);
            }
            map.put("search", search);
        }

        int count = userMapper.countTerminateList(map);

        Pagination pagination = new Pagination(count, page);

        map.put("startIndex", pagination.getStartIndex());
        map.put("pageSize", pagination.getPageSize());

        List<UsersTerminateDTO> list = userMapper.selectTerminateList(map);

        model.addAttribute("terminateList", list);
        model.addAttribute("pagination", pagination);
        model.addAttribute("count", count);
        model.addAttribute("sort", sort);
        model.addAttribute("search", search);

        return "admin/user/terminate/list";
    }

    /**
     * 회원 탈퇴 서비스
     *
     * @param usersTerminateDTO
     * @return int
     */
    public int insertTerminate(UsersTerminateDTO usersTerminateDTO) {

        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setUsersIdx(usersTerminateDTO.getUsersIdx());
        usersDTO.setUsersEnabled(0);
        usersDTO.setUsersDelete(1);

        userMapper.updateUsersTerminate(usersDTO);

        return userMapper.insertTerminate(usersTerminateDTO);
    }

    /**
     * 회원 탈퇴 취소 서비스
     *
     * @param usersIdx
     * @return int
     */
    public int deleteTerminate(long usersIdx) {

        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setUsersIdx(usersIdx);
        usersDTO.setUsersEnabled(1);
        usersDTO.setUsersDelete(0);

        userMapper.updateUsersTerminate(usersDTO);

        return userMapper.deleteTerminate(usersIdx);
    }

    /**
     * 회원 삭제 서비스
     *
     * @param usersIdx
     * @return int
     */
    public int deleteUser(long usersIdx) {

        userMapper.deleteTerminate(usersIdx);

        return userMapper.deleteUsers(usersIdx);
    }

    /**
     * 비회원 목록 조회 서비스
     *
     * @param model
     * @param sort
     * @param search
     * @param page
     * @return String
     */
    public String tempList(Model model, String sort, String search, int page) {

        Map<String, Object> map = new HashMap<>();

        if (!search.equals("")) {
            map.put("sort", sort);
            map.put("search", search);
        }

        int count = userMapper.countTempList(map);

        Pagination pagination = new Pagination(count, page);

        map.put("startIndex", pagination.getStartIndex());
        map.put("pageSize", pagination.getPageSize());

        List<UsersDTO> list = userMapper.selectTempList(map);

        model.addAttribute("tempList", list);
        model.addAttribute("pagination", pagination);
        model.addAttribute("count", count);
        model.addAttribute("sort", sort);
        model.addAttribute("search", search);

        return "admin/user/temp/list";
    }

    /**
     * 비회원 삭제 서비스
     *
     * @param tempUserIdx
     * @return int
     */
    public int deleteTemp(String tempUserIdx) {

        String arr[] = tempUserIdx.split(",");

        return userMapper.deleteTemp(arr);
    }

    /**
     * 회원 등록 폼 서비스
     *
     * @return String
     */
    public String registerUser() {

        return "admin/user/register";
    }

    /**
     * 회원 등록 서비스
     *
     * @param usersDTO
     * @return int
     */
    public int insertUsers(UsersDTO usersDTO) {

        // 비밀번호 인코딩
        String rawPassword = usersDTO.getUsersPass();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        usersDTO.setUsersPass(encPassword);

        return userMapper.insertUsers(usersDTO);
    }

}
