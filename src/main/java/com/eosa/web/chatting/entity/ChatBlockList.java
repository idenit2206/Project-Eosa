package com.eosa.web.chatting.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="ChatBlockList")
public class ChatBlockList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatBlockListIdx;

    @Column private Long usersIdxBlocker;               // 차단을 실행한 사용자의 usersIdx
    @Column private Long usersIdxBlocked;               // 차단당한 사용자의 usersIdx
    @Column private LocalDateTime chatBlockListDate;    // 차단당한 날짜
    
}
