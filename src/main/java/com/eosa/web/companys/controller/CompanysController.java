package com.eosa.web.companys.controller;

import java.io.IOException;
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
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eosa.web.companys.entity.Companys;
import com.eosa.web.companys.entity.CompanysActiveRegion;
import com.eosa.web.companys.entity.CompanysCategory;
import com.eosa.web.companys.entity.CompanysLicense;
import com.eosa.web.companys.entity.CompanysMember;
import com.eosa.web.companys.entity.SelectAllCompanysList;
import com.eosa.web.companys.entity.SelectCompanyInfoByUsersIdx;
import com.eosa.web.companys.repository.CompanysActiveRegionRepository;
import com.eosa.web.companys.repository.CompanysCategoryRepository;
import com.eosa.web.companys.repository.CompanysLicenseRepository;
import com.eosa.web.companys.repository.CompanysMemberRepository;
import com.eosa.web.companys.service.CompanysActiveRegionService;
import com.eosa.web.companys.service.CompanysCategoryService;
import com.eosa.web.companys.service.CompanysService;
import com.eosa.web.users.Users;
import com.eosa.web.users.repository.UsersRepository;
import com.eosa.web.util.CustomResponseData;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.oauth2.sdk.ParseException;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.spring.web.json.Json;

@Slf4j
@RestController
@RequestMapping(value="/api/companys")
public class CompanysController {

    @Autowired private CompanysService companysService;
    @Autowired private CompanysLicenseRepository companysLicenseRepository;
    @Autowired private CompanysCategoryService companysCategoryService;
    @Autowired private CompanysActiveRegionService companysActiveRegionService;
    @Autowired private CompanysMemberRepository companysMemberRepository;

    @Autowired private UsersRepository usersRepository;

    @PostMapping("/insertCompanys")
    public CustomResponseData insertCompanys(
      @RequestBody String param
      // MultipartHttpServletRequest req
    ) throws JSONException, ParseException, IOException {
      CustomResponseData result = new CustomResponseData();
      JsonObject jsonObject = (JsonObject) JsonParser.parseString(param).getAsJsonObject();
      // log.debug("param: {}", param.toString());
      // log.debug("jsonObject: {}", jsonObject.toString());

      // log.debug("req1: {}", req.getParameter("companyName"));
      // MultipartFile file1 = req.getFile("companyRegisterCerti");
      // MultipartFile file2 = req.getFile("comapnyProfileImage");
      // MultipartFile file3 = req.getFile("comapnyCerti");
      // // List<MultipartFile> files3 = req.getFiles("companyCerti");
      
      Companys entity = new Companys();
        // @RequestBody String param JSON으로 받는 방식 파일처리 하는방법을 못 찾아서 이 방식은 보류
        entity.setCompanysCeoIdx(Long.parseLong(jsonObject.get("companysCeoIdx").getAsString()));
        entity.setCompanysCeoName(jsonObject.get("companysCeoName").getAsString());
        entity.setCompanysName(jsonObject.get("companyName").getAsString());
        entity.setCompanysComment(jsonObject.get("companyComment").getAsString());
        entity.setCompanysSpec(jsonObject.get("companysSpec").getAsString());
        // entity.setCompanysPhone(jsonObject.get("companysPhone").getAsString());
        entity.setCompanysRegion1(jsonObject.get("companyRegion1").getAsString());
        entity.setCompanysRegion2(jsonObject.get("companyRegion2").getAsString());
        entity.setCompanysRegion3(jsonObject.get("companyRegion3").getAsString());
        // entity.setCompanysRegistCerti(jsonObject.get("companyRegistCerti").getAsString());
        // entity.setCompanysProfileImage(jsonObject.get("companyProfileImage").getAsString());
        entity.setCompanysBankName(jsonObject.get("companyBankName").getAsString());
        entity.setCompanysBankNumber(jsonObject.get("companyBankNumber").getAsString());

        // entity.setCompanysCeoIdx(Long.parseLong(req.getParameter("companysCeoIdx")));
        // entity.setCompanysCeoName(req.getParameter("companysCeoName"));
        // entity.setCompanysName(req.getParameter("companyName"));
        // entity.setCompanysComment(req.getParameter("companyComment"));
        // entity.setCompanysSpec(req.getParameter("companysSpec"));
        // // entity.setCompanysPhone(jsonObject.get("companysPhone").getAsString());
        // entity.setCompanysRegion1(req.getParameter("companyRegion1"));
        // entity.setCompanysRegion2(req.getParameter("companyRegion2"));
        // entity.setCompanysRegion3(req.getParameter("companyRegion3"));
        // entity.setCompanysRegistCerti(file1.getOriginalFilename());
        // if(file2 != null) {
        //   entity.setCompanysProfileImage(file2.getOriginalFilename());
        // }
        // entity.setCompanysBankName(req.getParameter("companyBankName"));
        // entity.setCompanysBankNumber(req.getParameter("companyBankNumber"));        
        
        log.debug("entity: {}", entity.toString());
      Companys step1 = companysService.save(entity);

      CompanysLicense entity2 = new CompanysLicense();
      CompanysCategory entity3 = new CompanysCategory();      
      CompanysActiveRegion entity4 = new CompanysActiveRegion();
      CompanysMember entity5 = new CompanysMember();
      
      // log.debug("step1: {}", step1.toString());
      if(step1 != null) {
        Long companysIdx = step1.getCompanysIdx();

        if(jsonObject.get("companysLicense").getAsJsonArray() != null) {
          JsonArray companyLicenses = jsonObject.get("companysLicense").getAsJsonArray();
          log.debug("companysIdx {} 가 보유중인 자격증명 {}", companysIdx, companyLicenses.toString());
          if(companyLicenses.size() > 0) {
            for(int i = 0; i < companyLicenses.size(); i++) {
              String companysLicenseValue = companyLicenses.get(i).getAsString();
              entity2.setCompanysIdx(companysIdx);
              entity2.setCompanysLicenseName("companysLicenseName");
              entity2.setCompanysLicenseValue(companysLicenseValue);
              entity2.setInsertDate(LocalDateTime.now());
              companysLicenseRepository.insertCompanysLicense(entity2);          
            }
          }
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
          String companysActiveRegion = companysActiveRegions.get(i).getAsString();
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

    @GetMapping("/testInsert")
    public String testInsert() {
      List<Users> usersLists = usersRepository.selectAllDetective();
      List<Users> answer = new ArrayList<>();
      for (Users users : usersLists) {
        log.debug("{} : {}", users.getUsersIdx(), users.getUsersName());
      }
      return "";
    }
    
    @GetMapping("/selectAllCategory")
    public CustomResponseData selectAllCategory() {
      CustomResponseData result = new CustomResponseData();

      List<String> items = companysService.selectAllCategory();

      return result;
    }

    @Secured({"CLIENT", "DETECTIVE"})
    @GetMapping("/selectAllCompanys")
    public CustomResponseData selectAllCompanys() {
      CustomResponseData result = new CustomResponseData();
      List<SelectAllCompanysList> list = companysService.selectAllCompanysList();

      result.setStatusCode(HttpStatus.OK.value());
      result.setResultItem(list);
      result.setResponseDateTime(LocalDateTime.now());
      return result;
    }

    /**
     * usersIdx가 소유한 업체정보를 조회
     * @param param
     * @return
     */
    @GetMapping("/selectCompanyInfoByUsersIdx")
    public CustomResponseData selectCompanyInfoByUsersIdx(
      @RequestParam("usersIdx") String param
    ){
      CustomResponseData result = new CustomResponseData();
      Long usersIdx = Long.parseLong(param);
      log.debug("usersIdx: {}의  Companys 정보를 조회합니다", usersIdx);
      SelectCompanyInfoByUsersIdx info = companysService.selectCompanyInfoByUsersIdx(usersIdx);
      if(info == null) {
        log.info("usersIdx: {} 는 소속된 업체가 없습니다.");
        result.setStatusCode(HttpStatus.OK.value());
        result.setResultItem(null);
        result.setResponseDateTime(LocalDateTime.now());
      }
      else {
        result.setStatusCode(HttpStatus.OK.value());
        result.setResultItem(info);
        result.setResponseDateTime(LocalDateTime.now());
      }
      return result;
    }

}
