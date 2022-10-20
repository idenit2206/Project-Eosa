package com.eosa.web.chatting.entity;

import lombok.Data;

import javax.persistence.*;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ChatMessageParam {    

    private Long messageIdx;
    private MessageType messageType;
    private String roomId;
    private String sender;
    private String message;
    private Object fileMessage;
    private MultipartFile file;
    private String sendDate;    

}
