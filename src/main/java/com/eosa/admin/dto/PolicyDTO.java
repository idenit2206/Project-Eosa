package com.eosa.admin.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * packageName    : com.eosa.admin.dto
 * fileName       : PolicyDTO
 * author         : Jihun Kim
 * date           : 2022-09-20
 * description    : 정책 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-20        Jihun Kim       최초 생성
 */
@Data
public class PolicyDTO {

    private long policyIdx;                 // 정책의 고유 번호
    private String policyTitle;             // 정책의 제목
    private String policyContents;          // 정책의 내용
    private Timestamp modifiedDate;         // 정책의 수정일
    private Timestamp policyCreateDate;     // 정책의 최초 생성일

}
