package com.eosa.demo.usersreview;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eosa.demo.util.CustomResponseData;

@RestController
@RequestMapping(value="/api/usersreview")
public class UsersReviewController {

    @PostMapping("/registUsersReview")
    public CustomResponseData registUsersReview() {
        CustomResponseData result = new CustomResponseData();

        result.setResponseDateTime(LocalDateTime.now());
        return result;
    } 
    
}
