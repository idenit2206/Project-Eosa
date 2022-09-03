package com.eosa.web.companys.controller;

import com.eosa.web.companys.entity.CompanysFlag;
import com.eosa.web.companys.entity.CompanysFlagCategory;
import com.eosa.web.companys.entity.CompanysFlagRegion;
import com.eosa.web.companys.repository.CompanysFlagRegionRepository;
import com.eosa.web.companys.service.CompanysFlagCategoryService;
import com.eosa.web.companys.service.CompanysFlagRegionService;
import com.eosa.web.companys.service.CompanysFlagService;
import com.eosa.web.companys.service.CompanysService;
import com.eosa.web.util.CustomResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
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


    @PostMapping("/insertCompanysFlag")
    public CustomResponseData insertCompanysFlag(
            @RequestPart("CompanysFlag") CompanysFlag companysFlag,
//            @RequestPart("CompanysFlagRegion") List<CompanysFlagRegion> companysFlagRegion,
//            @RequestPart("CompanysFlagCategory") CompanysFlagCategory companysFlagCategory

    ) {
        CustomResponseData result = new CustomResponseData();

        CompanysFlag insertCompanysFlag = companysFlagService.save(companysFlag);
//        if(insertCompanysFlag != null) {
//            for(int i = 0; i < companysFlagRegion.size(); i++) {}
//            CompanysFlagRegion insertFlagRegion = companysFlagRegion.save()
//        }


        return result;
    }

}
