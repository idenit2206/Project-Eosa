package com.eosa.admin.controller;

import com.eosa.admin.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * packageName    : com.eosa.admin.controller
 * fileName       : ReportController
 * author         : Jihun Kim
 * date           : 2022-09-21
 * description    : 신고 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-21        Jihun Kim       최초 생성
 */
@Controller
@RequestMapping("/admin/manage/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 신고 목록 조회 컨트롤러
     *
     * @param model
     * @param state
     * @param page
     * @return String
     */
    @GetMapping("/list")
    public String reportList(Model model,
                             @RequestParam(required = false) String state,
                             @RequestParam(defaultValue = "1") int page) {

        return reportService.reportList(model, state, page);
    }

    /**
     * 신고 삭제 컨트롤러
     *
     * @param idx
     * @return int
     */
    @ResponseBody
    @PostMapping("/delete")
    public int deleteReport(@RequestParam long idx) {

        return reportService.deleteReport(idx);
    }

    /**
     * 신고 다중 삭제 컨트롤러
     *
     * @param idx
     * @return int
     */
    @ResponseBody
    @PostMapping("/delete/multi")
    public int deleteReviewMulti(@RequestParam String idx) {

        return reportService.deleteReviewMulti(idx);
    }

}
