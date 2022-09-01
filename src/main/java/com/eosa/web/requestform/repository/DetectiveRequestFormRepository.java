package com.eosa.web.requestform.repository;

import com.eosa.web.requestform.entity.RequestForm;
import com.eosa.web.requestform.entity.SelectRequestFormList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetectiveRequestFormRepository extends JpaRepository<RequestForm, Long> {

    @Query(
            value="SELECT " +
                    "RequestForm.requestFormIdx, RequestForm.usersIdx, RequestForm.companysIdx, " +
                    "RequestForm.requestFormRegion1, RequestForm.requestFormRegion2, " +
                    "RequestForm.requestFormStatus, RequestForm.requestConsultDate, RequestForm.requestFormDate, " +
                    "RequestForm.requestFormAcceptDate, RequestForm.requestFormCompDate, RequestForm.requestFormRejectMessage, " +
                    "GROUP_CONCAT(RequestFormCategory.requestFormCategoryValue) AS requestFormCategory " +
                    "FROM RequestForm INNER JOIN RequestFormCategory ON RequestForm.requestFormIdx = RequestFormCategory.requestFormIdx " +
                    "WHERE RequestForm.companysIdx = ?1 " +
                    "GROUP BY RequestForm.RequestFormIdx",
            nativeQuery=true
    )
    List<SelectRequestFormList> selectAllDetectiveRequestFormListByCompanysIdx(Long companysIdx);

    @Query(
            value="SELECT " +
                    "RequestForm.requestFormIdx, RequestForm.usersIdx, RequestForm.companysIdx, " +
                    "RequestForm.requestFormRegion1, RequestForm.requestFormRegion2, " +
                    "RequestForm.requestFormStatus, RequestForm.requestConsultDate, RequestForm.requestFormDate, " +
                    "RequestForm.requestFormAcceptDate, RequestForm.requestFormCompDate, RequestForm.requestFormRejectMessage, " +
                    "GROUP_CONCAT(RequestFormCategory.requestFormCategoryValue) AS requestFormCategory " +
                    "FROM RequestForm INNER JOIN RequestFormCategory ON RequestForm.requestFormIdx = RequestFormCategory.requestFormIdx " +
                    "WHERE RequestForm.companysIdx = ?1 " +
                    "GROUP BY RequestForm.RequestFormIdx " +
                    "ORDER BY RequestForm.requestFormDate DESC",
            nativeQuery=true
    )
    List<SelectRequestFormList> selectAllDetectiveRequestFormListByCompanysIdxOrderByDESC(Long companysIdx);

    @Query(
            value="SELECT " +
                    "RequestForm.requestFormIdx, RequestForm.usersIdx, RequestForm.companysIdx, " +
                    "RequestForm.requestFormRegion1, RequestForm.requestFormRegion2, " +
                    "RequestForm.requestFormStatus, RequestForm.requestConsultDate, RequestForm.requestFormDate, " +
                    "RequestForm.requestFormAcceptDate, RequestForm.requestFormCompDate, RequestForm.requestFormRejectMessage, " +
                    "GROUP_CONCAT(RequestFormCategory.requestFormCategoryValue) AS requestFormCategory " +
                    "FROM RequestForm INNER JOIN RequestFormCategory ON RequestForm.requestFormIdx = RequestFormCategory.requestFormIdx " +
                    "WHERE RequestForm.companysIdx = ?1 " +
                    "GROUP BY RequestForm.RequestFormIdx " +
                    "ORDER BY RequestForm.requestFormDate ASC",
            nativeQuery=true
    )
    List<SelectRequestFormList> selectAllDetectiveRequestFormListByCompanysIdxOrderByASC(Long companysIdx);

//    @Query(value=
//            "SELECT * FROM RequestForm " +
//            ""
//    )
//    RequestForm selectDetectiveRequestFormInfo(Long.parseLong(requestFormIdx))

}
