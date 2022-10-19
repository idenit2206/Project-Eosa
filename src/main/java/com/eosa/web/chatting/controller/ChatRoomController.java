package com.eosa.web.chatting.controller;

import java.time.LocalDateTime;
import java.util.*;

import com.eosa.web.chatting.service.ChatBlockListService;
import com.eosa.web.chatting.service.ChatMessageService;
import com.eosa.web.util.CustomResponseData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eosa.web.chatting.entity.ChatBlockList;
import com.eosa.web.chatting.entity.ChatRoom;
import com.eosa.web.chatting.service.ChatRoomService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/api/chat")
public class ChatRoomController {
    
    @Autowired private ChatRoomService chatRoomService;
    @Autowired private ChatMessageService chatMessageService;
    @Autowired private ChatBlockListService chatBlockListService;

/** 
 * @return CustomResponseData
 */
//    // 채팅 리스트 화면 MVC pattern
//    @GetMapping("/room")
//    public String room(Model model) {
//        return "service/chatting/room";
//    }

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
     * 관리자와 채팅하기
     * @param usersIdx
     * @param companysIdx
     * @return
     */
    @GetMapping("/createRoomWithAdmin")
    @ResponseBody
    public CustomResponseData createRoomWithAdmin(
        @RequestParam("usersIdx") Long usersIdx
    ) {
        CustomResponseData result = new CustomResponseData();
        log.debug("관리자와의 채팅을 요청 합니다. 요청한 사용자의 인덱스: {} 생성된 시간: {}", usersIdx, LocalDateTime.now());
        ChatRoom entity = new ChatRoom();
            entity.setUsersIdx(usersIdx);
            entity.setCompanysIdx(Long.valueOf(0));
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
    @GetMapping("/room/enter/{roomId}")
    @ResponseBody
    public ChatRoom roomEnterRoomId(
            @PathVariable String roomId,
            @RequestParam(value="usersIdx") String usersName
    ) {
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

    
    /** 
     * @return List<ChatRoom>
     */
    @GetMapping("/room/currentChatRoomList")
    @ResponseBody
    public List<ChatRoom> currentChatRoomList() {
        return chatRoomService.findAllRoom();
    }

    /**
     * 서버에서 채팅방을 삭제하는 메서드
     * @param roomId
     * @param usersIdx
     * @return
     */
    @PutMapping("/deleteRoomByRoomId")
    @ResponseBody
    public List<ChatRoom> deleteRoomByRoomId(
        @RequestParam("roomId") String roomId,
        @RequestParam("usersIdx") Long usersIdx
    ) {
        List<ChatRoom> result = null;
        int updateEnableZero = chatRoomService.deleteRoomByRoomId(roomId.trim());
        if(updateEnableZero == 1) {
            result = chatRoomService.selectRoomListOnServer(roomId);
        } else {
            result = chatRoomService.getChatRoomListByUsersIdx(usersIdx);
        }
        return result;
    }

    /**
     * 채팅방의 읽음상태를 조회하는 컨트롤러
     * @param roomId
     * @return
     */
    @GetMapping("/selectReadStatus")
    @ResponseBody
    public CustomResponseData selectReadStatus(@RequestParam("roomId") String roomId) {
        CustomResponseData result = new CustomResponseData();
        ChatRoom cr = chatRoomService.selectReadStatus(roomId);

        if(cr != null) {
            Map<String, Integer> items = new HashMap<>();
            items.put("clientReadStatus", cr.getClientReadStatus());
            items.put("detectiveReadStatus", cr.getDetectiveReadStatus());
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(items);
            result.setResponseDateTime(LocalDateTime.now());
        }
        else {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(null);
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }
    
    @PostMapping("/updateReadStatus")
    @ResponseBody
    public CustomResponseData updateReadStatus(
        @RequestParam("roomId") String roomId,
        @RequestParam("usersRole") String usersRole
    ) {
        CustomResponseData result = new CustomResponseData();

        ChatRoom cr = chatRoomService.selectChatRoomByChatRoomId(roomId);

        if(usersRole.equals("CLIENT")) {
            chatRoomService.changeReadStatusReadFromClient(roomId);
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(true);
            result.setResponseDateTime(LocalDateTime.now());
        }
        else if(usersRole.equals("DETECTIVE")) {
            chatRoomService.changeReadStatusReadFromDetective(roomId);
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(true);
            result.setResponseDateTime(LocalDateTime.now());
        }
        else if(usersRole.equals("SUPER_ADMIN")) {
            chatRoomService.changeReadStatusReadFromDetective(roomId);
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(true);
            result.setResponseDateTime(LocalDateTime.now());
        }
        else if(usersRole.equals("ADMIN")) {
            chatRoomService.changeReadStatusReadFromDetective(roomId);
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(true);
            result.setResponseDateTime(LocalDateTime.now());
        }
        else {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(false);
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

    /**
     * 채팅 차단을 추가하는 컨트롤러
     * @param ChatBlockList
     * @return
     */
    @PostMapping("/addBlockList")
    @ResponseBody
    public CustomResponseData blockUser(ChatBlockList chatBlockList) {
        CustomResponseData result = new CustomResponseData();
        ChatBlockList saveEntity = chatBlockListService.save(chatBlockList);

        if(saveEntity != null) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(true);
            result.setResponseDateTime(LocalDateTime.now());
        }
        else {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(false);
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

    
    /** 
     * @return List<ChatRoom>
     */
    // TestMethod 현재 존재하는 모든 채팅방 삭제
    // @Secured({"CLIENT", "DETECTIVE"})
    // @GetMapping("/testAllFlush")
    // @ResponseBody
    // public void testAllFlush() {
    //     log.debug("서버에 존재하는 모든 채팅방을 삭제합니다. 삭제시간: {}", LocalDateTime.now());
    //     chatRoomService.testAllFlush();
    // }

    /**
     * TestMethod 모든 채팅방 목록 반환
    */
    @Secured({"ADMIN", "SUPER_ADMIN"})
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> rooms() {
        return chatRoomService.findAllRoom();
    }
}
