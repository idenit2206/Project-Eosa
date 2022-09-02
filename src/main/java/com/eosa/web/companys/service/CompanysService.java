package com.eosa.web.companys.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
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

import com.eosa.web.companys.entity.Companys;
import com.eosa.web.companys.entity.SelectAllCompanysList;
import com.eosa.web.companys.entity.SelectCompanyInfoByUsersIdx;
import com.eosa.web.companys.repository.CompanysRepository;

import lombok.extern.slf4j.Slf4j;

@Service
public class CompanysService implements CompanysRepository {

    @Autowired
    private CompanysRepository companysRepository;

    @Override
    public <S extends Companys> S save(S entity) {
        LocalDateTime currentTime = LocalDateTime.now();
        entity.setCompanysRegistDate(currentTime);
        return companysRepository.save(entity);        
    }

    @Override
    public int updateRegistCertiAndProfileImage(Long companysIdx, String file1Name, String file3Name) {
        return companysRepository.updateRegistCertiAndProfileImage(companysIdx, file1Name, file3Name);
    }

    @Override
    public int updateRegistCerti(Long companysIdx, String file1Name) {
        return companysRepository.updateRegistCerti(companysIdx, file1Name);
    }

    @Override
    public int updateLicense(Long companysIdx, String file2URL) {
        return companysRepository.updateLicense(companysIdx, file2URL);
    }

    @Override
    public List<String> selectAllCategory() {
        return companysRepository.selectAllCategory();
    }

    // @Override
    // public int insertCompanys(Companys entity) {
    //     return companysRepository.insertCompanys(entity);
    // }

    @Override
    public List<SelectAllCompanysList> selectAllCompanysList() {
        return companysRepository.selectAllCompanysList();
    }

    @Override
    public Companys selectCompanyInfoByUsersIdx(Long usersIdx) {
        return companysRepository.selectCompanyInfoByUsersIdx(usersIdx);
    }

//    @Override
//    public SelectCompanyInfoByUsersIdx selectCompanyInfoByUsersIdx(Long usersIdx) {
//        return companysRepository.selectCompanyInfoByUsersIdx(usersIdx);
//    }

    @Override
    public List<Companys> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Companys> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Companys> findAllById(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long selectCompanysIdxByUsersIdx(Long usersIdx) {
        return companysRepository.selectCompanysIdxByUsersIdx(usersIdx);
    }

    @Override
    public int updateCompanys(Companys entity) {
        return companysRepository.updateCompanys(entity);
    }

    public int findByCompanysIdx(Long companysIdx) {
        return companysRepository.findByCompanysIdx(companysIdx);
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
