package com.eosa.web.companys.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eosa.web.companys.entity.Companys;
import com.eosa.web.companys.entity.CompanysActiveRegion;
import com.eosa.web.companys.entity.CompanysCategory;
import com.eosa.web.companys.entity.CompanysLicense;
import com.eosa.web.companys.entity.CompanysMember;
import com.eosa.web.companys.entity.SelectAllCompanysList;
import com.eosa.web.companys.repository.CompanysActiveRegionRepository;
import com.eosa.web.companys.repository.CompanysCategoryRepository;
import com.eosa.web.companys.repository.CompanysLicenseRepository;
import com.eosa.web.companys.repository.CompanysMemberRepository;
import com.eosa.web.companys.service.CompanysActiveRegionService;
import com.eosa.web.companys.service.CompanysCategoryService;
import com.eosa.web.companys.service.CompanysService;
import com.eosa.web.util.CustomResponseData;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.oauth2.sdk.ParseException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value="/api/companys")
public class CompanysController {

    private Logger logger = LoggerFactory.getLogger(CompanysController.class);

    @Autowired private CompanysService companysService;
    @Autowired private CompanysLicenseRepository companysLicenseRepository;
    @Autowired private CompanysCategoryService companysCategoryService;
    @Autowired private CompanysActiveRegionService companysActiveRegionService;
    @Autowired private CompanysMemberRepository companysMemberRepository;

    @PostMapping("/insertCompanys")
    public CustomResponseData insertCompanys(
      @RequestBody String param
    ) throws JSONException, ParseException {
      CustomResponseData result = new CustomResponseData();
      JsonObject jsonObject = (JsonObject) JsonParser.parseString(param).getAsJsonObject();
      // log.debug("param: {}", jsonObject.toString());
      
      Companys entity = new Companys();
        entity.setCompanysCeoIdx(jsonObject.get("companysCeoIdx").getAsLong());
        entity.setCompanysCeoName(jsonObject.get("companysCeoName").getAsString());
        entity.setCompanysName(jsonObject.get("companysName").getAsString());
        entity.setCompanysComment(jsonObject.get("companysComment").getAsString());
        entity.setCompanysSpec(jsonObject.get("companysSpec").getAsString());
        // entity.setCompanysPhone(jsonObject.get("companysPhone").getAsString());
        entity.setCompanysRegion1(jsonObject.get("companysRegion1").getAsString());
        entity.setCompanysRegion2(jsonObject.get("companysRegion2").getAsString());
        entity.setCompanysRegion3(jsonObject.get("companysRegion3").getAsString());
        entity.setCompanysRegistCerti(jsonObject.get("companysRegistCerti").getAsString());
        entity.setCompanysProfileImage(jsonObject.get("companysProfileImage").getAsString());
        entity.setCompanysBankName(jsonObject.get("companysBankName").getAsString());
        entity.setCompanysBankNumber(jsonObject.get("companysBankNumber").getAsString());
        
      Companys step1 = companysService.save(entity);

      CompanysLicense entity2 = new CompanysLicense();
      CompanysCategory entity3 = new CompanysCategory();      
      CompanysActiveRegion entity4 = new CompanysActiveRegion();
      CompanysMember entity5 = new CompanysMember();
      
      log.debug("step1: {}", step1.toString());
      if(step1 != null) {
        Long companysIdx = step1.getCompanysIdx();

        JsonArray companyLicenses = jsonObject.get("companysLicense").getAsJsonArray();
        log.debug("companysIdx {} 가 보유중인 자격증명 {}", companysIdx, companyLicenses.toString());
        for(int i = 0; i < companyLicenses.size(); i++) {
          String companysLicenseValue = companyLicenses.get(i).getAsString();
          entity2.setCompanysIdx(companysIdx);
          entity2.setCompanysLicenseName("companysLicenseName");
          entity2.setCompanysLicenseValue(companysLicenseValue);
          entity2.setInsertDate(LocalDateTime.now());
          companysLicenseRepository.insertCompanysLicense(entity2);          
        }

        JsonArray companysCategoryValues = jsonObject.get("companysCategory").getAsJsonArray();
        log.debug("companysIdx {} 의 활동 분야 {}",companysIdx, companysCategoryValues.toString());
        for(int i = 0; i < companysCategoryValues.size(); i++) {
          String companysCategoryValue = companysCategoryValues.get(i).getAsString();
          // log.debug("companysIdx: {} 의 활동 분야 {}", companysIdx, companysCategoryValue);
          entity3.setCompanysIdx(companysIdx);
          entity3.setCompanysCategoryValue(companysCategoryValue);
          // log.debug("entity3: {}", entity3.toString());
          companysCategoryService.insertCompanysCategory(entity3);
        }

        JsonArray companysActiveRegions = jsonObject.get("companysActiveRegion").getAsJsonArray();
        log.debug("companysIdx {} 의 활동 지역 {}",companysIdx, companysActiveRegions.toString());
        for(int i = 0; i < companysActiveRegions.size(); i++) {
          String companysActiveRegion = companysActiveRegions.get(i).toString();
          entity4.setCompanysIdx(companysIdx);
          entity4.setActiveRegion(companysActiveRegion);
          companysActiveRegionService.insertCompanysActiveRegion(entity4);
        }

        entity5.setUsersIdx(step1.getCompanysCeoIdx());
        entity5.setCompanysIdx(companysIdx);
        entity5.setStatusValue(1);
        entity5.setRegistDate(LocalDateTime.now());
        companysMemberRepository.save(entity5);

        result.setStatusCode(HttpStatus.OK.value());
        result.setResultItem("resultItem");
        result.setResponseDateTime(LocalDateTime.now());
      }
      return result;
    }
    
    @GetMapping("/selectAllCategory")
    public CustomResponseData selectAllCategory() {
      CustomResponseData result = new CustomResponseData();

      List<String> items = companysService.selectAllCategory();

      return result;
    }

    @GetMapping("/selectAllCompanys")
    public CustomResponseData selectAllCompanys() {
      CustomResponseData result = new CustomResponseData();
      List<SelectAllCompanysList> list = companysService.selectAllCompanysList();

      result.setStatusCode(HttpStatus.OK.value());
      result.setResultItem(list);
      result.setResponseDateTime(LocalDateTime.now());
      return result;
    }

}
