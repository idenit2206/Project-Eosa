package com.eosa.admin.controller;

import com.eosa.admin.dto.CompanysDTO;
import com.eosa.admin.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

/**
 * packageName    : com.eosa.admin.controller
 * fileName       : CompanyController
 * author         : Jihun Kim
 * date           : 2022-09-15
 * description    : 업체 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-15        Jihun Kim       최초 생성
 */
@Controller
@RequestMapping("/admin/manage/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    /**
     * 업체 목록 조회 컨트롤러
     *
     * @param model
     * @param enabled
     * @param sort
     * @param search
     * @param page
     * @return String
     */
    @GetMapping("/list")
    public String companyList(Model model,
                              @RequestParam(defaultValue = "1") int enabled,
                              @RequestParam(defaultValue = "name") String sort,
                              @RequestParam(defaultValue = "") String search,
                              @RequestParam(defaultValue = "1") int page) {

        return companyService.companyList(model, enabled, sort, search, page);
    }

    /**
     * 업체 목록에서 업체 삭제하기
     * @param model
     * @param companysIdx
     * @return
     */
    @PutMapping("/listDelete")
    public String companysListDelete(
        @RequestParam List<Long> listCheckValueList,
        @RequestParam(defaultValue = "1") int enabled,
        @RequestParam(defaultValue = "name") String sort,
        @RequestParam(defaultValue = "") String search,
        @RequestParam(defaultValue = "1") int page,
        Model model
    ) {
        return companyService.companysListDelete(listCheckValueList, enabled, sort, search, page, model);
    }

    /**
     * 업체 상세 조회 컨트롤러
     *
     * @param model
     * @param companysIdx
     * @return String
     */
    @GetMapping("/details")
    public String companyDetails(Model model, @RequestParam long companysIdx) {
        return companyService.companyDetails(model, companysIdx);
    }

    /**
     *  업체 상세 조회 화면에서 업체 정보 삭제하기
     * @param companysIdx
     * @return
     */
    @PutMapping("/delete")
    @ResponseBody
    public int deleteCompanys(
        Long companysIdx
    ) {
        return companyService.deleteCompanys(companysIdx);
    }

    /**
     * 업체 수정 컨트롤러
     *
     * @param companysDTO
     * @return int
     */
    @ResponseBody
    @PostMapping("/update")
    public int updateCompanys(CompanysDTO companysDTO) {

        return companyService.updateCompany(companysDTO);
    }

    /**
     * 안심번호 추출 컨트롤러
     *
     * @return String
     * @throws NoSuchAlgorithmException
     */
    @ResponseBody
    @PostMapping("/safety")
    public String safetyNumber() throws NoSuchAlgorithmException {

        return companyService.safetyNumber();
    }

    /**
     * 안심번호 등록/삭제 컨트롤러
     *
     * @param companysDTO
     * @return int
     * @throws NoSuchAlgorithmException
     */
    @ResponseBody
    @PostMapping("/safety/mapping")
    public int safetyMapping(CompanysDTO companysDTO) throws NoSuchAlgorithmException {

        return companyService.safetyMapping(companysDTO);
    }

    /**
     * 업체 인증 컨트롤러
     *
     * @param companysIdx
     * @param sort
     * @param num
     * @return
     */
    @ResponseBody
    @PostMapping("/check")
    public int updateCheck(@RequestParam long companysIdx, @RequestParam int sort, @RequestParam int num) {

        return companyService.updateCheck(companysIdx, sort, num);
    }

    /**
     * 업체 제휴협회 신청 컨트롤러
     *
     * @param companysDTO
     * @return int
     */
    @ResponseBody
    @PostMapping("/premium/request")
    public int requestPremium(CompanysDTO companysDTO) {

        return companyService.requestPremium(companysDTO);
    }

    /**
     * 업체 제휴협회 등록 컨트롤러
     *
     * @param companysDTO
     * @return int
     */
    @ResponseBody
    @PostMapping("/premium/approval")
    public int approvalPremium(CompanysDTO companysDTO) {

        return companyService.approvalPremium(companysDTO);
    }

    /**
     * 업체 제휴협회 해지 컨트롤러
     *
     * @param companysIdx
     * @return int
     */
    @ResponseBody
    @PostMapping("/premium/cancel")
    public int cancelPremium(@RequestParam long companysIdx) {

        return companyService.cancelPremium(companysIdx);
    }
    
    /**
     * 업체 제휴협회 삭제 컨트롤러
     * @param idx
     * @return
     */
    @ResponseBody
    @PostMapping("/premium/delete")
    public int deletePremium(@RequestParam long idx) {
        return companyService.deletePremium(idx);
    }

    /**
     * 업체 마패 신청 컨트롤러
     *
     * @param companysDTO
     * @return int
     */
    @ResponseBody
    @PostMapping("/flag/request")
    public int requestFlag(CompanysDTO companysDTO) {
        return companyService.requestFlag(companysDTO);
    }

    /**
     * 업체 마패 등록 컨트롤러
     *
     * @param companysDTO
     * @return int
     */
    @ResponseBody
    @PostMapping("/flag/approval")
    public int approvalFlag(CompanysDTO companysDTO) {

        return companyService.approvalFlag(companysDTO);
    }

    /**
     * 업체 마패 수정 컨트롤러
     *
     * @param companysDTO
     * @return int
     */
    @ResponseBody
    @PostMapping("/flag/update")
    public int updateFlag(CompanysDTO companysDTO) {
        return companyService.updateFlag(companysDTO);
    }

    /**
     * 업체 마패 해지 컨트롤러
     *
     * @param companysIdx
     * @return int
     */
    @ResponseBody
    @PostMapping("/flag/cancel")
    public int cancelFlag(@RequestParam long companysIdx) {

        return companyService.cancelFlag(companysIdx);
    }

    /**
     * 업체 마패 삭제 컨트롤러
     * @param companysFlagIdx
     * @return
     */
    @ResponseBody
    @PostMapping("/flag/delete")
    public int deleteFlag(@RequestParam long companysFlagIdx) {
        return companyService.deleteFlag(companysFlagIdx);
    }

    /**
     * 제휴협회 목록 조회 컨트롤러
     *
     * @param model
     * @param enabled
     * @param page
     * @return String
     */
    @GetMapping("/premium")
    public String premiumList(Model model,
                              @RequestParam(required = false) String enabled,
                              @RequestParam(defaultValue = "1") int page) {

        return companyService.premiumList(model, enabled, page);
    }

    /**
     * 마패 목록 조회 컨트롤러
     *
     * @param model
     * @param enabled
     * @param page
     * @return String
     */
    @GetMapping("/flag")
    public String flagList(Model model,
                           @RequestParam(required = false) String enabled,
                           @RequestParam(defaultValue = "1") int page) {

        return companyService.flagList(model, enabled, page);
    }

    /**
     * 업체 리뷰 조회 컨트롤러
     *
     * @param model
     * @param companysIdx
     * @param page
     * @return String
     */
    @GetMapping("/review")
    public String companyReview(Model model,
                                @RequestParam long companysIdx,
                                @RequestParam(defaultValue = "1") int page) {

        return companyService.companyReview(model, companysIdx, page);
    }

    /**
     * 업체 신고 조회 컨트롤러
     *
     * @param model
     * @param companysIdx
     * @param state
     * @param page
     * @return String
     */
    @GetMapping("/report")
    public String companyReport(Model model,
                                @RequestParam long companysIdx,
                                @RequestParam(required = false) String state,
                                @RequestParam(defaultValue = "1") int page) {

        return companyService.companyReport(model, companysIdx, state, page);
    }

    /**
     * 통계 목록 조회 컨트롤러
     *
     * @param model
     * @param sort
     * @param search
     * @param page
     * @return String
     */
    @GetMapping("/chart/list")
    public String chartList(Model model,
                            @RequestParam(defaultValue = "company") String sort,
                            @RequestParam(defaultValue = "") String search,
                            @RequestParam(defaultValue = "1") int page) {

        return companyService.chartList(model, sort, search, page);
    }

    /**
     * 전체 통계 조회 컨트롤러
     *
     * @param model
     * @return String
     */
    @GetMapping("/chart/whole")
    public String wholeChart(Model model) {

        return companyService.wholeChart(model);
    }

    /**
     * 통계 데이터 조회 컨트롤러
     *
     * @return Map
     */
    @ResponseBody
    @PostMapping("/chart/whole/data")
    public Map<String, Object> chartData(@RequestParam String sort, @RequestParam long companysIdx) {

        return companyService.chartData(sort, companysIdx);
    }

    /**
     * 업체 통계 조회 컨트롤러
     *
     * @param model
     * @param companysIdx
     * @return String
     */
    @GetMapping("/chart")
    public String chart(Model model, @RequestParam long companysIdx) {

        return companyService.chart(model, companysIdx);
    }

}
