package com.eosa.web.usersreview;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersReviewRepository extends JpaRepository<UsersReview, Long>{

    @Modifying
    @Transactional
    @Query(
        value="INSERT INTO UsersReview(" +
        "usersIdx, companysIdx, requestFormIdx, " +
        "resultScore, communicationScore, processScore, specialityScore, " +
        "reviewDetail, reviewDate) " +
        "VALUES(:#{#UsersReview.usersIdx}, :#{#UsersReview.companysIdx}, :#{#UsersReview.requestFormIdx}, " +
        ":#{#UsersReview.resultScore}, :#{#UsersReview.communicationScore}, :#{#UsersReview.processScore}, :#{#UsersReview.specialityScore}, " +
        ":#{#UsersReview.reviewDetail}, :#{#UsersReview.reviewDate})"
        ,nativeQuery=true
    )
    int registUsersReview(@Param("UsersReview") UsersReview param);

}
