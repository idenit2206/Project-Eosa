package com.eosa.web.users.repository;

import com.eosa.web.users.entity.UserLikeCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserLikeCompanyRepository extends JpaRepository<UserLikeCompany, Long> {

    @Transactional
    @Modifying
    @Query(
        value="DELETE FROM UserLikeCompany " +
        "WHERE usersIdx = ?1 AND companysIdx = ?2",
        nativeQuery = true
    )
    int deleteByUsersAndCompanysIdx(Long usersIdx, Long companysIdx);

}
