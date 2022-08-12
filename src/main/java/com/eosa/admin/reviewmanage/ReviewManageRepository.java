package com.eosa.admin.reviewmanage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eosa.web.usersreview.UsersReview;

@Repository
public interface ReviewManageRepository extends JpaRepository<UsersReview, Long> {
    
    @Query(
        value="SELECT * FROM UsersReview LIMIT ?1, ?2",
        nativeQuery=true
    )
    List<UsersReview> findAllUsersReview(int currentPageStartPost, int POST_COUNT);

    @Query(
        value="SELECT COUNT(*) FROM UsersReview",
        nativeQuery=true
    )
    int findAllReviewCount();

}
