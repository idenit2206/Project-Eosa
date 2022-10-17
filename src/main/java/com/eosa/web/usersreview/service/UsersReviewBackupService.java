package com.eosa.web.usersreview.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.eosa.web.usersreview.entity.SelectReviewEntity;
import com.eosa.web.usersreview.entity.UsersReview;
import com.eosa.web.usersreview.entity.UsersReviewBackup;
import com.eosa.web.usersreview.repository.UsersReviewBackupRepository;
import com.eosa.web.usersreview.repository.UsersReviewRepository;

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
public class UsersReviewBackupService implements UsersReviewBackupRepository{

    @Autowired UsersReviewBackupRepository usersReviewBackupRepository;

    @Override
    public <S extends UsersReviewBackup> S save(S entity) {
        return usersReviewBackupRepository.save(entity);
    }

    @Override
    public List<UsersReviewBackup> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UsersReviewBackup> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UsersReviewBackup> findAllById(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends UsersReviewBackup> List<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void flush() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <S extends UsersReviewBackup> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends UsersReviewBackup> List<S> saveAllAndFlush(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<UsersReviewBackup> entities) {
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
    public UsersReviewBackup getOne(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UsersReviewBackup getById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UsersReviewBackup getReferenceById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends UsersReviewBackup> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends UsersReviewBackup> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<UsersReviewBackup> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }   

    @Override
    public Optional<UsersReviewBackup> findById(Long id) {
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
    public void delete(UsersReviewBackup entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll(Iterable<? extends UsersReviewBackup> entities) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <S extends UsersReviewBackup> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public <S extends UsersReviewBackup> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends UsersReviewBackup> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <S extends UsersReviewBackup> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <S extends UsersReviewBackup, R> R findBy(Example<S> example,
            Function<FetchableFluentQuery<S>, R> queryFunction) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int insertUsersReview(UsersReviewBackup param) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<SelectReviewEntity> selectAllUsersReview() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<SelectReviewEntity> selectUsersReviewByCompanysIdx(Long comapnysIdx) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<SelectReviewEntity> selectUsersReviewByUsersIdx(Long usersIdx) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SelectReviewEntity selectOneUsersReviewByRequestFormIdx(Long requestFormIdx) {
        // TODO Auto-generated method stub
        return null;
    }


}
