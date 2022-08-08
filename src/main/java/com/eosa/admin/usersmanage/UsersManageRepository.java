package com.eosa.admin.usersmanage;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eosa.web.users.Users;

@Repository
public interface UsersManageRepository extends JpaRepository<Users, Long> {

    @Query(
        value="SELECT * FROM Users WHERE usersAccount = ?1",
        nativeQuery=true
    )
    Users getByUsersAccount(String usersAccount);

    @Modifying
    @Transactional
    @Query(
        value="UPDATE Users SET " +
        "usersPass = :#{#Users.usersPass}, usersNick = :#{#Users.usersNick}, " + 
        "usersRegion1 = :#{#Users.usersRegion1}, usersRegion2 = :#{#Users.usersRegion2}, " +
        "usersNotice = :#{#Users.usersNotice} " +
        "WHERE usersAccount = :#{#Users.usersAccount}",
        nativeQuery=true
    )
    int modifyUsersInfo(@Param("Users") Users users);

}
