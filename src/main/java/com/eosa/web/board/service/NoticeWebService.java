package com.eosa.web.board.service;

import com.eosa.web.board.entity.Notice;
import com.eosa.web.board.repository.NoticeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
@Service
public class NoticeWebService implements NoticeRepository {

    @Autowired NoticeRepository noticeRepository;

    
    /** 
     * @return List<Notice>
     */
    @Override
    public List<Notice> findAll() {
        return noticeRepository.findAll();
    }

    
    /** 
     * @param idx
     * @return Notice
     */
    @Override
    public Notice selectNoticeByNoticeIdx(Long idx) {
        return noticeRepository.selectNoticeByNoticeIdx(idx);
    }

    
    /** 
     * @param sort
     * @return List<Notice>
     */
    @Override
    public List<Notice> findAll(Sort sort) {
        return null;
    }

    
    /** 
     * @param pageable
     * @return Page<Notice>
     */
    @Override
    public Page<Notice> findAll(Pageable pageable) {
        return null;
    }

    
    /** 
     * @param longs
     * @return List<Notice>
     */
    @Override
    public List<Notice> findAllById(Iterable<Long> longs) {
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
    public void delete(Notice entity) {

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
    public void deleteAll(Iterable<? extends Notice> entities) {

    }

    @Override
    public void deleteAll() {

    }

    
    /** 
     * @param entity
     * @return S
     */
    @Override
    public <S extends Notice> S save(S entity) {
        return null;
    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends Notice> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    
    /** 
     * @param aLong
     * @return Optional<Notice>
     */
    @Override
    public Optional<Notice> findById(Long aLong) {
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
    public <S extends Notice> S saveAndFlush(S entity) {
        return null;
    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends Notice> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    
    /** 
     * @param entities
     */
    @Override
    public void deleteAllInBatch(Iterable<Notice> entities) {

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
     * @return Notice
     */
    @Override
    public Notice getOne(Long aLong) {
        return null;
    }

    
    /** 
     * @param aLong
     * @return Notice
     */
    @Override
    public Notice getById(Long aLong) {
        return null;
    }

    
    /** 
     * @param aLong
     * @return Notice
     */
    @Override
    public Notice getReferenceById(Long aLong) {
        return null;
    }

    
    /** 
     * @param example
     * @return Optional<S>
     */
    @Override
    public <S extends Notice> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    
    /** 
     * @param example
     * @return List<S>
     */
    @Override
    public <S extends Notice> List<S> findAll(Example<S> example) {
        return null;
    }

    
    /** 
     * @param example
     * @param sort
     * @return List<S>
     */
    @Override
    public <S extends Notice> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    
    /** 
     * @param example
     * @param pageable
     * @return Page<S>
     */
    @Override
    public <S extends Notice> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    
    /** 
     * @param example
     * @return long
     */
    @Override
    public <S extends Notice> long count(Example<S> example) {
        return 0;
    }

    
    /** 
     * @param example
     * @return boolean
     */
    @Override
    public <S extends Notice> boolean exists(Example<S> example) {
        return false;
    }

    
    /** 
     * @param queryFunction
     * @return R
     */
    @Override
    public <S extends Notice, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
