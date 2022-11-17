package com.eosa.web.chatting.entity;

import lombok.Data;

@Data
public class ChatBlockParam {
    
    private String roomId;      // 채팅방의 roomId
    private Long usersIdx;      // 사용자의 usersIdx;
    private String usersRole;   // 사용자의 usersRole

}
