package com.eosa.admin.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * packageName    : com.eosa.admin.dto
 * fileName       : CompanysDTO
 * author         : Jihun Kim
 * date           : 2022-09-15
 * description    : 업체 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-15        Jihun Kim       최초 생성
 */
@Data
public class CompanysDTO {

    private long companysIdx;               // 업체 고유 번호
    private String companysName;            // 업체 이름
    private long companysCeoIdx;            // 업체 대표 고유 번호
    private String companysCeoName;         // 업체 대표 이름
    private String companysComment;         // 업체 간단 소개
    private String companysSpec;            // 업체 상세 소개
    private String companysPhone;           // 업체 연락처
    private String companysRegion1;         // 시/도
    private String companysRegion2;         // 구/군
    private String companysRegion3;         // 상세주소
    private String companysRegistCerti;     // 사업자등록증
    private String companysProfileImage;    // 프로필 이미지
    private String companysBankName;        // 계좌 은행
    private String companysBankNumber;      // 계좌번호
    private Timestamp companysRegistDate;   // 업체 등록일
    private int companysPremium;            // 프리미엄 여부
    private int companysLocalPremium;       // 마패 여부
    private int companysEnabled;            // 승인 상태
    private int companysDelete;             // 삭제 여부
    private String companysDummyPhone;      // 업체 안심번호
    private String companysMemo;            // 업체 관리자 메모

    private long companysActiveRegionIdx;   // 업체 활동지역 고유 번호
    private String activeRegion;            // 업체 활동지역

    private long companysCategoryIdx;       // 업체 업무 분야 고유 번호
    private String companysCategoryValue;   // 업체 업무 분야

    private long idx;                       // 프리미엄 업체 고유 번호
    private Timestamp premiumReqDate;       // 프리미엄 신청일
    private Timestamp premiumStartDate;     // 프리미엄 시작일
    private Timestamp premiumEndDate;       // 프리미엄 종료일
    private int companysPremiumEnabled;     // 프리미엄 승인 여부

    private long companysFlagIdx;           // 깃발 업체 고유 번호
    private long flagPrice;                 // 깃발 가격
    private String flagPriceBank;           // 깃발 업체 은행
    private Timestamp flagReqDate;          // 깃발 광고 신청일
    private Timestamp flagStartDate;        // 깃발 광고 시작일
    private Timestamp flagEndDate;          // 깃발 광고 종료일
    private int companysFlagEnabled;        // 깃발 승인 여부

    private long companysFlagRegionIdx;     // 깃발 업체 활동지역 고유 번호
    private String companysFlagRegion1;     // 깃발 업체 활동 시/도
    private String companysFlagRegion2;     // 깃발 업체 활동 구/군

    private long companysFlagCategoryIdx;   // 깃발 업체 업무 분야 고유 번호
    private String companysFlagCategory;    // 깃발 업체 업무 분야

}
