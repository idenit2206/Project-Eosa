package com.eosa.admin.service;

import com.eosa.admin.dto.ReportDTO;
import com.eosa.admin.mapper.ReportMapper;
import com.eosa.admin.pagination.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName    : com.eosa.admin.service
 * fileName       : ReportService
 * author         : Jihun Kim
 * date           : 2022-09-21
 * description    : 신고 서비스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-21        Jihun Kim       최초 생성
 */
@Service
public class ReportService {

    @Autowired
    private ReportMapper reportMapper;

    /**
     * 신고 목록 조회 서비스
     *
     * @param model
     * @param state
     * @param page
     * @return String
     */
    public String reportList(Model model, String state, int page) {

        Map<String, Object> map = new HashMap<>();

        if (state != null) {
            if (!state.equals("")) {
                map.put("state", state);
            }
        }

        int count = reportMapper.countReportList(map);

        Pagination pagination = new Pagination(count, page);

        map.put("startIndex", pagination.getStartIndex());
        map.put("pageSize", pagination.getPageSize());

        List<ReportDTO> list = reportMapper.selectReportList(map);

        model.addAttribute("reportList", list);
        model.addAttribute("pagination", pagination);
        model.addAttribute("count", count);
        model.addAttribute("state", state);

        return "admin/board/report/list";
    }

    
    /** 
     * @param reportDTO
     * @return int
     */
    public int reportUpdate(ReportDTO reportDTO) {
        return reportMapper.reportUpdate(reportDTO);
    }

    /**
     * 신고 삭제 서비스
     *
     * @param idx
     * @return int
     */
    public int deleteReport(long idx) {

        return reportMapper.deleteReport(idx);
    }

    /**
     * 신고 다중 삭제 서비스
     *
     * @param idx
     * @return int
     */
    public int deleteReviewMulti(String idx) {

        String arr[] = idx.split(",");

        return reportMapper.deleteReportMulti(arr);
    }

}
