package com.eosa.admin.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * packageName    : com.eosa.admin.dto
 * fileName       : ReportDTO
 * author         : Jihun Kim
 * date           : 2022-09-21
 * description    : 신고 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-21        Jihun Kim       최초 생성
 */
@Data
public class ReportDTO {

    private long idx;                       // 신고의 고유 번호
    private long usersIdx;                  // 신고자의 고유 번호
    private long companysIdx;               // 신고 업체의 고유 번호
    private String reportDetail;            // 신고 내용
    private int reportCheckState;           // 신고 처리 상태
    private Timestamp reportDate;           // 신고일
    private Timestamp reportCheckDate;      // 신고 처리일
    
    private String usersAccount;            // 신고자의 계정
    private String companysName;            // 신고 업체의 이름

}
