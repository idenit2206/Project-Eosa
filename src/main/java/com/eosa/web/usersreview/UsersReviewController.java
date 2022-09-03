package com.eosa.web.usersreview;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.eosa.web.util.CustomResponseData;

import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Null;

@Slf4j
@RestController
@RequestMapping(value="/api/usersReview")
public class UsersReviewController {

    @Autowired UsersReviewService usersReviewService;

    @PostMapping("/insertUsersReview")
    public CustomResponseData insertUsersReview(UsersReview param) {
        CustomResponseData result = new CustomResponseData();
        int transaction = usersReviewService.insertUsersReview(param);
        log.debug("[insertUsersReview]: {}", String.valueOf(transaction));

        if(transaction == 1) {
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
     * 특정 companysIdx를 갖는 리뷰를 전부 조회
     * @param companysIdx
     * @return
     */
    @GetMapping("/selectUsersReviewByCompanysIdx")
    public CustomResponseData selectUsersReviewByCompanysIdx(@RequestParam("companysIdx") Long companysIdx) {
        CustomResponseData result = new CustomResponseData();
        List<UsersReview> items = usersReviewService.selectUsersReviewByCompanysIdx(companysIdx);

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
     * 특정 usersIdx를 갖는 리뷰를 전부 조회
     * @param usersIdx
     * @return
     */
    @GetMapping("/selectUsersReviewByUsersIdx")
    public CustomResponseData selectUsersReviewByUsersIdx(@RequestParam("usersIdx") Long usersIdx) {
        CustomResponseData result = new CustomResponseData();
        List<UsersReview> items = usersReviewService.selectUsersReviewByUsersIdx(usersIdx);

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
