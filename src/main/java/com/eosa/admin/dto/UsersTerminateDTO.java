package com.eosa.admin.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * packageName    : com.eosa.admin.dto
 * fileName       : UsersTerminateDTO
 * author         : Jihun Kim
 * date           : 2022-09-14
 * description    : 회원, 탈퇴 회원 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-14        Jihun Kim       최초 생성
 */
@Data
public class UsersTerminateDTO {

    private long usersIdx;                  // 회원 고유 번호
    private String usersAccount;            // 회원 아이디
    private String usersPass;               // 회원 비밀번호
    private String usersName;               // 회원 이름
    private String usersNick;               // 회원 닉네임
    private String usersPhone;              // 회원 연락처
    private String usersEmail;              // 회원 이메일
    private String usersRole;               // 회원 역할
    private int usersAge;                   // 회원 연령
    private String usersRegion1;            // 시/도
    private String usersRegion2;            // 구/군
    private int usersGender;                // 회원 성별
    private Timestamp usersJoinDate;        // 가입일일
    private int usersNotice;                // SMS, E-mail 수신여부
    private String provider;                // SNS 구분
    private String providerId;              // SNS 계정
    private String picture;                 // SNS 프로필 이미지
    private int usersEnabled;               // 계정 승인 여부
    private int usersDelete;                // 계정 삭제 여부

    private long terminateUserIdx;          // 탈퇴 목록 고유 번호
    private String terminateReason;         // 탈퇴 사유
    private Timestamp terminateRequestDate; // 탈퇴예정일
    private Timestamp terminateCreateDate;  // 탈퇴일

}
