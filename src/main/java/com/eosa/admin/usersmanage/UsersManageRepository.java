package com.eosa.admin.usersmanage;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.type.TrueFalseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eosa.web.users.Users;

@Repository
public interface UsersManageRepository extends JpaRepository<Users, Long> {

    @Query(
        value="SELECT usersAccount, usersName, usersNick, " + 
        "usersPhone, usersEmail, usersRole, " +
        "usersRegion1, usersRegion2, usersNotice " +
        "FROM Users WHERE usersAccount = ?1",
        nativeQuery=true
    )
    GetByUsersAccount getByUsersAccount(String usersAccount);

    @Query(
        value="SELECT * FROM Users WHERE " + 
        "usersRole='CLIENT' " + 
        "AND usersEnabled=1 AND usersDelete=0 " +
        "ORDER BY usersIdx DESC LIMIT ?1, ?2",
        nativeQuery=true
    )
    List<Users> findAllClient(int currentPageStartPost, int postSize);

    @Query(
        value="SELECT COUNT(*) FROM Users WHERE usersRole='CLIENT'"
    )
    int findAllClientCount();

    @Query(
        value="SELECT usersIdx, usersAccount, usersRole, usersJoinDate FROM Users WHERE usersRole='CLIENT' OR usersRole='DETECTIVE' ORDER BY usersIdx DESC LIMIT ?1, ?2",
        nativeQuery=true
    )
    List<GetUsersList> findAllUsers(int currentPageStartPost, int postSize);

    @Query(
        value="SELECT COUNT(*) FROM Users"
    )
    int findAllUsersCount();

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
    int updateUsersInfo(@Param("Users") Users users);

    @Query(
        value="SELECT * FROM Users WHERE usersAccount LIKE CONCAT('%',?1,'%') " +
        "AND usersRole='CLIENT' " + 
        "LIMIT ?2, ?3",
        nativeQuery=true
    )
    List<Users> findByUsersAccount(String usersAccount, int currentPageStartPost, int pOST_COUNT);

    @Query(
        value="SELECT COUNT(*) FROM Users WHERE usersAccount LIKE CONCAT('%',?1,'%') " +
        "AND usersRole='CLIENT'",
        nativeQuery=true
    )
    int findByUsersAccountCount(String usersAccount);

}
