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
     * 안심번호 등록 컨트롤러
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

}
