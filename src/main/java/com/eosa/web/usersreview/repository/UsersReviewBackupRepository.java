package com.eosa.web.usersreview.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eosa.web.usersreview.entity.SelectReviewEntity;
import com.eosa.web.usersreview.entity.UsersReviewBackup;

public interface UsersReviewBackupRepository extends JpaRepository <UsersReviewBackup, Long> {

    @Modifying
    @Transactional
    @Query(
        value="INSERT INTO UsersReviewBackup(" +
        "usersReivewIdx, reviewUsersIdx, reviewCompanysIdx, reviewRequestFormIdx, " +
        "resultScore, communicationScore, processScore, specialityScore, " +
        "reviewDetail, reviewDate) " +
        "VALUES(:#{#UsersReview.usersReviewIdx}, :#{#UsersReview.reviewUsersIdx}, :#{#UsersReview.reviewCompanysIdx}, :#{#UsersReview.reviewRequestFormIdx}, " +
        ":#{#UsersReview.resultScore}, :#{#UsersReview.communicationScore}, :#{#UsersReview.processScore}, :#{#UsersReview.specialityScore}, " +
        ":#{#UsersReview.reviewDetail}, :#{#UsersReview.reviewDate})"
        ,nativeQuery=true
    )
    int insertUsersReview(@Param("UsersReview") UsersReviewBackup param);

    @Query(
    value="SELECT * " +
          "FROM UsersReviewBackup as ur LEFT JOIN Users as u ON u.usersIdx=ur.reviewUsersIdx " +
          "LEFT JOIN Companys as c ON c.companysIdx=ur.reviewCompanysIdx"
    , nativeQuery = true)
    List<SelectReviewEntity> selectAllUsersReview();

    @Query(
            value="SELECT * " +            
            "FROM UsersReviewBackup UR " +
            "LEFT JOIN Companys C ON UR.reviewCompanysIdx = C.companysIdx " +
            "LEFT JOIN Users U ON UR.reviewUsersIdx = U.usersIdx " +
            "WHERE UR.reviewCompanysIdx = ?1",
            nativeQuery = true
    )
    List<SelectReviewEntity> selectUsersReviewByCompanysIdx(Long comapnysIdx);

    @Query(
            value="SELECT * FROM UsersReviewBackup " +
                    "WHERE reviewUsersIdx = ?1",
            nativeQuery = true
    )
    List<SelectReviewEntity> selectUsersReviewByUsersIdx(Long usersIdx);

    @Query(value="SELECT * FROM UsersReviewBackup WHERE reviewRequestFormIdx = ?1", nativeQuery = true)
    SelectReviewEntity selectOneUsersReviewByRequestFormIdx(Long requestFormIdx);

}
