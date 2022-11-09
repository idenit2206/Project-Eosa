package com.eosa.web.users.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eosa.web.users.entity.TerminateUser;

@Repository
public interface TerminateUserRepository extends JpaRepository<TerminateUser, Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM TerminateUser WHERE usersIdx = ?1", nativeQuery = true)
    int deleteByUsersIdx(Long usersIdx);
    
}
