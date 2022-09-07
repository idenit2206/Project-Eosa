package com.eosa.web.tempuser.repository;

import com.eosa.web.tempuser.entity.TempUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempUserRepository extends JpaRepository<TempUser, Long> {
}
