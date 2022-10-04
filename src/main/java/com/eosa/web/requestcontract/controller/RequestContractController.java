package com.eosa.web.requestcontract.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eosa.web.companys.entity.Companys;
import com.eosa.web.companys.entity.SelectAllCompanysList;
import com.eosa.web.companys.service.CompanysService;
import com.eosa.web.requestcontract.service.RequestContractService;
import com.eosa.web.util.CustomResponseData;

@RestController
@RequestMapping("/api/requestContract")
public class RequestContractController {
    
    @Autowired private RequestContractService requestContractService;
    @Autowired private CompanysService companysService;

    @GetMapping("/selectCompanysByCompanysIdxUseRequestContractController")
    public CustomResponseData selectCompanysByCompanysIdxUseRequestContractController(
        @RequestParam("companysIdx") Long companysIdx
    ) {
        CustomResponseData result = new CustomResponseData();

        SelectAllCompanysList selectResult = companysService.selectCompanysByCompanysIdx(companysIdx);

        if(selectResult != null) {
            Map<String, Object> item = new HashMap<>();
            item.put("companysName", selectResult.getCompanysName());
            item.put("companysAddress", selectResult.getCompanysRegion1() + " " + selectResult.getCompanysRegion3());
            item.put("companysDummyPhone", selectResult.getCompanysDummyPhone());
            item.put("companysRegistCertiDate", selectResult.getCompanysRegistCertiDate());

            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(item);
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
