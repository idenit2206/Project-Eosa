package com.eosa.web.users.service;

import com.eosa.web.companys.entity.SelectCompanys;
import com.eosa.web.companys.entity.SelectCompanysUserLikeCompanyEnable;
import com.eosa.web.users.repository.UserLikeCompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
@Service
public class UserLikeCompanyService implements UserLikeCompanyRepository {

    @Autowired private UserLikeCompanyRepository userLikeCompanyRepository;

    
    /** 
     * @param entity
     * @return S
     */
    @Override
    public <S extends com.eosa.web.users.entity.UserLikeCompany> S save(S entity) {
        entity.setLikeDate(LocalDateTime.now());
        entity.setUserLikeCompanyEnable(1);
//        log.debug("[save]: {}", entity.toString());
        return userLikeCompanyRepository.save(entity);
    }

    
    /** 
     * @param usersIdx
     * @param companysIdx
     * @return int
     */
    @Override
    public int deleteByUsersAndCompanysIdx(Long usersIdx, Long companysIdx) {
        return userLikeCompanyRepository.deleteByUsersAndCompanysIdx(usersIdx, companysIdx);
    }

    
    /** 
     * @param companysIdx
     * @return int
     */
    @Override
    public int selectUserLikeCompanyEnableByCompanysIdx(Long companysIdx) {
        return userLikeCompanyRepository.selectUserLikeCompanyEnableByCompanysIdx(companysIdx);
    }

    
    /** 
     * @param usersIdx
     * @return List<SelectCompanysUserLikeCompanyEnable>
     */
    @Override
    public List<SelectCompanysUserLikeCompanyEnable> selectLikeCompanysListByUsersIdx(Long usersIdx) {
        return userLikeCompanyRepository.selectLikeCompanysListByUsersIdx(usersIdx);
    }

    
    /** 
     * @return List<UserLikeCompany>
     */
    @Override
    public List<com.eosa.web.users.entity.UserLikeCompany> findAll() {
        return null;
    }

    
    /** 
     * @param sort
     * @return List<UserLikeCompany>
     */
    @Override
    public List<com.eosa.web.users.entity.UserLikeCompany> findAll(Sort sort) {
        return null;
    }

    
    /** 
     * @param pageable
     * @return Page<UserLikeCompany>
     */
    @Override
    public Page<com.eosa.web.users.entity.UserLikeCompany> findAll(Pageable pageable) {
        return null;
    }

    
    /** 
     * @param longs
     * @return List<UserLikeCompany>
     */
    @Override
    public List<com.eosa.web.users.entity.UserLikeCompany> findAllById(Iterable<Long> longs) {
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
    public void delete(com.eosa.web.users.entity.UserLikeCompany entity) {

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
    public void deleteAll(Iterable<? extends com.eosa.web.users.entity.UserLikeCompany> entities) {

    }

    @Override
    public void deleteAll() {

    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends com.eosa.web.users.entity.UserLikeCompany> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    
    /** 
     * @param aLong
     * @return Optional<UserLikeCompany>
     */
    @Override
    public Optional<com.eosa.web.users.entity.UserLikeCompany> findById(Long aLong) {
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
    public <S extends com.eosa.web.users.entity.UserLikeCompany> S saveAndFlush(S entity) {
        return null;
    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends com.eosa.web.users.entity.UserLikeCompany> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    
    /** 
     * @param entities
     */
    @Override
    public void deleteAllInBatch(Iterable<com.eosa.web.users.entity.UserLikeCompany> entities) {

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
     * @return UserLikeCompany
     */
    @Override
    public com.eosa.web.users.entity.UserLikeCompany getOne(Long aLong) {
        return null;
    }

    
    /** 
     * @param aLong
     * @return UserLikeCompany
     */
    @Override
    public com.eosa.web.users.entity.UserLikeCompany getById(Long aLong) {
        return null;
    }

    
    /** 
     * @param aLong
     * @return UserLikeCompany
     */
    @Override
    public com.eosa.web.users.entity.UserLikeCompany getReferenceById(Long aLong) {
        return null;
    }

    
    /** 
     * @param example
     * @return Optional<S>
     */
    @Override
    public <S extends com.eosa.web.users.entity.UserLikeCompany> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    
    /** 
     * @param example
     * @return List<S>
     */
    @Override
    public <S extends com.eosa.web.users.entity.UserLikeCompany> List<S> findAll(Example<S> example) {
        return null;
    }

    
    /** 
     * @param example
     * @param sort
     * @return List<S>
     */
    @Override
    public <S extends com.eosa.web.users.entity.UserLikeCompany> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    
    /** 
     * @param example
     * @param pageable
     * @return Page<S>
     */
    @Override
    public <S extends com.eosa.web.users.entity.UserLikeCompany> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    
    /** 
     * @param example
     * @return long
     */
    @Override
    public <S extends com.eosa.web.users.entity.UserLikeCompany> long count(Example<S> example) {
        return 0;
    }

    
    /** 
     * @param example
     * @return boolean
     */
    @Override
    public <S extends com.eosa.web.users.entity.UserLikeCompany> boolean exists(Example<S> example) {
        return false;
    }

    
    /** 
     * @param queryFunction
     * @return R
     */
    @Override
    public <S extends com.eosa.web.users.entity.UserLikeCompany, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

}
