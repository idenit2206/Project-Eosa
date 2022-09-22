package com.eosa.admin.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * packageName    : com.eosa.admin.dto
 * fileName       : ChatDTO
 * author         : Jihun Kim
 * date           : 2022-09-22
 * description    : 채팅 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-22        Jihun Kim       최초 생성
 */
@Data
public class ChatDTO {

    private long idx;                       // 채팅방 고유 번호
    private String roomId;                  // 채팅방 고유 이름
    private String roomName;                // 채팅방 이름
    private long usersIdx;                  // 의뢰인 고유 번호
    private long companysIdx;               // 업체 고유 번호
    private String dataInfo;                // 채팅 데이터 주소
    private Timestamp createdDate;          // 채팅방 생성일
    private int usable;                     // 채팅방 사용 여부

    private long messageIdx;                // 메시지의 고유 번호
    private String messageType;             // 메시지의 유형
    private long sender;                    // 메시지 작성자
    private String message;                 // 메시지 내용
    private String fileMessage;             // 파일 메시지
    private Timestamp sendDate;             // 메시지 작성일

    private String usersAccount;            // 회원 계정
    private String usersRole;               // 회원 역할

}
