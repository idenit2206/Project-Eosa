package com.eosa.web.users;

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
        value="INSERT INTO Users(usersAccount, usersPass, usersName, usersNick," +
        "usersPhone, usersEmail, usersRole, usersAge," + 
        "usersRegion1, usersRegion2, usersGender, usersJoinDate, usersNotice, usersEnabled)" +
        "VALUES(:#{#Users.usersAccount}, :#{#Users.usersPass}, :#{#Users.usersName}, :#{#Users.usersNick}," +
        ":#{#Users.usersPhone}, :#{#Users.usersEmail}, :#{#Users.usersRole}, :#{#Users.usersAge}," +
        ":#{#Users.usersRegion1}, :#{#Users.usersRegion2}, :#{#Users.usersGender}, :#{#Users.usersJoinDate}," +
        ":#{#Users.usersNotice}, :#{#Users.usersEnabled})"
    ,nativeQuery=true)
    int userSave(@Param("Users") Users user);

    /**
     * 회원정보 수정을 위해 활용되는 회원정보 조회 메서드
     * @param usersAccount
     * @return
     */
    @Query(
        value= "SELECT usersAccount, usersName, usersNick, usersPhone, " +
        "usersEmail, usersRole, usersAge, " +
        "usersRegion1, usersRegion2, usersGender " +
        "FROM Users WHERE usersAccount = ?1",
        nativeQuery=true
    )
    findByUsersAccount selectByUsersAccount(String usersAccount);

    /**
     * 로그인을 할때 활용하는 회원정보 조회 메서드
     * @param usersAccount
     * @return
     */
    @Query(
        value="SELECT * FROM Users WHERE usersAccount = ?1 AND usersEnabled = 1"
        ,nativeQuery=true
    )
    Users findByUsersAccount(String usersAccount);

    @Query(
        value="SELECT 1 FROM Users WHERE usersIdx = ?1 AND usersRole = 'DETECTIVE' ",
        nativeQuery=true
    )
    int findDetectiveByUsersIdx(Long usersIdx);

    // /**
    //  * OAuth2에서 활용하는 이메일 조회 메서드(기존회원인지 신규회원인지 구분)
    //  */
    @Query(
        value="SELECT * FROM Users WHERE usersEmail = ?1 AND usersEnabled = 1",
        nativeQuery=true
    )
    Users findByUsersEmail(String usersEmail);
    // Users findByUsersEmail(String usersEmail);
    // Optional<Users> findByUsersEmail(String usersEmail);

    @Modifying
    @Transactional
    @Query(
        value="UPDATE Users " +
        "SET usersPass=:#{#Users.usersPass}, " +
        "usersNick=:#{#Users.usersNick}, " +
        "usersEmail=:#{#Users.usersEmail}, " +
        "usersGender=:#{#Users.usersGender}, " +
        "usersAge=:#{#Users.usersAge}, " +
        "usersRegion1=:#{#Users.usersRegion1}, " +
        "usersRegion2=:#{#Users.usersRegion2} " +
        "WHERE usersIdx=:#{#Users.usersIdx}"
        ,nativeQuery=true
    )
    int updateUserInfo(@Param("Users") Users param);

    @Modifying
    @Transactional
    @Query(
        value="UPDATE Users " +
        "SET usersEnabled=0, " +
        "usersDelete=1 " +
        "WHERE usersIdx=?1"
        ,nativeQuery=true
    )
    int deleteUserInfo(Long usersIdx);
     
}
