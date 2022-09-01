package com.eosa.web.companys.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.eosa.web.companys.service.CompanysMemberService;
import com.eosa.web.util.file.AwsS3Service;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

import javax.swing.filechooser.FileSystemView;

@Slf4j
@RestController
@RequestMapping(value="/api/companys")
public class CompanysController {

    @Autowired private CompanysService companysService;
    @Autowired private CompanysLicenseRepository companysLicenseRepository;
    @Autowired private CompanysCategoryService companysCategoryService;
    @Autowired private CompanysActiveRegionService companysActiveRegionService;
    @Autowired private CompanysMemberService companysMemberService;
    @Autowired private UsersRepository usersRepository;
    @Autowired private AwsS3Service awsS3Service;

    @PostMapping("/insertCompanys")
    public CustomResponseData insertCompanys(
//      @RequestBody String param
        @RequestPart("companyInfo") Companys params,
        @RequestPart("companysCategory") List<String> companysCategory,
        @RequestPart("companysActiveRegion") List<String> companysActiveRegions,
        @RequestPart(value = "companysRegistCerti", required = false) MultipartFile file1,
        @RequestPart(value = "companysLicense", required = false) List<MultipartFile> file2,
        @RequestPart(value = "companysProfileImage", required = false) MultipartFile file3
    ) throws JSONException, ParseException, IOException {
      CustomResponseData result = new CustomResponseData();
//        JsonObject jsonObject = (JsonObject) JsonParser.parseString(param).getAsJsonObject();
//        log.debug("param: {}", param.toString());
//        log.debug("jsonObject: {}", jsonObject.toString());
          log.debug("params: {}", params.toString());
          log.debug("{}, {}", companysCategory.toString(), companysActiveRegions.toString());
          log.debug("file1: {}", file1.getOriginalFilename());
//          log.debug("file3: {}", file3.getOriginalFilename());

      Companys entity = new Companys();
//        // @RequestBody String param JSON으로 받는 방식 파일처리 하는방법을 못 찾아서 이 방식은 보류
//        entity.setCompanysCeoIdx(Long.parseLong(jsonObject.get("companysCeoIdx").getAsString()));
//        entity.setCompanysCeoName(jsonObject.get("companysCeoName").getAsString());
//        entity.setCompanysName(jsonObject.get("companyName").getAsString());
//        entity.setCompanysComment(jsonObject.get("companyComment").getAsString());
//        entity.setCompanysSpec(jsonObject.get("companysSpec").getAsString());
//        // entity.setCompanysPhone(jsonObject.get("companysPhone").getAsString());
//        entity.setCompanysRegion1(jsonObject.get("companyRegion1").getAsString());
//        entity.setCompanysRegion2(jsonObject.get("companyRegion2").getAsString());
//        entity.setCompanysRegion3(jsonObject.get("companyRegion3").getAsString());
//        // entity.setCompanysRegistCerti(jsonObject.get("companyRegistCerti").getAsString());
//        // entity.setCompanysProfileImage(jsonObject.get("companyProfileImage").getAsString());
//        entity.setCompanysBankName(jsonObject.get("companyBankName").getAsString());
//        entity.setCompanysBankNumber(jsonObject.get("companyBankNumber").getAsString());
//        log.debug("companysCategory: {}", jsonObject.get("companyCategory").getAsJsonArray().toString());
//        log.debug("companysActiveRegion: {}", jsonObject.get("companyActiveRegion").getAsJsonArray().toString());
//        log.debug("companyRegistCerti: {}", jsonObject.get("companyRegistCerti").getAsJsonObject().toString());
//        log.debug("companyLicense: {}", jsonObject.get("companyCerti").getAsJsonObject().toString());
//        log.debug("companyProfileImage: {}", jsonObject.get("companyProfileImage").getAsJsonObject().toString());

            entity.setCompanysName(params.getCompanysName());
            entity.setCompanysCeoName(params.getCompanysCeoName());
            entity.setCompanysCeoIdx(params.getCompanysCeoIdx());
            entity.setCompanysComment(params.getCompanysComment());
            entity.setCompanysSpec(params.getCompanysSpec());
            entity.setCompanysRegion1(params.getCompanysRegion1());
            entity.setCompanysRegion2(params.getCompanysRegion2());
            entity.setCompanysRegion3(params.getCompanysRegion3());
            entity.setCompanysBankName(params.getCompanysBankName());
            entity.setCompanysBankNumber(params.getCompanysBankNumber());
            log.debug("entity: {}", entity.toString());

      Companys step1 = companysService.save(entity);
      log.debug("step1: {}", step1.toString());

      if(file3 != null) {
          String file1Name = awsS3Service.uploadSingleFile(file1,"registcerti", step1.getCompanysIdx());
          String file3Name = awsS3Service.uploadSingleFile(file3, "profileimage", step1.getCompanysIdx());
          int step1b = companysService.updateRegistCertiAndProfileImage(step1.getCompanysIdx(), file1Name, file3Name);
      }
      if(file3 == null) {
          log.debug("{}, {}", file1.getOriginalFilename(), step1.getCompanysIdx());
          String file1Name = awsS3Service.uploadSingleFile(file1,"registcerti", step1.getCompanysIdx());
          int step1b = companysService.updateRegistCerti(step1.getCompanysIdx(), file1Name);
      }

      CompanysLicense entity2 = new CompanysLicense();
      CompanysCategory entity3 = new CompanysCategory();
      CompanysActiveRegion entity4 = new CompanysActiveRegion();
      CompanysMember entity5 = new CompanysMember();

       log.debug("step1: {}", step1.toString());
      if(step1 != null) {
        Long companysIdx = step1.getCompanysIdx();
        if(file2 != null) {
          log.debug("companysIdx {} 가 보유중인 자격증명 {}", companysIdx, file2.toString());
            for(int i = 0; i < file2.size(); i++) {
              String fileName = awsS3Service.uploadSingleFile(file2.get(i), "license", companysIdx);
              entity2.setCompanysIdx(companysIdx);
              entity2.setCompanysLicenseName("companysLicenseName");
              entity2.setCompanysLicenseValue(fileName);
              entity2.setInsertDate(LocalDateTime.now());
              companysLicenseRepository.insertCompanysLicense(entity2);
            }
          }

        log.debug("companysIdx {} 의 활동 분야 {}",companysIdx, companysCategory.toString());
        for(int i = 0; i < companysCategory.size(); i++) {
          String companysCategoryValue = companysCategory.get(i);
          // log.debug("companysIdx: {} 의 활동 분야 {}", companysIdx, companysCategoryValue);
          entity3.setCompanysIdx(companysIdx);
          entity3.setCompanysCategoryValue(companysCategoryValue);
          // log.debug("entity3: {}", entity3.toString());
          companysCategoryService.insertCompanysCategory(entity3);
        }
        log.debug("companysIdx {} 의 활동 지역 {}",companysIdx, companysActiveRegions.toString());
        for(int i = 0; i < companysActiveRegions.size(); i++) {
          String companysActiveRegion = companysActiveRegions.get(i);
          entity4.setCompanysIdx(companysIdx);
          entity4.setActiveRegion(companysActiveRegion);
          companysActiveRegionService.insertCompanysActiveRegion(entity4);
        }

        entity5.setUsersIdx(step1.getCompanysCeoIdx());
        entity5.setCompanysIdx(companysIdx);
        entity5.setStatusValue(1);
        entity5.setRegistDate(LocalDateTime.now());
        companysMemberService.insertCompanysMember(entity5);

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
        log.info("usersIdx: {}는 소속된 업체가 없습니다.", usersIdx);
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

    @GetMapping("/selectCompanysIdxByUsersIdx")
    public CustomResponseData selectCompanysIdxByUsersIdx(
            @RequestParam("usersIdx") String usersIdx)
    {
        CustomResponseData result = new CustomResponseData();

        String item = String.valueOf(companysService.selectCompanysIdxByUsersIdx(Long.parseLong(usersIdx)));
        if(item != null) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(item);
            result.setResponseDateTime(LocalDateTime.now());
        }
        else {
            result.setStatusCode(HttpStatus.BAD_REQUEST.value());
            result.setResultItem("FALSE");
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

}
