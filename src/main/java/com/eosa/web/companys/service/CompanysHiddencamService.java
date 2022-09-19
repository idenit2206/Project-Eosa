package com.eosa.web.companys.service;

import com.eosa.web.companys.entity.CompanysHiddencam;
import com.eosa.web.companys.repository.CompanysHiddencamRepository;
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
public class CompanysHiddencamService implements CompanysHiddencamRepository {

    @Autowired private CompanysHiddencamRepository companysHiddencamRepository;

    @Override
    public <S extends CompanysHiddencam> S save(S entity) {
        entity.setCompanysHiddencamRequestDate(LocalDateTime.now());
        entity.setCompanysHiddencamCheckStatus(0);
        return companysHiddencamRepository.save(entity);
    }

    @Override
    public List<CompanysHiddencam> findAll() {
        return null;
    }

    @Override
    public List<CompanysHiddencam> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<CompanysHiddencam> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<CompanysHiddencam> findAllById(Iterable<Long> longs) {
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
    public void delete(CompanysHiddencam entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends CompanysHiddencam> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends CompanysHiddencam> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<CompanysHiddencam> findById(Long aLong) {
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
    public <S extends CompanysHiddencam> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends CompanysHiddencam> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<CompanysHiddencam> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public CompanysHiddencam getOne(Long aLong) {
        return null;
    }

    @Override
    public CompanysHiddencam getById(Long aLong) {
        return null;
    }

    @Override
    public CompanysHiddencam getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends CompanysHiddencam> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends CompanysHiddencam> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends CompanysHiddencam> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends CompanysHiddencam> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends CompanysHiddencam> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends CompanysHiddencam> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends CompanysHiddencam, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
