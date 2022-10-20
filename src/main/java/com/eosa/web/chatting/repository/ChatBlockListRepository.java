package com.eosa.web.chatting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eosa.web.chatting.entity.ChatBlockList;

@Repository
public interface ChatBlockListRepository extends JpaRepository<ChatBlockList, Long> {

    @Query(value = "SELECT * FROM ChatBlockList WHERE usersIdxBlocker = ?1 AND usersIdxBlocked = ?2", nativeQuery = true)
    ChatBlockList selectByBlockerBlocked(Long usersIdxBlocker, Long usersIdxBlocked);
    
    @Query(value = "SELECT * FROM ChatBlockList WHERE usersIdxBlocker = ?1", nativeQuery = true)
    List<ChatBlockList> selectChatBlockListsByBlocker(Long usersIdxBlocker);
}
