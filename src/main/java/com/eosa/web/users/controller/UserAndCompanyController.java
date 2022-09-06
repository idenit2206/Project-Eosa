package com.eosa.web.users.controller;

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
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/userAndCompany")
public class UserAndCompanyController {

    @Autowired private UserLikeCompanyService userLikeCompanyService;
    @Autowired private UserRecentCompanyService userRecentCompanyService;

    /**
     * CLIENT 사용자의 업체에 대한 좋아요 활성화 버튼
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
     * 좋아요 취소
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
     * 사용자가 최근에 조회한 업체를 24개까지 기록
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

    @GetMapping("/selectUserRecentCompanyByUsersIdx")
    public CustomResponseData selectUserRecentCompanyByUsersIdx(
        @RequestParam("usersIdx") Long usersIdx
    ) {
        CustomResponseData result = new CustomResponseData();
        List<UserRecentCompany> items = userRecentCompanyService.findByUsersIdx(usersIdx);

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
