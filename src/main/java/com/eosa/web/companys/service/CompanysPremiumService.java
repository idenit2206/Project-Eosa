package com.eosa.web.companys.service;

import com.eosa.web.companys.entity.CompanysPremium;
import com.eosa.web.companys.repository.CompanysPremiumRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
@Service
public class CompanysPremiumService implements CompanysPremiumRepository {

    @Autowired private CompanysPremiumRepository companysPremiumRepository;

    @Override
    public <S extends CompanysPremium> S save(S entity) {
        entity.setPremiumReqDate(LocalDateTime.now());
        entity.setCompanysPremiumEnabled(0);
        return companysPremiumRepository.save(entity);
    }

    @Override
    public List<CompanysPremium> findAll() {
        return null;
    }

    @Override
    public List<CompanysPremium> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<CompanysPremium> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<CompanysPremium> findAllById(Iterable<Long> longs) {
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
    public void delete(CompanysPremium entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends CompanysPremium> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends CompanysPremium> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<CompanysPremium> findById(Long aLong) {
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
    public <S extends CompanysPremium> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends CompanysPremium> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<CompanysPremium> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public CompanysPremium getOne(Long aLong) {
        return null;
    }

    @Override
    public CompanysPremium getById(Long aLong) {
        return null;
    }

    @Override
    public CompanysPremium getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends CompanysPremium> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends CompanysPremium> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends CompanysPremium> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends CompanysPremium> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends CompanysPremium> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends CompanysPremium> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends CompanysPremium, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
