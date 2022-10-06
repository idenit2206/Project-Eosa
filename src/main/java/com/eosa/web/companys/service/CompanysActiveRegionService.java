package com.eosa.web.companys.service;

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

import com.eosa.web.companys.entity.CompanysActiveRegion;
import com.eosa.web.companys.repository.CompanysActiveRegionRepository;

@Service
public class CompanysActiveRegionService implements CompanysActiveRegionRepository {

    @Autowired private CompanysActiveRegionRepository companysActiveRegionRepository;

    
    /** 
     * @param entity
     * @return S
     */
    @Override
    public <S extends CompanysActiveRegion> S save(S entity) {
        return companysActiveRegionRepository.save(entity);
    }

    
    /** 
     * @param entity
     */
    @Override
    public void insertCompanysActiveRegion(CompanysActiveRegion entity) {
        companysActiveRegionRepository.insertCompanysActiveRegion(entity);
    }

    
    /** 
     * @param companysIdx
     * @return List<String>
     */
    @Override
    public List<String> selectByCompanysIdx(Long companysIdx) {
        return companysActiveRegionRepository.selectByCompanysIdx(companysIdx);
    }

    
    /** 
     * @param companysIdx
     * @return int
     */
    @Override
    public int deleteActiveRegionByCompanysIdx(Long companysIdx) {
        return companysActiveRegionRepository.deleteActiveRegionByCompanysIdx(companysIdx);
    }

    
    /** 
     * @return List<CompanysActiveRegion>
     */
    @Override
    public List<CompanysActiveRegion> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param sort
     * @return List<CompanysActiveRegion>
     */
    @Override
    public List<CompanysActiveRegion> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param ids
     * @return List<CompanysActiveRegion>
     */
    @Override
    public List<CompanysActiveRegion> findAllById(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends CompanysActiveRegion> List<S> saveAll(Iterable<S> entities) {
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
    public <S extends CompanysActiveRegion> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends CompanysActiveRegion> List<S> saveAllAndFlush(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param entities
     */
    @Override
    public void deleteAllInBatch(Iterable<CompanysActiveRegion> entities) {
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
     * @return CompanysActiveRegion
     */
    @Override
    public CompanysActiveRegion getOne(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param id
     * @return CompanysActiveRegion
     */
    @Override
    public CompanysActiveRegion getById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param id
     * @return CompanysActiveRegion
     */
    @Override
    public CompanysActiveRegion getReferenceById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param example
     * @return List<S>
     */
    @Override
    public <S extends CompanysActiveRegion> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param example
     * @param sort
     * @return List<S>
     */
    @Override
    public <S extends CompanysActiveRegion> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param pageable
     * @return Page<CompanysActiveRegion>
     */
    @Override
    public Page<CompanysActiveRegion> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }    

    
    /** 
     * @param id
     * @return Optional<CompanysActiveRegion>
     */
    @Override
    public Optional<CompanysActiveRegion> findById(Long id) {
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
    public void delete(CompanysActiveRegion entity) {
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
    public void deleteAll(Iterable<? extends CompanysActiveRegion> entities) {
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
    public <S extends CompanysActiveRegion> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    
    /** 
     * @param example
     * @param pageable
     * @return Page<S>
     */
    @Override
    public <S extends CompanysActiveRegion> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param example
     * @return long
     */
    @Override
    public <S extends CompanysActiveRegion> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    
    /** 
     * @param example
     * @return boolean
     */
    @Override
    public <S extends CompanysActiveRegion> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }

    
    /** 
     * @param queryFunction
     * @return R
     */
    @Override
    public <S extends CompanysActiveRegion, R> R findBy(Example<S> example,
            Function<FetchableFluentQuery<S>, R> queryFunction) {
        // TODO Auto-generated method stub
        return null;
    }

}
