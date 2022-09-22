package com.eosa.admin.service;

import com.eosa.admin.dto.ChatDTO;
import com.eosa.admin.mapper.ChatMapper;
import com.eosa.admin.pagination.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName    : com.eosa.admin.service
 * fileName       : ChatService
 * author         : Jihun Kim
 * date           : 2022-09-22
 * description    : 채팅 서비스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-22        Jihun Kim       최초 생성
 */
@Service
public class ChatService {

    @Autowired
    private ChatMapper chatMapper;

    /**
     * 채팅 목록 조회 서비스
     *
     * @param model
     * @param sort
     * @param search
     * @param page
     * @return String
     */
    public String chatList(Model model, String sort, String search, int page) {

        Map<String, Object> map = new HashMap<>();

        if (!search.equals("")) {
            if (sort.equals("client") || sort.equals("company")) {
                map.put("sort", sort);
            }
            map.put("search", search);
        }

        int count = chatMapper.countChatList(map);

        Pagination pagination = new Pagination(count, page);

        map.put("startIndex", pagination.getStartIndex());
        map.put("pageSize", pagination.getPageSize());

        List<ChatDTO> list = chatMapper.selectChatList(map);

        model.addAttribute("chatList", list);
        model.addAttribute("pagination", pagination);
        model.addAttribute("count", count);
        model.addAttribute("sort", sort);
        model.addAttribute("search", search);

        return "admin/board/chat/list";
    }

    public String chatDetails(Model model) {

        return "admin/board/chat/details";
    }

}
