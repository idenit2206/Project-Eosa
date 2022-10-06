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

import com.eosa.web.companys.entity.CompanysCategory;
import com.eosa.web.companys.repository.CompanysCategoryRepository;

@Service
public class CompanysCategoryService implements CompanysCategoryRepository {

    @Autowired private CompanysCategoryRepository companysCategoryRepository;

    
    /** 
     * @param entity
     * @return S
     */
    @Override
    public <S extends CompanysCategory> S save(S entity) {
        return companysCategoryRepository.save(entity);
    }

    
    /** 
     * @param entity
     */
    @Override
    public void insertCompanysCategory(CompanysCategory entity) {
        companysCategoryRepository.insertCompanysCategory(entity);
    }

    
    /** 
     * @param companysIdx
     * @return List<String>
     */
    @Override
    public List<String> selectByCompanysIdx(Long companysIdx) {
        return companysCategoryRepository.selectByCompanysIdx(companysIdx);
    }

    
    /** 
     * @param companysIdx
     * @return int
     */
    @Override
    public int deleteCategoryByCompanysIdx(Long companysIdx) {
        return companysCategoryRepository.deleteCategoryByCompanysIdx(companysIdx);
    }

    
    /** 
     * @return List<CompanysCategory>
     */
    @Override
    public List<CompanysCategory> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param sort
     * @return List<CompanysCategory>
     */
    @Override
    public List<CompanysCategory> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param ids
     * @return List<CompanysCategory>
     */
    @Override
    public List<CompanysCategory> findAllById(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends CompanysCategory> List<S> saveAll(Iterable<S> entities) {
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
    public <S extends CompanysCategory> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends CompanysCategory> List<S> saveAllAndFlush(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param entities
     */
    @Override
    public void deleteAllInBatch(Iterable<CompanysCategory> entities) {
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
     * @return CompanysCategory
     */
    @Override
    public CompanysCategory getOne(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param id
     * @return CompanysCategory
     */
    @Override
    public CompanysCategory getById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param id
     * @return CompanysCategory
     */
    @Override
    public CompanysCategory getReferenceById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param example
     * @return List<S>
     */
    @Override
    public <S extends CompanysCategory> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param example
     * @param sort
     * @return List<S>
     */
    @Override
    public <S extends CompanysCategory> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param pageable
     * @return Page<CompanysCategory>
     */
    @Override
    public Page<CompanysCategory> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }    

    
    /** 
     * @param id
     * @return Optional<CompanysCategory>
     */
    @Override
    public Optional<CompanysCategory> findById(Long id) {
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
    public void delete(CompanysCategory entity) {
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
    public void deleteAll(Iterable<? extends CompanysCategory> entities) {
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
    public <S extends CompanysCategory> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    
    /** 
     * @param example
     * @param pageable
     * @return Page<S>
     */
    @Override
    public <S extends CompanysCategory> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param example
     * @return long
     */
    @Override
    public <S extends CompanysCategory> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    
    /** 
     * @param example
     * @return boolean
     */
    @Override
    public <S extends CompanysCategory> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }

    
    /** 
     * @param queryFunction
     * @return R
     */
    @Override
    public <S extends CompanysCategory, R> R findBy(Example<S> example,
            Function<FetchableFluentQuery<S>, R> queryFunction) {
        // TODO Auto-generated method stub
        return null;
    }

}
