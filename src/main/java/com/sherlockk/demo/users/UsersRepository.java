package com.sherlockk.demo.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UsersRepository extends JpaRepository<Users, Long> {
    
}
