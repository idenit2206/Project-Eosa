package com.sherlockk.demo.users;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;


@Service
public class UsersService implements UsersRepository {

    @Autowired
    private UsersRepository usersRepository;


    /**
     * 사용자 정보 DB저장 (회원가입) - JPA Repository 기본형
     * @param <Users>entity Users 형태의 데이터를 매개변수로 받는다.
     */
    @Override
    public <S extends Users> S save(S entity) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        LocalDateTime currentTime = LocalDateTime.now();
        entity.setUsersPass(passwordEncoder.encode(entity.getUsersPass()));
        entity.setUsersEnabled(1);
        entity.setUsersJoinDate(currentTime);
        
        return usersRepository.save(entity);
    }

     /**
     * 사용자 정보 DB저장 (회원가입) - JPA INSERT test용
     * @param <Users>entity Users 형태의 데이터를 매개변수로 받는다.
     */
    public int userSave(Users entity) {
        int result = 0;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        LocalDateTime currentTime = LocalDateTime.now();
        entity.setUsersPass(passwordEncoder.encode(entity.getUsersPass()));
        entity.setUsersEnabled(1);
        entity.setUsersJoinDate(currentTime);

        try {
            result = usersRepository.userSave(entity);
        }
        catch(Exception e) {
            result = 0;
            System.out.println("XXX: " + e);
        }
               
        return result;
    }

    public Users findByUsersAccount(String usersAccount) {
        return usersRepository.findByUsersAccount(usersAccount);
    }

    /**
     * 모든 사용자 계정정보 조회(개발시 테스트용)
     */
    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    /**
     * 모든 사용자 계정정보 조회 정렬하여 출력(개발 테스트용)
     */
    @Override
    public List<Users> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Users> findAllById(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Users> List<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void flush() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <S extends Users> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Users> List<S> saveAllAndFlush(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Users> entities) {
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
    public Users getOne(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Users getById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Users getReferenceById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Users> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Users> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<Users> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }    

    @Override
    public Optional<Users> findById(Long id) {
        // TODO Auto-generated method stub
        return null;
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
    public void delete(Users entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll(Iterable<? extends Users> entities) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <S extends Users> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Users> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Users> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <S extends Users> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <S extends Users, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        // TODO Auto-generated method stub
        return null;
    }
    
    
}
