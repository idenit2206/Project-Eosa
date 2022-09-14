package com.eosa.web.companys.repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import com.eosa.web.companys.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanysRepository extends JpaRepository<Companys, Long> {

    @Query(value=
        "SELECT " +
        "C.companysIdx, C.companysName, C.companysCeoIdx, C.companysCeoName, " +
        "C.companysComment, C.companysSpec, C.companysPhone, " +
        "C.companysRegion1, C.companysRegion2, C.companysRegion3, " +
        "C.companysRegistCerti, C.companysRegistCertiName, " +
        "C.companysLicense, C.companysLicenseName, " +
        "C.companysProfileImage, C.companysProfileImageName, " +
        "C.companysBankName, C.companysBankNumber, C.companysRegistDate, " +
        "C.companysPremium, C.companysLocalPremium, " +
        "C.companysEnabled, C.companysDelete, " +
        "GROUP_CONCAT(DISTINCT CAR.activeRegion) AS activeRegion, " +
        "GROUP_CONCAT(DISTINCT CC.companysCategoryValue) AS companysCategoryValue " +
        "FROM Companys C " +
        "LEFT JOIN CompanysActiveRegion CAR on C.companysIdx = CAR.companysIdx " +
        "LEFT JOIN CompanysCategory CC on C.companysIdx = CC.companysIdx " +
        "WHERE C.companysIdx=?1 " +
        "GROUP BY C.companysIdx"
        ,nativeQuery = true
    )
    SelectCompanys selectOneCompanysByCompanysIdxTest(Long companysIdx);

    @Query(value=
        "SELECT " +
        "C.companysIdx, C.companysName, C.companysCeoIdx, C.companysCeoName, " +
        "C.companysComment, C.companysSpec, C.companysPhone, " +
        "C.companysRegion1, C.companysRegion2, C.companysRegion3, " +
        "C.companysRegistCerti, C.companysRegistCertiName, " +
        "C.companysLicense, C.companysLicenseName, " +
        "C.companysProfileImage, C.companysProfileImageName, " +
        "C.companysBankName, C.companysBankNumber, C.companysRegistDate, " +
        "C.companysPremium, C.companysLocalPremium, " +
        "C.companysEnabled, C.companysDelete, " +
        "GROUP_CONCAT(DISTINCT CAR.activeRegion) AS activeRegion, " +
        "GROUP_CONCAT(DISTINCT CC.companysCategoryValue) AS companysCategoryValue, " +
        "(SELECT IFNULL(ULC.userLikeCompanyEnable, '0') FROM UserLikeCompany ULC " +
        "RIGHT OUTER JOIN (SELECT '') AS n ON ULC.usersIdx=:usersIdx AND ULC.companysIdx=C.companysIdx) AS UserLikeCompanyEnable " +
        "FROM Companys C " +
        "LEFT JOIN CompanysActiveRegion CAR on C.companysIdx = CAR.companysIdx " +
        "LEFT JOIN CompanysCategory CC on C.companysIdx = CC.companysIdx " +
        "WHERE C.companysIdx=:companysIdx " +
        "GROUP BY C.companysIdx"
        ,nativeQuery = true
    )
    SelectCompanysUserLikeCompanyEnable selectOneCompanysUserLikeCompanyEnableByCompanysIdxUsersIdx(@Param("companysIdx") Long companysIdx, @Param("usersIdx") Long usersIdx);

    @Transactional
    @Modifying
    @Query(value=
            "UPDATE Companys " +
                    "SET companysRegistCerti = :file1URL, companysRegistCertiName = :file1Name " +
                    "WHERE companysIdx = :companysIdx",
            nativeQuery = true
    )
    int updateRegistCerti(@Param("companysIdx") Long companysIdx, @Param("file1URL") String file1URL, @Param("file1Name") String file1Name);

    @Transactional
    @Modifying
    @Query(value=
            "UPDATE Companys " +
                    "SET companysLicense = :file2URL, companysLicenseName = :file2Name " +
                    "WHERE companysIdx = :companysIdx",
            nativeQuery = true
    )
    int updateLicense(@Param("companysIdx") Long companysIdx, @Param("file2URL") String file2URL, @Param("file2Name") String file2Name);

    @Transactional
    @Modifying
    @Query(value=
        "UPDATE Companys " +
        "SET companysRegistCerti = :file1URL, companysProfileImage = :file3URL, " +
        "SET companysRegistCertiName = :file1Name, companysProfileImageName = :file3Name " +
        "WHERE companysIdx = :companysIdx",
        nativeQuery = true
    )
    int updateRegistCertiAndProfileImage(
        @Param("companysIdx") Long companysIdx,
        @Param("file1URL") String file1URL, @Param("file3URL") String file3URL,
        @Param("file1Name") String file1Name, @Param("file3Name") String file3Name
    );

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
        "companysRegistCerti = :#{#Companys.companysRegistCerti}, companysProfileImage = :#{#Companys.companysProfileImage}, companysEnabled = :#{#Companys.companysEnabled}, " +
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
    @Query(value=
        "SELECT " +
        "Companys.companysIdx, Companys.companysName, Companys.companysCeoIdx, Companys.companysCeoName, " +
        "Companys.companysPhone, Companys.companysComment, Companys.companysSpec, Companys.companysRegistDate, " +
        "Companys.companysRegion1, Companys.companysProfileImage, Companys.companysEnabled, " +
        "Companys.companysPremium, Companys.companysLocalPremium, " +
        "(SELECT GROUP_CONCAT(CompanysCategory.companysCategoryValue) FROM CompanysCategory WHERE CompanysCategory.companysIdx = Companys.companysIdx) " +
//        "(SELECT IFNULL(a.idx, 0) AS UserLikeCompanyEnable FROM UserLikeCompany a RIGHT OUTER JOIN (SELECT '') AS b ON a.usersIdx=?1 AND a.companysIdx=Companys.companysIdx) " +
        "AS CompanysCategory FROM Companys",
        nativeQuery = true
    )
    List<SelectCompanys> selectAllCompanys(Long usersIdx);

    @Query(value=
            "SELECT " +
                    "Companys.companysIdx, Companys.companysName, Companys.companysCeoIdx, Companys.companysCeoName, " +
                    "Companys.companysPhone, Companys.companysComment, Companys.companysSpec, Companys.companysRegistDate, " +
                    "Companys.companysRegion1, Companys.companysProfileImage, Companys.companysEnabled, " +
                    "Companys.companysPremium, Companys.companysLocalPremium, " +
                    "(SELECT IF(userLikeCompanyEnable IS NULL, 0, 1) FROM UserLikeCompany WHERE UserLikeCompany.usersIdx=?1 AND UserLikeCompany.companysIdx=?2)\n" +
                    "AS userLikeCompanysEnable," +
                    "(SELECT GROUP_CONCAT(CompanysCategory.companysCategoryValue) FROM CompanysCategory WHERE CompanysCategory.companysIdx = Companys.companysIdx) " +
                    "AS CompanysCategory FROM Companys " +
                    "INNER JOIN UserLikeCompany ON Companys.companysIdx = UserLikeCompany.companysIdx",
            // value="SELECT * FROM Companys",
            nativeQuery = true
    )
    List<SelectAllCompanysList> selectAllCompanysListByUsersIdxAndCompanysIdx(Long usersIdx, Long companysIdx);

    @Query(value="SELECT companysIdx FROM CompanysCategory WHERE companysCategoryValue = ?1", nativeQuery = true)
    List<Long> selectCompanysIdxByCompanysCategory(String companysCategoryValue);

    @Query(
            value="SELECT " +
                    "Companys.companysIdx, Companys.companysName, Companys.companysCeoIdx, Companys.companysCeoName, " +
                    "Companys.companysPhone, Companys.companysComment, Companys.companysSpec, Companys.companysRegistDate, " +
                    "Companys.companysRegion1, Companys.companysEnabled, Companys.companysPremium, Companys.companysLocalPremium, " +
                    "(SELECT GROUP_CONCAT(companysCategoryValue) FROM CompanysCategory WHERE companysIdx = 55" +
                    "GROUP BY companysIdx) AS companysCategory" +
                    "FROM Companys INNER JOIN CompanysCategory ON Companys.companysIdx = CompanysCategory.companysIdx " +
                    "GROUP BY Companys.companysIdx",
            // value="SELECT * FROM Companys",
            nativeQuery = true
    )
    List<SelectAllCompanysList> selectCompanysByCategory(Long companysIdx);

//     @Query(
//     value=
//         "SELECT " +
//         "Companys.companysIdx, Companys.companysName, Companys.companysCeoIdx, Companys.companysCeoName, " +
//         "Companys.companysPhone, Companys.companysComment, Companys.companysSpec, Companys.companysRegistDate, " +
//         "Companys.companysRegion1, Companys.companysProfileImage, Companys.companysEnabled, " +
//         "Companys.companysPremium, Companys.companysLocalPremium, " +
//         "UserLikeCompany.userLikeCompanyEnable, " +
//         "(SELECT GROUP_CONCAT(CompanysCategory.companysCategoryValue) FROM CompanysCategory WHERE CompanysCategory.companysIdx = ?1) " +
//         "AS CompanysCategory FROM Companys " +
//         "INNER JOIN UserLikeCompany ON Companys.companysIdx = UserLikeCompany.companysIdx " +
//         "WHERE Companys.companysIdx = ?1 ",
//     nativeQuery = true)
//     SelectAllCompanysList selectCompanysByCompanysIdx(Long companysIdx);

        @Query(
            value=
                "SELECT " +
                "Companys.companysIdx, Companys.companysName, Companys.companysCeoIdx, Companys.companysCeoName, " +
                "Companys.companysPhone, Companys.companysComment, Companys.companysSpec, Companys.companysRegistDate, " +
                "Companys.companysRegion1, Companys.companysProfileImage, Companys.companysEnabled, " +
                "Companys.companysPremium, Companys.companysLocalPremium, " +
                "(SELECT GROUP_CONCAT(CompanysCategory.companysCategoryValue) FROM CompanysCategory WHERE CompanysCategory.companysIdx = ?1) " +
                "AS CompanysCategory FROM Companys WHERE Companys.companysIdx = ?1 ",
            nativeQuery = true)
        SelectAllCompanysList selectCompanysByCompanysIdx(Long companysIdx);

    @Query(
    value=
        "SELECT " +
        "Companys.companysIdx, Companys.companysName, Companys.companysCeoIdx, Companys.companysCeoName, " +
        "Companys.companysPhone, Companys.companysComment, Companys.companysSpec, Companys.companysRegistDate, " +
        "Companys.companysRegion1, Companys.companysProfileImage, Companys.companysEnabled, " +
        "Companys.companysPremium, Companys.companysLocalPremium, " +
        "UserLikeCompany.userLikeCompanyEnable, " +
        "(SELECT GROUP_CONCAT(CompanysCategory.companysCategoryValue) FROM CompanysCategory WHERE CompanysCategory.companysIdx = ?1) " +
        "AS CompanysCategory FROM Companys " +
        "INNER JOIN UserLikeCompany ON Companys.companysIdx = UserLikeCompany.companysIdx " +
        "WHERE Companys.companysIdx = ?1 AND Companys.companysRegion1 = ?2 " +
        "GROUP BY Companys.companysIdx",
    nativeQuery = true)
    SelectAllCompanysList selectCompanysByCompanysIdxAndCompanysRegion1(Long companysIdx, String companysRegion1);

    @Query(value="SELECT companysIdx FROM Companys WHERE companysRegion1 = ?1", nativeQuery = true)
    List<Long> selectCompanysIdxByRegion1(String companysRegion1);

    @Query(
            value="SELECT " +
                    "Companys.companysIdx, Companys.companysName, Companys.companysCeoIdx, Companys.companysCeoName, " +
                    "Companys.companysPhone, Companys.companysComment, Companys.companysSpec, Companys.companysRegistDate, " +
                    "Companys.companysRegion1, Companys.companysEnabled, Companys.companysPremium, Companys.companysLocalPremium, " +
                    "GROUP_CONCAT(CompanysCategory.companysCategoryValue) AS companysCategory " +
                    "FROM Companys INNER JOIN CompanysCategory ON Companys.companysIdx = CompanysCategory.companysIdx " +
                    "WHERE Companys.companysRegion1 = ?1 " +
                    "GROUP BY Companys.companysIdx",
            // value="SELECT * FROM Companys",
            nativeQuery = true
    )
    List<SelectAllCompanysList> selectCompanysByCompanysRegion1(String companysRegion1);

    @Query(value = "SELECT * FROM Companys WHERE companysCeoIdx = ?1", nativeQuery = true)
    Companys selectCompanyInfoByUsersIdx(Long companysCeoIdx);

    @Query(
        value="SELECT " +
        "c.companysIdx, c.companysName, c.companysCeoIdx, c.companysCeoName, " +
        "c.companysComment, c.companysSpec, c.companysPhone, " +
        "c.companysRegion1, c.companysRegion2, c.companysRegion3, " +
        "c.companysRegistCerti, c.companysLicense, c.companysProfileImage, " +
        "c.companysRegistCertiName, c.companysLicenseName, c.companysProfileImageName, " +
        "c.companysBankName, c.companysBankNumber, c.companysPremium, c.companysLocalPremium, " +
        "c.companysEnabled, c.companysDelete, " +
        "c.companysRegistDate, " +
        "GROUP_CONCAT(c2.companysCategoryValue) AS companysCategory " +
        "FROM Companys c " +
        "INNER JOIN CompanysCategory c2 ON c.companysIdx = c2.companysIdx " +
        "WHERE c.companysIdx = ?1 " +
        "GROUP BY c.companysIdx"
        ,nativeQuery = true
    )
    SelectAllCompanysForNormal selectOneCompanyInfoByCompanysIdx(Long companysIdx);

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


    @Query(value="SELECT * FROM Companys WHERE companysCeoIdx = ?1", nativeQuery = true)
    Companys selectCompanysPremiumEnabled(Long companysCeoIdx);
}