package com.eosa.web.users.service;

import com.eosa.web.users.entity.UserRecentCompany;
import com.eosa.web.users.repository.UserRecentCompanyRepository;
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

@Service
public class UserRecentCompanyService implements UserRecentCompanyRepository {

    @Autowired private UserRecentCompanyRepository userRecentCompanyRepository;

    @Override
    public int countByUsersIdx(Long usersIdx) {
        return userRecentCompanyRepository.countByUsersIdx(usersIdx);
    }

    @Override
    public Long selectOldestOneIdxByDate() {
        return userRecentCompanyRepository.selectOldestOneIdxByDate();
    }

    @Override
    public void deleteOldestOne(Long idx) {
        userRecentCompanyRepository.deleteOldestOne(idx);
    }

    @Override
    public List<UserRecentCompany> findAll() {
        return null;
    }

    @Override
    public List<UserRecentCompany> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<UserRecentCompany> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<UserRecentCompany> findAllById(Iterable<Long> longs) {
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
    public void delete(UserRecentCompany entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends UserRecentCompany> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends UserRecentCompany> S save(S entity) {
        return null;
    }

    @Override
    public <S extends UserRecentCompany> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<UserRecentCompany> findById(Long aLong) {
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
    public <S extends UserRecentCompany> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends UserRecentCompany> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<UserRecentCompany> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public UserRecentCompany getOne(Long aLong) {
        return null;
    }

    @Override
    public UserRecentCompany getById(Long aLong) {
        return null;
    }

    @Override
    public UserRecentCompany getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends UserRecentCompany> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends UserRecentCompany> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends UserRecentCompany> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends UserRecentCompany> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends UserRecentCompany> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends UserRecentCompany> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends UserRecentCompany, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

}
