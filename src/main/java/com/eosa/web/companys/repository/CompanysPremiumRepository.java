package com.eosa.web.companys.repository;

import com.eosa.web.companys.entity.CompanysPremium;
import com.eosa.web.companys.entity.SelectCompanys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanysPremiumRepository extends JpaRepository<CompanysPremium, Long> {

    /**
     * 활동중인 모든 제휴업체를 검색하는 레포지터리
     * @return
     */
    @Nullable
    @Query(
        value = "SELECT " +
              "C.companysIdx, C.companysName, C.companysCeoIdx, C.companysCeoName, " +
              "C.companysComment, C.companysSpec, C.companysPhone, " +
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
              "WHERE C.companysPremium = true " +
              "AND C.companysEnabled = 1 " +
              "GROUP BY C.companysIdx",
        nativeQuery = true
    )
    List<SelectCompanys> selectAllCompanysPremium();

    /**
     * companysName과 companysCeoName이 일치하는 제휴협회를 검색하는 레포지터리
     * @param companysName
     * @param companysCeoName
     * @return
     */
    @Query(value="SELECT * FROM CompanysPremium C WHERE C.companysName = ?1 AND C.companysCeoName = ?2", nativeQuery = true)
    CompanysPremium selectCompanysPremiumByCompanysNameCompanysCeoName(String companysName, String companysCeoName);

}
