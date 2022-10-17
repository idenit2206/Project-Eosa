package com.eosa.web.requestform.service.tablebackup;

import com.eosa.web.requestform.entity.RequestFormCategory;
import com.eosa.web.requestform.repository.tablebackup.RequestFormCategoryBackupRepository;

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
public class RequestFormCategoryBackupService implements RequestFormCategoryBackupRepository {

    @Autowired private RequestFormCategoryBackupRepository requestFormCategoryBackupRepository;
    
    /** 
     * @param entity
     * @return int
     */
    @Override
    public int insertRequestFormCategory(RequestFormCategory entity) {
        return requestFormCategoryBackupRepository.insertRequestFormCategory(entity);
    }

    
    /** 
     * @return List<RequestFormCategory>
     */
    @Override
    public List<RequestFormCategory> findAll() {
        return null;
    }

    
    /** 
     * @param sort
     * @return List<RequestFormCategory>
     */
    @Override
    public List<RequestFormCategory> findAll(Sort sort) {
        return null;
    }

    
    /** 
     * @param pageable
     * @return Page<RequestFormCategory>
     */
    @Override
    public Page<RequestFormCategory> findAll(Pageable pageable) {
        return null;
    }

    
    /** 
     * @param longs
     * @return List<RequestFormCategory>
     */
    @Override
    public List<RequestFormCategory> findAllById(Iterable<Long> longs) {
        return null;
    }

    
    /** 
     * @return long
     */
    @Override
    public long count() {
        return 0;
    }

    
    /** 
     * @param aLong
     */
    @Override
    public void deleteById(Long aLong) {

    }

    
    /** 
     * @param entity
     */
    @Override
    public void delete(RequestFormCategory entity) {

    }

    
    /** 
     * @param longs
     */
    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    
    /** 
     * @param entities
     */
    @Override
    public void deleteAll(Iterable<? extends RequestFormCategory> entities) {

    }

    @Override
    public void deleteAll() {

    }

    
    /** 
     * @param entity
     * @return S
     */
    @Override
    public <S extends RequestFormCategory> S save(S entity) {
        return null;
    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends RequestFormCategory> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    
    /** 
     * @param aLong
     * @return Optional<RequestFormCategory>
     */
    @Override
    public Optional<RequestFormCategory> findById(Long aLong) {
        return Optional.empty();
    }

    
    /** 
     * @param aLong
     * @return boolean
     */
    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    
    /** 
     * @param entity
     * @return S
     */
    @Override
    public <S extends RequestFormCategory> S saveAndFlush(S entity) {
        return null;
    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends RequestFormCategory> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    
    /** 
     * @param entities
     */
    @Override
    public void deleteAllInBatch(Iterable<RequestFormCategory> entities) {

    }

    
    /** 
     * @param longs
     */
    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    
    /** 
     * @param aLong
     * @return RequestFormCategory
     */
    @Override
    public RequestFormCategory getOne(Long aLong) {
        return null;
    }

    
    /** 
     * @param aLong
     * @return RequestFormCategory
     */
    @Override
    public RequestFormCategory getById(Long aLong) {
        return null;
    }

    
    /** 
     * @param aLong
     * @return RequestFormCategory
     */
    @Override
    public RequestFormCategory getReferenceById(Long aLong) {
        return null;
    }

    
    /** 
     * @param example
     * @return Optional<S>
     */
    @Override
    public <S extends RequestFormCategory> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    
    /** 
     * @param example
     * @return List<S>
     */
    @Override
    public <S extends RequestFormCategory> List<S> findAll(Example<S> example) {
        return null;
    }

    
    /** 
     * @param example
     * @param sort
     * @return List<S>
     */
    @Override
    public <S extends RequestFormCategory> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    
    /** 
     * @param example
     * @param pageable
     * @return Page<S>
     */
    @Override
    public <S extends RequestFormCategory> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    
    /** 
     * @param example
     * @return long
     */
    @Override
    public <S extends RequestFormCategory> long count(Example<S> example) {
        return 0;
    }

    
    /** 
     * @param example
     * @return boolean
     */
    @Override
    public <S extends RequestFormCategory> boolean exists(Example<S> example) {
        return false;
    }

    
    /** 
     * @param queryFunction
     * @return R
     */
    @Override
    public <S extends RequestFormCategory, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
