package com.eosa.web.chatting.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.eosa.web.chatting.entity.ChatBlockList;
import com.eosa.web.chatting.repository.ChatBlockListRepository;

@Service
public class ChatBlockListService implements ChatBlockListRepository {

    @Autowired private ChatBlockListRepository chatBlockListRepository;
    
    /**
     * 채팅 차단을 추가하는 서비스
     */
    @Override
    public <S extends ChatBlockList> S save(S entity) {
        entity.setChatBlockListDate(LocalDateTime.now());
        return chatBlockListRepository.save(entity);
    }

    /**
     * usersIdxBlocker, usersIdxBlocked 가 일치하는 Entity 조회
     * @param usersIdxBlocker
     * @param usersIdxBlocked
     * @return
     */
    @Override
    public ChatBlockList selectByBlockerBlocked(Long usersIdxBlocker, Long usersIdxBlocked) {
        return chatBlockListRepository.selectByBlockerBlocked(usersIdxBlocker, usersIdxBlocked);
    }

    /**
     * usersIdxBlocker와 일치하는 chatBlockList List 를 조회하는 서비스
     */
    @Override
    public List<ChatBlockList> selectChatBlockListsByBlocker(Long usersIdxBlocker) {
        return chatBlockListRepository.selectChatBlockListsByBlocker(usersIdxBlocker);
    }

    /**
     * usersIdxBlocked와 일치하는 chatBlockList List 를 조회하는 서비스
     * @param usersIdxBlocked
     * @return
     */
    @Override
    public List<ChatBlockList> selectChatBlockListByBlocked(Long usersIdxBlocked) {
        return chatBlockListRepository.selectChatBlockListByBlocked(usersIdxBlocked);
    }

    /**
     * chatBlockListIdx와 일치하는 chatBlockList를 삭제하는 서비스
     */
    @Override
    public int deleteChatBlockListByChatBlockListIdx(Long chatBlockListIdx) {
        return chatBlockListRepository.deleteChatBlockListByChatBlockListIdx(chatBlockListIdx);
    }

    @Override
    public List<ChatBlockList> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ChatBlockList> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ChatBlockList> findAllById(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends ChatBlockList> List<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void flush() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <S extends ChatBlockList> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends ChatBlockList> List<S> saveAllAndFlush(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<ChatBlockList> entities) {
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
    public ChatBlockList getOne(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ChatBlockList getById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ChatBlockList getReferenceById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends ChatBlockList> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends ChatBlockList> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<ChatBlockList> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<ChatBlockList> findById(Long id) {
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
    public void delete(ChatBlockList entity) {
        chatBlockListRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll(Iterable<? extends ChatBlockList> entities) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <S extends ChatBlockList> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public <S extends ChatBlockList> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends ChatBlockList> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <S extends ChatBlockList> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <S extends ChatBlockList, R> R findBy(Example<S> example,
            Function<FetchableFluentQuery<S>, R> queryFunction) {
        // TODO Auto-generated method stub
        return null;
    }

    
}
