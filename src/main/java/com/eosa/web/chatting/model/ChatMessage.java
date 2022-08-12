package com.eosa.web.chatting.model;

import lombok.Data;

@Data
public class ChatMessage {    
    
    private MessageType messageType;
    private String roomId;   
    private String sender;
    private Object message;
    private Object fileMessage;
    private String sendDate;

}
