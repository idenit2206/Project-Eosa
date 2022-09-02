package com.eosa.web.users.repository;

import com.eosa.web.users.entity.TempUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempUserRepository extends JpaRepository<TempUser, Long> {
}
