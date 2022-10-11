package com.eosa.web.companys.controller;

import com.eosa.web.companys.entity.CompanysPremium;
import com.eosa.web.companys.entity.SelectCompanys;
import com.eosa.web.companys.service.CompanysPremiumService;
import com.eosa.web.companys.service.CompanysService;
import com.eosa.web.util.CustomResponseData;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/companys")
public class CompanysPremiumController {

    @Autowired private CompanysService companysService;
    @Autowired private CompanysPremiumService companysPremiumService;

    
    /** 
     * @return CustomResponseData
     */
    @PostMapping("/insertCompanysPremium")
    public CustomResponseData insertCompanysPremium(
            @RequestParam("companysIdx") Long companysIdx,
            @RequestParam("companysName") String companysName,
            @RequestParam("companysCeoName") String companysCeoName
    ) {
        CustomResponseData result = new CustomResponseData();
//        Parameter 조건에 해당하는 Companys가 DB에 존재하는지 확인
        Long searchCompanysIdx = companysService.selectCompanyIdxByComapnysNameAndCompanysCeoName(companysName.trim(), companysCeoName.trim());
        if(searchCompanysIdx != null) {
            log.info("[insertCompanyPremium] Premium 신청이 가능합니다. companysIdx: {}", String.valueOf(searchCompanysIdx));
            CompanysPremium entity = new CompanysPremium();
            entity.setCompanysName(companysName);
            entity.setCompanysCeoName(companysCeoName);
            CompanysPremium insertData = companysPremiumService.save(entity);

            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(insertData);
            result.setResponseDateTime(LocalDateTime.now());
        }
        else {
            log.info("[insertCompanyPremium] Premium 신청이 불가합니다.");
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(null);
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

    /**
     * companysPremium이 True인 업체를 전부 출력 == Premium업체 조회
     * @return
     */
    @GetMapping("/selectAllCompanysPremium")
    public CustomResponseData selectAllCompanysPremium() {
        CustomResponseData result = new CustomResponseData();

        List<SelectCompanys> items = companysPremiumService.selectAllCompanysPremium();

        if(items != null) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(items);
            result.setResponseDateTime(LocalDateTime.now());
        }
        else {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(null);
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

}
