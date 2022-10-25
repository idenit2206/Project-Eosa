package com.eosa.web.companys.controller;

import java.io.IOException;
import java.time.LocalDateTime;
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

import com.eosa.web.users.repository.UsersRepository;
import com.eosa.web.util.CustomResponseData;
import com.nimbusds.oauth2.sdk.ParseException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/companys")
public class CompanysController {

    @Autowired
    private CompanysService companysService;
    @Autowired
    private CompanysCategoryService companysCategoryService;
    @Autowired
    private CompanysActiveRegionService companysActiveRegionService;
    @Autowired
    private CompanysMemberService companysMemberService;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private AwsS3Service awsS3Service;

    /**
     * companys 등록
     * 
     * @param params
     * @param companysCategory
     * @param companysActiveRegions
     * @param file1
     * @param file2
     * @param file3
     * @return
     * @throws JSONException
     * @throws ParseException
     * @throws IOException
     */
    @PostMapping("/insertCompanys")
    public CustomResponseData insertCompanys(
            // @RequestBody String param
            @RequestPart("companyInfo") Companys params,
            @RequestPart("companysCategory") List<String> companysCategory,
            @RequestPart("companysActiveRegion") List<String> companysActiveRegions,
            @RequestPart(value = "companysRegistCerti", required = false) MultipartFile file1,
            @RequestPart(value = "companysLicense", required = false) MultipartFile file2,
            @RequestPart(value = "companysProfileImage", required = false) MultipartFile file3)
            throws JSONException, ParseException, IOException {
        CustomResponseData result = new CustomResponseData();
        // JsonObject jsonObject = (JsonObject)
        // JsonParser.parseString(param).getAsJsonObject();
        // log.debug("param: {}", param.toString());
        // log.debug("jsonObject: {}", jsonObject.toString());
        log.debug("[insertCompanys] parameter companysInfo: {}", params.toString());
        log.debug("[insertCompanys] parameter companysCategory: {}, comapnysActiveRegion: {}",
                companysCategory.toString(), companysActiveRegions.toString());
        log.debug("[insertCompanys] parameter companysRegistCerti: {}", file1.getOriginalFilename());
        if (file2 != null) {
            log.debug("[insertCompanys] parameter companysLicense: {}", file2.getOriginalFilename());
        }
        if (file3 != null) {
            log.debug("[insertCompanys] parameter companysProfileImage: {}", file3.getOriginalFilename());
        }

        Companys entity = new Companys();
        // // @RequestBody String param JSON으로 받는 방식 파일처리 하는방법을 못 찾아서 이 방식은 보류
        // entity.setCompanysCeoIdx(Long.parseLong(jsonObject.get("companysCeoIdx").getAsString()));
        // entity.setCompanysCeoName(jsonObject.get("companysCeoName").getAsString());
        // entity.setCompanysName(jsonObject.get("companyName").getAsString());
        // entity.setCompanysComment(jsonObject.get("companyComment").getAsString());
        // entity.setCompanysSpec(jsonObject.get("companysSpec").getAsString());
        // // entity.setCompanysPhone(jsonObject.get("companysPhone").getAsString());
        // entity.setCompanysRegion1(jsonObject.get("companyRegion1").getAsString());
        // entity.setCompanysRegion2(jsonObject.get("companyRegion2").getAsString());
        // entity.setCompanysRegion3(jsonObject.get("companyRegion3").getAsString());
        // //
        // entity.setCompanysRegistCerti(jsonObject.get("companyRegistCerti").getAsString());
        // //
        // entity.setCompanysProfileImage(jsonObject.get("companyProfileImage").getAsString());
        // entity.setCompanysBankName(jsonObject.get("companyBankName").getAsString());
        // entity.setCompanysBankNumber(jsonObject.get("companyBankNumber").getAsString());
        // log.debug("companysCategory: {}",
        // jsonObject.get("companyCategory").getAsJsonArray().toString());
        // log.debug("companysActiveRegion: {}",
        // jsonObject.get("companyActiveRegion").getAsJsonArray().toString());
        // log.debug("companyRegistCerti: {}",
        // jsonObject.get("companyRegistCerti").getAsJsonObject().toString());
        // log.debug("companyLicense: {}",
        // jsonObject.get("companyCerti").getAsJsonObject().toString());
        // log.debug("companyProfileImage: {}",
        // jsonObject.get("companyProfileImage").getAsJsonObject().toString());

        entity.setCompanysName(params.getCompanysName());
        entity.setCompanysPhone(params.getCompanysPhone());
        entity.setCompanysCeoName(params.getCompanysCeoName());
        entity.setCompanysCeoIdx(params.getCompanysCeoIdx());
        entity.setCompanysComment(params.getCompanysComment());
        entity.setCompanysSpec(params.getCompanysSpec());
        entity.setCompanysRegistCertiDate(params.getCompanysRegistCertiDate());
        entity.setCompanysRegion1(params.getCompanysRegion1());
        entity.setCompanysRegion2(params.getCompanysRegion2());
        entity.setCompanysRegion3(params.getCompanysRegion3());
        entity.setCompanysBankName(params.getCompanysBankName());
        entity.setCompanysBankNumber(params.getCompanysBankNumber());
        log.debug("[insertCompanys] Companys entity: {}", entity.toString());

        Companys step1 = companysService.save(entity);

        if (file3 != null) {
            List<String> file1Object = awsS3Service.uploadSingleFile(file1, "registcerti", step1.getCompanysIdx());
            List<String> file3Object = awsS3Service.uploadSingleFile(file3, "profileimage", step1.getCompanysIdx());
            String file1URL = file1Object.get(1);
            String file3URL = file3Object.get(1);
            String file1Name = file1Object.get(0);
            String file3Name = file3Object.get(0);
            int step1a = companysService.updateRegistCertiAndProfileImage(step1.getCompanysIdx(), file1URL, file3URL,
                    file1Name, file3Name);
        }
        if (file3 == null) {
            log.debug("[insertCompanys] file1 Name: {},  companysIdx: {}", file1.getOriginalFilename(),
                    step1.getCompanysIdx());
            List<String> fileObject = awsS3Service.uploadSingleFile(file1, "registcerti", step1.getCompanysIdx());
            String fileURL = fileObject.get(1);
            String fileName = fileObject.get(0);
            int step1b = companysService.updateRegistCerti(step1.getCompanysIdx(), fileURL, fileName);
        }

        if (file2 != null) {
            List<String> file2Object = awsS3Service.uploadSingleFile(file2, "license", step1.getCompanysIdx());
            String file2URL = file2Object.get(1);
            String file2NAME = file2Object.get(0);
            int step1c = companysService.updateLicense(step1.getCompanysIdx(), file2URL, file2NAME);
        }

        CompanysCategory entity3 = new CompanysCategory();
        CompanysActiveRegion entity4 = new CompanysActiveRegion();
        CompanysMember entity5 = new CompanysMember();
        log.debug("[insertCompanys] New Companys Save step1: {}", step1.toString());

        if (step1 != null) {
            Long companysIdx = step1.getCompanysIdx();

            log.debug("[insertCompanys] companysIdx {} 의 활동 분야 {}", companysIdx, companysCategory.toString());
            for (int i = 0; i < companysCategory.size(); i++) {
                String companysCategoryValue = companysCategory.get(i);
                // log.debug("companysIdx: {} 의 활동 분야 {}", companysIdx, companysCategoryValue);
                entity3.setCompanysIdx(companysIdx);
                entity3.setCompanysCategoryValue(companysCategoryValue);
                // log.debug("entity3: {}", entity3.toString());
                companysCategoryService.insertCompanysCategory(entity3);
            }
            log.debug("[insertCompanys] companysIdx {} 의 활동 지역 {}", companysIdx, companysActiveRegions.toString());
            for (int i = 0; i < companysActiveRegions.size(); i++) {
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

    /**
     * CompanysCategory의 category 전부 조회
     * 
     * @return
     */
    @GetMapping("/selectAllCategory")
    public CustomResponseData selectAllCategory() {
        CustomResponseData result = new CustomResponseData();

        List<String> items = companysService.selectAllCategory();
        log.debug("[selectAllCategory] category: {}", items.toString());

        if (items != null) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(items);
            result.setResponseDateTime(LocalDateTime.now());
        } else {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(null);
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

    
    /** 
     * @param selectAllCompanys(
     * @return CustomResponseData
     */
    // @Secured({"CLIENT", "DETECTIVE"})
    @GetMapping("/selectAllCompanys")
    public CustomResponseData selectAllCompanys(
            @RequestParam(value = "usersIdx", required = false) Long usersIdx,
            @RequestParam(value = "companysIdx", required = false) Long companysIdx) {
        log.debug("[selectAllCompanys] usersIdx: {} Requested...", usersIdx);
        CustomResponseData result = new CustomResponseData();
        List<SelectCompanys> list = companysService.selectAllCompanys();

        result.setStatusCode(HttpStatus.OK.value());
        result.setResultItem(list);
        result.setResponseDateTime(LocalDateTime.now());
        return result;
    }

    
    /** 
     * @return CustomResponseData
     */
    @GetMapping("/selectAllCompanysRandom")
    public CustomResponseData selectAllCompanysRandom() {
        CustomResponseData result = new CustomResponseData();
        List<SelectCompanys> items = companysService.selectAllCompanysRandom();
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
     * 업체검색(필터 적용)
     * 
     * @param companysCategory
     * @param companysRegion1
     * @param companysRegion2
     * @return
     */
    @GetMapping("/selectCompanysByFilter")
    public CustomResponseData selectCompanysByFilter(
            @RequestParam(value = "companysCategory", required = false, defaultValue = "") List<String> companysCategory,
            @RequestParam(value = "companysRegion1", required = false, defaultValue = "") List<String> companysRegion1,
            @RequestParam(value = "companysRegion2", required = false, defaultValue = "") List<String> companysRegion2) {
        log.info("[selectCompanysByFilter] 업체를 검색합니다. 검색 조건 카테고리: {}, 지역1: {}, 지역2: {}", companysCategory.toString(),
                companysRegion1.toString(), companysRegion2.toString());
        CustomResponseData result = new CustomResponseData();
        List<SelectCompanys> itemList = new ArrayList<>();
        Set<Long> companysIdxSet = new HashSet<>();
        List<Long> companysIdxList = new ArrayList<>();

        if (companysCategory.size() == 0 && companysRegion1.size() == 0 && companysRegion2.size() == 0) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(null);
            result.setResponseDateTime(LocalDateTime.now());
        }

        if (companysCategory.size() == 0 && companysRegion1.size() != 0) {
            for (int i = 0; i < companysRegion1.size(); i++) {
                companysIdxSet.addAll(companysService.selectCompanysByFilter2("", companysRegion1.get(i)));
            }
        }

        if (companysCategory.size() == 0 && companysRegion1.size() != 0) {
            for (int i = 0; i < companysRegion1.size(); i++) {
                companysIdxSet.addAll(companysService.selectCompanysByFilter2("", companysRegion1.get(i)));
            }
        }

        if (companysCategory.size() != 0 && companysRegion1.size() == 0) {
            // Set<Long> list = new HashSet<>();
            for (int i = 0; i < companysCategory.size(); i++) {
                companysIdxSet.addAll(companysService.selectCompanysByFilter2(companysCategory.get(i), ""));
            }
        }

        if (companysCategory.size() != 0 && companysRegion1.size() != 0) {
            for (int i = 0; i < companysCategory.size(); i++) {
                for (int j = 0; j < companysRegion1.size(); j++) {
                    companysIdxSet.addAll(
                            companysService.selectCompanysByFilter2(companysCategory.get(i), companysRegion1.get(j)));
                }
            }
        }

        if (companysCategory.size() != 0 && companysRegion1.size() != 0) {
            for (int i = 0; i < companysCategory.size(); i++) {
                for (int j = 0; j < companysRegion1.size(); j++) {
                    companysIdxSet.addAll(
                            companysService.selectCompanysByFilter2(companysCategory.get(i), companysRegion1.get(j)));
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

    // /**
    // * 활동분야를 기준으로 한 업체목록 조회
    // */
    // @GetMapping("/selectCompanysByCategory")
    // public CustomResponseData selectCompanysByCategory(
    // @RequestParam("companysCategoryValue") List<String> companysCategory
    // ) {
    // CustomResponseData result = new CustomResponseData();
    // List<SelectAllCompanysList> list = new LinkedList<>();
    // List<Long> companysIdxList = new ArrayList<>();
    // for(int i = 0; i < companysCategory.size(); i++) {
    // String keyword = companysCategory.get(i).trim();
    //// log.debug("[selectCompanysByCategory] RequestParam companysCategory: {}",
    // keyword);
    // companysIdxList =
    // companysService.selectCompanysIdxByCompanysCategory(keyword);
    // }
    // log.debug("[selectCompanysByCategory] companysIdx: {}",
    // companysIdxList.toString());
    // for(int i = 0; i < companysIdxList.size(); i++) {
    // Long companysIdx = companysIdxList.get(i);
    // SelectAllCompanysList query =
    // companysService.selectCompanysByCompanysIdx(companysIdx);
    // list.add(query);
    // }
    // log.debug("[selectCompanysByCategory] list[0] companysName: {}",
    // list.get(0).getCompanysName());
    //
    // if(list != null) {
    // result.setStatusCode(HttpStatus.OK.value());
    // result.setResultItem(list);
    // result.setResponseDateTime(LocalDateTime.now());
    // }
    // else {
    // result.setStatusCode(HttpStatus.OK.value());
    // result.setResultItem(null);
    // result.setResponseDateTime(LocalDateTime.now());
    // }
    //
    // return result;
    // }
    //
    // /**
    // * Companys 활동분야, 소재지 시/도 기준으로 회사목록 검색
    // * @param companysCategory String
    // * @param companysRegion1 String
    // * @return
    // */
    // @GetMapping("/selectCompanysByCategoryRegion1")
    // public CustomResponseData selectCompanysByCategoryAndRegion1(
    // @RequestParam("companysCategoryValue") List<String> companysCategory,
    // @RequestParam("companysRegion1") List<String> companysRegion1
    // ) {
    // CustomResponseData result = new CustomResponseData();
    // List<SelectAllCompanysList> list = new LinkedList<>();
    // List<Long> companysIdxList = new ArrayList<>();
    // for(int i = 0; i < companysCategory.size(); i++) {
    // String keyword = companysCategory.get(i).trim();
    //// log.debug("[selectCompanysByCategory] RequestParam companysCategory: {}",
    // keyword);
    // companysIdxList =
    // companysService.selectCompanysIdxByCompanysCategory(keyword);
    // }
    // log.debug("[selectCompanysByCategoryRegion1] companysIdx: {}",
    // companysIdxList.toString());
    // for(int i =0; i < companysRegion1.size(); i++) {
    // for(int j = 0; j < companysIdxList.size(); j++) {
    // Long companysIdx = companysIdxList.get(j);
    // SelectAllCompanysList query =
    // companysService.selectCompanysByCompanysIdxAndCompanysRegion1(companysIdx,
    // companysRegion1.get(i));
    // list.add(query);
    // }
    // }
    //
    // if(list != null) {
    // result.setStatusCode(HttpStatus.OK.value());
    // result.setResultItem(list);
    // result.setResponseDateTime(LocalDateTime.now());
    // }
    // else {
    // result.setStatusCode(HttpStatus.OK.value());
    // result.setResultItem(null);
    // result.setResponseDateTime(LocalDateTime.now());
    // }
    //
    // return result;
    // }
    //
    // @GetMapping("/selectCompanysByCompanysRegion1")
    // public CustomResponseData selectCompanysByCompanysRegion1(
    // @RequestParam("companysRegion1") List<String> companysRegion1
    // ) {
    // CustomResponseData result = new CustomResponseData();
    // List<SelectAllCompanysList> list = new LinkedList<>();
    // List<Long> companysIdxList = new LinkedList<>();
    // for(int i = 0; i < companysRegion1.size(); i++) {
    // String keyword = companysRegion1.get(i).trim();
    //// log.debug("[selectCompanysByCategory] RequestParam companysCategory: {}",
    // keyword);
    // companysIdxList = companysService.selectCompanysIdxByRegion1(keyword);
    // }
    // log.debug("[selectCompanysByCompanysRegion1] companysIdx: {}",
    // companysIdxList.toString());
    // for(int i = 0; i < companysIdxList.size(); i++) {
    // Long companysIdx = companysIdxList.get(i);
    // SelectAllCompanysList query =
    // companysService.selectCompanysByCompanysIdx(companysIdx);
    // list.add(query);
    // }
    //
    // if(list != null) {
    // result.setStatusCode(HttpStatus.OK.value());
    // result.setResultItem(list);
    // result.setResponseDateTime(LocalDateTime.now());
    // }
    // else {
    // result.setStatusCode(HttpStatus.OK.value());
    // result.setResultItem(null);
    // result.setResponseDateTime(LocalDateTime.now());
    // }
    //
    // return result;
    // }

    /**
     * companysIdx와 일치하는 Companys 정보 조회
     * 
     * @param companysIdx
     * @return
     */
    @GetMapping("/selectOneCompanyInfoByCompanysIdx")
    public CustomResponseData selectOneCompanyInfoByCompanysIdx(
            @RequestParam("companysIdx") Long companysIdx) {
        CustomResponseData result = new CustomResponseData();
        SelectAllCompanysForNormal data = companysService.selectOneCompanyInfoByCompanysIdx(companysIdx);
        log.debug("[selectCompanyInfoByCompanysIdx] data: {}", data.toString());

        if (data != null) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(data);
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

    /**
     * usersIdx와 일치하는 Companys 정보 조회
     * 
     * @param usersIdx Long type
     * @return
     */
    @GetMapping("/selectCompanyInfoByUsersIdx")
    public CustomResponseData selectCompanyInfoByUsersIdx(
            @RequestParam(name = "usersIdx", required = false) Long usersIdx) {
        CustomResponseData result = new CustomResponseData();
        Map<String, Object> items = new HashMap<>();
        log.debug("[selectCompanyInfoByUsersIdx] companysIdx가 일치하는 Companys 정보를 조회합니다 companysIdx: {}", usersIdx);

        Companys step1 = companysService.selectCompanyInfoByUsersIdx(usersIdx);

        if (step1 != null) {
            // log.debug("[selectCompanyInfoByUsersIdx] step1: {}", step1.toString());
            Long companysIdx = step1.getCompanysIdx();
            List<String> companysCategory = companysCategoryService.selectByCompanysIdx(companysIdx);
            List<String> companysActiveRegion = companysActiveRegionService.selectByCompanysIdx(companysIdx);

            log.debug("[selectCompanyInfoByUsersIdx] companysCategory: {}", companysCategory.toString());
            log.debug("[selectCompanyInfoByUsersIdx] companysActiveRegion: {}", companysActiveRegion.toString());

            items.put("companysIdx", step1.getCompanysIdx());
            items.put("companysName", step1.getCompanysName());
            items.put("companysPhone", step1.getCompanysPhone());
            // items.put("companysD")
            items.put("companysComment", step1.getCompanysComment());
            items.put("companysSpec", step1.getCompanysSpec());
            items.put("companysRegion1", step1.getCompanysRegion1());
            items.put("companysRegion2", step1.getCompanysRegion2());
            items.put("companysRegion3", step1.getCompanysRegion3());
            items.put("companysRegistCerti", step1.getCompanysRegistCerti());
            items.put("companysRegistCertiName", step1.getCompanysRegistCertiName());
            items.put("companysRegistCertiDate", step1.getCompanysRegistCertiDate());
            items.put("companysLicense", step1.getCompanysLicense());
            items.put("companysLicenseName", step1.getCompanysLicenseName());
            items.put("companysProfileImage", step1.getCompanysProfileImage());
            items.put("companysProfileImageName", step1.getCompanysProfileImageName());
            items.put("companysBankName", step1.getCompanysBankName());
            items.put("companysBankNumber", step1.getCompanysBankNumber());
            items.put("companysEnabled", step1.getCompanysEnabled());
            items.put("companysCategory", companysCategory);
            items.put("companysActiveRegion", companysActiveRegion);

            // log.debug("[selectCompanyInfoByUsersIdx]399lines prepare result items: {}", items.toString());

            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(items);
            result.setResponseDateTime(LocalDateTime.now());
        } else {
            log.debug("[selectCompanyInfoByUsersIdx] entity Companys is Null");
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(null);
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

    /**
     * 업체 등급별 검색 일반, 마패, 제휴협회, 전체
     *
     */
    // @GetMapping("/selectCompanysByCompanysGrade")
    // public CustomResponseData selectCompanysByCompanysGrade(
    // @RequestParam("companysGrade") String companysGrade
    // )

    /**
     * usersIdx를 매개변수로 받아 해당하는 Companys의 companysIdx를 출력합니다.
     * 
     * @param usersIdx
     * @return
     */
    @GetMapping("/selectCompanysIdxByUsersIdx")
    public CustomResponseData selectCompanysIdxByUsersIdx(
        @RequestParam("usersIdx") Long usersIdx
    ) {
        CustomResponseData result = new CustomResponseData();

        String item = String.valueOf(companysService.selectCompanysIdxByUsersIdx(usersIdx));
        if (item != null) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(item);
            result.setResponseDateTime(LocalDateTime.now());
        } else {
            result.setStatusCode(HttpStatus.BAD_REQUEST.value());
            result.setResultItem("FALSE");
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

    /**
     * 업체 정보를 수정하는 컨트롤러
     * 
     * @param companyInfo           Companys 타입
     * @param companysCategory      List(String)
     * @param companysActiveRegions List(String)
     * @param file1                 MultipartFile
     * @param file2                 MultipartFile
     * @param file3                 MultipartFile
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
            @RequestPart(value = "companysProfileImage", required = false) MultipartFile file3)
            throws JSONException, ParseException, IOException {
        CustomResponseData result = new CustomResponseData();
        log.debug("[updateCompanys] parameter companyInfo: {}", companyInfo.toString());
        log.debug("[updateCompanys] parameter companysCategory: {}, companysActiveRegion: {}",
                companysCategory.toString(), companysActiveRegions.toString());
        if (file1 != null) {
            log.debug("[updateCompanys] 새로운 사업자등록증: {}", file1.getOriginalFilename());
        } else {
            log.debug("[updateCompanys] 기존의 사업자 등록증: {}", companyInfo.getCompanysRegistCerti());
        }

        if (file2 != null) {
            log.debug("[updateCompanys] 새로운 라이센스: {}", file2.getOriginalFilename());
        } else {
            log.debug("[updateCompanys] 기존의 라이센스: {}", companyInfo.getCompanysLicense());
        }

        if (file3 != null) {
            log.debug("[updateCompanys] 새로운 프로필이미지: {}", file3.getOriginalFilename());
        } else {
            log.debug("[updateCompanys] 기존의 프로필이미지: {}", companyInfo.getCompanysProfileImage());
        }

        Companys entity = new Companys();
        entity.setCompanysIdx(companyInfo.getCompanysIdx());

        if (file1 != null) {
            List<String> file1URL = awsS3Service.uploadSingleFile(file1, "registcerti", entity.getCompanysIdx());
            entity.setCompanysRegistCerti(file1URL.get(1));
            entity.setCompanysRegistCertiName(file1URL.get(0));
        } else {
            entity.setCompanysRegistCerti(companyInfo.getCompanysRegistCerti());
            entity.setCompanysRegistCertiName(companyInfo.getCompanysRegistCertiName());
        }

        if (file2 != null) {
            List<String> file2URL = awsS3Service.uploadSingleFile(file2, "license", entity.getCompanysIdx());
            entity.setCompanysLicense(file2URL.get(1));
            entity.setCompanysLicenseName(file2URL.get(0));
        } else {
            entity.setCompanysLicense(companyInfo.getCompanysLicense());
            entity.setCompanysLicenseName(companyInfo.getCompanysLicenseName());
        }

        if (file3 != null) {
            log.info("[updateCompanys] new file3: {}", file3.getOriginalFilename());
            List<String> file3URL = awsS3Service.uploadSingleFile(file3, "profileimage", entity.getCompanysIdx());
            entity.setCompanysProfileImage(file3URL.get(1));
            entity.setCompanysProfileImageName(file3URL.get(0));
        } else {
            entity.setCompanysProfileImage(companyInfo.getCompanysProfileImage());
            entity.setCompanysProfileImageName(companyInfo.getCompanysProfileImageName());
        }

        entity.setCompanysName(companyInfo.getCompanysName());
        entity.setCompanysCeoName(companyInfo.getCompanysCeoName());
        entity.setCompanysCeoIdx(companyInfo.getCompanysCeoIdx());
        entity.setCompanysComment(companyInfo.getCompanysComment());
        entity.setCompanysSpec(companyInfo.getCompanysSpec());
        entity.setCompanysRegistCertiDate(companyInfo.getCompanysRegistCertiDate());
        entity.setCompanysRegion1(companyInfo.getCompanysRegion1());
        entity.setCompanysRegion2(companyInfo.getCompanysRegion2());
        entity.setCompanysRegion3(companyInfo.getCompanysRegion3());
        entity.setCompanysBankName(companyInfo.getCompanysBankName());
        entity.setCompanysBankNumber(companyInfo.getCompanysBankNumber());

        Long companysIdx = entity.getCompanysIdx();
        log.debug("[updateCompanys] update wait entity: {}", entity.toString());

        int step1 = companysService.updateCompanys(entity);

        if (step1 != 0) {
            int deletePrevCategory = companysCategoryService.deleteCategoryByCompanysIdx(companysIdx);
            int deletePrevActiveRegion = companysActiveRegionService.deleteActiveRegionByCompanysIdx(companysIdx);

            for (int i = 0; i < companysCategory.size(); i++) {
                CompanysCategory entity2 = new CompanysCategory();
                entity2.setCompanysIdx(companysIdx);
                entity2.setCompanysCategoryValue(companysCategory.get(i));
                companysCategoryService.insertCompanysCategory(entity2);
            }

            for (int i = 0; i < companysActiveRegions.size(); i++) {
                CompanysActiveRegion entity3 = new CompanysActiveRegion();
                entity3.setCompanysIdx(companysIdx);
                entity3.setActiveRegion(companysActiveRegions.get(i));
                companysActiveRegionService.insertCompanysActiveRegion(entity3);
            }

            log.debug("[updateCompanys] line542 update Done");
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem("SUCCESS");
            result.setResponseDateTime(LocalDateTime.now());
        } else {
            log.debug("[updateCompanys] line542 update FAIL");
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem("FAILURE");
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

    /**
     * 반려당한 업체정보를 재등록하는 컨트롤러
     * 
     * @param companyInfo           Companys 타입
     * @param companysCategory      List(String)
     * @param companysActiveRegions List(String)
     * @param file1                 MultipartFile
     * @param file2                 MultipartFile
     * @param file3                 MultipartFile
     * @return
     * @throws JSONException
     * @throws ParseException
     * @throws IOException
     */
    @PutMapping("/updateCompanys02")
    public CustomResponseData updateCompanys02(
            @RequestPart("companyInfo") Companys companyInfo,
            @RequestPart("companysCategory") List<String> companysCategory,
            @RequestPart("companysActiveRegion") List<String> companysActiveRegions,
            @RequestPart(value = "companysRegistCerti", required = false) MultipartFile file1,
            @RequestPart(value = "companysLicense", required = false) MultipartFile file2,
            @RequestPart(value = "companysProfileImage", required = false) MultipartFile file3)
            throws JSONException, ParseException, IOException {
        CustomResponseData result = new CustomResponseData();
        log.debug("[updateCompanys] parameter companyInfo: {}", companyInfo.toString());
        log.debug("[updateCompanys] parameter companysCategory: {}, companysActiveRegion: {}",
                companysCategory.toString(), companysActiveRegions.toString());
        if (file1 != null) {
            log.debug("[updateCompanys] 새로운 사업자등록증: {}", file1.getOriginalFilename());
        } else {
            log.debug("[updateCompanys] 기존의 사업자 등록증: {}", companyInfo.getCompanysRegistCerti());
        }

        if (file2 != null) {
            log.debug("[updateCompanys] 새로운 라이센스: {}", file2.getOriginalFilename());
        } else {
            log.debug("[updateCompanys] 기존의 라이센스: {}", companyInfo.getCompanysLicense());
        }

        if (file3 != null) {
            log.debug("[updateCompanys] 새로운 프로필이미지: {}", file3.getOriginalFilename());
        } else {
            log.debug("[updateCompanys] 기존의 프로필이미지: {}", companyInfo.getCompanysProfileImage());
        }

        Companys entity = new Companys();
        entity.setCompanysIdx(companyInfo.getCompanysIdx());

        if (file1 != null) {
            List<String> file1URL = awsS3Service.uploadSingleFile(file1, "registcerti", entity.getCompanysIdx());
            entity.setCompanysRegistCerti(file1URL.get(1));
            entity.setCompanysRegistCertiName(file1URL.get(0));
        } else {
            entity.setCompanysRegistCerti(companyInfo.getCompanysRegistCerti());
            entity.setCompanysRegistCertiName(companyInfo.getCompanysRegistCertiName());
        }

        if (file2 != null) {
            List<String> file2URL = awsS3Service.uploadSingleFile(file2, "license", entity.getCompanysIdx());
            entity.setCompanysLicense(file2URL.get(1));
            entity.setCompanysLicenseName(file2URL.get(0));
        } else {
            entity.setCompanysLicense(companyInfo.getCompanysLicense());
            entity.setCompanysLicenseName(companyInfo.getCompanysLicenseName());
        }

        if (file3 != null) {
            log.info("[updateCompanys] new file3: {}", file3.getOriginalFilename());
            List<String> file3URL = awsS3Service.uploadSingleFile(file3, "profileimage", entity.getCompanysIdx());
            entity.setCompanysProfileImage(file3URL.get(1));
            entity.setCompanysProfileImageName(file3URL.get(0));
        } else {
            entity.setCompanysProfileImage(companyInfo.getCompanysProfileImage());
            entity.setCompanysProfileImageName(companyInfo.getCompanysProfileImageName());
        }

        entity.setCompanysName(companyInfo.getCompanysName());
        entity.setCompanysCeoName(companyInfo.getCompanysCeoName());
        entity.setCompanysCeoIdx(companyInfo.getCompanysCeoIdx());
        entity.setCompanysComment(companyInfo.getCompanysComment());
        entity.setCompanysSpec(companyInfo.getCompanysSpec());
        entity.setCompanysRegistCertiDate(companyInfo.getCompanysRegistCertiDate());
        entity.setCompanysRegion1(companyInfo.getCompanysRegion1());
        entity.setCompanysRegion2(companyInfo.getCompanysRegion2());
        entity.setCompanysRegion3(companyInfo.getCompanysRegion3());
        entity.setCompanysBankName(companyInfo.getCompanysBankName());
        entity.setCompanysBankNumber(companyInfo.getCompanysBankNumber());
        entity.setCompanysEnabled(0);

        Long companysIdx = entity.getCompanysIdx();
        log.debug("[updateCompanys] update wait entity: {}", entity.toString());

        int step1 = companysService.updateCompanys02(entity);

        if (step1 != 0) {
            int deletePrevCategory = companysCategoryService.deleteCategoryByCompanysIdx(companysIdx);
            int deletePrevActiveRegion = companysActiveRegionService.deleteActiveRegionByCompanysIdx(companysIdx);

            for (int i = 0; i < companysCategory.size(); i++) {
                CompanysCategory entity2 = new CompanysCategory();
                entity2.setCompanysIdx(companysIdx);
                entity2.setCompanysCategoryValue(companysCategory.get(i));
                companysCategoryService.insertCompanysCategory(entity2);
            }

            for (int i = 0; i < companysActiveRegions.size(); i++) {
                CompanysActiveRegion entity3 = new CompanysActiveRegion();
                entity3.setCompanysIdx(companysIdx);
                entity3.setActiveRegion(companysActiveRegions.get(i));
                companysActiveRegionService.insertCompanysActiveRegion(entity3);
            }

            log.debug("[updateCompanys] line542 update Done");
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem("SUCCESS");
            result.setResponseDateTime(LocalDateTime.now());
        } else {
            log.debug("[updateCompanys] line542 update FAIL");
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem("FAILURE");
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

    /**
     * companysIdx 기반의 탐정업체 정보 조회(2)
     * @param companysIdx
     * @return
     */
    @GetMapping("/selectOneCompanysByCompanysIdxTest")
    public CustomResponseData selectOneCompanysByCompanysIdxTest(@RequestParam("companysIdx") Long companysIdx) {
        CustomResponseData result = new CustomResponseData();
        SelectCompanys item = companysService.selectOneCompanysByCompanysIdxTest(companysIdx);

        result.setResultItem(item);

        return result;
    }

    
    /** 
     * @param selectOneCompanysUserLikeCompanyEnableByCompanysIdxUsersIdxTest(
     * @return CustomResponseData
     */
    @GetMapping("/selectOneCompanysUserLikeCompanyEnableByCompanysIdxUsersIdx")
    public CustomResponseData selectOneCompanysUserLikeCompanyEnableByCompanysIdxUsersIdxTest(
            @RequestParam("companysIdx") Long companysIdx, @RequestParam("usersIdx") Long usersIdx) {
        CustomResponseData result = new CustomResponseData();
        log.debug("[selectOneCompanysUserLikeCompanyEnableByCompanysIdxUsersIdxTest] companysIdx: {}, usersIdx: {}",
                companysIdx, usersIdx);
        SelectCompanysUserLikeCompanyEnable item = companysService
                .selectOneCompanysUserLikeCompanyEnableByCompanysIdxUsersIdx(companysIdx, usersIdx);
        result.setResultItem(item);
        return result;
    }

    
    /** 
     * @param selectListCompanysUserLikeCompanyEnableByCompanysIdxUsersIdxTest(
     * @return CustomResponseData
     */
    @GetMapping("/selectListCompanysUserLikeCompanyEnableByCompanysIdxUsersIdx")
    public CustomResponseData selectListCompanysUserLikeCompanyEnableByCompanysIdxUsersIdxTest(
            @RequestParam("companysIdx") List<Long> companysIdxList,
            @RequestParam("usersIdx") Long usersIdx) {
        CustomResponseData result = new CustomResponseData();
        log.debug("[selectOneCompanysUserLikeCompanyEnableByCompanysIdxUsersIdxTest] companysIdx: {}",
                companysIdxList.toString());
        log.debug("[selectOneCompanysUserLikeCompanyEnableByCompanysIdxUsersIdxTest] usersIdx: {}", usersIdx);
        List<SelectCompanysUserLikeCompanyEnable> items = new ArrayList<>();

        for (int i = 0; i < companysIdxList.size(); i++) {
            items.add(companysService
                    .selectOneCompanysUserLikeCompanyEnableByCompanysIdxUsersIdx(companysIdxList.get(i), usersIdx));
        }
        if (items != null) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(items);
            result.setResponseDateTime(LocalDateTime.now());
        } else {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(null);
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

    
    /** 
     * @param usersIdx
     * @return CustomResponseData
     */
    @GetMapping("/selectCompanysPremiumEnabled")
    public CustomResponseData selectCompanysPremiumEnabled(@RequestParam("usersIdx") Long usersIdx) {
        CustomResponseData result = new CustomResponseData();
        Map<String, Object> item = new HashMap<>();
        Companys selectItem = companysService.selectCompanysPremiumEnabled(usersIdx);

        if (selectItem != null) {
            item.put("companysPremium", selectItem.isCompanysPremium() ? 1 : 0);
            item.put("companysLocalPremium", selectItem.isCompanysLocalPremium() ? 1 : 0);
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(item);
            result.setResponseDateTime(LocalDateTime.now());
        } else {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(null);
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

}
