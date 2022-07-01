package com.sherlockk.demo.users;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
   
    // @Query(value="SELECT * FROM Users WHERE usersAccount=?1", nativeQuery=true)
    // public Users findByUserAccount(String userAccount);

    @Modifying
    @Transactional
    @Query(
    value="INSERT INTO Users(usersAccount, usersPass, UsersName, UsersNick," +
        "usersPhone, usersEmail, usersRole, usersAge," + 
        "usersRegion1, usersRegion2, usersGender, usersJoinDate, usersEnabled)" +
        "VALUES(:#{#Users.usersAccount}, :#{#Users.usersPass}, :#{#Users.usersName}, :#{#Users.usersNick}," +
        ":#{#Users.usersPhone}, :#{#Users.usersEmail}, :#{#Users.usersRole}, :#{#Users.usersAge}," +
        ":#{#Users.usersRegion1}, :#{#Users.usersRegion2}, :#{#Users.usersGender}, :#{#Users.usersJoinDate}," +
        ":#{#Users.usersEnabled})"
    ,nativeQuery=true)
    int userSave(@Param("Users") Users user);

    @Query(
        value="SELECT * FROM Users WHERE usersAccount = ?1", 
        nativeQuery=true
    )
    Users findByUsersAccount(String usersAccount);
     
}
