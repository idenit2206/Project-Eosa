package com.eosa.web.requestform.service.tablebackup;

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

import com.eosa.web.requestform.entity.RequestFormBackup;
import com.eosa.web.requestform.entity.SelectRequestFormList;
import com.eosa.web.requestform.repository.tablebackup.RequestFormBackupRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestFormBackupService implements RequestFormBackupRepository {

    @Autowired private RequestFormBackupRepository requestFormBackupRepository;

    /** 
     * CLIENT의 의뢰 신청 서비스
     * @param entity
     * @return S
     */
    @Override
    public <S extends RequestFormBackup> S save(S entity) {
        entity.setRequestFormStatusChangeDate(LocalDateTime.now());
        return requestFormBackupRepository.save(entity);
    }
    
    /** 
     * @param entity
     * @return int
     */
    public int requestFormRegister(RequestFormBackup entity) {
        LocalDateTime currentTime = LocalDateTime.now();
        
        entity.setRequestFormStatus("REQUEST");
        entity.setRequestFormDate(currentTime);
    
        return requestFormBackupRepository.requestFormRegister(entity);
    }

    
    /** 
     * @param companysIdx
     * @return List<SelectRequestFormList>
     */
    @Override
    public List<SelectRequestFormList> findByCompanysIdxIdx(Long companysIdx) {
        return requestFormBackupRepository.findByCompanysIdxIdx(companysIdx);
    }

    
    /** 
     * @return List<RequestForm>
     */
    @Override
    public List<RequestFormBackup> findAll() {
        return requestFormBackupRepository.findAll();
    }

    
    /** 
     * @return List<SelectRequestFormList>
     */
    @Override
    public List<SelectRequestFormList> selectAllRequestFormList() {
        return requestFormBackupRepository.selectAllRequestFormList();
    }
    
    /** 
     * @param usersIdx
     * @return List<SelectRequestFormList>
     */
    @Override
    public List<SelectRequestFormList> selectAllRequestFormListByUsersIdx(Long usersIdx) {
        return requestFormBackupRepository.selectAllRequestFormListByUsersIdx(usersIdx);
    }
    
    /** 
     * @param usersIdx
     * @return List<SelectRequestFormList>
     */
    @Override
    public List<SelectRequestFormList> selectAllRequestFormListByUsersIdxOrderByRequestFormDateDESC(Long usersIdx) {
        return requestFormBackupRepository.selectAllRequestFormListByUsersIdxOrderByRequestFormDateDESC(usersIdx);
    }
        
    /** 
     * @param requestFormIdx
     * @return RequestForm
     */
    @Override
    public RequestFormBackup selectOneRequestFormByRequsetFormIdx(Long requestFormIdx) {
        return requestFormBackupRepository.selectOneRequestFormByRequsetFormIdx(requestFormIdx);
    }

    /**
     * CLIENT 회원의 의뢰 임무 계약서 작성을 위한 정보 업데이트 서비스(백업 데이터)
     */
    @Override
    public int updateRequestFormContractData(RequestFormBackup requestForm) {
        return requestFormBackupRepository.updateRequestFormContractData(requestForm);
    }

    @Override
    public List<RequestFormBackup> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public List<RequestFormBackup> findAllById(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public <S extends RequestFormBackup> List<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public void flush() {
        // TODO Auto-generated method stub
        
    }


    @Override
    public <S extends RequestFormBackup> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public <S extends RequestFormBackup> List<S> saveAllAndFlush(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public void deleteAllInBatch(Iterable<RequestFormBackup> entities) {
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
    public RequestFormBackup getOne(Long id) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public RequestFormBackup getById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public RequestFormBackup getReferenceById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public <S extends RequestFormBackup> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public <S extends RequestFormBackup> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public Page<RequestFormBackup> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public Optional<RequestFormBackup> findById(Long id) {
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
    public void delete(RequestFormBackup entity) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void deleteAll(Iterable<? extends RequestFormBackup> entities) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        
    }


    @Override
    public <S extends RequestFormBackup> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }


    @Override
    public <S extends RequestFormBackup> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public <S extends RequestFormBackup> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public <S extends RequestFormBackup> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public <S extends RequestFormBackup, R> R findBy(Example<S> example,
            Function<FetchableFluentQuery<S>, R> queryFunction) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public List<RequestFormBackup> selectRequestFormByUsersIdx(Long usersIdx) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public List<RequestFormBackup> selectPushNotificationForDetective(Long companysIdx) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public List<RequestFormBackup> selectRequestFormByCompanysIdx(Long companysIdx) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public int updateReadStateRead(Long requestFormIdx) {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public int updateReadStateReadDetective(Long requestFormIdx, Long companysIdx) {
        // TODO Auto-generated method stub
        return 0;
    }

}
