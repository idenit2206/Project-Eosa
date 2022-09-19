package com.eosa.web.chatting.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="ChatMessage")
public class ChatMessage {    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column private Long messageIdx;
    @Column private MessageType messageType;
    @Column private String roomId;
    @Column private String sender;
    @Column private String message;
    @Transient private String fileMessage;
    @Column private String sendDate;

}
