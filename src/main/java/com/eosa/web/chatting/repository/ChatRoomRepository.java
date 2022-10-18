package com.eosa.web.chatting.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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

    /**
     * roomId가 일치하는 채팅방을 DB에서 삭제하는 레포지터리
     * @param roomId
     * @return
     */
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

    /**
     * roomId가 채팅방 읽음 상태 조회하는 목적의 레포지터리
     * @param roomId
     * @return
     */
    @Query(value = "SELECT * FROM ChatRoom CR WHERE CR.roomId = ?1", nativeQuery = true)
    ChatRoom selectReadStatus(String roomId);

    /**
     * 읽음 상태로 변경(CLIENT)
     * @param roomId
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE ChatRoom CR SET CR.clientReadStatus = 1 WHERE CR.roomId = ?1", nativeQuery = true)
    int changeReadStatusReadFromClient(String roomId);

    /**
     * 읽음 상태로 변경(CLIENT)
     * @param roomId
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE ChatRoom CR SET CR.detectiveReadStatus = 1 WHERE CR.roomId = ?1", nativeQuery = true)
    int changeReadStatusReadFromDetective(String roomId);

    /**
     * 안읽음 상태로 변경(CLIENT) 레포지터리
     * @param roomId
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE ChatRoom CR SET CR.detectiveReadStatus = 0 WHERE CR.roomId = ?1", nativeQuery = true)
    int changeReadStatusUnreadFromClient(String roomId);

    /**
     * 안읽음 상태로 변경(DETECTIVE) 레포지터리
     * @param roomId
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE ChatRoom CR SET CR.clientReadStatus = 0 WHERE CR.roomId = ?1", nativeQuery = true)
    int changeReadStatusUnreadFromDetective(String roomId);

}
