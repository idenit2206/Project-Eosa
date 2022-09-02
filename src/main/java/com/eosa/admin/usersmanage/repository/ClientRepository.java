package com.eosa.admin.usersmanage.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.type.TrueFalseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eosa.admin.usersmanage.entity.GetByUsersAccount;
import com.eosa.admin.usersmanage.entity.GetUsersList;
import com.eosa.web.users.entity.Users;

@Repository
public interface ClientRepository extends JpaRepository<Users, Long> {

    @Query(
        value="SELECT usersAccount, usersName, usersNick, " + 
        "usersPhone, usersEmail, usersRole, " +
        "usersRegion1, usersRegion2, usersNotice " +
        "FROM Users WHERE usersAccount = ?1",
        nativeQuery=true
    )
    GetByUsersAccount getByUsersAccount(String usersAccount);    

    @Query(
        value="SELECT usersIdx, usersAccount, usersRole, usersJoinDate FROM Users " +
        "WHERE usersDelete=0 AND usersRole='CLIENT' ORDER BY usersIdx DESC LIMIT ?1, ?2",
        nativeQuery=true
    )
    List<GetUsersList> findAllUsers(int currentPageStartPost, int postSize);

    @Query(
        value="SELECT COUNT(*) FROM Users " + 
        "WHERE usersDelete=0 AND usersRole='CLIENT'",
        nativeQuery=true
    )
    int findAllUsersCount();

    @Query(
        value="SELECT usersIdx, usersAccount, usersRole, usersJoinDate FROM Users WHERE (usersRole='CLIENT' OR usersRole='DETECTIVE') AND (usersDelete=1) ORDER BY usersIdx DESC LIMIT ?1, ?2",
        nativeQuery=true
    )
    List<GetUsersList> findAllWithdrawalUser(int currentPageStartPost, int postSize);

    @Query(
        value="SELECT COUNT(*) FROM Users WHERE (usersRole='CLIENT' OR usersRole='DETECTIVE') AND (usersDelete=1)",
        nativeQuery=true
    )
    int findAllWithdrawalUserCount();

    @Query(
        value="SELECT usersIdx, usersAccount, usersRole, usersJoinDate FROM Users WHERE " + 
        "usersRole='CLIENT' " + 
        "AND usersEnabled=1 AND usersDelete=0 " +
        "ORDER BY usersIdx DESC LIMIT ?1, ?2",
        nativeQuery=true
    )
    List<GetUsersList> findAllClient(int currentPageStartPost, int postSize);

    @Query(
        value="SELECT COUNT(usersIdx) FROM Users WHERE usersRole='CLIENT'"
    )
    int findAllClientCount();

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
