package com.eosa.web.users.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.eosa.web.users.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    @Modifying
    @Transactional
    @Query(
        value="INSERT INTO Users(usersAccount, usersPass, usersName, usersNick," +
        "usersPhone, usersEmail, usersRole, usersAge," + 
        "usersRegion1, usersRegion2, provider, usersProfile, usersGender, usersJoinDate, usersNotice, usersEnabled)" +
        "VALUES(:#{#Users.usersAccount}, :#{#Users.usersPass}, :#{#Users.usersName}, :#{#Users.usersNick}," +
        ":#{#Users.usersPhone}, :#{#Users.usersEmail}, :#{#Users.usersRole}, :#{#Users.usersAge}," +
        ":#{#Users.usersRegion1}, :#{#Users.usersRegion2}, :#{#Users.provider}, :#{#Users.usersProfile}, :#{#Users.usersGender}, :#{#Users.usersJoinDate}," +
        ":#{#Users.usersNotice}, :#{#Users.usersEnabled})"
    ,nativeQuery=true)
    int userSave(@Param("Users") Users user);

    /**
     * 회원가입시 핸드폰 인증 코드를 발송하기전
     * 핸드폰번호가 기존의 사용중이 번호인지 검사합니다.
     */
    @Query(
        value="SELECT IFNULL(" +
            "(SELECT 1 FROM Users WHERE usersPhone = ?1), 0) " +
        "AS usersPhoneCheck"
    , nativeQuery = true)
    int selectUsersPhoneCheckByUsersPhone(String usersPhone);

    /**
     * 회원가입을 할 때 아이디가 중복인지 검사하기 위한 메서드
     * @param usersAccount
     * @return 0 | null
     */
    @Query(
        value="SELECT * FROM Users WHERE usersAccount=?1",
        nativeQuery=true
    )
    Users usersAccountDupliCheck(String usersAccount);

    /**
     * 회원가입을 할 때 이메일이 중복인지 검사하기 위한 메서드
     * @param usersEmail
     * @return 0 | null
     * @Author PARK MIN JAE
     */
    @Query(
        value="SELECT * FROM Users WHERE usersEmail=?1",
        nativeQuery=true
    )
    Users selectUsersByUsersEmail(String usersEmail);

    /**
     * Spring Security formLogin()에서 인증을 성공했을 때
     * 인증성공한 회원의 회원정보를 갖고온다.
     * @param usersAccount
     * @return
     */
    @Query(
        value= "SELECT usersIdx, usersAccount, usersName, usersNick, usersRole " +
        "FROM Users WHERE usersAccount = ?1",
        nativeQuery=true
    )
    GetUsersInfoByUsersAccountEntity getUsersInfoByUsersAccount(String usersAccount);

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
        "usersNick=:#{#Users.usersNick}, " +
        "usersEmail=:#{#Users.usersEmail}, " +
        "usersGender=:#{#Users.usersGender}, " +
        "usersAge=:#{#Users.usersAge}, " +
        "usersRegion1=:#{#Users.usersRegion1}, " +
        "usersRegion2=:#{#Users.usersRegion2} " +
        "WHERE usersIdx=:#{#Users.usersIdx}"
        ,nativeQuery=true
    )
    int updateUserInfoExcludeUsersPass(@Param("Users") Users param);

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
  
    /**
     * 회원정보 삭제
     * usersIdx와 일치하는 엔티티를 삭제하는 레포지터리
     * @param usersIdx
     * @return
     */
    @Modifying
    @Transactional
    @Query(
        value="DELETE FROM Users " +
        "WHERE usersIdx = ?1"
        ,nativeQuery = true
    )
    int deleteUserInfo(Long usersIdx);

    /**
     * 회원탈퇴
     * @param usersIdx
     * @return
     */
    @Modifying
    @Transactional
    @Query(
        value="UPDATE Users " +
        "SET usersEnabled = 0 " +
        "WHERE usersIdx=?1"
        ,nativeQuery=true
    )
    int deleteUserInfo02(Long usersIdx);

    @Transactional
    @Query(
        value="SELECT 1 FROM Users WHERE usersEmail=?1",
        nativeQuery = true
    )
    int checkAccountByUsersEmail(String usersEmail);

    @Query(
        value="SELECT usersAccount FROM Users WHERE usersEmail=?1 AND usersRole != 'TEMP'",
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

    @Query(
        value="SELECT * FROM Users WHERE usersRole='DETECTIVE' ORDER BY usersIdx DESC",
        nativeQuery=true
    )
    List<Users> selectAllDetective();

    @Query(
        value="SELECT * FROM Users " +
        "WHERE usersIdx = ?1",
        nativeQuery = true
    )
    Users selectUsersByUsersIdx(Long usersIdx);

    @Query(
        value="SELECT usersAccount FROM Users " +
        "WHERE usersIdx = ?1",
        nativeQuery = true
    )
    String selectUsersAccountByUsersIdx(Long usersIdx);


    @Transactional
    @Modifying
    @Query(value =
        "UPDATE Users " +
        "SET usersPass = :usersPass " +
        "WHERE usersAccount = :usersAccount AND usersEmail = :usersEmail AND usersRole != 'TEMP' "
        ,nativeQuery = true
    )
    int updateUsersPass(@Param("usersAccount") String usersAccount, @Param("usersEmail") String usersEmail, @Param("usersPass") String encodedCode);
    
    /**
     * usersIdx 사용자의 token 조회
     * @param usersIdx
     * @return
     */
    @Query(value = "SELECT token FROM Users WHERE usersIdx = ?1", nativeQuery = true)
    String getTokenByUsersIdx(Long usersIdx);

    @Query(value = "SELECT device FROM Users WHERE usersIdx = ?1", nativeQuery = true)
    String getDeviceByUsersIdx(Long usersIdx);

    // @Transactional
    // @Modifying(flushAutomatically = true, clearAutomatically = true)
    // @Query(value = "INSERT INTO Users(token) VALUES (:token) ON DUPLICATE KEY UPDATE usersIdx = :usersIdx, token = :token", nativeQuery = true)
    // int updateUsersToken(@Param("token") String token, @Param("usersIdx") Long usersIdx);

    /**
     * token을 활용해 Users정보를 Select하는 레포지터리 (token을 보유중인 Users를 조회)
     * @param token
     * @return
     */
    @Query(value = "SELECT * FROM Users u WHERE u.token = ?1", nativeQuery = true)
    Users getUsersByToken(String token);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Users SET token = NULL, device = NULL WHERE usersIdx = ?1", nativeQuery = true)
    int removeUsersTokenDevice(Long usersIdx);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    // @Query(value = "INSERT INTO Users(token, device) VALUES (:token, :device) ON DUPLICATE KEY UPDATE usersIdx = :usersIdx, token = :token, device = :device", nativeQuery = true)
    @Query(value = "UPDATE Users SET token = :token, device = :device WHERE usersIdx = :usersIdx", nativeQuery = true)
    int updateUsersTokenDevice(@Param("usersIdx") Long usersIdx, @Param("token") String token, @Param("device") String device);
    
}
