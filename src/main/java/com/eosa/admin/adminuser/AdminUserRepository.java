package com.eosa.admin.adminuser;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eosa.web.users.Users;

@Repository
public interface AdminUserRepository extends JpaRepository<Users, Long> {

    // @Query(
    //     value="SELECT adminUseridx, adminUserAccount, adminUserName, adminUserMobile, " + 
    //     "adminUserEmail, adminUserGrade, adminUserDate FROM AdminUser" + 
    //     "WHERE adminUserAccount = ?1",
    //     nativeQuery=true
    // )
    @Query(
        value="SELECT * FROM AdminUser WHERE adminUserAccount = ?1",
        nativeQuery=true
    )
    Users findByAdminUserAccount(String adminAccount);

   
    @Modifying
    @Transactional
    @Query(
        value = "INSERT INTO AdminUser(adminUserAccount, adminUserPass, adminUserName, " +
        "adminUserMobile, adminUserEmail, adminUserGrade, adminUserDate) " +
        "VALUES(:#{#AdminUser.adminUserAccount}, :#{#AdminUser.adminUserPass}, :#{#AdminUser.adminUserName}," +
        ":#{#AdminUser.adminUserMobile}, :#{#AdminUser.adminUserEmail}, :#{#AdminUser.adminUserGrade}, :#{#AdminUser.adminUserDate})"
        ,nativeQuery=true
    )
    int adminRegist(@Param("AdminUser") Users adminUser);
    
}
