package com.eosa.admin.adminuser;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eosa.web.users.entity.Users;

@Repository
public interface AdminUserRepository extends JpaRepository<Users, Long> {

    // @Query(
    //     value="SELECT adminUseridx, adminUserAccount, adminUserName, adminUserMobile, " + 
    //     "adminUserEmail, adminUserGrade, adminUserDate FROM AdminUser" + 
    //     "WHERE adminUserAccount = ?1",
    //     nativeQuery=true
    // )
    @Query(
        value="SELECT * FROM Users WHERE usersAccount = ?1",
        nativeQuery=true
    )
    Users findByAdminUserAccount(String adminAccount);
   
    @Modifying
    @Transactional
    @Query(
        value = "INSERT INTO Users(usersAccount, usersPass, usersName, " +
        "usersPhone, usersEmail, usersRole, usersJoinDate) " +
        "VALUES(:#{#Users.usersAccount}, :#{#Users.usersPass}, :#{#Users.usersName}," +
        ":#{#Users.usersPhone}, :#{#Users.usersEmail}, :#{#Users.usersRole}, :#{#Users.usersJoinDate})"
        ,nativeQuery=true
    )
    int adminRegist(@Param("Users") Users adminUser);

    @Query(
        value="SELECT * FROM Users " +
        "WHERE usersRole = 'ADMIN' OR usersRole = 'SUPER_ADMIN' AND " +
        "usersEnabled = 1 AND usersDelete = 0 "  +
        "LIMIT ?1 , ?2",
        nativeQuery=true 
    )
    List<Users> findAllAdmin(int currentPageStartPost, int pOST_COUNT);

    @Query(
        value="SELECT COUNT(*) FROM Users " + 
        "WHERE usersRole = 'ADMIN' OR usersRole = 'SUPER_ADMIN' AND " +
        "usersEnabled = 1 AND usersDelete = 0 ",
        nativeQuery=true
    )
    int findAllAdminCount();
    
}
