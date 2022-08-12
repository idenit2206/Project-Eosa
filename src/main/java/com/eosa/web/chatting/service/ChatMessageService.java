package com.eosa.web.chatting.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.eosa.web.chatting.model.ChatMessage;
@Service
public class ChatMessageService {
    
    private static List<ChatMessage> chatMessagesList = new LinkedList<>();

    public void addMessage(ChatMessage chatMessage) {
        chatMessagesList.add(chatMessage);
    }

    public List<ChatMessage> readMessagesList() {
        return chatMessagesList;
    }

}
