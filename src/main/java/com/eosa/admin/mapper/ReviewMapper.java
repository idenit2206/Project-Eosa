package com.eosa.admin.mapper;

import com.eosa.admin.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * packageName    : com.eosa.admin.mapper
 * fileName       : ReviewMapper
 * author         : Jihun Kim
 * date           : 2022-09-20
 * description    : 리뷰 매퍼
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-20        Jihun Kim       최초 생성
 */
@Mapper
public interface ReviewMapper {

    /**
     * 리뷰 목록 개수 조회 매퍼
     *
     * @param map
     * @return int
     */
    int countReviewList(Map<String, Object> map);

    /**
     * 리뷰 목록 조회 매퍼
     *
     * @param map
     * @return ReviewDTO
     */
    List<ReviewDTO> selectReviewList(Map<String, Object> map);

    /**
     * 리뷰 삭제 매퍼
     *
     * @param idx
     * @return int
     */
    int deleteReview(long idx);

    /**
     * 리뷰 다중 삭제 매퍼
     *
     * @param arr
     * @return int
     */
    int deleteReviewMulti(String[] arr);

}
