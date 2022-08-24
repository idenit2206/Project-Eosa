package com.eosa.web.chatting.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name="LiveChat")
@Table(name="LiveChat")
public class ChatRoom {
   
    // 방 고유키
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Id
    @Column
    private String roomId;

    // 방 제목
    @Column
    private String roomName;

    // 방 만든 사람 (회원의 색인 번호)
    @Column
    private Long usersIdx;

    @Column
    private Long companysIdx;

    // 채팅 로그의 위치가 담긴 컬럼
    @Column
    private String dataInfo;

    // 채팅방이 생성된 타임스탬프
    @Column
    private LocalDateTime createdDate;

    @Column
    private int usable;

    public static ChatRoom create(String roomName, Long usersIdx, Long companysIdx) {
        ChatRoom room = new ChatRoom();
        room.roomId = UUID.randomUUID().toString();
        room.roomName = roomName;
        room.usersIdx = usersIdx;
        // room.companysIdx = Long.parseLong("1");
        room.companysIdx = companysIdx;
        room.dataInfo = "데이터 로그파일 주소";
        room.createdDate = LocalDateTime.now();
        room.usable = 1;

        return room;
    }
    
    public ChatRoom() {}

    public ChatRoom(String roomName, Long usersIdx) {
        this.roomId = UUID.randomUUID().toString();
        this.roomName = roomName;
        this.usersIdx = usersIdx;
        this.companysIdx = Long.parseLong("1");
        this.dataInfo = "데이터 로그파일 주소";
        this.createdDate = LocalDateTime.now();
        this.usable = 1;
    }

}
