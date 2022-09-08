package com.eosa.web.chatting.repository;

import com.eosa.web.chatting.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    @Query(
        value="SELECT * FROM ChatMessage c WHERE c.roomId = ?1 AND c.sender =?2 ",
        nativeQuery = true
    )
    ChatMessage selectOneRecentMessageByRoomIdAndUsersIdx(String roomId, Long usersIdx);

}
