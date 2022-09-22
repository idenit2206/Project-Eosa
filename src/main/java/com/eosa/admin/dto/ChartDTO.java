package com.eosa.admin.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * packageName    : com.eosa.admin.dto
 * fileName       : ChartDTO
 * author         : Jihun Kim
 * date           : 2022-09-22
 * description    : 통계 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-22        Jihun Kim       최초 생성
 */
@Data
public class ChartDTO {

    private long companysIdx;               // 업체 고유 번호
    private String companysName;            // 업체 이름
    private Timestamp companysRegistDate;   // 업체 등록일

    private long companysActiveRegionIdx;   // 업체 활동지역 고유 번호
    private String activeRegion;            // 업체 활동지역

    private long companysCategoryIdx;       // 업체 업무 분야 고유 번호
    private String companysCategoryValue;   // 업체 업무 분야

    private int requestCount;               // 업체 의뢰 건수
    private BigDecimal average;             // 업체 평점

    private long requestFormIdx;            // 의뢰의 고유 번호
    private String requestFormRegion1;      // 의뢰 요청 지역
    private Timestamp requestFormDate;      // 의뢰일

    private long usersIdx;                  // 의뢰인 고유 번호
    private int usersAge;                   // 의뢰인 연령

}
