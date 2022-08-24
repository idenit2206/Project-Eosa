package com.eosa.web.chatting.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.eosa.web.chatting.model.ChatRoom;
import com.eosa.web.chatting.repository.ChatRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ChatService implements ChatRepository {    

    private Map<String, ChatRoom> chatRooms;
        
    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    public void testAllFlush() {
        chatRooms.clear();
    }

    @Autowired
    private ChatRepository chatRepository;

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
     * roomId를 갖는 채팅방 찾기
     * @param roomId
     * @return
     */
    public ChatRoom findById(String roomId) {
        return chatRooms.get(roomId);
    }

    /**
     * 채팅방 생성하기
     * @param roomName
     * @param usersIdx
     * @Param companysIdx
     * @return
     */
    public ChatRoom createChatRoom(String roomName, Long usersIdx, Long companysIdx) {
        ChatRoom chatRoom = ChatRoom.create(roomName, usersIdx, companysIdx);
        chatRooms.put(chatRoom.getRoomId(), chatRoom);
        log.info("## UsersIdx: {} create ChatROOM RoomId: {}\n## T: {}", Long.toString(usersIdx), chatRoom.getRoomId(), LocalDateTime.now());
        // chatRepository.createChatRoom(chatRoom);
        return chatRoom;
    }
    
    public List<ChatRoom> deleteChatRoom(String roomId) {
        chatRooms.remove(roomId);
        List<ChatRoom> result = new ArrayList<>(chatRooms.values());
    
        return result;
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
    public <S extends ChatRoom> S save(S entity) {
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

    @Override
    public int createChatRoom(ChatRoom chatRoom) {
        // TODO Auto-generated method stub
        return 0;
    }


}
