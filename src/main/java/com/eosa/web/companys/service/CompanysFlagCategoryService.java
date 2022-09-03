package com.eosa.web.companys.service;

import com.eosa.web.companys.entity.CompanysFlagCategory;
import com.eosa.web.companys.repository.CompanysFlagCategoryRepository;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Service
public class CompanysFlagCategoryService implements CompanysFlagCategoryRepository {

    @Autowired private CompanysFlagCategoryRepository companysFlagCategoryRepository;

    @Override
    public <S extends CompanysFlagCategory> S save(S entity) {
        return companysFlagCategoryRepository.save(entity);
    }

    @Override
    public List<CompanysFlagCategory> findAll() {
        return null;
    }

    @Override
    public List<CompanysFlagCategory> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<CompanysFlagCategory> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<CompanysFlagCategory> findAllById(Iterable<Long> longs) {
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
    public void delete(CompanysFlagCategory entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends CompanysFlagCategory> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends CompanysFlagCategory> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<CompanysFlagCategory> findById(Long aLong) {
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
    public <S extends CompanysFlagCategory> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends CompanysFlagCategory> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<CompanysFlagCategory> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public CompanysFlagCategory getOne(Long aLong) {
        return null;
    }

    @Override
    public CompanysFlagCategory getById(Long aLong) {
        return null;
    }

    @Override
    public CompanysFlagCategory getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends CompanysFlagCategory> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends CompanysFlagCategory> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends CompanysFlagCategory> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends CompanysFlagCategory> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends CompanysFlagCategory> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends CompanysFlagCategory> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends CompanysFlagCategory, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
