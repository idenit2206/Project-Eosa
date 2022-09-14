package com.eosa.admin.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * packageName    : com.eosa.admin.dto
 * fileName       : TempUserDTO
 * author         : Jihun Kim
 * date           : 2022-09-14
 * description    : 비회원 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-14        Jihun Kim       최초 생성
 */
@Data
public class TempUserDTO {

    private long tempUserIdx;               // 비회원 고유 번호
    private String tempUserEmail;           // 비회원 이메일
    private String tempUserPass;            // 비회원 비밀번호
    private int tempUserGender;             // 비회원 성별
    private int tempUserAge;                // 비회원 연령
    private String tempUserRegion1;         // 시/도
    private String tempUserRegion2;         // 구/군
    private Timestamp tempUserRegistDate;   // 비회원 등록일
    private int num;                        // 순번

}
