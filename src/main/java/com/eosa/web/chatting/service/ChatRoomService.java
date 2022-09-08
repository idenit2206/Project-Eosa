package com.eosa.web.chatting.service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;

import javax.annotation.PostConstruct;

import com.eosa.web.chatting.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.eosa.web.chatting.entity.ChatRoom;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ChatRoomService implements ChatRoomRepository {

    private Map<String, ChatRoom> chatRooms;
        
    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    public void testAllFlush() {
        chatRooms.clear();
    }

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    /**
     * 모든 채팅방 목록보기
     * @return
    */
    public List<ChatRoom> findAllRoom() {
        List<ChatRoom> result = new ArrayList<>(chatRooms.values());
        Collections.reverse(result);
        return result;
    }

    /**
     * ChatRoom 찾기 roomId 기준
     * @param roomId
     * @return
     */
    public ChatRoom findChatRoomByRoomId(String roomId) {
        return chatRooms.get(roomId);
    }

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
     * 채팅방 생성하기2
     * @param entity must not be {@literal null}.
     * @return
     * @param <S>
     */
    @Override
    public <S extends ChatRoom> S save(S entity) {
        entity.setRoomId(UUID.randomUUID().toString());
        entity.setRoomName(
            "CLIENT " + String.valueOf(entity.getUsersIdx()) + " 과 " +
            String.valueOf(entity.getCompanysIdx()) + " 의 대화방" +
            String.valueOf(LocalDateTime.now())
        );
        entity.setDataInfo("");
        entity.setCreatedDate(LocalDateTime.now());
        entity.setUsable(1);
        chatRooms.put(entity.getRoomName(), entity);
        log.info("[save] insertRoomInfo: {}", entity.toString());
        return (S) chatRoomRepository.save(entity);
    }
    
    public List<ChatRoom> deleteChatRoom(String roomId) {
        chatRooms.remove(roomId);
        List<ChatRoom> result = new ArrayList<>(chatRooms.values());
    
        return result;
    }

    @Override
    public List<ChatRoom> selectChatRoomListByUsersIdx(Long usersIdx) {
        return chatRoomRepository.selectChatRoomListByUsersIdx(usersIdx);
    }

    @Override
    public List<ChatRoom> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ChatRoom> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ChatRoom> findAllById(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends ChatRoom> List<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void flush() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <S extends ChatRoom> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends ChatRoom> List<S> saveAllAndFlush(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<ChatRoom> entities) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAllInBatch() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ChatRoom getOne(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ChatRoom getById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ChatRoom getReferenceById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends ChatRoom> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends ChatRoom> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<ChatRoom> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<ChatRoom> findById(Long id) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(ChatRoom entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll(Iterable<? extends ChatRoom> entities) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <S extends ChatRoom> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public <S extends ChatRoom> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends ChatRoom> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <S extends ChatRoom> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <S extends ChatRoom, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        // TODO Auto-generated method stub
        return null;
    }

//    @Override
//    public int createChatRoom(ChatRoom chatRoom) {
//        // TODO Auto-generated method stub
//        return 0;
//    }


}
