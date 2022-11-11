package com.eosa.admin.mapper;

import com.eosa.admin.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReviewBackupMapper {

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
