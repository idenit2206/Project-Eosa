package com.eosa.web.companys.service;

import com.eosa.web.companys.entity.CompanysFlag;
import com.eosa.web.companys.repository.CompanysFlagRepository;
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
public class CompanysFlagService implements CompanysFlagRepository {

    @Autowired private CompanysFlagRepository companysFlagRepository;

    @Override
    public <S extends CompanysFlag> S save(S entity) {
        entity.setFlagReqDate(LocalDateTime.now());
        entity.setCompanysFlagEnabled(0);
        log.debug("[save] entity: {}", entity.toString());
        return companysFlagRepository.save(entity);
    }

    @Override
    public List<CompanysFlag> findAll() {
        return null;
    }

    @Override
    public List<CompanysFlag> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<CompanysFlag> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<CompanysFlag> findAllById(Iterable<Long> longs) {
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
    public void delete(CompanysFlag entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends CompanysFlag> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends CompanysFlag> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<CompanysFlag> findById(Long aLong) {
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
    public <S extends CompanysFlag> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends CompanysFlag> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<CompanysFlag> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public CompanysFlag getOne(Long aLong) {
        return null;
    }

    @Override
    public CompanysFlag getById(Long aLong) {
        return null;
    }

    @Override
    public CompanysFlag getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends CompanysFlag> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends CompanysFlag> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends CompanysFlag> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends CompanysFlag> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends CompanysFlag> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends CompanysFlag> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends CompanysFlag, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
