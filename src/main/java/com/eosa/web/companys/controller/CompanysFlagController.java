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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/api/companys")
public class CompanysFlagController {

    @Autowired private CompanysService companysService;
    @Autowired private CompanysFlagService companysFlagService;
    @Autowired private CompanysFlagRegionService companysFlagRegionService;
    @Autowired private CompanysFlagCategoryService companysFlagCategoryService;

    
    /** 
     * @param strings
     * @return List<String>
     */
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


    
    /** 
     * 마패 업체 신청
     * @param companysFlag
     * @param companysFlagCategory
     * @return CustomResponseData
     */
    @PostMapping("/insertCompanysFlag")
    public CustomResponseData insertCompanysFlag(
        CompanysFlag companysFlag,
        CompanysFlagCategory companysFlagCategory
    ) {
        CustomResponseData result = new CustomResponseData();
        // log.info("insertCompanysFlag: {}", companysFlag.toString());
        Long companysIdx = companysService.selectCompanyIdxByComapnysNameAndCompanysCeoName(companysFlag.getCompanysName(), companysFlag.getCompanysCeoName());
        if(companysIdx != null) {
            CompanysFlag cf = companysFlagService.selectCompanysFlagByCompanysIdx(companysIdx);
            if(cf == null) {
                companysFlag.setCompanysIdx(companysIdx);
                log.info("[마패업체신청] 마패업체 신청이 가능합니다. 신청자 정보 회사명: {}", companysFlag.getCompanysName());
                // log.info("insertCompanysFlag_Second: {}", companysFlag.toString());
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

                result.setStatusCode(HttpStatus.OK.value());
                result.setResultItem(1);
                result.setResponseDateTime(LocalDateTime.now());
            }
            else {
                log.info("[마패업체신청] 마패업체 신청이 이미 되어있습니다. 신청자 정보 회사명: {}", companysFlag.getCompanysName());
                result.setStatusCode(HttpStatus.OK.value());
                result.setResultItem(-1);
                result.setResponseDateTime(LocalDateTime.now());
            }
        }
        else {
            log.info("[마패업체신청] 마패업체 신청이 불가합니다. 유효하지 않은 업체입니다.");
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(0);
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

    
    /** 
     * @return CustomResponseData
     */
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

    
    /** 
     * @return CustomResponseData
     */
    @GetMapping("/selectCompanysFlagByFilter")
    public CustomResponseData selectCompanysFlagByFilter(
        @RequestParam(value = "companysCategory", required = false, defaultValue = "") List<String> companysCategory,
        @RequestParam(value = "companysRegion1", required = false, defaultValue = "") List<String> companysRegion1
    ) {
        CustomResponseData result = new CustomResponseData();
        List<SelectCompanys> itemList = new ArrayList<>();
        Set<Long> companysIdxSet = new HashSet<>();

        if (companysCategory.size() == 0 && companysRegion1.size() == 0) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(null);
            result.setResponseDateTime(LocalDateTime.now());
        }

        // if (companysCategory.size() == 0 && companysRegion1.size() != 0) {
        //     for (int i = 0; i < companysRegion1.size(); i++) {
        //         companysIdxSet.addAll(companysService.selectCompanysFlagByFilter("", companysRegion1.get(i)));
        //     }
        // }

        if (companysCategory.size() == 0 && companysRegion1.size() != 0) {
            for (int i = 0; i < companysRegion1.size(); i++) {
                companysIdxSet.addAll(companysService.selectCompanysFlagByFilter("", companysRegion1.get(i)));
            }
        }

        if (companysCategory.size() != 0 && companysRegion1.size() == 0) {
            // Set<Long> list = new HashSet<>();
            for (int i = 0; i < companysCategory.size(); i++) {
                companysIdxSet.addAll(companysService.selectCompanysFlagByFilter(companysCategory.get(i), ""));
            }
        }

        if (companysCategory.size() != 0 && companysRegion1.size() != 0) {
            for (int i = 0; i < companysCategory.size(); i++) {
                for (int j = 0; j < companysRegion1.size(); j++) {
                    companysIdxSet.addAll(
                            companysService.selectCompanysFlagByFilter(companysCategory.get(i), companysRegion1.get(j)));
                }
            }
        }

        if (companysCategory.size() != 0 && companysRegion1.size() != 0) {
            for (int i = 0; i < companysCategory.size(); i++) {
                for (int j = 0; j < companysRegion1.size(); j++) {
                    companysIdxSet.addAll(
                            companysService.selectCompanysFlagByFilter(companysCategory.get(i), companysRegion1.get(j)));
                }
            }
        }

        Iterator<Long> iter = companysIdxSet.iterator();
        while (iter.hasNext()) {
            itemList.add(companysService.selectOneCompanysByCompanysIdxTest(iter.next()));
        }

        // List<Long> tempIdx = companysService.selectCompanysByFilter2("가정", "서울",
        // "서초");

        result.setStatusCode(HttpStatus.OK.value());
        result.setResultItem(itemList);
        result.setResponseDateTime(LocalDateTime.now());

        return result;
    }

}
