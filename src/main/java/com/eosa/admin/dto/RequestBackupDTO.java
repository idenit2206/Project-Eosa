package com.eosa.admin.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * packageName    : com.eosa.admin.dto
 * fileName       : RequestDTO
 * author         : Jihun Kim
 * date           : 2022-09-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-21        Jihun Kim       최초 생성
 */
@Data
public class RequestBackupDTO {

    private long requestFormBackupIdx;      // 의뢰백업의 고유 번호
    private long requestFormIdx;            // 의뢰의 고유 번호
    private long usersIdx;                  // 의뢰인의 고유 번호
    private long companysIdx;               // 의뢰 업체의 고유 번호
    private String requestFormRegion1;      // 의뢰 요청 지역
    private String requestFormRegion2;      // 의뢰 요청 지역
    private String requestFormChannel;      // 의뢰 구분
    private String requestFormStatus;       // 의뢰 진행 상태
    private Timestamp requestConsultDate;   // 상담 요청일
    private Timestamp requestFormDate;      // 의뢰 요청일
    private Timestamp requestFormAcceptDate;// 의뢰 결정일
    private Timestamp requestFormCompDate;  // 의뢰 완료일
    private String requestFormRejectMessage;// 의뢰 거절 사유

    private long requestFormCategoryIdx;    // 의뢰 분야의 고유 번호
    private String requestFormCategoryValue;// 의뢰 분야

    private String usersAccount;            // 의뢰인의 계정
    private String companysName;            // 의뢰 업체의 이름

    private int usersGender;                // 의뢰인의 성별
    private int usersAge;                   // 의뢰인의 연령대
}
