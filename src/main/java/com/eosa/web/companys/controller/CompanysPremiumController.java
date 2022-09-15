package com.eosa.web.companys.controller;

import com.eosa.web.companys.entity.CompanysPremium;
import com.eosa.web.companys.entity.SelectCompanys;
import com.eosa.web.companys.service.CompanysPremiumService;
import com.eosa.web.companys.service.CompanysService;
import com.eosa.web.util.CustomResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/companys")
public class CompanysPremiumController {

    @Autowired private CompanysService companysService;
    @Autowired private CompanysPremiumService companysPremiumService;

    @PostMapping("/insertCompanysPremium")
    public CustomResponseData insertCompanysPremium(
            @RequestParam("companysName") String companysName,
            @RequestParam("companysCeoName") String companysCeoName
    ) {
        CustomResponseData result = new CustomResponseData();

        Long companysIdx = companysService.selectCompanyIdxByComapnysNameAndCompanysCeoName(companysName.trim(), companysCeoName.trim());
//        log.debug("[insertCompanyPremium] companysIdx: {}", String.valueOf(companysIdx));
        if(companysIdx != null) {
            CompanysPremium requestPremium = new CompanysPremium();
            requestPremium.setCompanysIdx(companysIdx);
            CompanysPremium insertData = companysPremiumService.save(requestPremium);

            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(insertData);
            result.setResponseDateTime(LocalDateTime.now());
        }
        else {
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
