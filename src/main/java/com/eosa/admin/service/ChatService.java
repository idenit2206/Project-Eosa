package com.eosa.admin.service;

import com.eosa.admin.dto.ChatDTO;
import com.eosa.admin.mapper.ChatMapper;
import com.eosa.admin.pagination.Pagination;
import com.nimbusds.oauth2.sdk.util.date.SimpleDate;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

@Slf4j
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

    /**
     * 채팅 내역 조회 서비스
     *
     * @param model
     * @param roomId
     * @return String
     */
    public String chatDetails(Model model, String roomId) {
        // log.debug("[chatDetails] auth: {}", auth.getName());
        List<ChatDTO> list = new ArrayList<>();
        if(roomId != null) {
            list = chatMapper.selectChat(roomId);
        }
        else {
            log.info("[chatDetails] roomId is null");
            list = null;
        }
        
        model.addAttribute("chatList", list);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if (list.size() > 1) {
            list.get(0).setDateCheck(1);
            for (int i = 1; i < list.size(); i++) {
                if(!sdf.format(list.get(i).getSendDate()).equals(sdf.format(list.get(i - 1).getSendDate()))) {
                    list.get(i).setDateCheck(1);
                }
            }
        }

        return "admin/board/chat/details";
    }

    /**
     * 의뢰내역서의 usersIdx와 companysIdx를 이용해 채팅방 roomId 조회
     * @param usersIdx
     * @param companysIdx
     * @return
     */
    public String chatListFromRequestList(Model model, Long usersIdx, Long companysIdx) {
        log.info("usersIdx: {}, companysIdx: {} 가 해당되는 채팅방의 대화내용을 불러옵니다.", usersIdx, companysIdx);
        String roomId = chatMapper.selectChatByUsersIdxCompanysIdx(usersIdx, companysIdx);
        
        // if(roomId.equals(null)) {
        //     return "null";
        // }
        if(roomId == null) {
            return null;
        }
        else {
            log.info("chatListFromRequestList] roomId: {}", roomId);
        
            List<ChatDTO> list = chatMapper.selectChat(roomId);

            model.addAttribute("chatList", list);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            if (list.size() > 1) {
                list.get(0).setDateCheck(1);
                for (int i = 1; i < list.size(); i++) {
                    if(!sdf.format(list.get(i).getSendDate()).equals(sdf.format(list.get(i - 1).getSendDate()))) {
                        list.get(i).setDateCheck(1);
                    }
                }
            }

            return roomId;
        }
        
    }

}
