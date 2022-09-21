package com.eosa.web.users.service;

import com.eosa.web.companys.entity.SelectCompanys;
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

    @Override
    public <S extends com.eosa.web.users.entity.UserLikeCompany> S save(S entity) {
        entity.setLikeDate(LocalDateTime.now());
        entity.setUserLikeCompanyEnable(1);
//        log.debug("[save]: {}", entity.toString());
        return userLikeCompanyRepository.save(entity);
    }

    @Override
    public int deleteByUsersAndCompanysIdx(Long usersIdx, Long companysIdx) {
        return userLikeCompanyRepository.deleteByUsersAndCompanysIdx(usersIdx, companysIdx);
    }

    @Override
    public int selectUserLikeCompanyEnableByCompanysIdx(Long companysIdx) {
        return userLikeCompanyRepository.selectUserLikeCompanyEnableByCompanysIdx(companysIdx);
    }

    @Override
    public List<SelectCompanys> selectLikeCompanysListByUsersIdx(Long usersIdx) {
        return userLikeCompanyRepository.selectLikeCompanysListByUsersIdx(usersIdx);
    }

    @Override
    public List<com.eosa.web.users.entity.UserLikeCompany> findAll() {
        return null;
    }

    @Override
    public List<com.eosa.web.users.entity.UserLikeCompany> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<com.eosa.web.users.entity.UserLikeCompany> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<com.eosa.web.users.entity.UserLikeCompany> findAllById(Iterable<Long> longs) {
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
    public void delete(com.eosa.web.users.entity.UserLikeCompany entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends com.eosa.web.users.entity.UserLikeCompany> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends com.eosa.web.users.entity.UserLikeCompany> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<com.eosa.web.users.entity.UserLikeCompany> findById(Long aLong) {
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
    public <S extends com.eosa.web.users.entity.UserLikeCompany> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends com.eosa.web.users.entity.UserLikeCompany> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<com.eosa.web.users.entity.UserLikeCompany> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public com.eosa.web.users.entity.UserLikeCompany getOne(Long aLong) {
        return null;
    }

    @Override
    public com.eosa.web.users.entity.UserLikeCompany getById(Long aLong) {
        return null;
    }

    @Override
    public com.eosa.web.users.entity.UserLikeCompany getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends com.eosa.web.users.entity.UserLikeCompany> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends com.eosa.web.users.entity.UserLikeCompany> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends com.eosa.web.users.entity.UserLikeCompany> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends com.eosa.web.users.entity.UserLikeCompany> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends com.eosa.web.users.entity.UserLikeCompany> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends com.eosa.web.users.entity.UserLikeCompany> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends com.eosa.web.users.entity.UserLikeCompany, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

}
