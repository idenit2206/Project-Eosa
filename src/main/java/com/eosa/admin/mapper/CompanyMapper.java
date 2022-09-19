package com.eosa.admin.mapper;

import com.eosa.admin.dto.CompanysDTO;
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

}