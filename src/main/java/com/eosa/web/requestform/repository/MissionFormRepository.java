package com.eosa.web.requestform.repository;

import com.eosa.web.requestform.entity.MissionForm;
import com.eosa.web.requestform.entity.SelectMissionFormList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionFormRepository extends JpaRepository<MissionForm, Long> {

    @Query(
            value="SELECT " +
                    "MissionForm.missionFormIdx, MissionForm.usersIdx, Users.usersAccount, MissionForm.companysIdx, " +
                    "MissionForm.missionFormRegion1, MissionForm.missionFormRegion2, " +
                    "MissionForm.missionFormStatus, " +
                    "MissionForm.missionFormAcceptDate, MissionForm.missionFormCompDate, MissionForm.missionFormMessage, " +
                    "GROUP_CONCAT(RequestFormCategory.requestFormCategoryValue) AS missionFormCategory " +
                    "FROM MissionForm INNER JOIN RequestFormCategory ON MissionForm.missionFormIdx = RequestFormCategory.requestFormIdx " +
                    "INNER JOIN Users ON RequestForm.usersIdx = Users.usersIdx " +
                    "WHERE MissionForm.companysIdx = ?1 " +
                    "GROUP BY MissionForm.RequestFormIdx " +
                    "ORDER BY RequestForm.requestFormDate DESC",
            nativeQuery=true
    )
    List<SelectMissionFormList> selectAllDetectiveMissionFormListByCompanysIdxOrderByDESC(Long companysIdx);

    @Query(value=
            "SELECT * FROM MissionForm " +
                    "WHERE missionFormIdx = ?1",
            nativeQuery = true
    )
    MissionForm selectDetectiveMissionFormInfoByMissionFormIdx(Long missionFormIdx);

}
