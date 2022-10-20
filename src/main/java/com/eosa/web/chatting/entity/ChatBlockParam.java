package com.eosa.web.chatting.entity;

import lombok.Data;

@Data
public class ChatBlockParam {
    
    private String roomId;
    private Long usersIdx;
    private String usersRole;

}
