package com.eosa.web.companys.repository;

import com.eosa.web.companys.entity.CompanysFlag;
import com.eosa.web.companys.entity.SelectCompanys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanysFlagRepository extends JpaRepository<CompanysFlag, Long> {

    @Query(
        value = "SELECT " +
                "C.companysIdx, C.companysName, C.companysCeoIdx, C.companysCeoName, " +
                "C.companysComment, C.companysSpec, C.companysPhone, C.companysDummyPhone, C.companysmemo, " +
                "C.companysRegion1, C.companysRegion2, C.companysRegion3, " +
                "C.companysRegistCerti, C.companysRegistCertiName, C.companysRegistCertiCheck, " +
                "C.companysLicense, C.companysLicenseName, C.companysLicenseCheck, " +
                "C.companysProfileImage, C.companysProfileImageName, " +
                "C.companysBankName, C.companysBankNumber, C.companysRegistDate, " +
                "C.companysPremium, C.companysLocalPremium, " +
                "C.companysEnabled, C.companysDelete, " +
                "GROUP_CONCAT(DISTINCT CAR.activeRegion) AS activeRegion, " +
                "GROUP_CONCAT(DISTINCT CC.companysCategoryValue) AS companysCategoryValue " +
                "FROM Companys C " +
                "LEFT JOIN CompanysActiveRegion CAR on C.companysIdx = CAR.companysIdx " +
                "LEFT JOIN CompanysCategory CC on C.companysIdx = CC.companysIdx " +
                "WHERE C.companysLocalPremium = true " +
                "GROUP BY C.companysIdx",
        nativeQuery = true
    )
    List<SelectCompanys> selectAllCompanysFlag();

    @Query(value = "SELECT * FROM CompanysFlag WHERE companysIdx = ?1", nativeQuery = true)
    CompanysFlag selectCompanysFlagByCompanysIdx(Long companysIdx);

}
