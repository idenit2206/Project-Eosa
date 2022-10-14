package com.eosa.web.requestform.repository.tablebackup;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eosa.web.requestform.entity.RequestForm;
import com.eosa.web.requestform.entity.SelectRequestFormList;

@Repository
public interface RequestFormBackupRepository extends JpaRepository<RequestForm, Long> {

    @Modifying
    @Transactional
    @Query(value="INSERT INTO RequestFormBackup(usersIdx, companysIdx, " +
        "requestFormRegion1, requestFormRegion2, requestFormStatus, requestFormDate)" +
        "VALUES(" +
        ":#{#RequestFormBackup.usersIdx}, :#{#RequestFormBackup.companysIdx}, " +
        ":#{#RequestFormBackup.requestFormRegion1}, :#{#RequestFormBackup.requestFormRegion2}," +
        ":#{#RequestFormBackup.requestFormStatus}, :#{#RequestFormBackup.requestFormDate})"
    ,nativeQuery=true)
    int requestFormRegister(@Param("RequestFormBackup") RequestForm entity);

    @Query(
        value="SELECT " +
            "R.requestFormIdx, R.usersIdx, R.companysIdx, " +
            "R.requestFormRegion1, R.requestFormRegion2, " +
            "R.requestFormStatus, R.requestConsultDate, R.requestFormDate, " +
            "R.requestFormAcceptDate, R.requestFormCompDate, R.requestFormRejectMessage, " +
            "GROUP_CONCAT(RFC.requestFormCategoryValue) AS requestFormCategory " +
            "FROM RequestFormBackup R INNER JOIN RequestFormCategoryBackup RFC ON R.requestFormIdx = RFC.requestFormIdx " +
            "WHERE R.companysIdx = ?1 " +
            "GROUP BY R.RequestFormIdx",
        nativeQuery=true
    )
    List<SelectRequestFormList> findByCompanysIdxIdx(Long companysIdx);

    @Query(
        value="SELECT " + 
        "R.requestFormIdx, R.usersIdx, " +
        "U.usersAge, U.usersGender, R.companysIdx, " +
        "C.companysPremium, " +
        "R.requestFormRegion1, R.requestFormRegion2, " +
        "R.requestFormStatus, R.requestConsultDate, R.requestFormDate, " +
        "R.requestFormAcceptDate, R.requestFormCompDate, R.requestFormRejectMessage, " +
        "GROUP_CONCAT(RFC.requestFormCategoryValue) AS requestFormCategory " +
        "FROM RequestFormBackup R INNER JOIN RequestFormCategoryBackup RFC ON R.requestFormIdx = RFC.requestFormIdx " +
        "LEFT OUTER JOIN Users U ON U.usersIdx = R.usersIdx " +
        "LEFT OUTER JOIN Companys C on R.companysIdx = C.companysIdx " +
        "GROUP BY R.RequestFormIdx",
        nativeQuery=true
    )
    List<SelectRequestFormList> selectAllRequestFormList();

    @Query(
        value="SELECT " + 
        "R.requestFormIdx, R.usersIdx, R.companysIdx, " +
        "R.requestFormRegion1, R.requestFormRegion2, " +
        "R.requestFormStatus, R.requestConsultDate, R.requestFormDate, " +
        "R.requestFormAcceptDate, R.requestFormCompDate, R.requestFormRejectMessage, " +
        "GROUP_CONCAT(RFC.requestFormCategoryValue) AS requestFormCategory " +
        "FROM RequestFormBackup R INNER JOIN RequestFormCategoryBackup RFC ON R.requestFormIdx = RFC.requestFormIdx " +
        "WHERE R.usersIdx = ?1 " +
        "GROUP BY R.RequestFormIdx",
        nativeQuery=true
    )
    List<SelectRequestFormList> selectAllRequestFormListByUsersIdx(Long usersIdx);

    @Query(
    value="SELECT " +
    "R.requestFormIdx, R.usersIdx, U.usersNick, R.companysIdx, " +
    "C.companysName, C.companysPremium, " +
    "R.requestFormRegion1, R.requestFormRegion2, " +
    "R.requestFormStatus, R.requestConsultDate, R.requestFormDate, " +
    "R.requestFormAcceptDate, R.requestFormCompDate, R.requestFormRejectMessage, " +
    "GROUP_CONCAT(RFC.requestFormCategoryValue) AS requestFormCategory " +
    "FROM RequestFormBackup R INNER JOIN RequestFormCategoryBackup RFC ON R.requestFormIdx = RFC.requestFormIdx " +
    "JOIN Companys C ON R.companysIdx = C.companysIdx " +
    "LEFT JOIN Users U ON R.usersIdx = U.usersIdx " +
    "WHERE R.usersIdx = ?1 " +
    "GROUP BY R.RequestFormIdx " +
    "ORDER BY R.requestFormDate DESC",
    nativeQuery=true
    )
    List<SelectRequestFormList> selectAllRequestFormListByUsersIdxOrderByRequestFormDateDESC(Long usersIdx);

    @Query(value = "SELECT * FROM RequestFormBackup R WHERE R.requestFormIdx = ?1", nativeQuery = true)
    RequestForm selectOneRequestFormByRequsetFormIdx(Long requestFormIdx);


    // 알림을 위한 조회 쿼리

    @Query(value =
        "SELECT R.requestFormIdx, R.usersIdx, R.companysIdx, R.requestFormRegion1, R.requestFormChannel, R.requestFormStatus, " +
        "R.requestFormStatusChangeDate, " +
        "R.requestFormDate, R.requestConsultDate, R.requestFormAcceptDate, R.requestFormCompDate, R.requestFormRejectMessage, " +
        "R.requestFormClientReadState, R.requestFormClientReadDate, " +
        "R.requestFormDetectiveReadState, R.requestFormDetectiveReadDate, " +
        "GROUP_CONCAT(RFC.requestFormCategoryValue) AS requestFormCategory " +
        "FROM RequestFormBackup R " +
        "LEFT JOIN RequestFormCategoryBackup RFC on R.requestFormIdx = RFC.requestFormIdx " +
        "LEFT JOIN Users U ON R.usersIdx = U.usersIdx " +
        // "WHERE R.usersIdx = ?1 AND R.requestFormClientReadState = 0 " +
        "WHERE U.usersIdx = ?1 " +
        "GROUP BY R.requestFormIdx, R.usersIdx, R.companysIdx, R.requestFormRegion1, R.requestFormChannel, R.requestFormStatus, R.requestFormDate, R.requestConsultDate, R.requestFormAcceptDate, R.requestFormCompDate, R.requestFormRejectMessage " +
        "ORDER BY R.requestFormStatusChangeDate DESC"
    ,nativeQuery = true)
    List<RequestForm> selectRequestFormByUsersIdx(Long usersIdx);

    @Query(value =
        "SELECT R.requestFormIdx, R.usersIdx, R.companysIdx, R.requestFormRegion1, R.requestFormChannel, R.requestFormStatus, " +
        "R.requestFormStatusChangeDate, " +
        "R.requestFormDate, R.requestConsultDate, R.requestFormAcceptDate, R.requestFormCompDate, R.requestFormRejectMessage, " +
        "R.requestFormClientReadState, R.requestFormClientReadDate, " +
        "R.requestFormDetectiveReadState, R.requestFormDetectiveReadDate, " +
        "GROUP_CONCAT(RFC.requestFormCategoryValue) AS requestFormCategory " +
        "FROM RequestFormBackup R " +
        "LEFT JOIN RequestFormCategoryBackup RFC on R.requestFormIdx = RFC.requestFormIdx " +
        "WHERE R.companysIdx = ?1 " +
        "GROUP BY R.requestFormIdx, R.usersIdx, R.companysIdx, R.requestFormRegion1, R.requestFormChannel, R.requestFormStatus, R.requestFormDate, R.requestConsultDate, R.requestFormAcceptDate, R.requestFormCompDate, R.requestFormRejectMessage " +
        "ORDER BY R.requestFormStatusChangeDate DESC"
    ,nativeQuery = true)
    List<RequestForm> selectPushNotificationForDetective(Long companysIdx);

    @Query(value =
        "SELECT R.requestFormIdx, R.usersIdx, R.companysIdx, R.requestFormRegion1, R.requestFormChannel, R.requestFormStatus, " +
        "R.requestFormDate, R.requestConsultDate, R.requestFormAcceptDate, R.requestFormCompDate, R.requestFormRejectMessage, " +
        "R.requestFormClientReadState, R.requestFormClientReadDate, " +
        "R.requestFormDetectiveReadState, R.requestFormDetectiveReadDate, " +
        "GROUP_CONCAT(RFC.requestFormCategoryValue) AS requestFormCategory " +
        "FROM RequestFormBackup R " +
        "LEFT JOIN RequestFormCategoryBackup RFC on R.requestFormIdx = RFC.requestFormIdx " +
        "WHERE R.companysIdx = ?1 " +
        "GROUP BY R.requestFormIdx, R.usersIdx, R.companysIdx, R.requestFormRegion1, R.requestFormChannel, R.requestFormStatus, R.requestFormDate, R.requestConsultDate, R.requestFormAcceptDate, R.requestFormCompDate, R.requestFormRejectMessage " +
        "ORDER BY R.requestFormClientReadDate DESC"
    ,nativeQuery = true)
    List<RequestForm> selectRequestFormByCompanysIdx(Long companysIdx);

    @Transactional
    @Modifying
    @Query(value = 
        "UPDATE RequestFormBackup R " + 
        "SET R.requestFormClientReadState = 1, R.requestFormClientReadDate = now() " + 
        "WHERE R.requestFormIdx = ?1"
        ,nativeQuery = true
    )
    int updateReadStateRead(Long requestFormIdx);

    @Transactional
    @Modifying
    @Query(value = 
        "UPDATE RequestFormBackup R " + 
        "SET R.requestFormDetectiveReadState = 1, R.requestFormDetectiveReadDate = now() " + 
        "WHERE R.requestFormIdx = ?1 AND R.companysIdx = ?2"
        ,nativeQuery = true
    )
    int updateReadStateReadDetective(Long requestFormIdx, Long companysIdx);
}
