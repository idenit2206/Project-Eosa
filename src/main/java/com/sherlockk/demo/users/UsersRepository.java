package com.sherlockk.demo.users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query(value="SELECT * FROM Users WHERE usersAccount=?1", nativeQuery=true)
    public Users findByUserAccount(String userAccount);
    
}
