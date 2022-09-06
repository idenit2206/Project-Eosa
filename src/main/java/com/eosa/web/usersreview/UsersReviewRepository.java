package com.eosa.web.usersreview;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    int insertUsersReview(@Param("UsersReview") UsersReview param);

    @Query(
            value="SELECT * FROM UsersReview " +
            "WHERE companysIdx = ?1",
            nativeQuery = true
    )
    List<UsersReview> selectUsersReviewByCompanysIdx(Long comapnysIdx);

    @Query(
            value="SELECT * FROM UsersReview " +
                    "WHERE usersIdx = ?1",
            nativeQuery = true
    )
    List<UsersReview> selectUsersReviewByUsersIdx(Long usersIdx);

    @Query(value="SELECT * FROM UsersReview WHERE requestFormIdx = ?1", nativeQuery = true)
    UsersReview selectOneUsersReviewByRequestFormIdx(Long requestFormIdx);
}
