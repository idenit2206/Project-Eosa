package com.eosa.web.tempuser.service;

import com.eosa.web.tempuser.entity.TempUser;
import com.eosa.web.tempuser.repository.TempUserRepository;
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
import java.util.function.Function;

@Slf4j
@Service
public class TempUserService implements TempUserRepository {
    @Autowired  private BCryptPasswordEncoder passwordEncoder;
    @Autowired private TempUserRepository tempUserRepository;

    @Override
    public <S extends TempUser> S save(S entity) {
        entity.setTempUserPass(passwordEncoder.encode(entity.getTempUserPass()));
        entity.setTempUserRegistDate(LocalDateTime.now());
        return tempUserRepository.save(entity);
    }

    @Override
    public List<TempUser> findAll() {
        return null;
    }

    @Override
    public List<TempUser> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<TempUser> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<TempUser> findAllById(Iterable<Long> longs) {
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
    public void delete(TempUser entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends TempUser> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends TempUser> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<TempUser> findById(Long aLong) {
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
    public <S extends TempUser> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends TempUser> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<TempUser> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public TempUser getOne(Long aLong) {
        return null;
    }

    @Override
    public TempUser getById(Long aLong) {
        return null;
    }

    @Override
    public TempUser getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends TempUser> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends TempUser> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends TempUser> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends TempUser> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends TempUser> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends TempUser> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends TempUser, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

}
