package com.eosa.web.chatting.controller;

import java.util.LinkedList;
import java.util.List;

import com.eosa.web.chatting.service.ChatMessageService;
import com.eosa.web.chatting.service.ChatRoomService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import com.eosa.web.chatting.entity.ChatMessage;
import com.eosa.web.chatting.entity.MessageType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatMessageController {
    /*
    @MessageMapping()의 경로가 "/chat/message"이지만 ChatConfig의 setApplicationDestinationPrefixes()를 통해 
    prefix를 "/app"으로 해줬기 때문에 실질 경로는 "/app/chat/message"가 됨
    클라이언트에서 "/app/chat/message"의 경로로 메시지를 보내는 요청을 하면,
    메시지 Controller가 받아서 "topic/chat/room/{roomId}"를 구독하고 있는 클라이언트에게 메시지를 전달하게 됨.
    */
    private final SimpMessageSendingOperations sendingOperations;
    
    @Autowired private ChatMessageService chatMessageService;
    @Autowired private ChatRoomService chatRoomService;

    List<Object> messageList = new LinkedList<>();

    @MessageMapping("/chat/message")
    public void sendMessage(ChatMessage message) {
        if((message.getMessageType()).equals(MessageType.ENTER)) {
            log.debug("sendMessage [ENTER]: {}", message.toString());
            message.setMessage(message.getSender() + "님이 입장했습니다.");
            chatMessageService.addMessage(message);
            chatMessageService.save(message);
        }

        if((message.getMessageType()).equals(MessageType.TALK)) {
            log.debug("sendMessage [TALK]: {}", message.toString());
            chatMessageService.addMessage(message);
            chatMessageService.save(message);
            if(message.getSender().equals("client")) {
                chatRoomService.setDetectiveReadStatusUnread(message.getRoomId());
                chatRoomService.setClientReadStatusRead(message.getRoomId());
            }
            else if(message.getSender().equals("ADMIN")) {
                chatRoomService.setClientReadStatusUnread(message.getRoomId());
            }
            else if(message.getSender().equals("detective")) {
                chatRoomService.setClientReadStatusUnread(message.getRoomId());
                chatRoomService.setDetectiveReadStatusRead(message.getRoomId());
            }
        }

        if((message.getMessageType()).equals(MessageType.FILE)) {
            log.debug("sendMessage [FILE]: {}", message.toString());
            chatMessageService.addMessage(message);
            chatMessageService.save(message);
        }

        if((message.getMessageType()).equals(MessageType.LEAVE)) {
            message.setMessage(message.getSender() + "님이 퇴장했습니다.");
            log.debug("sendMessage [LEAVE]: {}", message.toString());
            chatMessageService.addMessage(message);
            chatMessageService.save(message);
        }    

        sendingOperations.convertAndSend("/topic/chat/room/" + message.getRoomId(), message);
    }

    @GetMapping("/chat/message/read")
    public List<ChatMessage> readMessageList() {        
        return chatMessageService.readMessagesList();
    }

//    @GetMapping("/chat/message/selectChatMessageByRoomId/{roomId}")
//    public List<ChatMessage> selectChatMessageByRoomId(@PathVariable String roomId) {
//        return chatMessageService.selectChatMessageByByRoomId(roomId);
//    }
    @GetMapping("/api/chat/message/selectChatMessageByRoomId")
    public List<ChatMessage> selectChatMessageByRoomId(@RequestParam("roomId") String roomId) {
        return chatMessageService.selectChatMessageByRoomId(roomId);
    }

}
