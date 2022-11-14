package com.eosa.web.companys.service;

import com.eosa.web.companys.entity.CompanysFlagCategory;
import com.eosa.web.companys.repository.CompanysFlagCategoryRepository;
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
public class CompanysFlagCategoryService implements CompanysFlagCategoryRepository {

    @Autowired private CompanysFlagCategoryRepository companysFlagCategoryRepository;

    
    /** 
     * CompanysFlagCategory를 저장하는 서비스
     * @param entity
     * @return S
     */
    @Override
    public <S extends CompanysFlagCategory> S save(S entity) {
        return companysFlagCategoryRepository.save(entity);
    }

    
    /** 
     * @return List<CompanysFlagCategory>
     */
    @Override
    public List<CompanysFlagCategory> findAll() {
        return null;
    }

    
    /** 
     * @param sort
     * @return List<CompanysFlagCategory>
     */
    @Override
    public List<CompanysFlagCategory> findAll(Sort sort) {
        return null;
    }

    
    /** 
     * @param pageable
     * @return Page<CompanysFlagCategory>
     */
    @Override
    public Page<CompanysFlagCategory> findAll(Pageable pageable) {
        return null;
    }

    
    /** 
     * @param longs
     * @return List<CompanysFlagCategory>
     */
    @Override
    public List<CompanysFlagCategory> findAllById(Iterable<Long> longs) {
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
    public void delete(CompanysFlagCategory entity) {

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
    public void deleteAll(Iterable<? extends CompanysFlagCategory> entities) {

    }

    @Override
    public void deleteAll() {

    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends CompanysFlagCategory> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    
    /** 
     * @param aLong
     * @return Optional<CompanysFlagCategory>
     */
    @Override
    public Optional<CompanysFlagCategory> findById(Long aLong) {
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
    public <S extends CompanysFlagCategory> S saveAndFlush(S entity) {
        return null;
    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends CompanysFlagCategory> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    
    /** 
     * @param entities
     */
    @Override
    public void deleteAllInBatch(Iterable<CompanysFlagCategory> entities) {

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
     * @return CompanysFlagCategory
     */
    @Override
    public CompanysFlagCategory getOne(Long aLong) {
        return null;
    }

    
    /** 
     * @param aLong
     * @return CompanysFlagCategory
     */
    @Override
    public CompanysFlagCategory getById(Long aLong) {
        return null;
    }

    
    /** 
     * @param aLong
     * @return CompanysFlagCategory
     */
    @Override
    public CompanysFlagCategory getReferenceById(Long aLong) {
        return null;
    }

    
    /** 
     * @param example
     * @return Optional<S>
     */
    @Override
    public <S extends CompanysFlagCategory> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    
    /** 
     * @param example
     * @return List<S>
     */
    @Override
    public <S extends CompanysFlagCategory> List<S> findAll(Example<S> example) {
        return null;
    }

    
    /** 
     * @param example
     * @param sort
     * @return List<S>
     */
    @Override
    public <S extends CompanysFlagCategory> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    
    /** 
     * @param example
     * @param pageable
     * @return Page<S>
     */
    @Override
    public <S extends CompanysFlagCategory> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    
    /** 
     * @param example
     * @return long
     */
    @Override
    public <S extends CompanysFlagCategory> long count(Example<S> example) {
        return 0;
    }

    
    /** 
     * @param example
     * @return boolean
     */
    @Override
    public <S extends CompanysFlagCategory> boolean exists(Example<S> example) {
        return false;
    }

    
    /** 
     * @param queryFunction
     * @return R
     */
    @Override
    public <S extends CompanysFlagCategory, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
