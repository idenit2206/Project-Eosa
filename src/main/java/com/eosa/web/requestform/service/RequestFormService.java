package com.eosa.web.requestform.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.eosa.web.requestform.entity.RequestForm;
import com.eosa.web.requestform.entity.SelectRequestFormList;
import com.eosa.web.requestform.repository.RequestFormRepository;

@Service
public class RequestFormService implements RequestFormRepository {

    @Autowired
    private RequestFormRepository requestFormRepository;
    
    @Override
    public <S extends RequestForm> S save(S entity) {
        return requestFormRepository.save(entity);
    }

    public int requestFormRegister(RequestForm entity) {
        LocalDateTime currentTime = LocalDateTime.now();
        
        entity.setRequestFormStatus("REQUEST");
        entity.setRequestFormDate(currentTime);
    
        return requestFormRepository.requestFormRegister(entity);
    }

    @Override
    public List<SelectRequestFormList> findByCompanysIdxIdx(Long companysIdx) {
        return requestFormRepository.findByCompanysIdxIdx(companysIdx);
    }

    @Override
    public List<RequestForm> findAll() {
        return requestFormRepository.findAll();
    }

    @Override
    public List<SelectRequestFormList> selectAllRequestFormList() {
        return requestFormRepository.selectAllRequestFormList();
    }

    @Override
    public List<SelectRequestFormList> selectAllRequestFormListByUsersIdx(Long usersIdx) {
        return requestFormRepository.selectAllRequestFormListByUsersIdx(usersIdx);
    }

    @Override
    public List<RequestForm> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<RequestForm> findAllById(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends RequestForm> List<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void flush() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <S extends RequestForm> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends RequestForm> List<S> saveAllAndFlush(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<RequestForm> entities) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAllInBatch() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public RequestForm getOne(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RequestForm getById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RequestForm getReferenceById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends RequestForm> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends RequestForm> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<RequestForm> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<RequestForm> findById(Long id) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(RequestForm entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll(Iterable<? extends RequestForm> entities) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <S extends RequestForm> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public <S extends RequestForm> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends RequestForm> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <S extends RequestForm> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <S extends RequestForm, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        // TODO Auto-generated method stub
        return null;
    }    

}
