package com.eosa.admin.mapper;

import com.eosa.admin.dto.RequestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * packageName    : com.eosa.admin.mapper
 * fileName       : RequestMapper
 * author         : Jihun Kim
 * date           : 2022-09-21
 * description    : 의뢰 매퍼
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-21        Jihun Kim       최초 생성
 */
@Mapper
public interface RequestMapper {

    /**
     * 의뢰 목록 개수 조회 매퍼
     *
     * @param map
     * @return int
     */
    int countRequestList(Map<String, Object> map);

    /**
     * 의뢰 목록 조회 매퍼
     *
     * @param map
     * @return RequestDTO
     */
    List<RequestDTO> selectRequestList(Map<String, Object> map);

    /**
     * 의뢰 삭제 매퍼
     *
     * @param requestFormIdx
     * @return int
     */
    int deleteRequest(long requestFormIdx);

    /**
     * 의뢰 분야 삭제 매퍼
     *
     * @param requestFormIdx
     * @return int
     */
    int deleteRequestCategory(long requestFormIdx);

    /**
     * 의뢰 수정 매퍼
     *
     * @param requestDTO
     * @return int
     */
    int updateRequest(RequestDTO requestDTO);

    /**
     * 의뢰 분야 등록 매퍼
     *
     * @param requestDTO
     * @return int
     */
    int insertRequestCategory(RequestDTO requestDTO);

}
