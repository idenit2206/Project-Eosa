package com.eosa.web.users.repository;

import com.eosa.web.companys.entity.SelectCompanysUserLikeCompanyEnable;
import com.eosa.web.users.entity.UserLikeCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserLikeCompanyRepository extends JpaRepository<UserLikeCompany, Long> {

    @Transactional
    @Modifying
    @Query(
        value="DELETE FROM UserLikeCompany " +
        "WHERE usersIdx = ?1 AND companysIdx = ?2",
        nativeQuery = true
    )
    int deleteByUsersAndCompanysIdx(Long usersIdx, Long companysIdx);

    @Query(
        value="SELECT userLikeCompanyEnable FROM UserLikeCompany WHERE companysIdx = ?1", nativeQuery = true
    )
    int selectUserLikeCompanyEnableByCompanysIdx(Long companysIdx);

    @Query(value=
        "SELECT " +
        "C.companysIdx, C.companysName, C.companysCeoIdx, C.companysCeoName, " +
        "C.companysComment, C.companysSpec, C.companysPhone, " +
        "C.companysDummyPhone, C.companysMemo, " +
        "C.companysRegion1, C.companysRegion2, C.companysRegion3, " +
        "C.companysRegistCerti, C.companysRegistCertiName, C.companysRegistCertiCheck, " +
        "C.companysLicense, C.companysLicenseName, C.companysLicenseCheck, " +
        "C.companysProfileImage, C.companysProfileImageName, " +
        "C.companysBankName, C.companysBankNumber, C.companysRegistDate, " +
        "C.companysPremium, C.companysLocalPremium, " +
        "C.companysEnabled, C.companysDelete, " +
        "ULC.userLikeCompanyEnable, " +
        "U.usersIdx, " +
        // "GROUP_CONCAT(DISTINCT CAR.activeRegion) AS activeRegion, " +
        // "GROUP_CONCAT(DISTINCT CC.companysCategoryValue) AS companysCategoryValue " +
        "CAR.activeRegion AS activeRegion, " +
        "CC.companysCategoryValue AS companysCategoryValue " +
        "FROM Companys C " +
        // "LEFT JOIN CompanysActiveRegion CAR on C.companysIdx = CAR.companysIdx " +
        // "LEFT JOIN CompanysCategory CC on C.companysIdx = CC.companysIdx " +
        "LEFT JOIN (SELECT c1.companysIdx, " +
            "GROUP_CONCAT(c1.activeRegion SEPARATOR ',') AS activeRegion " +
        "FROM CompanysActiveRegion c1 " +
        "GROUP BY c1.companysIdx) CAR on C.companysIdx = CAR.companysIdx " +
        "LEFT JOIN (SELECT c2.companysIdx, " +
            "GROUP_CONCAT(c2.companysCategoryValue separator ',') AS companysCategoryValue " +
        "FROM CompanysCategory c2 " +
        "GROUP BY c2.companysIdx) CC on C.companysIdx = CC.companysIdx " +
        "RIGHT JOIN UserLikeCompany ULC on CC.companysIdx = ULC.companysIdx " +
        "LEFT JOIN Users U ON U.usersIdx = ULC.usersIdx " +
        "WHERE U.usersIdx = ?1 " +
        "GROUP BY C.companysIdx, C.companysName, C.companysCeoIdx, C.companysCeoName, " +
        "C.companysComment, C.companysSpec, C.companysPhone, C.companysDummyPhone, C.companysMemo, " +
        "C.companysRegion1, C.companysRegion2, C.companysRegion3, " +
        "C.companysRegistCerti, C.companysRegistCertiName, C.companysLicense, C.companysLicenseName, C.companysProfileImage, C.companysProfileImageName, " +
        "C.companysBankName, C.companysBankNumber, C.companysRegistDate, C.companysPremium, C.companysLocalPremium, " +
        "C.companysEnabled, C.companysDelete, ULC.userLikeCompanyEnable, U.usersIdx",
        nativeQuery = true
    )
    List<SelectCompanysUserLikeCompanyEnable> selectLikeCompanysListByUsersIdx(Long usersIdx);

}
