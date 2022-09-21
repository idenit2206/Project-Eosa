package com.eosa.admin.service;

import com.eosa.admin.dto.RequestDTO;
import com.eosa.admin.mapper.RequestMapper;
import com.eosa.admin.pagination.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName    : com.eosa.admin.service
 * fileName       : RequestService
 * author         : Jihun Kim
 * date           : 2022-09-21
 * description    : 의뢰 서비스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-21        Jihun Kim       최초 생성
 */
@Service
public class RequestService {

    @Autowired
    private RequestMapper requestMapper;

    /**
     * 의뢰 목록 조회 서비스
     *
     * @param model
     * @param state
     * @param sort
     * @param search
     * @param page
     * @return String
     */
    public String requestList(Model model, String state, String sort, String search, int page) {

        Map<String, Object> map = new HashMap<>();

        if (!state.equals("")) {
            map.put("state", state);
        }

        if (search != "" && !search.equals("")) {
            if (sort.equals("client") || sort.equals("company")) {
                map.put("sort", sort);
            }
            map.put("search", search);
        }

        int count = requestMapper.countRequestList(map);

        Pagination pagination = new Pagination(count, page);

        map.put("startIndex", pagination.getStartIndex());
        map.put("pageSize", pagination.getPageSize());

        List<RequestDTO> list = requestMapper.selectRequestList(map);

        model.addAttribute("requestList", list);
        model.addAttribute("pagination", pagination);
        model.addAttribute("count", count);
        model.addAttribute("state", state);
        model.addAttribute("sort", sort);
        model.addAttribute("search", search);

        return "admin/board/request/list";
    }

}