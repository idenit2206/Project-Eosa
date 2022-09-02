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

    @Override
    public <S extends CompanysActiveRegion> S save(S entity) {
        return companysActiveRegionRepository.save(entity);
    }

    @Override
    public void insertCompanysActiveRegion(CompanysActiveRegion entity) {
        companysActiveRegionRepository.insertCompanysActiveRegion(entity);
    }

    @Override
    public List<String> selectByCompanysIdx(Long companysIdx) {
        return companysActiveRegionRepository.selectByCompanysIdx(companysIdx);
    }

    @Override
    public int deleteActiveRegionByCompanysIdx(Long companysIdx) {
        return companysActiveRegionRepository.deleteActiveRegionByCompanysIdx(companysIdx);
    }

    @Override
    public List<CompanysActiveRegion> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CompanysActiveRegion> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CompanysActiveRegion> findAllById(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends CompanysActiveRegion> List<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void flush() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <S extends CompanysActiveRegion> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends CompanysActiveRegion> List<S> saveAllAndFlush(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<CompanysActiveRegion> entities) {
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
    public CompanysActiveRegion getOne(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CompanysActiveRegion getById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CompanysActiveRegion getReferenceById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends CompanysActiveRegion> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends CompanysActiveRegion> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<CompanysActiveRegion> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }    

    @Override
    public Optional<CompanysActiveRegion> findById(Long id) {
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
    public void delete(CompanysActiveRegion entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll(Iterable<? extends CompanysActiveRegion> entities) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <S extends CompanysActiveRegion> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public <S extends CompanysActiveRegion> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends CompanysActiveRegion> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <S extends CompanysActiveRegion> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <S extends CompanysActiveRegion, R> R findBy(Example<S> example,
            Function<FetchableFluentQuery<S>, R> queryFunction) {
        // TODO Auto-generated method stub
        return null;
    }

}
