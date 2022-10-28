package com.eosa.web.chatting.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name="ChatRoom")
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @Column private String roomId;
    @Column private String roomName;
    @Column private Long usersIdx;
    @Column private Long companysIdx;
    @Column private int clientReadStatus;
    @Column private int detectiveReadStatus;
    @Column private String dataInfo;
    @Column private LocalDateTime createdDate;
    @Column private int usable;
    @Column private int usersUsable;
    @Column private int companysUsable;


//    public static ChatRoom create(String roomName, Long usersIdx, Long companysIdx) {
//        ChatRoom room = new ChatRoom();
//        room.roomId = UUID.randomUUID().toString();
//        room.roomName = roomName;
//        room.usersIdx = usersIdx;
//        room.companysIdx = companysIdx;
//        room.dataInfo = "";
//        room.createdDate = LocalDateTime.now();
//        room.usable = 1;
//
//        return room;
//    }
    
//    public ChatRoom() {}

//    public ChatRoom(String roomName, Long usersIdx, Long companysIdx) {
//        this.roomId = UUID.randomUUID().toString();
//        this.roomName = roomName;
//        this.usersIdx = usersIdx;
//        this.companysIdx = companysIdx;
//        this.createdDate = LocalDateTime.now();
//        this.usable = 1;
//    }

}
