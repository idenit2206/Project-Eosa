package com.eosa.web.board.repository;

import com.eosa.web.board.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @Query(value="SELECT * FROM Notice WHERE idx = ?1", nativeQuery = true)
    Notice selectNoticeByNoticeIdx(Long idx);

}
