package com.eosa.web.usersreview;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eosa.web.util.CustomResponseData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value="/api/usersreview")
public class UsersReviewController {

    @Autowired UsersReviewService usersReviewService;

    @PostMapping("/registUsersReview")
    public CustomResponseData registUsersReview(UsersReview param) {
        CustomResponseData result = new CustomResponseData();

        int transaction = usersReviewService.registUsersReview(param);

        result.setResponseDateTime(LocalDateTime.now());
        return result;
    } 
    
}
