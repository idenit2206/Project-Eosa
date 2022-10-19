package com.eosa.web.requestcontract.service;

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

import com.eosa.web.requestcontract.entity.RequestContract;
import com.eosa.web.requestcontract.repository.RequestContractRepository;

@Service
public class RequestContractService implements RequestContractRepository {

    @Autowired private RequestContractRepository requestContractRepository;

    
    /** 
     * @param entity
     * @return S
     */
    @Override
    public <S extends RequestContract> S save(S entity) {
        entity.setRequestContractTurn(1);
        entity.setRequestContractCreateDate(LocalDateTime.now());
        return requestContractRepository.save(entity);
    }
    
    /** 
     * requestFormIdx와 일치하는 RequestContract를 조회하는 서비스
     * @param requestFormIdx
     * @return RequestContract
     */
    @Override
    public RequestContract selectRequestContractByRequestFormIdx(Long requestFormIdx) {
        return requestContractRepository.selectRequestContractByRequestFormIdx(requestFormIdx);
    }

    @Override
    public int updateRequestContract(Long requestFormIdx, String requestContractContractId) {
        return requestContractRepository.updateRequestContract(requestFormIdx, requestContractContractId);
    }

    
    /** 
     * @return List<RequestContract>
     */
    @Override
    public List<RequestContract> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param sort
     * @return List<RequestContract>
     */
    @Override
    public List<RequestContract> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param ids
     * @return List<RequestContract>
     */
    @Override
    public List<RequestContract> findAllById(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends RequestContract> List<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void flush() {
        // TODO Auto-generated method stub
        
    }

    
    /** 
     * @param entity
     * @return S
     */
    @Override
    public <S extends RequestContract> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends RequestContract> List<S> saveAllAndFlush(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param entities
     */
    @Override
    public void deleteAllInBatch(Iterable<RequestContract> entities) {
        // TODO Auto-generated method stub
        
    }

    
    /** 
     * @param ids
     */
    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAllInBatch() {
        // TODO Auto-generated method stub
        
    }

    
    /** 
     * @param id
     * @return RequestContract
     */
    @Override
    public RequestContract getOne(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param id
     * @return RequestContract
     */
    @Override
    public RequestContract getById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param id
     * @return RequestContract
     */
    @Override
    public RequestContract getReferenceById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param example
     * @return List<S>
     */
    @Override
    public <S extends RequestContract> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param example
     * @param sort
     * @return List<S>
     */
    @Override
    public <S extends RequestContract> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param pageable
     * @return Page<RequestContract>
     */
    @Override
    public Page<RequestContract> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }   

    
    /** 
     * @param id
     * @return Optional<RequestContract>
     */
    @Override
    public Optional<RequestContract> findById(Long id) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    
    /** 
     * @param id
     * @return boolean
     */
    @Override
    public boolean existsById(Long id) {
        // TODO Auto-generated method stub
        return false;
    }

    
    /** 
     * @return long
     */
    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    
    /** 
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        
    }

    
    /** 
     * @param entity
     */
    @Override
    public void delete(RequestContract entity) {
        // TODO Auto-generated method stub
        
    }

    
    /** 
     * @param ids
     */
    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        // TODO Auto-generated method stub
        
    }

    
    /** 
     * @param entities
     */
    @Override
    public void deleteAll(Iterable<? extends RequestContract> entities) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        
    }

    
    /** 
     * @param example
     * @return Optional<S>
     */
    @Override
    public <S extends RequestContract> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    
    /** 
     * @param example
     * @param pageable
     * @return Page<S>
     */
    @Override
    public <S extends RequestContract> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param example
     * @return long
     */
    @Override
    public <S extends RequestContract> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    
    /** 
     * @param example
     * @return boolean
     */
    @Override
    public <S extends RequestContract> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }

    
    /** 
     * @param queryFunction
     * @return R
     */
    @Override
    public <S extends RequestContract, R> R findBy(Example<S> example,
            Function<FetchableFluentQuery<S>, R> queryFunction) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
