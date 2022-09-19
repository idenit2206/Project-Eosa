package com.eosa.web.chatting.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eosa.web.chatting.entity.ChatRoom;

import java.util.List;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

//    @Modifying
//    @Transactional
//    @Query(
//        value="INSERT INTO LiveChat(roomId, roomName, usersIdx, companysIdx, dataInfo, createdDate, usable)" +
//        "VALUES(:#{#LiveChat.roomId}, :#{#LiveChat.roomName}, :#{#LiveChat.usersIdx}, :#{#LiveChat.companysIdx}," +
//        ":#{#LiveChat.dataInfo}, :#{#LiveChat.createdDate}, :#{#LiveChat.usable})"
//        ,nativeQuery=true
//    )
//    public int createChatRoom(@Param("LiveChat") ChatRoom chatRoom);

    @Query(
        value="SELECT * FROM ChatRoom c WHERE c.usersIdx = ?1",
        nativeQuery = true
    )
    List<ChatRoom> selectChatRoomListByUsersIdx(Long usersIdx);

    @Query(
        value="SELECT * FROM ChatRoom c WHERE c.companysIdx =?1"
        ,nativeQuery = true
    )
    List<ChatRoom> selectChatRoomListByCompanysIdx(Long companysIdx);

    @Query(
        value=
        "UPDATE ChatRoom cr " +
        "SET cr.usable = 0 " +
        "WHERE cr.roomId = ?1"
        ,nativeQuery = true
    )
    int deleteRoomByRoomId(String roomId);

}
