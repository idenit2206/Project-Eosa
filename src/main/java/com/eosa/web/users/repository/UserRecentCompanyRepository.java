package com.eosa.web.users.repository;

import com.eosa.web.users.entity.UserRecentCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRecentCompanyRepository extends JpaRepository<UserRecentCompany, Long> {

    @Query(value="SELECT count(companysIdx) FROM UserRecentCompany WHERE usersIdx = ?1", nativeQuery = true)
    int countByUsersIdx(Long usersIdx);

    @Query(value="SELECT idx FROM UserRecentCompany ORDER BY browseDate ASC LIMIT 1", nativeQuery = true)
    Long selectOldestOneIdxByDate();

    @Transactional
    @Modifying
    @Query(value="DELETE FROM UserRecentCompany WHERE idx = ?1", nativeQuery = true)
    void deleteOldestOne(Long idx);

    @Query(value="SELECT * FROM UserRecentCompany WHERE usersIdx = ?1", nativeQuery = true)
    List<UserRecentCompany> findByUsersIdx(Long usersIdx);
}
