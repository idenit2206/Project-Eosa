package com.eosa.admin.mapper;

import com.eosa.admin.dto.PolicyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * packageName    : com.eosa.admin.mapper
 * fileName       : PolicyMapper
 * author         : Jihun Kim
 * date           : 2022-09-20
 * description    : 정책 매퍼
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-20        Jihun Kim       최초 생성
 */
@Mapper
public interface PolicyMapper {

    /**
     * 정책 조회 매퍼
     *
     * @return PolicyDTO
     */
    List<PolicyDTO> selectPolicy();

    /**
     * 정책 수정 매퍼
     *
     * @param policyDTO
     * @return int
     */
    int updatePolicy(PolicyDTO policyDTO);

}
