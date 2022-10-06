package com.eosa.web.users.service;

import com.eosa.web.users.entity.UserRecentCompany;
import com.eosa.web.users.repository.UserRecentCompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
@Service
public class UserRecentCompanyService implements UserRecentCompanyRepository {

    @Autowired private UserRecentCompanyRepository userRecentCompanyRepository;

    
    /** 
     * @param entity
     * @return S
     */
    @Override
    public <S extends UserRecentCompany> S save(S entity) {
        List<UserRecentCompany> urcList = userRecentCompanyRepository.findByUsersIdx(entity.getUsersIdx());
        entity.setBrowseDate(LocalDateTime.now());
        return userRecentCompanyRepository.save(entity);
    }

    
    /** 
     * @param usersIdx
     * @return int
     */
    @Override
    public int countByUsersIdx(Long usersIdx) {
        return userRecentCompanyRepository.countByUsersIdx(usersIdx);
    }

    
    /** 
     * @return Long
     */
    @Override
    public Long selectOldestOneIdxByDate() {
        return userRecentCompanyRepository.selectOldestOneIdxByDate();
    }

    
    /** 
     * @param idx
     */
    @Override
    public void deleteOldestOne(Long idx) {
        userRecentCompanyRepository.deleteOldestOne(idx);
    }

    
    /** 
     * @param usersIdx
     * @return List<UserRecentCompany>
     */
    @Override
    public List<UserRecentCompany> findByUsersIdx(Long usersIdx) {
        return userRecentCompanyRepository.findByUsersIdx(usersIdx);
    }

    
    /** 
     * @return List<UserRecentCompany>
     */
    @Override
    public List<UserRecentCompany> findAll() {
        return null;
    }

    
    /** 
     * @param sort
     * @return List<UserRecentCompany>
     */
    @Override
    public List<UserRecentCompany> findAll(Sort sort) {
        return null;
    }

    
    /** 
     * @param pageable
     * @return Page<UserRecentCompany>
     */
    @Override
    public Page<UserRecentCompany> findAll(Pageable pageable) {
        return null;
    }

    
    /** 
     * @param longs
     * @return List<UserRecentCompany>
     */
    @Override
    public List<UserRecentCompany> findAllById(Iterable<Long> longs) {
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
    public void delete(UserRecentCompany entity) {

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
    public void deleteAll(Iterable<? extends UserRecentCompany> entities) {

    }

    @Override
    public void deleteAll() {

    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends UserRecentCompany> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    
    /** 
     * @param aLong
     * @return Optional<UserRecentCompany>
     */
    @Override
    public Optional<UserRecentCompany> findById(Long aLong) {
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
    public <S extends UserRecentCompany> S saveAndFlush(S entity) {
        return null;
    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends UserRecentCompany> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    
    /** 
     * @param entities
     */
    @Override
    public void deleteAllInBatch(Iterable<UserRecentCompany> entities) {

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
     * @return UserRecentCompany
     */
    @Override
    public UserRecentCompany getOne(Long aLong) {
        return null;
    }

    
    /** 
     * @param aLong
     * @return UserRecentCompany
     */
    @Override
    public UserRecentCompany getById(Long aLong) {
        return null;
    }

    
    /** 
     * @param aLong
     * @return UserRecentCompany
     */
    @Override
    public UserRecentCompany getReferenceById(Long aLong) {
        return null;
    }

    
    /** 
     * @param example
     * @return Optional<S>
     */
    @Override
    public <S extends UserRecentCompany> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    
    /** 
     * @param example
     * @return List<S>
     */
    @Override
    public <S extends UserRecentCompany> List<S> findAll(Example<S> example) {
        return null;
    }

    
    /** 
     * @param example
     * @param sort
     * @return List<S>
     */
    @Override
    public <S extends UserRecentCompany> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    
    /** 
     * @param example
     * @param pageable
     * @return Page<S>
     */
    @Override
    public <S extends UserRecentCompany> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    
    /** 
     * @param example
     * @return long
     */
    @Override
    public <S extends UserRecentCompany> long count(Example<S> example) {
        return 0;
    }

    
    /** 
     * @param example
     * @return boolean
     */
    @Override
    public <S extends UserRecentCompany> boolean exists(Example<S> example) {
        return false;
    }

    
    /** 
     * @param queryFunction
     * @return R
     */
    @Override
    public <S extends UserRecentCompany, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

}
