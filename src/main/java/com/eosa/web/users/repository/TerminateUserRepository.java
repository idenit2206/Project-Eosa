package com.eosa.web.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eosa.web.users.entity.TerminateUser;

@Repository
public interface TerminateUserRepository extends JpaRepository<TerminateUser, Long> {
    
}
