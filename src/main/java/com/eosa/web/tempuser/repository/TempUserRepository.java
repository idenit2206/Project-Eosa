package com.eosa.web.tempuser.repository;

import com.eosa.web.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TempUserRepository extends JpaRepository<Users, Long> {

    @Query(value = "SELECT * FROM Users U WHERE U.usersEmail = ?1", nativeQuery = true)
    Users signIn(String usersEmail);

}
