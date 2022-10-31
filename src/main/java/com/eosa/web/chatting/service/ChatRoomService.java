package com.eosa.web.chatting.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;

import javax.annotation.PostConstruct;

import com.eosa.web.chatting.repository.ChatBlockListRepository;
import com.eosa.web.chatting.repository.ChatRoomRepository;
import com.eosa.web.companys.entity.SelectAllCompanysList;
import com.eosa.web.companys.service.CompanysService;
import com.eosa.web.users.service.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.eosa.web.chatting.entity.ChatBlockList;
import com.eosa.web.chatting.entity.ChatRoom;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ChatRoomService implements ChatRoomRepository {

    private Map<String, ChatRoom> chatRooms;
    private Set<String> chatRoomIdList;

    @Autowired private UsersService usersService;
    @Autowired private CompanysService companysService;
    @Autowired private ChatRoomRepository chatRoomRepository;
    @Autowired private ChatBlockListRepository chatBlockListRepository;
    
    @PostConstruct
    private void initChatRooms() {
        chatRooms = new LinkedHashMap<>();
    }

    @PostConstruct
    private void initChatRoomIdList() {
        chatRoomIdList = new HashSet<>();
    }

    public void testAllFlush() {
        chatRooms.clear();
        chatRoomIdList.clear();
    }

    /**
     * roomId가 일치하는 ChatRoom 찾기(Backend 서버 메모리에서) 서비스
     * @param roomId
     * @return
     */
    public ChatRoom findChatRoomByRoomId(String roomId) {
        return chatRooms.get(roomId);
    }
    
    /** 
     * usersIdx가 일치하는 채팅방들을 List로 출력하는 서비스
     * @param usersIdx
     * @return List<ChatRoom>
     */
    public List<ChatRoom> getChatRoomListByUsersIdx(Long usersIdx) {
        List<ChatRoom> result = new ArrayList<>();
        Iterator<String> keys = chatRooms.keySet().iterator();
        while(keys.hasNext()) {
            String key = keys.next();
            ChatRoom c = chatRooms.get(key);
            if(c.getUsersIdx() == usersIdx) {
                result.add(c);
            }
        }
        return result;
    }

    /**
    * usersIdx와 companysIdx가 일치하는 ChatRoom을 출력하는 서비스
    */    
    public ChatRoom selectChatRoomByUsersIdxCompanysIdx(Long usersIdx, Long companysIdx) {
        return chatRoomRepository.selectChatRoomByUsersIdxCompanysIdx(usersIdx, companysIdx);
    }

    /**
     * roomId와 일치하는 Chatroom을 초기화 하는 서비스
     */
    public int initChatRoom(String roomId) {
        return chatRoomRepository.initChatRoom(roomId);
    }

    /**
     * 채팅방을 새로 생성하는 서비스
     * @param entity must not be {@literal null}.
     * @return
     * @param <S>
     */
    @Override
    public <S extends ChatRoom> S save(S entity) {
        if(entity.getCompanysIdx() == 0) {
            // 상담사와 채팅을 시작하는 경우
            entity.setRoomId(UUID.randomUUID().toString());
            entity.setRoomName("상담사");
            entity.setDataInfo("");
            entity.setCreatedDate(LocalDateTime.now());
            entity.setUsable(1);
            entity.setUsersUsable(1);
            entity.setCompanysUsable(1);
            chatRooms.put(entity.getRoomName(), entity);
            log.info("[save] insertRoomInfo: {}", entity.toString());
            return (S) chatRoomRepository.save(entity);
        } else {
            SelectAllCompanysList companys =  companysService.selectCompanysByCompanysIdx(entity.getCompanysIdx());
    //        log.debug("[save] companys: {}", companys.toString());
            String companysName = companys.getCompanysName();

            entity.setRoomId(UUID.randomUUID().toString());
            entity.setRoomName(companysName);
            entity.setDataInfo("");
            entity.setCreatedDate(LocalDateTime.now());
            entity.setUsable(1);
            entity.setUsersUsable(1);
            entity.setCompanysUsable(1);
            chatRooms.put(entity.getRoomName(), entity);
            log.info("[save] insertRoomInfo: {}", entity.toString());
            return (S) chatRoomRepository.save(entity);
        }
    }

    
    /** 
     * roomId가 일치하는 채팅방을 삭제하는 서비스
     * @param roomId
     * @return int
     */
    @Override
    public int deleteRoomByRoomId(String roomId) {
        return chatRoomRepository.deleteRoomByRoomId(roomId);


    }

    /** 
     * roomId가 일치하는 채팅방을 삭제하는 서비스(CLIENT)
     * @param roomId
     * @return int
     */
    @Override
    public int deleteRoomByRoomIdClient(String roomId) {
        return chatRoomRepository.deleteRoomByRoomIdClient(roomId);
    }

    /** 
     * roomId가 일치하는 채팅방을 삭제하는 서비스(DETECTIVE)
     * @param roomId
     * @return int
     */
    @Override
    public int deleteRoomByRoomIdDetective(String roomId) {
        return chatRoomRepository.deleteRoomByRoomIdDetective(roomId);
    }

    
    /** 
     * roomId가 일치하는 채팅방을 Backend서버 메모리에서 삭제하고
     * Backend 서버의 채팅방 List를 출력하는 서비스
     * @param roomId
     * @return List<ChatRoom>
     */
    public List<ChatRoom> selectRoomListOnServer(String roomId) {
        chatRooms.remove(roomId);
        List<ChatRoom> result = new ArrayList<>(chatRooms.values());
        return result;
    }

    /**
     * usersIdx가 일치하는 채팅방들을 List 형식으로 반환하는 서비스
     * @param usersIdx
     * @return
     */
    @Override
    public List<ChatRoom> selectChatRoomListByUsersIdx(Long usersIdx) {
        List<ChatBlockList> blockList = chatBlockListRepository.selectChatBlockListsByBlocker(usersIdx);
        // List<Long> blockedList = new ArrayList<>();
        // if(blockList != null) {
        //     for(int i = 0; i < blockList.size(); i++) {
        //         blockedList.add(blockList.get(i).getUsersIdxBlocked());
        //     }
        // }

        // List<ChatRoom> result = new ArrayList<>();
        // if(blockedList == null) {
        //     result = chatRoomRepository.selectChatRoomListByUsersIdx(usersIdx);
        // }
        // else {
        //     for(int i = 0; i < blockedList.size(); i++) {
        //         ChatRoom r = chatRoomRepository.selectselectChatRoomListByUsersIdx02(usersIdx, blockedList);
        //     }
        //     result.add()
        // }
        List<ChatRoom> result = chatRoomRepository.selectChatRoomListByUsersIdx(usersIdx);
        
        for(int i = 0; i < result.size(); i++) {
           chatRoomIdList.add(result.get(i).getRoomId());
        }
        Iterator<String> iter = chatRoomIdList.iterator();
        while(iter.hasNext()) {
            String crid = iter.next();
            log.debug("[selectChatRoomListByUsersIdx] iter: {}", crid);
            ChatRoom cr = selectChatRoomByChatRoomId(crid);
            chatRooms.put(crid, cr);
        }
        return result;
    }


    /**
     * companysIdx가 일치하는 채팅방들을 List로 출력하는 서비스
     * @param companysIdx
     * @return
     */
    @Override
    public List<ChatRoom> selectChatRoomListByCompanysIdx(Long companysIdx) {
        List<ChatRoom> result = chatRoomRepository.selectChatRoomListByCompanysIdx(companysIdx);
        for(int i = 0; i < result.size(); i++) {
            chatRooms.put(result.get(i).getRoomId(), result.get(i));
        }
        return result;
    }

    
    /** 
     * roomId가 일치하는 ChatRoom 찾기(DB에서) 하는 서비스
     * @param roomId
     * @return ChatRoom
     */
    @Override
    public ChatRoom selectChatRoomByChatRoomId(String roomId) {
        return chatRoomRepository.selectChatRoomByChatRoomId(roomId);
    }

    /**
     * 모든 채팅방 목록보기(테스트용) 하는 서비스
     * @return
     */
    public List<ChatRoom> findAllRoom() {
        // List<ChatRoom> result = new ArrayList<>(chatRooms.values()); 
        List<ChatRoom> result = null;
        List<ChatRoom> selectRows = chatRoomRepository.findAll();
        for(int i = 0; i < selectRows.size(); i++) {
            chatRooms.put(selectRows.get(i).getRoomId(), selectRows.get(i));
        }
        result = new ArrayList<>(chatRooms.values());
        Collections.reverse(result);
        return result;
    }
    
    /**
     * roomId와 일치하는 채팅방의 client와 detective의 읽음 상태를 출력하는 서비스
     * @param roomId
     * @return ChatRoom
     */
    @Override
    public ChatRoom selectReadStatus(String roomId) {
        return chatRoomRepository.selectReadStatus(roomId);
    }

    /**
     * 채팅방 읽음처리하는 서비스
     * @param roomId
     * @param usersIdx
     */
    public void changeReadStatus(String roomId, Long userIdx) {
        ChatRoom room = selectChatRoomByChatRoomId(roomId);
        Long usersIdx = 0L;
        Long companysIdx = companysService.selectCompanysIdxByUsersIdx(userIdx);
        if(companysIdx == null || companysIdx == 0) {
            usersIdx = userIdx;
        }
        else {
            usersIdx = room.getUsersIdx();
        }
        if(usersIdx != null && companysIdx != null) {
            // 사용자가 DETECTIVE인 경우
            // log.info("DETECTIVE 또는 ADMIN이 메시지를 읽었습니다.");
            chatRoomRepository.changeReadStatusReadFromDetective(roomId);
        }
        else {        
            // 사용자가 CLIENT인 경우
            // log.info("CLIENT가 메시지를 읽었습니다.");
            chatRoomRepository.changeReadStatusReadFromClient(roomId);
        }
    }

    /**
     * Client가 roomId의 채팅방에 메시지를 보낼때 상대방의 읽음 상태를 안읽음으로 변경하는 서비스
     */
    @Override
    public int changeReadStatusUnreadFromClient(String roomId) {
        return chatRoomRepository.changeReadStatusUnreadFromClient(roomId);
    }

    /**
     * Detective가 roomId의 채팅방에 메시지를 보낼때 상대방의 읽음 상태를 안읽음으로 변경하는 서비스
     */
    @Override
    public int changeReadStatusUnreadFromDetective(String roomId) {
        return chatRoomRepository.changeReadStatusUnreadFromDetective(roomId);
    }
    
    /** 
     * @return List<ChatRoom>
     */
    @Override
    public List<ChatRoom> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param sort
     * @return List<ChatRoom>
     */
    @Override
    public List<ChatRoom> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param ids
     * @return List<ChatRoom>
     */
    @Override
    public List<ChatRoom> findAllById(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends ChatRoom> List<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void flush() {
        // TODO Auto-generated method stub
        
    }

    
    /** 
     * @param entity
     * @return S
     */
    @Override
    public <S extends ChatRoom> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends ChatRoom> List<S> saveAllAndFlush(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param entities
     */
    @Override
    public void deleteAllInBatch(Iterable<ChatRoom> entities) {
        // TODO Auto-generated method stub
        
    }

    
    /** 
     * @param ids
     */
    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAllInBatch() {
        // TODO Auto-generated method stub
        
    }

    
    /** 
     * @param id
     * @return ChatRoom
     */
    @Override
    public ChatRoom getOne(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param id
     * @return ChatRoom
     */
    @Override
    public ChatRoom getById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param id
     * @return ChatRoom
     */
    @Override
    public ChatRoom getReferenceById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param example
     * @return List<S>
     */
    @Override
    public <S extends ChatRoom> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param example
     * @param sort
     * @return List<S>
     */
    @Override
    public <S extends ChatRoom> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param pageable
     * @return Page<ChatRoom>
     */
    @Override
    public Page<ChatRoom> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param id
     * @return Optional<ChatRoom>
     */
    @Override
    public Optional<ChatRoom> findById(Long id) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    
    /** 
     * @param id
     * @return boolean
     */
    @Override
    public boolean existsById(Long id) {
        // TODO Auto-generated method stub
        return false;
    }

    
    /** 
     * @return long
     */
    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    
    /** 
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        
    }

    
    /** 
     * @param entity
     */
    @Override
    public void delete(ChatRoom entity) {
        // TODO Auto-generated method stub
        
    }

    
    /** 
     * @param ids
     */
    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        // TODO Auto-generated method stub
        
    }

    
    /** 
     * @param entities
     */
    @Override
    public void deleteAll(Iterable<? extends ChatRoom> entities) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        
    }

    
    /** 
     * @param example
     * @return Optional<S>
     */
    @Override
    public <S extends ChatRoom> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    
    /** 
     * @param example
     * @param pageable
     * @return Page<S>
     */
    @Override
    public <S extends ChatRoom> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param example
     * @return long
     */
    @Override
    public <S extends ChatRoom> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    
    /** 
     * @param example
     * @return boolean
     */
    @Override
    public <S extends ChatRoom> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }

    
    /** 
     * @param queryFunction
     * @return R
     */
    @Override
    public <S extends ChatRoom, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * usersIdx가 일치하는 List<roomId> 출력하는 서비스
     */
    @Override
    public List<String> selectChatRoomIdListByUsersIdx(Long usersIdx) {
        return chatRoomRepository.selectChatRoomIdListByUsersIdx(usersIdx);
    }

    /**
     * * 읽음 상태로 변경(CLIENT)하는 서비스
     */
    @Override
    public int changeReadStatusReadFromClient(String roomId) {
        return chatRoomRepository.changeReadStatusReadFromClient(roomId);
    }

    /**
     * 읽음 상태로 변경(CLIENT) 하는 서비스
     */
    @Override
    public int changeReadStatusReadFromDetective(String roomId) {
        return chatRoomRepository.changeReadStatusReadFromDetective(roomId);
    }

    /**
     * usersIdx가 일치하는 Chatroom(차단대상 제외)을 조회하는 서비스
     */
    @Override
    public ChatRoom selectChatRoomListByUsersIdx02(Long usersIdx, Long usersIdxBlocked) {
        return chatRoomRepository.selectChatRoomListByUsersIdx02(usersIdx, usersIdxBlocked);
    }

    /**
     * usersIdx 가 일치하는 ChatRoom을 조회하는 서비스
     */
    @Override
    public ChatRoom selectChatRoomByUsersIdx(Long usersIdx) {
        return chatRoomRepository.selectChatRoomByUsersIdx(usersIdx);
    }

}
