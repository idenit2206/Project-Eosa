package com.eosa.web.requestform.service;

import com.eosa.web.requestform.entity.RequestFormCategory;
import com.eosa.web.requestform.repository.RequestFormCategoryRepository;
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
public class RequestFormCategoryService implements RequestFormCategoryRepository {

    @Autowired private RequestFormCategoryRepository requestFormCategoryRepository;
    @Override
    public int insertRequestFormCategory(RequestFormCategory entity) {
        return requestFormCategoryRepository.insertRequestFormCategory(entity);
    }

    @Override
    public List<RequestFormCategory> findAll() {
        return null;
    }

    @Override
    public List<RequestFormCategory> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<RequestFormCategory> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<RequestFormCategory> findAllById(Iterable<Long> longs) {
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
    public void delete(RequestFormCategory entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends RequestFormCategory> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends RequestFormCategory> S save(S entity) {
        return null;
    }

    @Override
    public <S extends RequestFormCategory> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<RequestFormCategory> findById(Long aLong) {
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
    public <S extends RequestFormCategory> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends RequestFormCategory> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<RequestFormCategory> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public RequestFormCategory getOne(Long aLong) {
        return null;
    }

    @Override
    public RequestFormCategory getById(Long aLong) {
        return null;
    }

    @Override
    public RequestFormCategory getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends RequestFormCategory> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends RequestFormCategory> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends RequestFormCategory> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends RequestFormCategory> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends RequestFormCategory> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends RequestFormCategory> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends RequestFormCategory, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
