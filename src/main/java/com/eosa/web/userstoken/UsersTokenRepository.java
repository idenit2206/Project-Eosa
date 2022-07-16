package com.eosa.web.userstoken;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersTokenRepository extends JpaRepository<UsersToken, Long> {

    @Modifying
    @Transactional
    @Query(
        value="INSERT INTO UsersToken(tokenUsersIdx, accessToken, tokenCreateDate)" + 
        "VALUES(?1, ?2, ?3)"
        ,nativeQuery=true
    )
    int saveAccessToken(Long tokenUsersIdx, String accessToken, LocalDateTime tokenCreateDate);
    
}
