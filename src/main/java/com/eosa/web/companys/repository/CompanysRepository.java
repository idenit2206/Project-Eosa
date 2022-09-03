package com.eosa.web.companys.repository;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eosa.web.companys.entity.Companys;
import com.eosa.web.companys.entity.SelectAllCompanysList;
import com.eosa.web.companys.entity.SelectCompanyInfoByUsersIdx;

@Repository
public interface CompanysRepository extends JpaRepository<Companys, Long> {

    @Transactional
    @Modifying
    @Query(value=
            "UPDATE Companys " +
                    "SET companysRegistCerti = :file1URL " +
                    "WHERE companysIdx = :companysIdx",
            nativeQuery = true
    )
    int updateRegistCerti(@Param("companysIdx") Long companysIdx, @Param("file1URL") String file1URL);

    @Transactional
    @Modifying
    @Query(value=
            "UPDATE Companys " +
                    "SET companysLicense = :file2URL " +
                    "WHERE companysIdx = :companysIdx",
            nativeQuery = true
    )
    int updateLicense(@Param("companysIdx") Long companysIdx, @Param("file2URL") String file2URL);

    @Transactional
    @Modifying
    @Query(value=
        "UPDATE Companys " +
        "SET companysRegistCerti = :file1Name, companysProfileImage = :file3Name " +
        "WHERE companysIdx = :companysIdx",
        nativeQuery = true
    )
    int updateRegistCertiAndProfileImage(@Param("companysIdx") Long companysIdx, @Param("file1Name") String file1Name, @Param("file3Name") String file3Name);

    @Query(value=
            "SELECT Companys.companysIdx FROM Companys " +
            "WHERE Companys.companysCeoIdx = ?1",
            nativeQuery=true
    )
    Long selectCompanysIdxByUsersIdx(Long usersIdx);

    @Transactional
    @Modifying
    @Query(value=
        "UPDATE Companys " +
        "SET companysName = :#{#Companys.companysName}, companysComment = :#{#Companys.companysComment}, " +
        "companysRegion1 = :#{#Companys.companysRegion1}, companysRegion2 = :#{#Companys.companysRegion2}, companysRegion3 = :#{#Companys.companysRegion3}, " +
        "companysRegistCerti = :#{#Companys.companysRegistCerti}, companysProfileImage = :#{#Companys.companysProfileImage}, " +
        "companysBankName = :#{#Companys.companysBankName}, companysBankNumber = :#{#Companys.companysBankNumber} " +
        "WHERE companysIdx = :#{#Companys.companysIdx} ",
        nativeQuery=true
    )
    int updateCompanys(@Param("Companys") Companys entity);

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

    /**
     * 모든 업체정보를 목록으로 출력
     * @return
     */
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

    @Query(value = "SELECT * FROM Companys WHERE companysCeoIdx = ?1", nativeQuery = true)
    Companys selectCompanyInfoByUsersIdx(Long companysCeoIdx);

    @Query(value = "SELECT companysIdx FROM Companys WHERE companysName= ?1 AND companysCeoName= ?2", nativeQuery = true)
    Long selectCompanyIdxByComapnysNameAndCompanysCeoName(String companysName, String companysCeoName);
//    @Query(
//        value="SELECT " +
//        "Companys.companysIdx, Companys.companysName, " +
//        "Companys.companysComment, Companys.companysSpec, " +
//        "Companys.companysRegion1, Companys.companysRegion2, Companys.companysRegion3, " +
//        "Companys.companysRegistCerti, Companys.companysProfileImage, " +
//        "GROUP_CONCAT(DISTINCT CompanysCategory.companysCategoryValue) AS companysCategory, " +
//        "GROUP_CONCAT(DISTINCT CompanysActiveRegion.activeRegion) AS CompanysActiveRegion, " +
//        "GROUP_CONCAT(DISTINCT CompanysLicense.companysLicenseName) AS CompanysLicenseName "  +
//        "FROM Companys LEFT JOIN CompanysCategory ON Companys.companysIdx = CompanysCategory.companysIdx " +
//        "LEFT JOIN CompanysActiveRegion ON Companys.companysIdx = CompanysActiveRegion.companysIdx " +
//        "LEFT JOIN CompanysLicense ON Companys.companysIdx = CompanysLicense.companysIdx " +
//        "WHERE Companys.companysCeoIdx = ?1 " +
//        "GROUP BY Companys.companysIdx",
//        nativeQuery=true
//    )
//    SelectCompanyInfoByUsersIdx selectCompanyInfoByUsersIdx(Long companysCeoIdx);
}