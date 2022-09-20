package com.eosa.admin.service;

import com.eosa.admin.dto.ReviewDTO;
import com.eosa.admin.mapper.ReviewMapper;
import com.eosa.admin.pagination.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName    : com.eosa.admin.service
 * fileName       : ReviewMapper
 * author         : Jihun Kim
 * date           : 2022-09-20
 * description    : 리뷰 서비스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-20        Jihun Kim       최초 생성
 */
@Service
public class ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    /**
     * 리뷰 목록 조회 서비스
     *
     * @param model
     * @param sort
     * @param search
     * @param page
     * @return String
     */
    public String reviewList(Model model, String sort, String search, int page) {

        Map<String, Object> map = new HashMap<>();

        // 필터 검색 조건
        if (search != "" && !search.equals("")) {
            if (sort.equals("company")) {
                map.put("company", sort);
            } else if (sort.equals("writer")) {
                map.put("writer", sort);
            }
            map.put("search", search);
        }

        int count = reviewMapper.countReviewList(map);

        Pagination pagination = new Pagination(count, page);

        map.put("startIndex", pagination.getStartIndex());
        map.put("pageSize", pagination.getPageSize());

        List<ReviewDTO> list = reviewMapper.selectReviewList(map);

        model.addAttribute("reviewList", list);
        model.addAttribute("pagination", pagination);
        model.addAttribute("count", count);
        model.addAttribute("sort", sort);
        model.addAttribute("search", search);

        return "admin/board/review/list";
    }

    /**
     * 리뷰 삭제 서비스
     *
     * @param idx
     * @return int
     */
    public int deleteReview(long idx) {

        return reviewMapper.deleteReview(idx);
    }

    /**
     * 리뷰 다중 삭제 서비스
     *
     * @param idx
     * @return int
     */
    public int deleteReviewMulti(String idx) {

        String arr[] = idx.split(",");

        return reviewMapper.deleteReviewMulti(arr);
    }

}
