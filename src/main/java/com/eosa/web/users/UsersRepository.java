package com.eosa.web.users;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.EntityGraph;
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
    FindByUsersAccount selectByUsersAccount(String usersAccount);

    /**
     * 로그인 할 때 활용하는 메서드(Spring Security formLogin()을 통해 로그인을 할때 사용하는 메서드)
     * @param usersAccount
     * @return Users
     */
    @Query(
        value="SELECT * FROM Users WHERE usersAccount = ?1 AND usersEnabled = 1"
        ,nativeQuery=true
    )
    Users findByUsersAccount(String usersAccount);

    /**
     * Token 기반의 로그인을 수행할 때 활용 usersIdx를 반환
     * @param usersAccount
     * @return usersIdx
     */
    @Query(
        value="SELECT usersIdx FROM Users WHERE usersAccount = ?1",
        nativeQuery=true
    )
    Long findUsersIdxByUsersAccount(String usersAccount);

    /**
     * usersAccount를 기준으로 Users정보를 가져올때 권한 정보도 같이 가져온다.
     * @param usersAccount
     * @return
     */
    @EntityGraph(attributePaths = "authorities")
    Optional<Users> findOneWithAuthoritiesByUsersAccount(String usersAccount);

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
        "SET usersEnabled=0, " +
        "usersDelete=1 " +
        "WHERE usersIdx=?1"
        ,nativeQuery=true
    )
    int deleteUserInfo(Long usersIdx);
     
}
