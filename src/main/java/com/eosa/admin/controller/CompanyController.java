package com.eosa.admin.controller;

import com.eosa.admin.dto.CompanysDTO;
import com.eosa.admin.encode.SHA256;
import com.eosa.admin.service.CompanyService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
     * 업체 프리미엄 신청 컨트롤러
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
     * 업체 프리미엄 등록 컨트롤러
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
     * 업체 프리미엄 해지 컨트롤러
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
     * 프리미엄 목록 조회 컨트롤러
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

}
