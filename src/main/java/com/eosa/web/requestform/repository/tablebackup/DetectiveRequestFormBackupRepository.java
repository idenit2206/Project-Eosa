package com.eosa.web.requestform.repository.tablebackup;

import com.eosa.web.requestform.entity.RequestForm;
import com.eosa.web.requestform.entity.SelectRequestFormList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DetectiveRequestFormBackupRepository extends JpaRepository<RequestForm, Long> {

    @Query(
            value="SELECT " +
                    "R.requestFormIdx, R.usersIdx, Users.usersAccount, R.companysIdx, " +
                    "R.requestFormRegion1, R.requestFormRegion2, " +
                    "R.requestFormStatus, R.requestConsultDate, R.requestFormDate, " +
                    "R.requestFormAcceptDate, R.requestFormCompDate, R.requestFormRejectMessage, " +
                    "GROUP_CONCAT(RFC.requestFormCategoryValue) AS requestFormCategory " +
                    "FROM RequestFormBackup INNER JOIN RequestFormCategoryBackup RFC ON R.requestFormIdx = RFC.requestFormIdx " +
                    "INNER JOIN Users ON R.usersIdx = Users.usersIdx " +
                    "WHERE R.companysIdx = ?1 " +
                    "GROUP BY R.RequestFormIdx",
            nativeQuery=true
    )
    List<SelectRequestFormList> selectAllDetectiveRequestFormListByCompanysIdx(Long companysIdx);

    @Query(
            value="SELECT " +
                    "R.requestFormIdx, R.usersIdx, " + 
                    "Users.usersAccount, Users.usersNick, Users.usersAge, Users.usersGender, " +
                    "R.companysIdx, " +
                    "C.companysName, C.companysPremium, " +
                    "R.requestFormRegion1, R.requestFormRegion2, " +
                    "R.requestFormStatus, R.requestConsultDate, R.requestFormDate, " +
                    "R.requestFormAcceptDate, R.requestFormCompDate, R.requestFormRejectMessage, " +
                    "GROUP_CONCAT(RFC.requestFormCategoryValue) AS requestFormCategory " +
                    "FROM RequestFormBackup R INNER JOIN RequestFormCategoryBackup RFC ON R.requestFormIdx = RFC.requestFormIdx " +
                    "INNER JOIN Users ON R.usersIdx = Users.usersIdx " +
                    "LEFT JOIN Companys C ON R.companysIdx = C.companysIdx " +
                    "WHERE R.companysIdx = ?1 " +
                    "GROUP BY R.RequestFormIdx " +
                    "ORDER BY R.requestFormDate DESC",
            nativeQuery=true
    )
    List<SelectRequestFormList> selectAllDetectiveRequestFormListByCompanysIdxOrderByDESC(Long companysIdx);

    @Query(
            value="SELECT " +
                    "R.requestFormIdx, R.usersIdx, Users.usersAccount, Users.usersAge, Users.usersGender, R.companysIdx, " +
                    "R.requestFormRegion1, R.requestFormRegion2, " +
                    "R.requestFormStatus, R.requestConsultDate, R.requestFormDate, " +
                    "R.requestFormAcceptDate, R.requestFormCompDate, R.requestFormRejectMessage, " +
                    "GROUP_CONCAT(RFC.requestFormCategoryValue) AS requestFormCategory " +
                    "FROM RequestFormBackup R INNER JOIN RequestFormCategoryBackup RFC ON R.requestFormIdx = RFC.requestFormIdx " +
                    "INNER JOIN Users ON R.usersIdx = Users.usersIdx " +
                    "WHERE R.companysIdx = ?1 " +
                    "GROUP BY R.RequestFormIdx " +
                    "ORDER BY R.requestFormDate ASC",
            nativeQuery=true
    )
    List<SelectRequestFormList> selectAllDetectiveRequestFormListByCompanysIdxOrderByASC(Long companysIdx);

    @Query(value=
            "SELECT * FROM RequestFormBackup " +
            "WHERE requestFormIdx = ?1",
            nativeQuery = true
    )
    RequestForm selectDetectiveRequestFormInfoByRequestFormIdx(Long requestFormIdx);

    @Query(
        value =
        "SELECT R.requestFormIdx, R.usersIdx, R.companysIdx, R.requestFormRegion1, R.requestFormChannel, R.requestFormStatus, " +
        "R.requestFormStatusChangeDate, " +
        "R.requestFormDate, R.requestConsultDate, R.requestFormAcceptDate, R.requestFormCompDate, R.requestFormRejectMessage, " +
        "R.requestFormClientReadState, R.requestFormClientReadDate, " +
        "R.requestFormDetectiveReadState, R.requestFormDetectiveReadDate, " +
        "GROUP_CONCAT(RFC.requestFormCategoryValue) AS requestFormCategoryValue " +
        "FROM RequestFormBackup R " +
        "LEFT JOIN RequestFormCategoryBackup RFC on R.requestFormIdx = RFC.requestFormIdx " +
        "WHERE R.requestFormIdx = ?1 " +
        "GROUP BY R.requestFormIdx, R.usersIdx, R.companysIdx, R.requestFormRegion1, R.requestFormChannel, R.requestFormStatus, R.requestFormDate, R.requestConsultDate, R.requestFormAcceptDate, R.requestFormCompDate, R.requestFormRejectMessage"
        ,nativeQuery = true
    )
    RequestForm selectRequestFormByRequestFormIdx(Long requestFormIdx);

    @Transactional
    @Modifying
    @Query(value=
        "UPDATE RequestFormBackup " +
        "SET " +
        "requestFormStatus = :requestFormStatus, requestFormRejectMessage = :requestFormRejectMessage " +
        "WHERE requestFormIdx = :requestFormIdx",
         nativeQuery = true
    )
    int updateRequestFormStatusByRequestFormIdx(
        @Param("requestFormIdx") Long requestFormIdx,
        @Param("requestFormStatus") String requestFormStatus,
        @Param("requestFormRejectMessage") String requestFormRejectMessage
    );

    @Transactional
    @Modifying
    @Query(
        value = 
        "UPDATE RequestFormBackup " +
        "SET " +
        "requestFormStatus = :#{#R.requestFormStatus}, requestFormStatusChangeDate = NOW(), " +
        "requestFormRejectMessage = :#{#R.requestFormRejectMessage}, " +
        "requestFormAcceptDate = :#{#R.requestFormAcceptDate}, requestFormCompDate = :#{#R.requestFormCompDate}, " +
        "requestFormClientReadState = :#{#R.requestFormClientReadState}, requestFormClientReadDate = NOW(), " +
        "requestFormDetectiveReadState = 0 " +
        "WHERE requestFormIdx = :#{#R.requestFormIdx}"
        ,nativeQuery = true
    )
    int updateRequestFormByEntity(@Param("R") RequestForm entity) throws IOException;

    @Transactional
    @Modifying
    @Query(value=
        "UPDATE RequestFormBackup " +
        "SET " +
        "requestConsultDate = :requestFormAcceptDate, " +
        "requestFormStatus = :requestFormStatus, requestFormStatusChangeDate = NOW(), " + 
        "requestFormRejectMessage = :requestFormRejectMessage, " +
        "requestFormClientReadState = 0, requestFormClientReadDate = NOW(), " + 
        "requestFormDetectiveReadState = 0 " +
        "WHERE requestFormIdx = :requestFormIdx",
        nativeQuery = true
    )
    int updateRequestFormStatusByRequestFormIdxCaseConsultComplete(
            @Param("requestFormIdx") Long requestFormIdx,
            @Param("requestFormAcceptDate") LocalDateTime requestFormAcceptDate,
            @Param("requestFormStatus") String requestFormStatus,
            @Param("requestFormRejectMessage") String requestFormRejectMessage
    );

    @Transactional
    @Modifying
    @Query(value=
            "UPDATE RequestFormBackup " +
                    "SET " +
                    "requestFormCompDate = :requestFormAcceptDate, " +
                    "requestFormStatus = :requestFormStatus, requestFormRejectMessage = :requestFormRejectMessage " +
                    "WHERE requestFormIdx = :requestFormIdx",
            nativeQuery = true
    )
    int updateRequestFormStatusByRequestFormIdxCaseMissionComplete(
            @Param("requestFormIdx") Long requestFormIdx,
            @Param("requestFormAcceptDate") LocalDateTime requestCompAcceptDate,
            @Param("requestFormStatus") String requestFormStatus,
            @Param("requestFormRejectMessage") String requestFormRejectMessage
    );
}
