package com.eosa.admin.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * packageName    : com.eosa.admin.dto
 * fileName       : ReviewDTO
 * author         : Jihun Kim
 * date           : 2022-09-20
 * description    : 리뷰 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-20        Jihun Kim       최초 생성
 */
@Data
public class ReviewDTO {

    private long idx;                       // 리뷰의 고유 번호
    private long reviewUsersIdx;            // 작성자의 고유 번호
    private long reviewCompanysIdx;         // 업체의 고유 번호
    private long reviewRequestFormIdx;      // 의뢰의 고유 번호
    private int resultScore;                // 만족도 점수
    private int communicationScore;         // 소통 점수
    private int processScore;               // 일정 점수
    private int specialityScore;            // 전문성 점수
    private String reviewDetail;            // 리뷰 내용
    private Timestamp reviewDate;           // 리뷰 등록일

    private BigDecimal average;             // 평점
    
    private String usersAccount;            // 작성자의 계정
    
    private String companysName;            // 업체의 이름
    
}
