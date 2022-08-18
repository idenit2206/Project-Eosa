package com.eosa.web.users.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eosa.web.users.Users;
import com.eosa.web.users.entity.FindByUsersAccountEntity;
import com.eosa.web.users.entity.SelectByUsersAccountEntity;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

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

    @Query(
        value="SELECT 1 FROM Users WHERE usersAccount=?1",
        nativeQuery=true
    )
    int usersAccountDupliCheck(String usersAccount);

    /**
     * Spring Security를 활용한 로근인에 사용하는 메서드
     * @param usersAccount
     * @return
     */
    @Query(
        value= "SELECT usersAccount, usersRole " +
        "FROM Users WHERE usersAccount = ?1",
        nativeQuery=true
    )
    SelectByUsersAccountEntity selectByUsersAccount(String usersAccount);

    /**
     * OAuth2Login() 작동시  SNS계정으로 로그인을 시도한 유저가 기존에 존재하던 사용자인지를
     * 검사하기 위해 이메일 기반으로 사용자 검색을 수행하는 메서드
     * @param usersEmail
     * @return
     */
    @Query(
        value= "SELECT * " +
        "FROM Users WHERE usersEmail = ?1",
        nativeQuery=true
    )
    Users selectByUsersEmail(String usersEmail);

    /**
     * 로그인 할 때 활용하는 메서드
     * (Spring Security에서 로그인을 할때 사용하는 메서드)
     * @param usersAccount
     * @return Users
     */
    @Query(
        value="SELECT * FROM Users WHERE usersAccount = ?1 AND usersEnabled = 1"
        ,nativeQuery=true
    )
    Users findByUsersAccount(String usersAccount);

    /**
     * Token 기반의 로그인을 수행할 때 활용
     * @param usersAccount
     * @return usersIdx
     */
    @Query(
        value="SELECT usersIdx FROM Users WHERE usersAccount = ?1",
        nativeQuery=true
    )
    Long findUsersIdxByUsersAccount(String usersAccount);
   
    /**
     * usersIdx가 일치하면서 usersRole이 DETECTIVE 라면 1을 반환
     * @param usersIdx
     * @return 1
     */
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
        "SET " + 
        "usersAccount=:#{#Users.usersAccount}, " +
        "usersPass=:#{#Users.usersPass}, " +
        "usersName=:#{#Users.usersName}, " +
        "usersEmail=:#{#Users.usersEmail} " +    
        "WHERE usersIdx = :#{#Users.usersIdx}"
        ,nativeQuery=true
    )
    int updateAdminUserInfo(@Param("Users") Users param);

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

    @Transactional
    @Query(
        value="SELECT 1 FROM Users WHERE usersEmail=?1",
        nativeQuery = true
    )
    int checkAccountByUsersEmail(String usersEmail);

    @Query(
        value="SELECT usersAccount FROM Users WHERE usersEmail=?1",
        nativeQuery = true
    )
    String accountMailSend(String usersEmail);

    @Query(
        value="SELECT usersAccount, usersName, usersNick, " + 
        "usersPhone, usersEmail, usersRole, usersAge, " + 
        "usersRegion1, usersRegion2, usersGender, usersNotice " +
        "FROM Users WHERE usersAccount=:usersAccount AND usersPass=:usersPass"
        ,nativeQuery = true 
    )
    FindByUsersAccountEntity checkMyPageByPass(@Param("usersAccount") String usersAccount, @Param("usersPass") String usersPass);

    
     
}
