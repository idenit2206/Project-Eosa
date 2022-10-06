package com.eosa.web.chatting.websocket;

import com.eosa.web.chatting.entity.ChatMessage;
import com.eosa.web.chatting.entity.ChatRoom;
import com.eosa.web.chatting.service.ChatMessageService;
import com.eosa.web.chatting.service.ChatRoomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomWebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final ChatRoomService chatRoomService;

    
    /** 
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
//        log.info("[handleTextMessage] payload: {}", payload);
////        TextMessage tm = new TextMessage("Welcome Eosa chatting Server");
////        session.sendMessage(tm);
//        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
//        ChatRoom room = chatRoomService.findChatRoomByRoomId(chatMessage.getRoomId());
    }

}
