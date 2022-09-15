package com.eosa.web.companys.controller;

import com.eosa.web.companys.entity.CompanysFlag;
import com.eosa.web.companys.entity.CompanysFlagCategory;
import com.eosa.web.companys.entity.CompanysFlagRegion;
import com.eosa.web.companys.entity.SelectCompanys;
import com.eosa.web.companys.repository.CompanysFlagRegionRepository;
import com.eosa.web.companys.service.CompanysFlagCategoryService;
import com.eosa.web.companys.service.CompanysFlagRegionService;
import com.eosa.web.companys.service.CompanysFlagService;
import com.eosa.web.companys.service.CompanysService;
import com.eosa.web.util.CustomResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/companys")
public class CompanysFlagController {

    @Autowired private CompanysService companysService;
    @Autowired private CompanysFlagService companysFlagService;
    @Autowired private CompanysFlagRegionService companysFlagRegionService;
    @Autowired private CompanysFlagCategoryService companysFlagCategoryService;

    public List<String> stringsToArray (String strings) {
        List<String> result = new ArrayList<>();
        int count = 0;

        for(int i = 0; i < strings.length(); i++) {
            if(strings.charAt(i) == ',') {
                count++;
            }
        }

        if(count > 0) {
            for(int i = 0; i <= count; i++) {
                result.add(strings.split(",")[i]);
            }
        }
        else {
            result.add(strings.trim());
        }


        return result;
    }


    @PostMapping("/insertCompanysFlag")
    public CustomResponseData insertCompanysFlag(
        CompanysFlag companysFlag,
        CompanysFlagCategory companysFlagCategory
    ) {
        CustomResponseData result = new CustomResponseData();

        List<String> flagRegion1s = stringsToArray(companysFlag.getFlagRegion1());
        List<String> flagRegion2s = stringsToArray(companysFlag.getFlagRegion2());
        List<String> companysFlagCategorys =  stringsToArray(companysFlagCategory.getCompanysFlagCategory());

        CompanysFlag insertCompanysFlag = companysFlagService.save(companysFlag);
        int insertFlagRegionResult = 0;
        int insertFlagCategoryResult = 0;

        while(flagRegion2s.size() != flagRegion1s.size()) {
            flagRegion2s.add("null");
        }

        for(int i = 0; i < flagRegion1s.size(); i++) {
            CompanysFlagRegion cfr = new CompanysFlagRegion();
            cfr.setCompanysFlagIdx(insertCompanysFlag.getCompanysFlagIdx());
            cfr.setCompanysFlagRegion1(flagRegion1s.get(i).trim());
            if(flagRegion2s.get(i) == null) {
                cfr.setCompanysFlagRegion2(null);
            }
            else {
                cfr.setCompanysFlagRegion2(flagRegion2s.get(i).trim());
            }
            CompanysFlagRegion insertFlagRegion = companysFlagRegionService.save(cfr);
            if(insertFlagRegion != null) {
                insertFlagRegionResult = 1;
            }
        }

        for(int i = 0; i < companysFlagCategorys.size(); i++) {
            CompanysFlagCategory cfc = new CompanysFlagCategory();
            cfc.setCompanysFlagIdx(insertCompanysFlag.getCompanysFlagIdx());
            cfc.setCompanysFlagCategory(companysFlagCategorys.get(i));
            CompanysFlagCategory insertFlagCategory = companysFlagCategoryService.save(cfc);
            if(insertFlagCategory != null) {
                insertFlagCategoryResult = 1;
            }
        }

        if(insertCompanysFlag != null && insertFlagRegionResult == 1 && insertFlagCategoryResult == 1) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem("TRUE");
            result.setResponseDateTime(LocalDateTime.now());
        }
        else {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem("FALSE");
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

    @GetMapping("/selectAllCompanysFlag")
    public CustomResponseData selectAllCompanysFlag() {
        CustomResponseData result = new CustomResponseData();
        List<SelectCompanys> items = companysFlagService.selectAllCompanysFlag();

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
