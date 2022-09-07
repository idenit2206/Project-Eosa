package com.eosa.web.companys.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.eosa.web.companys.entity.*;
import com.eosa.web.companys.service.*;
import com.eosa.web.util.file.AwsS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.eosa.web.companys.repository.CompanysLicenseRepository;
import com.eosa.web.users.entity.Users;
import com.eosa.web.users.repository.UsersRepository;
import com.eosa.web.util.CustomResponseData;
import com.nimbusds.oauth2.sdk.ParseException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value="/api/companys")
public class CompanysController {

    @Autowired private CompanysService companysService;
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
        @RequestPart(value = "companysLicense", required = false) MultipartFile file2,
        @RequestPart(value = "companysProfileImage", required = false) MultipartFile file3
    ) throws JSONException, ParseException, IOException {
      CustomResponseData result = new CustomResponseData();
//    JsonObject jsonObject = (JsonObject) JsonParser.parseString(param).getAsJsonObject();
//    log.debug("param: {}", param.toString());
//    log.debug("jsonObject: {}", jsonObject.toString());
      log.debug("params: {}", params.toString());
      log.debug("{}, {}", companysCategory.toString(), companysActiveRegions.toString());
      log.debug("file1: {}", file1.getOriginalFilename());

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

      if(file3 != null) {
          String file1URL = awsS3Service.uploadSingleFile(file1,"registcerti", step1.getCompanysIdx());
          String file3URL = awsS3Service.uploadSingleFile(file3, "profileimage", step1.getCompanysIdx());
          int step1a = companysService.updateRegistCertiAndProfileImage(step1.getCompanysIdx(), file1URL, file3URL);
      }
      if(file3 == null) {
          log.debug("{}, {}", file1.getOriginalFilename(), step1.getCompanysIdx());
          String file1URL = awsS3Service.uploadSingleFile(file1,"registcerti", step1.getCompanysIdx());
          int step1b = companysService.updateRegistCerti(step1.getCompanysIdx(), file1URL);
      }

      if(file2 != null) {
          String file2URL = awsS3Service.uploadSingleFile(file2, "license", step1.getCompanysIdx());
          int step1c = companysService.updateLicense(step1.getCompanysIdx(), file2URL);
      }

      CompanysCategory entity3 = new CompanysCategory();
      CompanysActiveRegion entity4 = new CompanysActiveRegion();
      CompanysMember entity5 = new CompanysMember();
      log.debug("step1: {}", step1.toString());

      if(step1 != null) {
        Long companysIdx = step1.getCompanysIdx();

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
      log.debug("[selectAllCategory] category: {}", items.toString());

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

//    @Secured({"CLIENT", "DETECTIVE"})
    @GetMapping("/selectAllCompanys")
    public CustomResponseData selectAllCompanys(
        @RequestParam(value = "usersIdx", required = false) Long usersIdx,
        @RequestParam(value = "companysIdx", required = false) Long companysIdx
    ) {
        log.debug("[selectAllCompanys] usersIdx: {} Requested...", usersIdx);
        CustomResponseData result = new CustomResponseData();
        List<SelectAllCompanysList> list = companysService.selectAllCompanys(usersIdx);

        log.debug("[selectAllCompanys] list: {}", list.toString());

        result.setStatusCode(HttpStatus.OK.value());
        result.setResultItem(list);
        result.setResponseDateTime(LocalDateTime.now());
        return result;
    }

    /**
     * 활동분야를 기준으로 한 업체목록 조회
     */
    @GetMapping("/selectCompanysByCategory")
    public CustomResponseData selectCompanysByCategory(
            @RequestParam("companysCategoryValue") List<String> companysCategory
    ) {
        CustomResponseData result = new CustomResponseData();
        List<SelectAllCompanysList> list = new LinkedList<>();
        List<Long> companysIdxList = new ArrayList<>();
        for(int i = 0; i < companysCategory.size(); i++) {
            String keyword = companysCategory.get(i).trim();
//            log.debug("[selectCompanysByCategory] RequestParam companysCategory: {}", keyword);
            companysIdxList = companysService.selectCompanysIdxByCompanysCategory(keyword);
        }
        log.debug("[selectCompanysByCategory] companysIdx: {}", companysIdxList.toString());
        for(int i = 0; i < companysIdxList.size(); i++) {
            Long companysIdx = companysIdxList.get(i);
            SelectAllCompanysList query = companysService.selectCompanysByCompanysIdx(companysIdx);
            list.add(query);
        }
        log.debug("[selectCompanysByCategory] list[0] companysName: {}", list.get(0).getCompanysName());

        if(list != null) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(list);
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
     * Companys 활동분야, 소재지 시/도 기준으로 회사목록 검색
     * @param companysCategory String
     * @param companysRegion1 String
     * @return
     */
    @GetMapping("/selectCompanysByCategoryAndRegion1")
    public CustomResponseData selectCompanysByCategoryAndRegion1(
        @RequestParam("companysCategoryValue") List<String> companysCategory,
        @RequestParam("companysRegion1") List<String> companysRegion1
    ) {
        CustomResponseData result = new CustomResponseData();
        List<SelectAllCompanysList> list = new LinkedList<>();
        List<Long> companysIdxList = new ArrayList<>();
        for(int i = 0; i < companysCategory.size(); i++) {
            String keyword = companysCategory.get(i).trim();
//            log.debug("[selectCompanysByCategory] RequestParam companysCategory: {}", keyword);
            companysIdxList = companysService.selectCompanysIdxByCompanysCategory(keyword);
        }
        log.debug("[selectCompanysByCategoryAndRegion1] companysIdx: {}", companysIdxList.toString());
        for(int i =0; i < companysRegion1.size(); i++) {
            for(int j = 0; j < companysIdxList.size(); j++) {
                Long companysIdx = companysIdxList.get(j);
                SelectAllCompanysList query = companysService.selectCompanysByCompanysIdxAndCompanysRegion1(companysIdx, companysRegion1.get(i));
                list.add(query);
            }
        }

        if(list != null) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(list);
            result.setResponseDateTime(LocalDateTime.now());
        }
        else {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(null);
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

    @GetMapping("/selectCompanysByCompanysRegion1")
    public CustomResponseData selectCompanysByCompanysRegion1(
            @RequestParam("companysRegion1") List<String> companysRegion1
    ) {
        CustomResponseData result = new CustomResponseData();
        List<SelectAllCompanysList> list = new LinkedList<>();
        List<Long> companysIdxList = new LinkedList<>();
        for(int i = 0; i < companysRegion1.size(); i++) {
            String keyword = companysRegion1.get(i).trim();
//            log.debug("[selectCompanysByCategory] RequestParam companysCategory: {}", keyword);
            companysIdxList = companysService.selectCompanysIdxByRegion1(keyword);
        }
        log.debug("[selectCompanysByCompanysRegion1] companysIdx: {}", companysIdxList.toString());
        for(int i = 0; i < companysIdxList.size(); i++) {
            Long companysIdx = companysIdxList.get(i);
            SelectAllCompanysList query = companysService.selectCompanysByCompanysIdx(companysIdx);
            list.add(query);
        }

        if(list != null) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(list);
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
     * usersIdx의 DETECTIVE가 소유한 업체정보를 조회
     * @param param
     * @return
     */
    @GetMapping("/selectCompanyInfoByUsersIdx")
    public CustomResponseData selectCompanyInfoByUsersIdx(
      @RequestParam("usersIdx") String param
    ){
      CustomResponseData result = new CustomResponseData();
      Map<String, Object> items = new HashMap<>();

      Long usersIdx = Long.parseLong(param);
      log.debug("usersIdx: {}의  Companys 정보를 조회합니다", usersIdx);

      Companys step1 = companysService.selectCompanyInfoByUsersIdx(usersIdx);

      if(step1 != null) {
          log.debug("step1: {}", step1.toString());
          Long companysIdx = step1.getCompanysIdx();
          List<String> companysCategory = companysCategoryService.selectByCompanysIdx(companysIdx);
          List<String> companysActiveRegion = companysActiveRegionService.selectByCompanysIdx(companysIdx);

          log.debug("step1_category: {}", companysCategory.toString());
          log.debug("step1_ActiveRegion: {}", companysActiveRegion.toString());

          items.put("companys", step1);
          items.put("companysCategory", companysCategory);
          items.put("companysActiveRegion", companysActiveRegion);

          result.setStatusCode(HttpStatus.OK.value());
          result.setResultItem(items);
          result.setResponseDateTime(LocalDateTime.now());
      } else {
          log.debug("step1: Companys is Null");
          result.setStatusCode(HttpStatus.OK.value());
          result.setResultItem(null);
          result.setResponseDateTime(LocalDateTime.now());
      }

      return result;
    }

    /**
     * 업체 등급별 검색 일반, 마패, 프리미엄, 전체
     *
     */
//    @GetMapping("/selectCompanysByCompanysGrade")
//    public CustomResponseData selectCompanysByCompanysGrade(
//        @RequestParam("companysGrade") String companysGrade
//    )

    /**
     * usersIdx를 매개변수로 받아 해당하는 Companys의 companysIdx를 출력합니다.
     * @param usersIdx
     * @return
     */
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

    /**
     * 업체 정보 수정
     * @param companyInfo Companys 타입
     * @param companysCategory List(String)
     * @param companysActiveRegions List(String)
     * @param file1 MultipartFile
     * @param file2 MultipartFile
     * @param file3 MultipartFile
     * @return
     * @throws JSONException
     * @throws ParseException
     * @throws IOException
     */
    @PutMapping("/updateCompanys")
    public CustomResponseData updateCompanys(
            @RequestPart("companyInfo") Companys companyInfo,
            @RequestPart("companysCategory") List<String> companysCategory,
            @RequestPart("companysActiveRegion") List<String> companysActiveRegions,
            @RequestPart(value = "companysRegistCerti", required = false) MultipartFile file1,
            @RequestPart(value = "companysLicense", required = false) MultipartFile file2,
            @RequestPart(value = "companysProfileImage", required = false) MultipartFile file3
    ) throws JSONException, ParseException, IOException {
        CustomResponseData result = new CustomResponseData();
        log.debug("[updateCompanys] companyInfo: {}", companyInfo.getCompanysIdx());
        log.debug("[updateCompanys] {}, {}", companysCategory.toString(), companysActiveRegions.toString());

        Companys entity = new Companys();
        entity.setCompanysIdx(Long.parseLong("55"));

        if(file1 != null) {
            String file1URL = awsS3Service.uploadSingleFile(file1,"registcerti", entity.getCompanysIdx());
            entity.setCompanysRegistCerti(file1URL);
        }

        if(file2 != null) {
            String file2URL = awsS3Service.uploadSingleFile(file2,"license", entity.getCompanysIdx());
            entity.setCompanysRegistCerti(file2URL);
        }

        if(file3 != null) {
            String file3URL = awsS3Service.uploadSingleFile(file3, "license", entity.getCompanysIdx());
            entity.setCompanysProfileImage(file3URL);
        }

        entity.setCompanysName(companyInfo.getCompanysName());
        entity.setCompanysCeoName(companyInfo.getCompanysCeoName());
        entity.setCompanysCeoIdx(companyInfo.getCompanysCeoIdx());
        entity.setCompanysComment(companyInfo.getCompanysComment());
        entity.setCompanysSpec(companyInfo.getCompanysSpec());
        entity.setCompanysRegion1(companyInfo.getCompanysRegion1());
        entity.setCompanysRegion2(companyInfo.getCompanysRegion2());
        entity.setCompanysRegion3(companyInfo.getCompanysRegion3());
        entity.setCompanysBankName(companyInfo.getCompanysBankName());
        entity.setCompanysBankNumber(companyInfo.getCompanysBankNumber());

        Long companysIdx = entity.getCompanysIdx();
//        log.debug("[updateCompanys] entity: {}", entity.toString());

        int step1 = companysService.updateCompanys(entity);

        if(step1 != 0) {
            int deletePrevCategory = companysCategoryService.deleteCategoryByCompanysIdx(companysIdx);
            int deletePrevActiveRegion = companysActiveRegionService.deleteActiveRegionByCompanysIdx(companysIdx);

            for(int i = 0; i < companysCategory.size(); i++) {
                CompanysCategory entity2 = new CompanysCategory();
                entity2.setCompanysIdx(companysIdx);
                entity2.setCompanysCategoryValue(companysCategory.get(i));
                companysCategoryService.insertCompanysCategory(entity2);
            }

            for(int i = 0; i < companysActiveRegions.size(); i++) {
                CompanysActiveRegion entity3 = new CompanysActiveRegion();
                entity3.setCompanysIdx(companysIdx);
                entity3.setActiveRegion(companysActiveRegions.get(i));
                companysActiveRegionService.insertCompanysActiveRegion(entity3);
            }

            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem("SUCCESS");
            result.setResponseDateTime(LocalDateTime.now());
        }
        else {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem("FAILURE");
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

}
