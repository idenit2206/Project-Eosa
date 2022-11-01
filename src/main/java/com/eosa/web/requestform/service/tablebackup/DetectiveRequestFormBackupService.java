package com.eosa.web.requestform.service.tablebackup;

import com.eosa.web.companys.service.CompanysService;
import com.eosa.web.firebase.pushnoti.service.FirebaseCloudMessage;
import com.eosa.web.requestform.entity.RequestFormBackup;
import com.eosa.web.requestform.entity.SelectRequestFormList;
import com.eosa.web.requestform.repository.tablebackup.DetectiveRequestFormBackupRepository;
import com.eosa.web.users.service.UsersService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class DetectiveRequestFormBackupService implements DetectiveRequestFormBackupRepository {

    @Autowired private DetectiveRequestFormBackupRepository detectiveRequestFormBackupRepository;
    @Autowired private CompanysService companysService;
    @Autowired private UsersService usersService;
    private final FirebaseCloudMessage firebaseCloudMessage; 
    
    /** 
     * requestFormIdx가 일치하는 RequestFormBackup을 출력하는 서비스
     * @param requestFormIdx
     * @return RequestForm
     */
    @Override
    public RequestFormBackup selectRequestFormByRequestFormIdx(Long requestFormIdx) {
        return detectiveRequestFormBackupRepository.selectRequestFormByRequestFormIdx(requestFormIdx);
    }
    
    /** 
     * @param companysIdx
     * @return List<SelectRequestFormList>
     */
    @Override
    public List<SelectRequestFormList> selectAllDetectiveRequestFormListByCompanysIdx(Long companysIdx) {
        return detectiveRequestFormBackupRepository.selectAllDetectiveRequestFormListByCompanysIdx(companysIdx);
    }

    
    /** 
     * @param companysIdx
     * @return List<SelectRequestFormList>
     */
    @Override
    public List<SelectRequestFormList> selectAllDetectiveRequestFormListByCompanysIdxOrderByDESC(Long companysIdx) {
        return detectiveRequestFormBackupRepository.selectAllDetectiveRequestFormListByCompanysIdxOrderByDESC(companysIdx);
    }

    
    /** 
     * @param companysIdx
     * @return List<SelectRequestFormList>
     */
    @Override
    public List<SelectRequestFormList> selectAllDetectiveRequestFormListByCompanysIdxOrderByASC(Long companysIdx) {
        return detectiveRequestFormBackupRepository.selectAllDetectiveRequestFormListByCompanysIdxOrderByASC(companysIdx);
    }
    
    /** 
     * @param requestFormIdx
     * @return RequestFormBackup
     */
    @Override
    public RequestFormBackup selectDetectiveRequestFormInfoByRequestFormIdx(Long requestFormIdx) {
        return detectiveRequestFormBackupRepository.selectDetectiveRequestFormInfoByRequestFormIdx(requestFormIdx);
    }

    
    /** 
     * @param requestFormIdx
     * @param requestFormStatus
     * @param requestFormRejectMessage
     * @return int
     */
    @Override
    public int updateRequestFormStatusByRequestFormIdx(Long requestFormIdx, String requestFormStatus, String requestFormRejectMessage) {
        return detectiveRequestFormBackupRepository.updateRequestFormStatusByRequestFormIdx(requestFormIdx, requestFormStatus, requestFormRejectMessage);
    }
    
    /** 
     * @param RequestFormBackup
     * @return int
     * @throws IOException
     */
    @Override
    public int updateRequestFormByEntity(RequestFormBackup entity) throws IOException {
        // log.info("[entity] entity: {}", entity.toString());
        Long usersIdx = entity.getUsersIdx();
        
        if(entity.getRequestFormStatus().equals("의뢰거절")) {
            log.info("[BACKUP] requestFormIdx: {}, 진행상태: {}", entity.getRequestFormIdx(), entity.getRequestFormStatus());
            entity.setRequestFormCompDate(LocalDateTime.now()); 
        }
        else if(entity.getRequestFormStatus().equals("의뢰대기")) {
            log.info("[BACKUP] requestFormIdx: {}, 진행상태: {}", entity.getRequestFormIdx(), entity.getRequestFormStatus());
        }

        else if(entity.getRequestFormStatus().equals("계약진행")) {
            log.info("[BACKUP] requestFormIdx: {}, 진행상태: {}", entity.getRequestFormIdx(), entity.getRequestFormStatus());
            entity.setRequestFormAcceptDate(LocalDateTime.now());
            entity.setRequestFormStatus("계약진행"); 
        }
        else if(entity.getRequestFormStatus().equals("임무진행")) {
            log.info("[BACKUP] requestFormIdx: {}, 진행상태: {}", entity.getRequestFormIdx(), entity.getRequestFormStatus());
            entity.setRequestFormStatus("임무진행");
            entity.setRequestFormAcceptDate(LocalDateTime.now()); 
        }
        else if(entity.getRequestFormStatus().equals("임무완료")) { 
            log.info("[BACKUP] requestFormIdx: {}, 진행상태: {}", entity.getRequestFormIdx(), entity.getRequestFormStatus());
            // LocalDateTime requestFormAcceptDate = detectiveRequestFormBackupRepository.selectRequestFormByRequestFormIdx(entity.getRequestFormIdx()).getRequestFormAcceptDate();
            // entity.setRequestFormAcceptDate(requestFormAcceptDate);
            entity.setRequestFormStatus("임무완료");
            entity.setRequestFormCompDate(LocalDateTime.now());
        }
        return detectiveRequestFormBackupRepository.updateRequestFormByEntity(entity);
    }
    
    /** 
     * @param requestFormIdx
     * @param requestFormAcceptDate
     * @param requestFormStatus
     * @param requestFormRejectMessage
     * @return int
     */
    @Override
    public int updateRequestFormStatusByRequestFormIdxCaseConsultComplete(Long requestFormIdx, LocalDateTime requestFormAcceptDate, String requestFormStatus, String requestFormRejectMessage) {        
        return detectiveRequestFormBackupRepository.updateRequestFormStatusByRequestFormIdxCaseConsultComplete(requestFormIdx, requestFormAcceptDate, requestFormStatus, requestFormRejectMessage);
    }
    
    /** 
     * @param requestFormIdx
     * @param requestFormCompDate
     * @param requestFormStatus
     * @param requestFormRejectMessage
     * @return int
     */
    @Override
    public int updateRequestFormStatusByRequestFormIdxCaseMissionComplete(Long requestFormIdx, LocalDateTime requestFormCompDate, String requestFormStatus, String requestFormRejectMessage) {
        return detectiveRequestFormBackupRepository.updateRequestFormStatusByRequestFormIdxCaseMissionComplete(requestFormIdx, requestFormCompDate, requestFormStatus, requestFormRejectMessage);
    }

    @Override
    public List<RequestFormBackup> findAll() {
        // TODO Auto-generated method stub
        return null;
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
    public <S extends RequestFormBackup> S save(S entity) {
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

}
