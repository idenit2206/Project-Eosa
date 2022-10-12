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

    /**
     * usersIdx가 일치하는 List<ChatRoom>을 출력
     * @param usersIdx
     * @return List
     */
    @Query(
        value="SELECT * FROM ChatRoom c WHERE c.usersIdx = ?1 and c.usable = 1",
        nativeQuery = true
    )
    List<ChatRoom> selectChatRoomListByUsersIdx(Long usersIdx);

    @Query(
        value="SELECT * FROM ChatRoom c WHERE c.companysIdx =?1"
        ,nativeQuery = true
    )
    List<ChatRoom> selectChatRoomListByCompanysIdx(Long companysIdx);

    @Query(value="SELECT * FROM ChatRoom c WHERE c.roomId = ?1", nativeQuery = true)
    ChatRoom selectChatRoomByChatRoomId(String roomId);

    @Transactional
    @Modifying
    @Query(
        value=
        "UPDATE ChatRoom cr " +
        "SET cr.usable = 0 " +
        "WHERE cr.roomId = ?1"
        ,nativeQuery = true
    )
    int deleteRoomByRoomId(String roomId);

    @Query(value="SELECT roomId FROM ChatRoom WHERE usersIdx = ?1", nativeQuery = true)
    List<String> selectChatRoomIdListByUsersIdx(Long usersIdx);
}
