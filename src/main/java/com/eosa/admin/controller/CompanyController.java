package com.eosa.admin.controller;

import com.eosa.admin.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

}
