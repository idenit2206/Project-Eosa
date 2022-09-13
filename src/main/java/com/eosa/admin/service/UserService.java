package com.eosa.admin.service;

import com.eosa.admin.dto.UsersDTO;
import com.eosa.admin.mapper.UserMapper;
import com.eosa.admin.pagination.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
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
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

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

        Map<String, Object> cntMap = new HashMap<>();
        Map<String, Object> map = new HashMap<>();

        cntMap.put("usersRole", role);
        map.put("usersRole", role);

        // 필터 검색 조건
        if (search != "" && !search.equals("")) {
            if (sort.equals("id")) {
                cntMap.put("id", sort);
                map.put("id", sort);
            } else if (sort.equals("name")) {
                cntMap.put("name", sort);
                map.put("name", sort);
            } else if (sort.equals("phone")) {
                cntMap.put("phone", sort);
                map.put("phone", sort);
            }
            cntMap.put("search", search);
            map.put("search", search);
        }

        int count = userMapper.countUsersList(cntMap);

        Pagination pagination = new Pagination(count, page);

        map.put("startIndex", pagination.getStartIndex());
        map.put("pageSize", pagination.getPageSize());

        List<UsersDTO> usersList = userMapper.selectUsersList(map);

        model.addAttribute("usersList", usersList);
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

        UsersDTO details = userMapper.selectUsersDetails(usersIdx);

        model.addAttribute("details", details);

        return "admin/user/details";
    }

}
