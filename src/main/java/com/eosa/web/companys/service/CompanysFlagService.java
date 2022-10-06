package com.eosa.web.companys.service;

import com.eosa.web.companys.entity.CompanysFlag;
import com.eosa.web.companys.entity.SelectCompanys;
import com.eosa.web.companys.repository.CompanysFlagRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
@Service
public class CompanysFlagService implements CompanysFlagRepository {

    @Autowired private CompanysFlagRepository companysFlagRepository;

    
    /** 
     * @param entity
     * @return S
     */
    @Override
    public <S extends CompanysFlag> S save(S entity) {
        entity.setFlagReqDate(LocalDateTime.now());
        entity.setCompanysFlagEnabled(0);
        log.debug("[save] entity: {}", entity.toString());
        return companysFlagRepository.save(entity);
    }

    
    /** 
     * @return List<SelectCompanys>
     */
    @Override
    public List<SelectCompanys> selectAllCompanysFlag() {
        return companysFlagRepository.selectAllCompanysFlag();
    }

    
    /** 
     * @return List<CompanysFlag>
     */
    @Override
    public List<CompanysFlag> findAll() {
        return null;
    }

    
    /** 
     * @param sort
     * @return List<CompanysFlag>
     */
    @Override
    public List<CompanysFlag> findAll(Sort sort) {
        return null;
    }

    
    /** 
     * @param pageable
     * @return Page<CompanysFlag>
     */
    @Override
    public Page<CompanysFlag> findAll(Pageable pageable) {
        return null;
    }

    
    /** 
     * @param longs
     * @return List<CompanysFlag>
     */
    @Override
    public List<CompanysFlag> findAllById(Iterable<Long> longs) {
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
    public void delete(CompanysFlag entity) {

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
    public void deleteAll(Iterable<? extends CompanysFlag> entities) {

    }

    @Override
    public void deleteAll() {

    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends CompanysFlag> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    
    /** 
     * @param aLong
     * @return Optional<CompanysFlag>
     */
    @Override
    public Optional<CompanysFlag> findById(Long aLong) {
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
    public <S extends CompanysFlag> S saveAndFlush(S entity) {
        return null;
    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends CompanysFlag> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    
    /** 
     * @param entities
     */
    @Override
    public void deleteAllInBatch(Iterable<CompanysFlag> entities) {

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
     * @return CompanysFlag
     */
    @Override
    public CompanysFlag getOne(Long aLong) {
        return null;
    }

    
    /** 
     * @param aLong
     * @return CompanysFlag
     */
    @Override
    public CompanysFlag getById(Long aLong) {
        return null;
    }

    
    /** 
     * @param aLong
     * @return CompanysFlag
     */
    @Override
    public CompanysFlag getReferenceById(Long aLong) {
        return null;
    }

    
    /** 
     * @param example
     * @return Optional<S>
     */
    @Override
    public <S extends CompanysFlag> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    
    /** 
     * @param example
     * @return List<S>
     */
    @Override
    public <S extends CompanysFlag> List<S> findAll(Example<S> example) {
        return null;
    }

    
    /** 
     * @param example
     * @param sort
     * @return List<S>
     */
    @Override
    public <S extends CompanysFlag> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    
    /** 
     * @param example
     * @param pageable
     * @return Page<S>
     */
    @Override
    public <S extends CompanysFlag> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    
    /** 
     * @param example
     * @return long
     */
    @Override
    public <S extends CompanysFlag> long count(Example<S> example) {
        return 0;
    }

    
    /** 
     * @param example
     * @return boolean
     */
    @Override
    public <S extends CompanysFlag> boolean exists(Example<S> example) {
        return false;
    }

    
    /** 
     * @param queryFunction
     * @return R
     */
    @Override
    public <S extends CompanysFlag, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
