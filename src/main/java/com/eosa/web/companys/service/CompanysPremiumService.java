package com.eosa.web.companys.service;

import com.eosa.web.companys.entity.CompanysPremium;
import com.eosa.web.companys.entity.SelectCompanys;
import com.eosa.web.companys.repository.CompanysPremiumRepository;
import lombok.extern.slf4j.Slf4j;
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
public class CompanysPremiumService implements CompanysPremiumRepository {

    @Autowired private CompanysPremiumRepository companysPremiumRepository;

    
    /** 
     * @param entity
     * @return S
     */
    @Override
    public <S extends CompanysPremium> S save(S entity) {
        entity.setPremiumReqDate(LocalDateTime.now());
        entity.setCompanysPremiumEnabled(0);
        return companysPremiumRepository.save(entity);
    }

    
    /** 
     * 모든 제휴협회를 목록으로 조회하는 서비스
     * @return List<SelectCompanys>
     */
    @Override
    public List<SelectCompanys> selectAllCompanysPremium() {
        return companysPremiumRepository.selectAllCompanysPremium();
    }    

    /**
     * companysName과 companysCeoName 이 일치하는 CompanysPremium을 조회하는 서비스
     * @param companysName
     * @param companysCeoName
     * @return CompanysPremium
     */
    @Override
    public CompanysPremium selectCompanysPremiumByCompanysNameCompanysCeoName(
        String companysName,
        String companysCeoName
    ) {
        return companysPremiumRepository.selectCompanysPremiumByCompanysNameCompanysCeoName(companysName, companysCeoName);
    }
    
    /** 
     * @return List<CompanysPremium>
     */
    @Override
    public List<CompanysPremium> findAll() {
        return null;
    }

    
    /** 
     * @param sort
     * @return List<CompanysPremium>
     */
    @Override
    public List<CompanysPremium> findAll(Sort sort) {
        return null;
    }

    
    /** 
     * @param pageable
     * @return Page<CompanysPremium>
     */
    @Override
    public Page<CompanysPremium> findAll(Pageable pageable) {
        return null;
    }

    
    /** 
     * @param longs
     * @return List<CompanysPremium>
     */
    @Override
    public List<CompanysPremium> findAllById(Iterable<Long> longs) {
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
    public void delete(CompanysPremium entity) {

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
    public void deleteAll(Iterable<? extends CompanysPremium> entities) {

    }

    @Override
    public void deleteAll() {

    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends CompanysPremium> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    
    /** 
     * @param aLong
     * @return Optional<CompanysPremium>
     */
    @Override
    public Optional<CompanysPremium> findById(Long aLong) {
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
    public <S extends CompanysPremium> S saveAndFlush(S entity) {
        return null;
    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends CompanysPremium> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    
    /** 
     * @param entities
     */
    @Override
    public void deleteAllInBatch(Iterable<CompanysPremium> entities) {

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
     * @return CompanysPremium
     */
    @Override
    public CompanysPremium getOne(Long aLong) {
        return null;
    }

    
    /** 
     * @param aLong
     * @return CompanysPremium
     */
    @Override
    public CompanysPremium getById(Long aLong) {
        return null;
    }

    
    /** 
     * @param aLong
     * @return CompanysPremium
     */
    @Override
    public CompanysPremium getReferenceById(Long aLong) {
        return null;
    }

    
    /** 
     * @param example
     * @return Optional<S>
     */
    @Override
    public <S extends CompanysPremium> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    
    /** 
     * @param example
     * @return List<S>
     */
    @Override
    public <S extends CompanysPremium> List<S> findAll(Example<S> example) {
        return null;
    }

    
    /** 
     * @param example
     * @param sort
     * @return List<S>
     */
    @Override
    public <S extends CompanysPremium> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    
    /** 
     * @param example
     * @param pageable
     * @return Page<S>
     */
    @Override
    public <S extends CompanysPremium> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    
    /** 
     * @param example
     * @return long
     */
    @Override
    public <S extends CompanysPremium> long count(Example<S> example) {
        return 0;
    }

    
    /** 
     * @param example
     * @return boolean
     */
    @Override
    public <S extends CompanysPremium> boolean exists(Example<S> example) {
        return false;
    }

    
    /** 
     * @param queryFunction
     * @return R
     */
    @Override
    public <S extends CompanysPremium, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

}
