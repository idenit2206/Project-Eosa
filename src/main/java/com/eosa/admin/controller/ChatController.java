package com.eosa.admin.controller;

import com.eosa.admin.service.ChatService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * packageName    : com.eosa.admin.controller
 * fileName       : ChatController
 * author         : Jihun Kim
 * date           : 2022-09-22
 * description    : 채팅 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-22        Jihun Kim       최초 생성
 */
@Slf4j
@Controller
@RequestMapping("/admin/manage/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    /**
     * 채팅 목록 조회 컨트롤러
     *
     * @param model
     * @param sort
     * @param search
     * @param page
     * @return String
     */
    @GetMapping("/list")
    public String chatList(
        Model model,
        @RequestParam(defaultValue = "client") String sort,
        @RequestParam(defaultValue = "") String search,
        @RequestParam(defaultValue = "1") int page
    ) {
        return chatService.chatList(model, sort, search, page);
    }

    /**
     * 채팅 내역 조회 컨트롤러
     *
     * @param model
     * @param roomId
     * @return String
     */
    @GetMapping("/details")
    public String chatDetails(
        // Authentication auth,
        Model model,
        @RequestParam String roomId
    ) {
        return chatService.chatDetails(model, roomId);
    }

    /**
     * 의뢰 목록에서 출력하는 채팅 목록 조회 컨트롤러
     * @param model
     * @param usersIdx
     * @param companysIdx
     * @return
     */
    @GetMapping("/chatListFromRequestList")
    @ResponseBody
    public String chatListFromRequestList(
        Model model,
        @RequestParam String usersIdx,
        @RequestParam String companysIdx
    ) {
        Long longUsersIdx = Long.parseLong(usersIdx);
        Long longCompanysIdx = Long.parseLong(companysIdx);
        return chatService.chatListFromRequestList(model, longUsersIdx, longCompanysIdx);
        // return chatList(model, "client", "", 1);
    }

}
