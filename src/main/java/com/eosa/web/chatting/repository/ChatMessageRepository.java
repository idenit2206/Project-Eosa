package com.eosa.web.chatting.repository;

import com.eosa.web.chatting.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.transaction.Transactional;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    @Query(
        value="SELECT * FROM ChatMessage c WHERE c.roomId = ?1 AND c.sender =?2 ",
        nativeQuery = true
    )
    ChatMessage selectOneRecentMessageByRoomIdAndUsersIdx(String roomId, Long usersIdx);

    @Query(value="SELECT * FROM ChatMessage c WHERE c.roomId = ?1 ORDER BY c.sendDate ASC", nativeQuery = true)
    public List<ChatMessage> selectChatMessageByRoomId(String roomId);

    @Transactional
    @Modifying
    @Query(value="DELETE FROM ChatMessage WHERE roomId = ?1", nativeQuery = true)
    int deleteByRoomId(String roomId);

}
