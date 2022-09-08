package com.eosa.web.chatting.controller;

import java.util.LinkedList;
import java.util.List;

import com.eosa.web.chatting.service.ChatMessageService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eosa.web.chatting.entity.ChatMessage;
import com.eosa.web.chatting.entity.MessageType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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

    List<Object> messageList = new LinkedList<>();

    @MessageMapping("/chat/message")
    public void sendMessage(ChatMessage message) {
//        log.debug("[enter] message: {}", message);
//        Gson gson = new Gson();
//        gson.toJson(message);
//        log.debug("[sendMessage] gson: {}", gson.toString());
//        JsonObject jsonObject = new JsonObject();
//        jsonObject = (JsonObject) JsonParser.parseString(message).getAsJsonObject();
        if((message.getMessageType()).equals(MessageType.ENTER)) {
            message.setMessage(message.getSender() + "님이 입장했습니다.");
            sendingOperations.convertAndSend("/queue/chat/room"+message.getRoomId());
//            chatMessageService.addMessage(message);
        }

        if((message.getMessageType()).equals(MessageType.TALK)) {
            log.info("[발신인]: {} / [내용]: {} / [시간]: {}", message.getSender(), message.getMessage(), message.getSendDate());
            chatMessageService.addMessage(message);
        }

        if((message.getMessageType()).equals(MessageType.FILE)) {
            log.info("## {} : {} - Room: {} _ Time: {}", message.getSender(), message.getMessage(), message.getRoomId(), message.getSendDate());
            chatMessageService.addMessage(message);
        }

        // if(ChatMessage.MessageType.LEAVE.equals(message.getMessageType())) {
        if((message.getMessageType()).equals(MessageType.LEAVE)) {
            message.setMessage(message.getSender() + "님이 퇴장했습니다.");
            log.info("## User: {} has left RoomId: {}", message.getSender(), message.getRoomId());
            chatMessageService.addMessage(message);
        }    

        sendingOperations.convertAndSend("/topic/chat/room/" + message.getRoomId(), message);
    }

    @GetMapping("/chat/message/read")
    public List<ChatMessage> readMessageList() {        
        return chatMessageService.readMessagesList();
    }



}
