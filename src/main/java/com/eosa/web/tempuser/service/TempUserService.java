package com.eosa.web.tempuser.service;

import com.eosa.web.tempuser.repository.TempUserRepository;
import com.eosa.web.users.entity.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Slf4j
@Service
public class TempUserService implements TempUserRepository {
    @Autowired  private BCryptPasswordEncoder passwordEncoder;
    @Autowired private TempUserRepository tempUserRepository;

    
    /** 
     * @param entity
     * @return S
     */
    @Override
    public <S extends Users> S save(S entity) {
        entity.setUsersAccount(entity.getUsersEmail().split("@")[0]);
        entity.setUsersRole("TEMP");
        entity.setUsersJoinDate(LocalDateTime.now());
        entity.setUsersEnabled(1);
        return tempUserRepository.save(entity);
    }

    
    /** 
     * @param usersEmail
     * @param usersPass
     * @return Users
     */
    @Override
    public Users signIn(String usersEmail, String usersPass) {
        return tempUserRepository.signIn(usersEmail, usersPass);
    }


    
    /** 
     * @return List<Users>
     */
    @Override
    public List<Users> findAll() {
        return null;
    }

    
    /** 
     * @param sort
     * @return List<Users>
     */
    @Override
    public List<Users> findAll(Sort sort) {
        return null;
    }

    
    /** 
     * @param pageable
     * @return Page<Users>
     */
    @Override
    public Page<Users> findAll(Pageable pageable) {
        return null;
    }

    
    /** 
     * @param longs
     * @return List<Users>
     */
    @Override
    public List<Users> findAllById(Iterable<Long> longs) {
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
    public void delete(Users entity) {

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
    public void deleteAll(Iterable<? extends Users> entities) {

    }

    @Override
    public void deleteAll() {

    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends Users> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    
    /** 
     * @param aLong
     * @return Optional<Users>
     */
    @Override
    public Optional<Users> findById(Long aLong) {
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
    public <S extends Users> S saveAndFlush(S entity) {
        return null;
    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends Users> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    
    /** 
     * @param entities
     */
    @Override
    public void deleteAllInBatch(Iterable<Users> entities) {

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
     * @return Users
     */
    @Override
    public Users getOne(Long aLong) {
        return null;
    }

    
    /** 
     * @param aLong
     * @return Users
     */
    @Override
    public Users getById(Long aLong) {
        return null;
    }

    
    /** 
     * @param aLong
     * @return Users
     */
    @Override
    public Users getReferenceById(Long aLong) {
        return null;
    }

    
    /** 
     * @param example
     * @return Optional<S>
     */
    @Override
    public <S extends Users> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    
    /** 
     * @param example
     * @return List<S>
     */
    @Override
    public <S extends Users> List<S> findAll(Example<S> example) {
        return null;
    }

    
    /** 
     * @param example
     * @param sort
     * @return List<S>
     */
    @Override
    public <S extends Users> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    
    /** 
     * @param example
     * @param pageable
     * @return Page<S>
     */
    @Override
    public <S extends Users> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    
    /** 
     * @param example
     * @return long
     */
    @Override
    public <S extends Users> long count(Example<S> example) {
        return 0;
    }

    
    /** 
     * @param example
     * @return boolean
     */
    @Override
    public <S extends Users> boolean exists(Example<S> example) {
        return false;
    }

    
    /** 
     * @param queryFunction
     * @return R
     */
    @Override
    public <S extends Users, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
