package com.eosa.web.price.service;

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

import com.eosa.web.price.entity.Price;
import com.eosa.web.price.repository.PriceRepository;

import groovy.util.logging.Slf4j;

@Slf4j
@Service
public class PriceServiceService implements PriceRepository{

    @Autowired private PriceRepository priceRepository;

    
    /** 
     * @return Price
     */
    @Override
    public Price findPrice() {
        return priceRepository.findPrice();
    }

    
    /** 
     * @return List<Price>
     */
    @Override
    public List<Price> findAll() {
        return null;
    }

    
    /** 
     * @param sort
     * @return List<Price>
     */
    @Override
    public List<Price> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param ids
     * @return List<Price>
     */
    @Override
    public List<Price> findAllById(Iterable<Integer> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends Price> List<S> saveAll(Iterable<S> entities) {
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
    public <S extends Price> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends Price> List<S> saveAllAndFlush(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param entities
     */
    @Override
    public void deleteAllInBatch(Iterable<Price> entities) {
        // TODO Auto-generated method stub
        
    }

    
    /** 
     * @param ids
     */
    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> ids) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAllInBatch() {
        // TODO Auto-generated method stub
        
    }

    
    /** 
     * @param id
     * @return Price
     */
    @Override
    public Price getOne(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param id
     * @return Price
     */
    @Override
    public Price getById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param id
     * @return Price
     */
    @Override
    public Price getReferenceById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param example
     * @return List<S>
     */
    @Override
    public <S extends Price> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param example
     * @param sort
     * @return List<S>
     */
    @Override
    public <S extends Price> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param pageable
     * @return Page<Price>
     */
    @Override
    public Page<Price> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param entity
     * @return S
     */
    @Override
    public <S extends Price> S save(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param id
     * @return Optional<Price>
     */
    @Override
    public Optional<Price> findById(Integer id) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    
    /** 
     * @param id
     * @return boolean
     */
    @Override
    public boolean existsById(Integer id) {
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
    public void deleteById(Integer id) {
        // TODO Auto-generated method stub
        
    }

    
    /** 
     * @param entity
     */
    @Override
    public void delete(Price entity) {
        // TODO Auto-generated method stub
        
    }

    
    /** 
     * @param ids
     */
    @Override
    public void deleteAllById(Iterable<? extends Integer> ids) {
        // TODO Auto-generated method stub
        
    }

    
    /** 
     * @param entities
     */
    @Override
    public void deleteAll(Iterable<? extends Price> entities) {
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
    public <S extends Price> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    
    /** 
     * @param example
     * @param pageable
     * @return Page<S>
     */
    @Override
    public <S extends Price> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param example
     * @return long
     */
    @Override
    public <S extends Price> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    
    /** 
     * @param example
     * @return boolean
     */
    @Override
    public <S extends Price> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }

    
    /** 
     * @param queryFunction
     * @return R
     */
    @Override
    public <S extends Price, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
