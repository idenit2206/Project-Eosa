package com.eosa.admin.mapper;

import com.eosa.admin.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * packageName    : com.eosa.admin.mapper
 * fileName       : CompanyMapper
 * author         : Jihun Kim
 * date           : 2022-09-15
 * description    : 업체 매퍼
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-15        Jihun Kim       최초 생성
 */
@Mapper
public interface CompanyMapper {

    /**
     * 업체 목록 개수 조회 매퍼
     *
     * @param map
     * @return int
     */
    int countCompanyList(Map<String, Object> map);

    /**
     * 업체 목록 조회 매퍼
     *
     * @param map
     * @return CompanysDTO
     */
    List<CompanysDTO> selectCompanyList(Map<String, Object> map);

    /**
     * 업체 상세 조회 매퍼
     *
     * @param companysIdx
     * @return CompanysDTO
     */
    CompanysDTO selectCompanyDetails(long companysIdx);

    /**
     * 업체 상세 조회 화면에서 삭제
     * @param companysIdx
     * @return
     */
    int deleteCompanys(long companysIdx);

    /**
     * 업체 수정 매퍼
     *
     * @param companysDTO
     * @return int
     */
    int updateCompanys(CompanysDTO companysDTO);

    /**
     * 업체 활동 지역 등록 매퍼
     *
     * @param map
     * @return int
     */
    int insertCompanysRegion(Map<String, Object> map);

    /**
     * 업체 활동 지역 삭제 매퍼
     *
     * @param companysIdx
     * @return int
     */
    int deleteCompanysRegion(long companysIdx);

    /**
     * 업체 분야 등록 매퍼
     *
     * @param map
     * @return int
     */
    int insertCompanysCategory(Map<String, Object> map);

    /**
     * 업체 분야 삭제 매퍼
     *
     * @param companysIdx
     * @return int
     */
    int deleteCompanysCategory(long companysIdx);

    /**
     * 업체 인증 매퍼
     *
     * @param map
     * @return int
     */
    int updateCheck(Map<String, Object> map);

    /**
     * 업체 제휴협회 신청 매퍼
     *
     * @param companysDTO
     * @return int
     */
    int insertPremium(CompanysDTO companysDTO);

    /**
     * 업체 제휴협회 등록 매퍼
     *
     * @param companysDTO
     * @return int
     */
    int updatePremium(CompanysDTO companysDTO);

    /**
     * 업체 제휴협회 해지 매퍼
     *
     * @param companysIdx
     * @return int
     */
    int cancelPremium(long companysIdx);

    /**
     * 업체 제휴협회 삭제 매퍼
     * @param idx
     * @return
     */
    int deletePremium(long idx);

    /**
     * 업체 마패 신청 매퍼
     *
     * @param companysDTO
     * @return int
     */
    int insertFlag(CompanysDTO companysDTO);

    /**
     * 업체 마패 분야 등록 매퍼
     *
     * @param companysDTO
     * @return int
     */
    int insertFlagCategory(CompanysDTO companysDTO);

    /**
     * 업체 마패 분야 삭제 매퍼
     *
     * @param companysFlagIdx
     * @return int
     */
    int deleteFlagCategory(long companysFlagIdx);

    /**
     * 업체 마패 지역 등록 매퍼
     * @param companysDTO
     * @return int
     */
    int insertFlagRegion(CompanysDTO companysDTO);

    /**
     * 업체 마패 지역 삭제 매퍼
     * @param companysDTO
     * @return
     */
    int deleteFlagRegion(long companysFlagIdx);

    /**
     * 업체 마패 지역 수정 매퍼
     *
     * @param companysDTO
     * @return int
     */
    int updateFlagRegion(CompanysDTO companysDTO);

    /**
     * 업체 마패 가격 수정 매퍼
     *
     * @param companysDTO
     * @return int
     */
    int updateFlagPrice(CompanysDTO companysDTO);

    /**
     * 업체 마패 등록 매퍼
     *
     * @param companysDTO
     * @return
     */
    int updateFlag(CompanysDTO companysDTO);

    /**
     * 업체 마패 해지 매퍼
     *
     * @param companysIdx
     * @return int
     */
    int cancelFlag(long companysIdx);

    /**
     * 업체 마패 삭제 매퍼
     * @param companysFlagIdx
     * @return
     */
    int deleteFlag(long companysFlagIdx);

    /**
     * 업체 광고 상태 변경 매퍼
     *
     * @param map
     * @return int
     */
    int updateAd(Map<String, Object> map);

    /**
     * 제휴협회 목록 개수 조회 매퍼
     *
     * @param map
     * @return int
     */
    int countPremiumList(Map<String, Object> map);

    /**
     * 제휴협회 목록 조회 매퍼
     *
     * @param map
     * @return CompanysDTO
     */
    List<CompanysDTO> selectPremiumList(Map<String, Object> map);

    /**
     * 마패 목록 개수 조회 매퍼
     *
     * @param map
     * @return int
     */
    int countFlagList(Map<String, Object> map);

    /**
     * 마패 목록 조회 매퍼
     *
     * @param map
     * @return CompanysDTO
     */
    List<CompanysDTO> selectFlagList(Map<String, Object> map);

    /**
     * 업체 리뷰, 신고 개수 조회 매퍼
     *
     * @param map
     * @return Integer
     */
    List<Integer> countReviewReport(Map<String, Object> map);

    /**
     * 업체 리뷰 조회 매퍼
     * @param map
     * @return ReviewDTO
     */
    List<ReviewDTO> selectCompanysReview(Map<String, Object> map);

    /**
     * 업체 신고 개수 조회 매퍼
     *
     * @param map
     * @return int
     */
    int countCompanysReport(Map<String, Object> map);

    /**
     * 업체 신고 조회 매퍼
     *
     * @param map
     * @return ReportDTO
     */
    List<ReportDTO> selectCompanysReport(Map<String, Object> map);

    /**
     * 통계 목록 개수 조회 매퍼
     *
     * @param map
     * @return int
     */
    int countChartList(Map<String, Object> map);

    /**
     * 통계 목록 조회 매퍼
     *
     * @param map
     * @return ChartDTO
     */
    List<ChartDTO> selectChartList(Map<String, Object> map);

    /**
     * 업체 개수 조회 매퍼
     *
     * @return int
     */
    int countCompany();

    /**
     * 전체 통계 조회 매퍼
     *
     * @return ChartDTO
     */
    List<ChartDTO> selectChart();

    /**
     * 전체 통계 조회 매퍼
     * (RequestFormBackup)
     * @return ChartDTO
     */
    List<ChartDTO> selectChart2();

    /**
     * 분야 통계 조회 매퍼
     *
     * @return ChartDataDTO
     */
    List<ChartDataDTO> selectCategoryChart();

    /**
     * 분야 통계 조회 매퍼
     * (RequestFormCategoryBackup)
     * @return ChartDataDTO
     */
    List<ChartDataDTO> selectCategoryChart2();

    /**
     * 업체 통계 조회 매퍼
     *
     * @return ChartDTO
     */
    List<ChartDTO> selectCompanyChart(long companysIdx);

    /**
     * 업체 분야 통계 조회 매퍼
     *
     * @return ChartDataDTO
     */
    List<ChartDataDTO> selectCompanyCategoryChart(long companysIdx);

}
