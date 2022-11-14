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

import com.eosa.web.companys.entity.CompanysMember;
import com.eosa.web.companys.repository.CompanysMemberRepository;

@Service
public class CompanysMemberService implements CompanysMemberRepository {

    @Autowired CompanysMemberRepository companysMemberRepository;

    
    /** 
     * @param entity
     * @return S
     */
    @Override
    public <S extends CompanysMember> S save(S entity) {
        return companysMemberRepository.save(entity);
    }

    
    /** 
     * CompanysMember를 저장하는 서비스
     * @param entity
     * @return int
     */
    @Override
    public int insertCompanysMember(CompanysMember entity) {
        return companysMemberRepository.insertCompanysMember(entity);
    }

    
    /** 
     * @return List<CompanysMember>
     */
    @Override
    public List<CompanysMember> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param sort
     * @return List<CompanysMember>
     */
    @Override
    public List<CompanysMember> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param ids
     * @return List<CompanysMember>
     */
    @Override
    public List<CompanysMember> findAllById(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends CompanysMember> List<S> saveAll(Iterable<S> entities) {
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
    public <S extends CompanysMember> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends CompanysMember> List<S> saveAllAndFlush(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param entities
     */
    @Override
    public void deleteAllInBatch(Iterable<CompanysMember> entities) {
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
     * @return CompanysMember
     */
    @Override
    public CompanysMember getOne(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param id
     * @return CompanysMember
     */
    @Override
    public CompanysMember getById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param id
     * @return CompanysMember
     */
    @Override
    public CompanysMember getReferenceById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param example
     * @return List<S>
     */
    @Override
    public <S extends CompanysMember> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param example
     * @param sort
     * @return List<S>
     */
    @Override
    public <S extends CompanysMember> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param pageable
     * @return Page<CompanysMember>
     */
    @Override
    public Page<CompanysMember> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }   

    
    /** 
     * @param id
     * @return Optional<CompanysMember>
     */
    @Override
    public Optional<CompanysMember> findById(Long id) {
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
    public void delete(CompanysMember entity) {
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
    public void deleteAll(Iterable<? extends CompanysMember> entities) {
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
    public <S extends CompanysMember> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    
    /** 
     * @param example
     * @param pageable
     * @return Page<S>
     */
    @Override
    public <S extends CompanysMember> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param example
     * @return long
     */
    @Override
    public <S extends CompanysMember> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    
    /** 
     * @param example
     * @return boolean
     */
    @Override
    public <S extends CompanysMember> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }

    
    /** 
     * @param queryFunction
     * @return R
     */
    @Override
    public <S extends CompanysMember, R> R findBy(Example<S> example,
            Function<FetchableFluentQuery<S>, R> queryFunction) {
        // TODO Auto-generated method stub
        return null;
    }

}
