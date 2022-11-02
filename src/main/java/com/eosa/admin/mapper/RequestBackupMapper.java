package com.eosa.admin.mapper;

import com.eosa.admin.dto.RequestBackupDTO;
import com.eosa.admin.dto.RequestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * packageName    : com.eosa.admin.mapper
 * fileName       : RequestBackupMapper
 * author         : PARK MIN JAE
 * date           : 2022-11-01
 * description    : 의뢰 매퍼
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-01        PARK MIN JAE      RequestBackup 관련 최초 생성
 */
@Mapper
public interface RequestBackupMapper {

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
    List<RequestBackupDTO> selectRequestList(Map<String, Object> map);

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
    int updateRequest(RequestBackupDTO requestDTO);

    /**
     * 의뢰 분야 등록 매퍼
     *
     * @param requestDTO
     * @return int
     */
    int insertRequestCategory(RequestBackupDTO requestDTO);

    /**
     * companysIdx와 일치하는 RequestDTO List를 출력하는 매퍼
     * @param {Long} companysIdx
     * @return int
     */
    List<RequestBackupDTO> requestDTOByCompanysIdx(long companysIdx);

    /**
     * 모든 RequestDTO 를 List로 출력하는 매퍼
     * @return
     */
    List<RequestBackupDTO> selectAllRequestDTO();

    /**
     * 모든 RequestDTO 를 List로 출력하는 매퍼 (사용자의 성별, 나이 추가)
     * @return
     */
    List<RequestBackupDTO> selectAllRequestDTO2();
}
