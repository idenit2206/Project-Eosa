package com.eosa.web.users.controller;

import com.eosa.web.users.entity.UserRecentCompany;
import com.eosa.web.users.service.UserRecentCompanyService;
import com.eosa.web.util.CustomResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController("/api/userAndCompany")
public class UserAndCompany {

    @Autowired
    private UserRecentCompanyService userRecentCompanyService;

    /**
     * 사용자가 최근에 조회한 업체를 24개까지 기록
     * 오래된것 부터 삭제
     * @return
     */
    @PostMapping("/userRecentCompany")
    public CustomResponseData insertUserRecentCompany(@RequestBody UserRecentCompany body) {
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



}
