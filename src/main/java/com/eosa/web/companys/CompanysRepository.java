package com.eosa.web.companys;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eosa.web.companys.entity.Companys;
import com.eosa.web.companys.entity.SelectAllCompanysList;

@Repository
public interface CompanysRepository extends JpaRepository<Companys, Long> {

    @Query(
        value="SELECT 1 FROM Companys WHERE companysIdx = ?1",
        nativeQuery=true
    )
    int findByCompanysIdx(Long companysIdx);

    @Query(
        value="SELECT DISTINCT companysCategoryValue FROM CompanysCategory",
        nativeQuery=true
    )
    List<String> selectAllCategory();
    
    // @Transactional
    // @Modifying
    // @Query(value="INSERT INTO Companys(" +
    // "Companys.companysName, Companys.companysComment, Companys.companysSpec, " + 
    // "Companys.companysRegion1, Companys.companysRegion2, Companys.companysRegion3, " +
    // "Companys.companysRegistCerti, Companys.companysLicense, Companys.companysProfileImage, " +
    // "Companys.companysBankName, Companys.companysBankNumber" +
    // ")", nativeQuery=true)
    // int insertCompanys(Companys entity);

    @Query(
        value="SELECT " + 
        "Companys.companysIdx, Companys.companysName, Companys.companysCeoIdx, Companys.companysCeoName, " +
        "Companys.companysPhone, Companys.companysComment, Companys.companysSpec, Companys.companysRegistDate, " +        
        "Companys.companysRegion1, Companys.companysEnabled, Companys.companysPremium, Companys.companysLocalPremium, " +
        "GROUP_CONCAT(CompanysCategory.companysCategoryValue) AS companysCategory " +
        "FROM Companys INNER JOIN CompanysCategory ON Companys.companysIdx = CompanysCategory.companysIdx " +
        "GROUP BY Companys.companysIdx",
        // value="SELECT * FROM Companys",
        nativeQuery = true
    )
    List<SelectAllCompanysList> selectAllCompanysList();
}