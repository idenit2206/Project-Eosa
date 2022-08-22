package com.eosa.admin.companysmanage.service;

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

import com.eosa.admin.companysmanage.entity.GetCompanysList;
import com.eosa.admin.companysmanage.repository.CompanysManagerRepository;
import com.eosa.web.companys.entity.Companys;
import com.eosa.web.companys.entity.CompanysCategory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CompanysManagerService implements CompanysManagerRepository {

    @Autowired private CompanysManagerRepository companysManageRepository;

    @Override
    public <S extends Companys> S save(S entity) {
        return companysManageRepository.save(entity);
    }

    @Override
    public Long findCompanysIdxByCeoIdx(Long companysCeoIdx) {
        return companysManageRepository.findCompanysIdxByCeoIdx(companysCeoIdx);
    }

    @Override
    public void insertCompanysCategory(Long companysIdx, String string) {
        companysManageRepository.insertCompanysCategory(companysIdx, string);
    }

    @Override
    public void insertCompanysActiveRegion(Long companysIdx, String string) {
        companysManageRepository.insertCompanysActiveRegion(companysIdx, string);
    }

    @Override
    public List<GetCompanysList> viewFindAll() {
        return companysManageRepository.viewFindAll();
    }

    @Override
    public List<Companys> findAll() {
        return companysManageRepository.findAll();
    }

    @Override
    public List<Companys> findAll(Sort sort) {
        return companysManageRepository.findAll(sort);
    }

    public List<Companys> findAllCompany(int currentStartPost, int postCount) {
        return companysManageRepository.findAllCompany(currentStartPost, postCount);
    }

    @Override
    public Companys findByCompanysIdx(Long companysIdx) {
        return companysManageRepository.findByCompanysIdx(companysIdx);
    }

    @Override
    public List<Companys> findAllById(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Companys> List<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void flush() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <S extends Companys> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Companys> List<S> saveAllAndFlush(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Companys> entities) {
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
    public Companys getOne(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Companys getById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Companys getReferenceById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Companys> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Companys> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<Companys> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }    

    @Override
    public Optional<Companys> findById(Long id) {
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
    public void delete(Companys entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll(Iterable<? extends Companys> entities) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <S extends Companys> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public <S extends Companys> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Companys> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <S extends Companys> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <S extends Companys, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        // TODO Auto-generated method stub
        return null;
    }       
    
}