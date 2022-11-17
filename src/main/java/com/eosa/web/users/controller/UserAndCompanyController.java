package com.eosa.web.users.controller;

import com.eosa.web.companys.entity.SelectAllCompanysList;
import com.eosa.web.companys.entity.SelectCompanys;
import com.eosa.web.companys.entity.SelectCompanysUserLikeCompanyEnable;
import com.eosa.web.companys.service.CompanysService;
import com.eosa.web.users.entity.UserLikeCompany;
import com.eosa.web.users.entity.UserRecentCompany;
import com.eosa.web.users.service.UserLikeCompanyService;
import com.eosa.web.users.service.UserRecentCompanyService;
import com.eosa.web.util.CustomResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api/userAndCompany")
public class UserAndCompanyController {

    @Autowired
    private UserLikeCompanyService userLikeCompanyService;

    @Autowired
    private UserRecentCompanyService userRecentCompanyService;

    @Autowired
    private CompanysService companysService;

    /**
     * CLIENT 사용자의 업체에 대한 좋아요를 수행하는 컨트롤러
     * @param param
     * @return
     */
    @PostMapping("/insertUserLikeCompany")
    public CustomResponseData insertUserLikeCompany(UserLikeCompany param) {
        CustomResponseData result = new CustomResponseData();
//        log.debug("[insetUserLikeCompany] {}", param.toString());
        UserLikeCompany insertData = userLikeCompanyService.save(param);

        if(insertData != null) {
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

    /**
     * 좋아요 취소를 수행하는 컨트롤러
     * @param usersIdx Long
     * @param companysIdx Long
     * @return
     */
    @DeleteMapping("/deleteUserLikeCompany")
    public CustomResponseData deleteUserLikeCompany(
            @RequestParam("usersIdx") Long usersIdx,
            @RequestParam("companysIdx") Long companysIdx
    ) {
        CustomResponseData result = new CustomResponseData();

        int deleteRows = userLikeCompanyService.deleteByUsersAndCompanysIdx(usersIdx, companysIdx);

        if(deleteRows == 1) {
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

    /**
     * usersIdx 사용자의 좋아요 목록 출력하는 컨트롤러
     */
    @GetMapping("/selectLikeCompanysListByUsersIdx")
    public CustomResponseData selectLikeCompanysListByUsersIdx(@RequestParam("usersIdx") Long usersIdx) {
        CustomResponseData result = new CustomResponseData();

        List<SelectCompanysUserLikeCompanyEnable> items = userLikeCompanyService.selectLikeCompanysListByUsersIdx(usersIdx);

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
     * 사용자가 최근에 조회한 업체를 24개까지 저장하는 컨트롤러
     * 오래된것 부터 삭제
     * @return
     */
    @PostMapping("/userRecentCompany")
    public CustomResponseData insertUserRecentCompany(UserRecentCompany body) {
        CustomResponseData result = new CustomResponseData();
        UserRecentCompany entity = body;

        Long usersIdx = null;

        int userRecentCompanyCount = userRecentCompanyService.countByUsersIdx(usersIdx);
        if(userRecentCompanyCount < 24) {
            UserRecentCompany insertEntity = userRecentCompanyService.save(entity);

            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem("TRUE");
            result.setResponseDateTime(LocalDateTime.now());
        } else {
            Long oldestOne = userRecentCompanyService.selectOldestOneIdxByDate();
            userRecentCompanyService.deleteOldestOne(oldestOne);
            UserRecentCompany insertEntity = userRecentCompanyService.save(entity);

            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem("FALSE");
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

    /**
     * usersIdx의 최근 방문 업체를 목록을 조회하는 컨트롤러
     * @param usersIdx
     * @return
     */
    @GetMapping("/selectUserRecentCompanyByUsersIdx")
    public CustomResponseData selectUserRecentCompanyByUsersIdx(
        @RequestParam("usersIdx") Long usersIdx
    ) {
        CustomResponseData result = new CustomResponseData();
        Map<String, Object> items = new HashMap<>();
        List<UserRecentCompany> userRecentCompanyList = userRecentCompanyService.findByUsersIdx(usersIdx);
        List<SelectAllCompanysList> item1 = new ArrayList<>();
        List<Integer> item2 = new ArrayList<>();

        for(int i = 0; i < userRecentCompanyList.size(); i++) {
            Long companysIdx = userRecentCompanyList.get(i).getCompanysIdx();
            SelectAllCompanysList company = companysService.selectCompanysByCompanysIdx(companysIdx);
            item1.add(company);
        }


        if(items != null) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(item1);
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
