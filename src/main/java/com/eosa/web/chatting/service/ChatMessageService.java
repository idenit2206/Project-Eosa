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

    public void addMessage(ChatMessage chatMessage) {
        chatMessagesList.add(chatMessage);
    }

    public List<ChatMessage> readMessagesList() {
        return chatMessagesList;
    }

    @Override
    public <S extends ChatMessage> S save(S entity) {
        return chatMessageRepository.save(entity);
    }

    public ChatMessage selectOneRecentMessageByRoomIdAndUsersIdx(String roomId, Long usersIdx) {
        return chatMessageRepository.selectOneRecentMessageByRoomIdAndUsersIdx(roomId, usersIdx);
    }

    @Override
    public List<ChatMessage> selectChatMessageByRoomId(String roomId) {
        return chatMessageRepository.selectChatMessageByRoomId(roomId);
    }

    @Override
    public List<ChatMessage> findAll() {
        return null;
    }

    @Override
    public List<ChatMessage> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<ChatMessage> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<ChatMessage> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(ChatMessage entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ChatMessage> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends ChatMessage> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ChatMessage> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends ChatMessage> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends ChatMessage> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<ChatMessage> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public ChatMessage getOne(Long aLong) {
        return null;
    }

    @Override
    public ChatMessage getById(Long aLong) {
        return null;
    }

    @Override
    public ChatMessage getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends ChatMessage> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends ChatMessage> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends ChatMessage> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends ChatMessage> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends ChatMessage> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends ChatMessage> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends ChatMessage, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

//    @Override
//    public int createChatRoom(ChatRoom chatRoom) {
//        return chatRoomRepository.createChatRoom(chatRoom);
//    }

}
