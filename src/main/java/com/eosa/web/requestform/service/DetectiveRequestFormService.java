package com.eosa.web.requestform.service;

import com.eosa.web.requestform.entity.RequestForm;
import com.eosa.web.requestform.entity.SelectRequestFormList;
import com.eosa.web.requestform.repository.DetectiveRequestFormRepository;
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
public class DetectiveRequestFormService implements DetectiveRequestFormRepository {

    @Autowired private DetectiveRequestFormRepository detectiveRequestFormRepository;

    @Override
    public List<SelectRequestFormList> selectAllDetectiveRequestFormListByCompanysIdx(Long companysIdx) {
        return detectiveRequestFormRepository.selectAllDetectiveRequestFormListByCompanysIdx(companysIdx);
    }

    @Override
    public List<SelectRequestFormList> selectAllDetectiveRequestFormListByCompanysIdxOrderByDESC(Long companysIdx) {
        return detectiveRequestFormRepository.selectAllDetectiveRequestFormListByCompanysIdxOrderByDESC(companysIdx);
    }

    @Override
    public List<SelectRequestFormList> selectAllDetectiveRequestFormListByCompanysIdxOrderByASC(Long companysIdx) {
        return detectiveRequestFormRepository.selectAllDetectiveRequestFormListByCompanysIdxOrderByASC(companysIdx);
    }

    @Override
    public List<RequestForm> findAll() {
        return null;
    }

    @Override
    public List<RequestForm> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<RequestForm> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<RequestForm> findAllById(Iterable<Long> longs) {
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
    public void delete(RequestForm entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends RequestForm> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends RequestForm> S save(S entity) {
        return null;
    }

    @Override
    public <S extends RequestForm> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<RequestForm> findById(Long aLong) {
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
    public <S extends RequestForm> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends RequestForm> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<RequestForm> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public RequestForm getOne(Long aLong) {
        return null;
    }

    @Override
    public RequestForm getById(Long aLong) {
        return null;
    }

    @Override
    public RequestForm getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends RequestForm> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends RequestForm> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends RequestForm> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends RequestForm> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends RequestForm> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends RequestForm> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends RequestForm, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
