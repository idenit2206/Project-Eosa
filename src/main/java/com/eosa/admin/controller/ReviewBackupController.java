package com.eosa.admin.controller;

import com.eosa.admin.service.ReviewBackupService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/admin/manage/reviewBackup")
public class ReviewBackupController {

    @Autowired
    private ReviewBackupService reviewBackupService;

    /**
     * 리뷰(백업) 목록 조회 컨트롤러
     *
     * @param model
     * @param sort
     * @param search
     * @param page
     * @return String
     */
    @GetMapping("/list")
    public String reviewList(Model model,
        @RequestParam(defaultValue = "company") String sort,
        @RequestParam(defaultValue = "") String search,
        @RequestParam(defaultValue = "1") int page
    ) {
        log.info("[관리자] 리뷰를 조회합니다.");
        return reviewBackupService.reviewList(model, sort, search, page);
    }

    /**
     * 리뷰 삭제 컨트롤러
     *
     * @param idx
     * @return int
     */
    @ResponseBody
    @PostMapping("/delete")
    public int deleteReview(@RequestParam long idx) {

        return reviewBackupService.deleteReview(idx);
    }

    /**
     * 리뷰 다중 삭제 컨트롤러
     *
     * @param idx
     * @return int
     */
    @ResponseBody
    @PostMapping("/delete/multi")
    public int deleteReviewMulti(@RequestParam String idx) {

        return reviewBackupService.deleteReviewMulti(idx);
    }

}
