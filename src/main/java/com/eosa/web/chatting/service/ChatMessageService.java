package com.eosa.web.chatting.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.eosa.web.chatting.entity.ChatRoom;
import com.eosa.web.chatting.repository.ChatMessageRepository;
import com.eosa.web.chatting.repository.ChatRoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import com.eosa.web.chatting.entity.ChatMessage;

@Slf4j
@Service
public class ChatMessageService implements ChatMessageRepository {

    @Autowired private ChatRoomRepository chatRoomRepository;
    @Autowired private ChatMessageRepository chatMessageRepository;
    
    private static List<ChatMessage> chatMessagesList = new LinkedList<>();

    
    /** 
     * @param chatMessage
     */
    public void addMessage(ChatMessage chatMessage) {
        chatMessagesList.add(chatMessage);
    }

    
    /** 
     * @return List<ChatMessage>
     */
    public List<ChatMessage> readMessagesList() {
        return chatMessagesList;
    }

    
    /** 
     * @param entity
     * @return S
     */
    @Override
    public <S extends ChatMessage> S save(S entity) {
        return chatMessageRepository.save(entity);
    }

    
    /** 
     * @param roomId
     * @param usersIdx
     * @return ChatMessage
     */
    public ChatMessage selectOneRecentMessageByRoomIdAndUsersIdx(String roomId, Long usersIdx) {
        return chatMessageRepository.selectOneRecentMessageByRoomIdAndUsersIdx(roomId, usersIdx);
    }

    
    /** 
     * @param roomId
     * @return List<ChatMessage>
     */
    @Override
    public List<ChatMessage> selectChatMessageByRoomId(String roomId) {
        return chatMessageRepository.selectChatMessageByRoomId(roomId);
    }

    
    /** 
     * @return List<ChatMessage>
     */
    @Override
    public List<ChatMessage> findAll() {
        return null;
    }

    
    /** 
     * @param sort
     * @return List<ChatMessage>
     */
    @Override
    public List<ChatMessage> findAll(Sort sort) {
        return null;
    }

    
    /** 
     * @param pageable
     * @return Page<ChatMessage>
     */
    @Override
    public Page<ChatMessage> findAll(Pageable pageable) {
        return null;
    }

    
    /** 
     * @param longs
     * @return List<ChatMessage>
     */
    @Override
    public List<ChatMessage> findAllById(Iterable<Long> longs) {
        return null;
    }

    
    /** 
     * @return long
     */
    @Override
    public long count() {
        return 0;
    }

    
    /** 
     * @param aLong
     */
    @Override
    public void deleteById(Long aLong) {

    }

    
    /** 
     * @param entity
     */
    @Override
    public void delete(ChatMessage entity) {

    }

    
    /** 
     * @param longs
     */
    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    
    /** 
     * @param entities
     */
    @Override
    public void deleteAll(Iterable<? extends ChatMessage> entities) {

    }

    @Override
    public void deleteAll() {

    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends ChatMessage> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    
    /** 
     * @param aLong
     * @return Optional<ChatMessage>
     */
    @Override
    public Optional<ChatMessage> findById(Long aLong) {
        return Optional.empty();
    }

    
    /** 
     * @param aLong
     * @return boolean
     */
    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    
    /** 
     * @param entity
     * @return S
     */
    @Override
    public <S extends ChatMessage> S saveAndFlush(S entity) {
        return null;
    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends ChatMessage> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    
    /** 
     * @param entities
     */
    @Override
    public void deleteAllInBatch(Iterable<ChatMessage> entities) {

    }

    
    /** 
     * @param longs
     */
    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    
    /** 
     * @param aLong
     * @return ChatMessage
     */
    @Override
    public ChatMessage getOne(Long aLong) {
        return null;
    }

    
    /** 
     * @param aLong
     * @return ChatMessage
     */
    @Override
    public ChatMessage getById(Long aLong) {
        return null;
    }

    
    /** 
     * @param aLong
     * @return ChatMessage
     */
    @Override
    public ChatMessage getReferenceById(Long aLong) {
        return null;
    }

    
    /** 
     * @param example
     * @return Optional<S>
     */
    @Override
    public <S extends ChatMessage> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    
    /** 
     * @param example
     * @return List<S>
     */
    @Override
    public <S extends ChatMessage> List<S> findAll(Example<S> example) {
        return null;
    }

    
    /** 
     * @param example
     * @param sort
     * @return List<S>
     */
    @Override
    public <S extends ChatMessage> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    
    /** 
     * @param example
     * @param pageable
     * @return Page<S>
     */
    @Override
    public <S extends ChatMessage> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    
    /** 
     * @param example
     * @return long
     */
    @Override
    public <S extends ChatMessage> long count(Example<S> example) {
        return 0;
    }

    
    /** 
     * @param example
     * @return boolean
     */
    @Override
    public <S extends ChatMessage> boolean exists(Example<S> example) {
        return false;
    }

    
    /** 
     * @param queryFunction
     * @return R
     */
    @Override
    public <S extends ChatMessage, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

//    @Override
//    public int createChatRoom(ChatRoom chatRoom) {
//        return chatRoomRepository.createChatRoom(chatRoom);
//    }

}
