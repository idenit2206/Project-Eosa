package com.eosa.web.usersreview;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.eosa.web.usersreview.entity.SelectReviewEntity;
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

    @Override
    public <S extends UsersReview> S save(S entity) {
        entity.setReviewDate(LocalDateTime.now());
        entity.setReviewDetail(entity.getReviewDetail().trim());
//        log.debug("[save]: {]", entity.toString());
        return null;
    }

    @Override
    public int insertUsersReview(UsersReview param) {
        param.setReviewDate(LocalDateTime.now());
        param.setReviewDetail(param.getReviewDetail().trim());
//        log.debug("[insertUsersReview]: {}", param.toString());
        return usersReviewRepository.insertUsersReview(param);
    }

    @Override
    public List<SelectReviewEntity> selectAllUsersReview() {
        return usersReviewRepository.selectAllUsersReview();
    }

    @Override
    public List<SelectReviewEntity> selectUsersReviewByCompanysIdx(Long comapnysIdx) {
        return usersReviewRepository.selectUsersReviewByCompanysIdx(comapnysIdx);
    }

    @Override
    public List<SelectReviewEntity> selectUsersReviewByUsersIdx(Long usersIdx) {
        return usersReviewRepository.selectUsersReviewByUsersIdx(usersIdx);
    }

    @Override
    public SelectReviewEntity selectOneUsersReviewByRequestFormIdx(Long requestFormIdx) {
        return usersReviewRepository.selectOneUsersReviewByRequestFormIdx(requestFormIdx);
    }

    @Override
    public List<UsersReview> findAll() {
        return null;
    }

    @Override
    public List<UsersReview> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UsersReview> findAllById(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends UsersReview> List<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void flush() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <S extends UsersReview> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends UsersReview> List<S> saveAllAndFlush(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<UsersReview> entities) {
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
    public UsersReview getOne(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UsersReview getById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UsersReview getReferenceById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends UsersReview> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends UsersReview> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<UsersReview> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<UsersReview> findById(Long id) {
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
    public void delete(UsersReview entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll(Iterable<? extends UsersReview> entities) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <S extends UsersReview> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public <S extends UsersReview> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends UsersReview> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <S extends UsersReview> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <S extends UsersReview, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        // TODO Auto-generated method stub
        return null;
    }

}
