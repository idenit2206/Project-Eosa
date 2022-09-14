package com.eosa.web.chatting.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eosa.web.chatting.entity.ChatMessage;
import com.eosa.web.chatting.service.ChatMessageService;
import com.eosa.web.util.CustomResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eosa.web.chatting.entity.ChatRoom;
import com.eosa.web.chatting.service.ChatRoomService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/api/chat")
public class ChatRoomController {
    @Autowired private ChatRoomService chatRoomService;
    @Autowired private ChatMessageService chatMessageService;

    // 채팅 리스트 화면
    @GetMapping("/room")
    public String room(Model model) {
        return "service/chatting/room";
    }

    // 채팅방 생성
    // @PostMapping("/room")
    @GetMapping("/createRoom")
    @ResponseBody
    public CustomResponseData createRoom(
        @RequestParam("usersIdx") Long usersIdx,
        @RequestParam("companysIdx") Long companysIdx
    ) {
        CustomResponseData result = new CustomResponseData();
        log.debug("채팅방 생성을 요청 합니다. 요청한 사용자의 인덱스: {}, 대상 회사의 인덱스: {}, 생성된 시간: {}", usersIdx, companysIdx, LocalDateTime.now());
        ChatRoom entity = new ChatRoom();
            entity.setUsersIdx(usersIdx);
            entity.setCompanysIdx(companysIdx);
        ChatRoom createRow = chatRoomService.save(entity);

        if(createRow != null) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(createRow);
            result.setResponseDateTime(LocalDateTime.now());
        }
        else {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(null);
            result.setResponseDateTime(LocalDateTime.now());
        }
        return result;
    }

    /**
     * usersIdx가 참가한 모든 채팅방의 목록을 출력합니다.
     * @param usersIdx
     * @return
     */
    @GetMapping("/selectChatRoomListByUsersIdx")
    @ResponseBody
    public CustomResponseData selectChatRoomListByUsersIdx(@RequestParam("usersIdx") Long usersIdx) {
        log.debug("[selectChatRoomListByUsersIdx] usersIdx: {}가 채팅방 목록 조회를 요청합니다.", usersIdx);
        CustomResponseData result = new CustomResponseData();
        Map<String, Object> items = new HashMap<>();

        List<ChatRoom> selectRows = chatRoomService.selectChatRoomListByUsersIdx(usersIdx);
        items.put("ChatRoom", selectRows);

        if(selectRows != null) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(selectRows);
            result.setResponseDateTime(LocalDateTime.now());
        }
        else {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(null);
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

    /**
     * CompanysIdx가 일치하는 채팅방을 조회합니다.
     * @param companysIdx
     * @return
     */
    @GetMapping("/selectChatRoomListByCompanysIdx")
    @ResponseBody
    public CustomResponseData selectChatRoomListByCompanysIdx(@RequestParam("companysIdx") Long companysIdx) {
        log.debug("[selectChatRoomListByCompanysIdx] companysIdx: {}가 일치하는 채팅방 목록 조회를 요청합니다.", companysIdx);
        CustomResponseData result = new CustomResponseData();
        Map<String, Object> items = new HashMap<>();

        List<ChatRoom> selectRows = chatRoomService.selectChatRoomListByCompanysIdx(companysIdx);
        items.put("ChatRoom", selectRows);

        if(selectRows != null) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(selectRows);
            result.setResponseDateTime(LocalDateTime.now());
        }
        else {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(null);
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

    /**
     * 채팅방 입장을 위한 메서드
     * @param roomId
     * @param usersName
     * @return
     */
//    @GetMapping("/room/enter/{roomId}")
//    @ResponseBody
//    public ChatRoom roomDetailREST(
//        @PathVariable String roomId,
//        @RequestParam(value="usersIdx") String usersName
//    ) {
//        // model.addAttribute("roomId", roomId);
//        log.info("사용자 '{}' 가 RoomId: {} 채팅방에 입장했습니다. 입장시간: {}", usersName, roomId, LocalDateTime.now());
//        ChatRoom result = chatRoomService.findById(roomId);
//        return result;
//    }
    @GetMapping("/room/enter/{roomId}")
    @ResponseBody
    public ChatRoom roomEnterRoomId(
            @PathVariable String roomId,
            @RequestParam(value="usersIdx") String usersName
    ) {
        // model.addAttribute("roomId", roomId);
        log.info("사용자 '{}' 가 RoomId: {} 채팅방에 입장했습니다. 입장시간: {}", usersName, roomId, LocalDateTime.now());
        ChatRoom result = chatRoomService.findChatRoomByRoomId(roomId);
        log.info("[roomEnterRoomId] result: {}", result.toString());
        return result;
    }

    /**
     * 채팅방에 접속한 후 채팅룸 내부에서 접속을 갱신하는 메서드
     * @param roomId
     * @return
     */
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        ChatRoom transaction = chatRoomService.findChatRoomByRoomId(roomId);
        return transaction;
    }

    @GetMapping("/room/currentChatRoomList")
    @ResponseBody
    public List<ChatRoom> currentChatRoomList() {
        return chatRoomService.findAllRoom();
    }

    @PutMapping("/room")
    @ResponseBody
    public List<ChatRoom> deleteRoom(
        @RequestParam("roomId") String roomId
    ) {
        return chatRoomService.deleteChatRoom(roomId);
    }   

    // TestMethod 현재 존재하는 모든 채팅방 삭제
    @GetMapping("/testAllFlush")
    @ResponseBody
    public void testAllFlush() {
        log.debug("서버에 존재하는 모든 채팅방을 삭제합니다. 삭제시간: {}", LocalDateTime.now());
        chatRoomService.testAllFlush();
    }

    //    // TestMethod 모든 채팅방 목록 반환
//    @GetMapping("/rooms")
//    @ResponseBody
//    public List<ChatRoom> rooms() {
//        return chatRoomService.findAllRoom();
//    }
}
