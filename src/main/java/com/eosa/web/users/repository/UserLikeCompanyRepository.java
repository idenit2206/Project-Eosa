package com.eosa.web.users.repository;

import com.eosa.web.users.entity.UserLikeCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLikeCompanyRepository extends JpaRepository<UserLikeCompany, Long> {
}
