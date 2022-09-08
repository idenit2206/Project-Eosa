package com.eosa.web.chatting.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.*;

import com.eosa.web.chatting.service.ChatRoomService;
import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

@Data
@Entity
@Table(name="ChatRoom")
public class ChatRoom {
    // 방 고유키
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @Column private String roomId;
    // 방 제목
    @Column private String roomName;
    // 방 만든 사람 (회원의 색인 번호)
    @Column private Long usersIdx;
    @Column private Long companysIdx;
    // 채팅 로그의 위치가 담긴 컬럼
    @Column private String dataInfo;
    // 채팅방이 생성된 타임스탬프
    @Column private LocalDateTime createdDate;
    @Column private int usable;


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
