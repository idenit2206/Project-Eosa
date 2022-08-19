package com.eosa.admin.usersmanage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eosa.admin.usersmanage.entity.GetUsersList;
import com.eosa.web.users.Users;

@Repository
public interface DetectiveRepository extends JpaRepository<Users, Long> {
    
    @Query(
        value="SELECT usersIdx, usersAccount, usersRole, usersJoinDate FROM Users " +
        "WHERE usersDelete=0 AND usersRole='DETECTIVE' ORDER BY usersIdx DESC LIMIT ?1, ?2",
        nativeQuery=true
    )
    List<GetUsersList> findAllDetective(int currentPageStartPost, int postSize);

    @Query(
        value="SELECT COUNT(*) FROM Users " + 
        "WHERE usersDelete=0 AND usersRole='DETECTIVE'",
        nativeQuery=true
    )
    int findAllDetectiveCount();

    @Query(
        value="SELECT usersIdx, usersAccount, usersRole, usersJoinDate FROM Users " + 
        "WHERE (usersRole='DETECTIVE') AND (usersDelete=1) ORDER BY usersIdx DESC LIMIT ?1, ?2",
        nativeQuery=true
    )
    List<GetUsersList> findAllWithdrawalDetective(int currentPageStartPost, int postSize);

    @Query(
        value="SELECT COUNT(*) FROM Users " + 
        "WHERE (usersRole='CLIENT' OR usersRole='DETECTIVE') AND (usersDelete=1)",
        nativeQuery=true
    )
    int findAllWithdrawalDetectiveCount();

}
