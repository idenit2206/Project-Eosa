package com.eosa.admin.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * packageName    : com.eosa.admin.mapper
 * fileName       : SignMapper
 * author         : Jihun Kim
 * date           : 2022-10-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-05        Jihun Kim       최초 생성
 */
@Mapper
public interface SignMapper {

    /**
     * 사인 순서 변경 매퍼
     *
     * @param requestContractIdx
     * @return int
     */
    int updateTurn(long requestContractIdx);

    /**
     * 사인 상태 조회 매퍼
     *
     * @param requestContractIdx
     * @return int
     */
    int selectTurn(long requestContractIdx);

    int updateRequestStatus(long requestFormIdx);

}
