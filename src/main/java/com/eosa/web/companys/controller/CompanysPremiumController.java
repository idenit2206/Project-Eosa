package com.eosa.web.companys.controller;

import com.eosa.web.companys.entity.CompanysPremium;
import com.eosa.web.companys.service.CompanysPremiumService;
import com.eosa.web.companys.service.CompanysService;
import com.eosa.web.util.CustomResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

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

}
