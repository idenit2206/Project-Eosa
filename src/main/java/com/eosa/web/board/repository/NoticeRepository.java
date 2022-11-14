package com.eosa.web.board.repository;

import com.eosa.web.board.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

    /**
     * idx가 일치하는 공지사항을 조회하는 레포지터리
     * @param idx
     * @return
     */
    @Query(value="SELECT * FROM Notice WHERE idx = ?1", nativeQuery = true)
    Notice selectNoticeByNoticeIdx(Long idx);

}
