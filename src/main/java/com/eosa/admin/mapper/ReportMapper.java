package com.eosa.admin.mapper;

import com.eosa.admin.dto.ReportDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * packageName    : com.eosa.admin.mapper
 * fileName       : ReportMapper
 * author         : Jihun Kim
 * date           : 2022-09-21
 * description    : 신고 매퍼
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-21        Jihun Kim       최초 생성
 */
@Mapper
public interface ReportMapper {

    /**
     * 신고 목록 개수 조회 매퍼
     *
     * @param map
     * @return int
     */
    int countReportList(Map<String, Object> map);

    /**
     * 신고 목록 조회 매퍼
     *
     * @param map
     * @return ReportDTO
     */
    List<ReportDTO> selectReportList(Map<String, Object> map);

    /**
     * 신고 내역 처리 상태 업데이트
     * @param reportDTO
     * @return
     */
    int reportUpdate(ReportDTO reportDTO);

    /**
     * 신고 삭제 매퍼
     *
     * @param idx
     * @return int
     */
    int deleteReport(long idx);

    /**
     * 신고 다중 삭제 매퍼
     *
     * @param arr
     * @return int
     */
    int deleteReportMulti(String[] arr);

}
