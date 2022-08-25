package com.eosa.web.chatting.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eosa.web.chatting.model.ChatRoom;
import com.eosa.web.chatting.service.ChatService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/api/chat")
public class ChatRoomController {

    @Autowired private ChatService chatService;

    // 채팅 리스트 화면
    @GetMapping("/room")
    public String room(Model model) {
        return "service/chatting/room";
    }
    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> rooms() {
        return chatService.findAllRoom();
    }
    // 채팅방 생성
    // @PostMapping("/room")
    @GetMapping("/createRoom")
    @ResponseBody
    public ChatRoom createRoom(
        @RequestParam("roomName") String roomName,
        @RequestParam("usersIdx") String usersIdx,
        @RequestParam("companysIdx") String companysIdx
       
    ) {
        log.debug("채팅방 {} 생성을 요청 합니다. 요청한 사용자의 인덱스: {}, 대상 회사의 인덱스: {}, 생성된 시간: {}", roomName, usersIdx, companysIdx, LocalDateTime.now());
        return chatService.createChatRoom(roomName, Long.parseLong(usersIdx), Long.parseLong(companysIdx));
    }

    @PutMapping("/room")
    @ResponseBody
    public List<ChatRoom> deleteRoom(
        @RequestParam("roomId") String roomId
    ) {
        return chatService.deleteChatRoom(roomId);
    }   

    // // roomId에 해당하는 채팅방에 입장
    // @GetMapping("/room/enter/{roomId}")
    // public String roomDetail(Model model, 
    //     @PathVariable String roomId,
    //     @RequestParam(value="usersName") String usersName,
    //     @RequestParam(value="messageType") String messageType
    // ) {
    //     model.addAttribute("roomId", roomId);
    //     log.info("## User: {} get in RoomId: {}\n## T: {}", usersName, roomId, LocalDateTime.now());
    //     return "/chat/roomdetail";
    // }
    @GetMapping("/room/enter/{roomId}")
    @ResponseBody
    public ChatRoom roomDetailREST(
        @PathVariable String roomId,
        @RequestParam(value="usersName") String usersName
    ) {
        // model.addAttribute("roomId", roomId);
        log.info("사용자 '{}' 가 RoomId: {} 채팅방에 입장했습니다. 입장시간: {}", usersName, roomId, LocalDateTime.now());
        ChatRoom result = chatService.findById(roomId);
        return result;
    }
    // roomId에 해당하는 채팅방 조회
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId, Model model) {
        
        ChatRoom transaction = chatService.findById(roomId);
        model.addAttribute("room", transaction);

        return transaction;
    }

    // TestMethod 현재 존재하는 모든 채팅방 삭제
    @GetMapping("/testAllFlush")
    @ResponseBody
    public void testAllFlush() {
        log.debug("서버에 존재하는 모든 채팅방을 삭제합니다. 삭제시간: {}", LocalDateTime.now());
        chatService.testAllFlush();
    }
}
