package com.eosa.web.usersreview;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.eosa.web.usersreview.entity.SelectReviewEntity;
import com.eosa.web.usersreview.entity.UsersReview;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UsersReviewService implements UsersReviewRepository{

    @Autowired UsersReviewRepository usersReviewRepository;

    
    /** 
     * @param entity
     * @return S
     */
    @Override
    public <S extends UsersReview> S save(S entity) {
        entity.setReviewDate(LocalDateTime.now());
        entity.setReviewDetail(entity.getReviewDetail().trim());
//        log.debug("[save]: {]", entity.toString());
        return null;
    }

    
    /** 
     * @param param
     * @return int
     */
    @Override
    public int insertUsersReview(UsersReview param) {
        param.setReviewDate(LocalDateTime.now());
        param.setReviewDetail(param.getReviewDetail().trim());
//        log.debug("[insertUsersReview]: {}", param.toString());
        return usersReviewRepository.insertUsersReview(param);
    }

    
    /** 
     * @return List<SelectReviewEntity>
     */
    @Override
    public List<SelectReviewEntity> selectAllUsersReview() {
        return usersReviewRepository.selectAllUsersReview();
    }

    
    /** 
     * @param comapnysIdx
     * @return List<SelectReviewEntity>
     */
    @Override
    public List<SelectReviewEntity> selectUsersReviewByCompanysIdx(Long comapnysIdx) {
        return usersReviewRepository.selectUsersReviewByCompanysIdx(comapnysIdx);
    }

    
    /** 
     * @param usersIdx
     * @return List<SelectReviewEntity>
     */
    @Override
    public List<SelectReviewEntity> selectUsersReviewByUsersIdx(Long usersIdx) {
        return usersReviewRepository.selectUsersReviewByUsersIdx(usersIdx);
    }

    
    /** 
     * @param requestFormIdx
     * @return SelectReviewEntity
     */
    @Override
    public SelectReviewEntity selectOneUsersReviewByRequestFormIdx(Long requestFormIdx) {
        return usersReviewRepository.selectOneUsersReviewByRequestFormIdx(requestFormIdx);
    }

    
    /** 
     * @return List<UsersReview>
     */
    @Override
    public List<UsersReview> findAll() {
        return null;
    }

    
    /** 
     * @param sort
     * @return List<UsersReview>
     */
    @Override
    public List<UsersReview> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param ids
     * @return List<UsersReview>
     */
    @Override
    public List<UsersReview> findAllById(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends UsersReview> List<S> saveAll(Iterable<S> entities) {
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
    public <S extends UsersReview> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends UsersReview> List<S> saveAllAndFlush(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param entities
     */
    @Override
    public void deleteAllInBatch(Iterable<UsersReview> entities) {
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
     * @return UsersReview
     */
    @Override
    public UsersReview getOne(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param id
     * @return UsersReview
     */
    @Override
    public UsersReview getById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param id
     * @return UsersReview
     */
    @Override
    public UsersReview getReferenceById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param example
     * @return List<S>
     */
    @Override
    public <S extends UsersReview> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param example
     * @param sort
     * @return List<S>
     */
    @Override
    public <S extends UsersReview> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param pageable
     * @return Page<UsersReview>
     */
    @Override
    public Page<UsersReview> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param id
     * @return Optional<UsersReview>
     */
    @Override
    public Optional<UsersReview> findById(Long id) {
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
    public void delete(UsersReview entity) {
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
    public void deleteAll(Iterable<? extends UsersReview> entities) {
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
    public <S extends UsersReview> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    
    /** 
     * @param example
     * @param pageable
     * @return Page<S>
     */
    @Override
    public <S extends UsersReview> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param example
     * @return long
     */
    @Override
    public <S extends UsersReview> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    
    /** 
     * @param example
     * @return boolean
     */
    @Override
    public <S extends UsersReview> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }

    
    /** 
     * @param queryFunction
     * @return R
     */
    @Override
    public <S extends UsersReview, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        // TODO Auto-generated method stub
        return null;
    }

}
