package com.eosa.web.usersreview;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersReviewService implements UsersReviewRepository{

    @Autowired UsersReviewRepository usersReviewRepository;

    public int registUsersReview(UsersReview param) {
        param.setReviewDate(LocalDateTime.now());
        return usersReviewRepository.registUsersReview(param);
    }

}
