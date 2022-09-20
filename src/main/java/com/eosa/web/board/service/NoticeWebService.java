package com.eosa.web.board.service;

import com.eosa.web.board.entity.Notice;
import com.eosa.web.board.repository.NoticeRepository;
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
public class NoticeWebService implements NoticeRepository {

    @Autowired NoticeRepository noticeRepository;

    @Override
    public List<Notice> findAll() {
        return noticeRepository.findAll();
    }

    @Override
    public List<Notice> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Notice> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Notice> findAllById(Iterable<Long> longs) {
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
    public void delete(Notice entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Notice> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Notice> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Notice> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Notice> findById(Long aLong) {
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
    public <S extends Notice> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Notice> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Notice> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Notice getOne(Long aLong) {
        return null;
    }

    @Override
    public Notice getById(Long aLong) {
        return null;
    }

    @Override
    public Notice getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Notice> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Notice> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Notice> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Notice> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Notice> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Notice> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Notice, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
