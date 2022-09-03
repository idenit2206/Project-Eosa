package com.eosa.web.companys.service;

import com.eosa.web.companys.entity.CompanysFlagRegion;
import com.eosa.web.companys.repository.CompanysFlagRegionRepository;
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
public class CompanysFlagRegionService implements CompanysFlagRegionRepository {

    @Autowired private CompanysFlagRegionRepository companysFlagRegionRepository;

    @Override
    public <S extends CompanysFlagRegion> S save(S entity) {
        return companysFlagRegionRepository.save(entity);
    }

    @Override
    public List<CompanysFlagRegion> findAll() {
        return null;
    }

    @Override
    public List<CompanysFlagRegion> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<CompanysFlagRegion> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<CompanysFlagRegion> findAllById(Iterable<Long> longs) {
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
    public void delete(CompanysFlagRegion entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends CompanysFlagRegion> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends CompanysFlagRegion> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<CompanysFlagRegion> findById(Long aLong) {
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
    public <S extends CompanysFlagRegion> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends CompanysFlagRegion> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<CompanysFlagRegion> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public CompanysFlagRegion getOne(Long aLong) {
        return null;
    }

    @Override
    public CompanysFlagRegion getById(Long aLong) {
        return null;
    }

    @Override
    public CompanysFlagRegion getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends CompanysFlagRegion> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends CompanysFlagRegion> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends CompanysFlagRegion> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends CompanysFlagRegion> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends CompanysFlagRegion> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends CompanysFlagRegion> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends CompanysFlagRegion, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
