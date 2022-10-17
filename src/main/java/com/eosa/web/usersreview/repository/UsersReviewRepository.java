package com.eosa.web.usersreview.repository;

import javax.transaction.Transactional;

import com.eosa.web.usersreview.entity.SelectReviewEntity;
import com.eosa.web.usersreview.entity.UsersReview;
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
        "reviewUsersIdx, reviewCompanysIdx, reviewRequestFormIdx, " +
        "resultScore, communicationScore, processScore, specialityScore, " +
        "reviewDetail, reviewDate) " +
        "VALUES(:#{#UsersReview.reviewUsersIdx}, :#{#UsersReview.reviewCompanysIdx}, :#{#UsersReview.reviewRequestFormIdx}, " +
        ":#{#UsersReview.resultScore}, :#{#UsersReview.communicationScore}, :#{#UsersReview.processScore}, :#{#UsersReview.specialityScore}, " +
        ":#{#UsersReview.reviewDetail}, :#{#UsersReview.reviewDate})"
        ,nativeQuery=true
    )
    int insertUsersReview(@Param("UsersReview") UsersReview param);

    @Query(
    value="SELECT * " +
          "FROM UsersReview as ur LEFT JOIN Users as u ON u.usersIdx=ur.reviewUsersIdx " +
          "LEFT JOIN Companys as c ON c.companysIdx=ur.reviewCompanysIdx"
    , nativeQuery = true)
    List<SelectReviewEntity> selectAllUsersReview();

    @Query(
            value="SELECT * " +            
            "FROM UsersReview UR " +
            "LEFT JOIN Companys C ON UR.reviewCompanysIdx = C.companysIdx " +
            "LEFT JOIN Users U ON UR.reviewUsersIdx = U.usersIdx " +
            "WHERE UR.reviewCompanysIdx = ?1",
            nativeQuery = true
    )
    List<SelectReviewEntity> selectUsersReviewByCompanysIdx(Long comapnysIdx);

    @Query(
            value="SELECT * FROM UsersReview " +
                    "WHERE reviewUsersIdx = ?1",
            nativeQuery = true
    )
    List<SelectReviewEntity> selectUsersReviewByUsersIdx(Long usersIdx);

    @Query(value="SELECT * FROM UsersReview WHERE reviewRequestFormIdx = ?1", nativeQuery = true)
    SelectReviewEntity selectOneUsersReviewByRequestFormIdx(Long requestFormIdx);
}
