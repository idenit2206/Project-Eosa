package com.eosa.web.requestform.service;

import com.eosa.web.companys.entity.Companys;
import com.eosa.web.companys.entity.SelectAllCompanysList;
import com.eosa.web.companys.service.CompanysService;
import com.eosa.web.firebase.pushnoti.service.FirebaseCloudMessage;
import com.eosa.web.requestform.entity.RequestForm;
import com.eosa.web.requestform.entity.SelectRequestFormList;
import com.eosa.web.requestform.repository.DetectiveRequestFormRepository;
import com.eosa.web.requestform.repository.RequestFormRepository;
import com.eosa.web.users.service.UsersService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class DetectiveRequestFormService implements DetectiveRequestFormRepository {

    @Autowired private DetectiveRequestFormRepository detectiveRequestFormRepository;
    @Autowired private CompanysService companysService;
    @Autowired private UsersService usersService;
    private final FirebaseCloudMessage firebaseCloudMessage; 
    
    /** 
     * @param requestFormIdx
     * @return RequestForm
     */
    @Override
    public RequestForm selectRequestFormByRequestFormIdx(Long requestFormIdx) {
        return detectiveRequestFormRepository.selectRequestFormByRequestFormIdx(requestFormIdx);
    }
    
    /** 
     * @param companysIdx
     * @return List<SelectRequestFormList>
     */
    @Override
    public List<SelectRequestFormList> selectAllDetectiveRequestFormListByCompanysIdx(Long companysIdx) {
        return detectiveRequestFormRepository.selectAllDetectiveRequestFormListByCompanysIdx(companysIdx);
    }

    
    /** 
     * @param companysIdx
     * @return List<SelectRequestFormList>
     */
    @Override
    public List<SelectRequestFormList> selectAllDetectiveRequestFormListByCompanysIdxOrderByDESC(Long companysIdx) {
        return detectiveRequestFormRepository.selectAllDetectiveRequestFormListByCompanysIdxOrderByDESC(companysIdx);
    }

    
    /** 
     * @param companysIdx
     * @return List<SelectRequestFormList>
     */
    @Override
    public List<SelectRequestFormList> selectAllDetectiveRequestFormListByCompanysIdxOrderByASC(Long companysIdx) {
        return detectiveRequestFormRepository.selectAllDetectiveRequestFormListByCompanysIdxOrderByASC(companysIdx);
    }
    
    /** 
     * @param requestFormIdx
     * @return RequestForm
     */
    @Override
    public RequestForm selectDetectiveRequestFormInfoByRequestFormIdx(Long requestFormIdx) {
        return detectiveRequestFormRepository.selectDetectiveRequestFormInfoByRequestFormIdx(requestFormIdx);
    }

    
    /** 
     * @param requestFormIdx
     * @param requestFormStatus
     * @param requestFormRejectMessage
     * @return int
     */
    @Override
    public int updateRequestFormStatusByRequestFormIdx(Long requestFormIdx, String requestFormStatus, String requestFormRejectMessage) {
        return detectiveRequestFormRepository.updateRequestFormStatusByRequestFormIdx(requestFormIdx, requestFormStatus, requestFormRejectMessage);
    }
    
    /** 
     * @param entity
     * @return int
     * @throws IOException
     */
    @Override
    public int updateRequestFormByEntity(RequestForm entity) throws IOException {
        // log.info("[entity] entity: {}", entity.toString());
        Long usersIdx = entity.getUsersIdx();
        SelectAllCompanysList c = companysService.selectCompanysByCompanysIdx(entity.getCompanysIdx());
        String companysName = c.getCompanysName();       
        String clienttoken = usersService.getTokenByUsersIdx(usersIdx);
        String clientdevice = usersService.getDeviceByUsersIdx(usersIdx);

        Long companysCeoIdx = c.getCompanysCeoIdx();
        String detectivetoken = usersService.getTokenByUsersIdx(companysCeoIdx);
        String detectivedevice = usersService.getDeviceByUsersIdx(companysCeoIdx);
        
        if(entity.getRequestFormStatus().equals("상담취소")) {
            log.info("[updateRequestFormByEntity] requestForm 번호 {} 의 상담이 취소되었습니다.", entity.getRequestFormIdx());
            if(clienttoken != null) {
                firebaseCloudMessage.sendMessageTo(clienttoken, companysName + "에 신청한 상담이 취소되었습니다.", "/", clientdevice);
            }
            entity.setRequestFormStatus("상담취소");
            entity.setRequestFormCompDate(LocalDateTime.now());
        }
        else if(entity.getRequestFormStatus().equals("의뢰거절")) { 
            log.info("[updateRequestFormByEntity] requestForm 번호 {} 의 의뢰가 거절되었습니다.", entity.getRequestFormIdx());
            if(clienttoken != null) {
                firebaseCloudMessage.sendMessageTo(clienttoken, companysName + "에 신청한 의뢰가 거절되었습니다.", "/", clientdevice);
            }
            entity.setRequestFormCompDate(LocalDateTime.now());
        }
        else if(entity.getRequestFormStatus().equals("의뢰대기")) {
            log.info("[updateRequestFormByEntity] requestForm 번호 {} 에 대해 의뢰가 신청이 발생했습니다.", entity.getRequestFormIdx());
            if(detectivetoken != null) {
                firebaseCloudMessage.sendMessageTo(detectivetoken, "의뢰가 들어왔습니다.", "/", detectivedevice);
            }
        }

        else if(entity.getRequestFormStatus().equals("계약진행")) {
            log.info("[updateRequestFormByEntity] requestForm 번호 {} 에 대해 의뢰 계약서 작성을 진행합니다.", entity.getRequestFormIdx());

            if(clienttoken != null) {
                firebaseCloudMessage.sendMessageTo(clienttoken, companysName + " 과 계약을 위해 계약서를 작성해야합니다.", "/", clientdevice);
            }

            if(detectivetoken != null) {
                firebaseCloudMessage.sendMessageTo(detectivetoken, "의뢰인과 계약을 위해 계약서를 작성해야합니다.", "/", detectivedevice);
            }

            entity.setRequestFormAcceptDate(LocalDateTime.now());
            entity.setRequestFormStatus("계약진행");
        }
        else if(entity.getRequestFormStatus().equals("임무진행")) { 
            log.info("[updateRequestFormByEntity] requestForm 번호 {} 의 임무를 시작합니다.", entity.getRequestFormIdx());
            if(clienttoken != null) {
                firebaseCloudMessage.sendMessageTo(clienttoken, companysName +  " 에서 임무를 진행합니다.", "/", clientdevice);
            }
            entity.setRequestFormAcceptDate(LocalDateTime.now()); 
        }
        else if(entity.getRequestFormStatus().equals("임무완료")) { 
            log.info("[updateRequestFormByEntity] requestForm 번호 {} 의 임무가 완료되었습니다.", entity.getRequestFormIdx());
            if(clienttoken != null) {
                firebaseCloudMessage.sendMessageTo(clienttoken, companysName + " 에서 임무를 완료했습니다.", "/", clientdevice);
            }
            LocalDateTime requestFormAcceptDate = detectiveRequestFormRepository.selectRequestFormByRequestFormIdx(entity.getRequestFormIdx()).getRequestFormAcceptDate();
            entity.setRequestFormAcceptDate(requestFormAcceptDate);
            entity.setRequestFormCompDate(LocalDateTime.now());
        }
        return detectiveRequestFormRepository.updateRequestFormByEntity(entity);
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
        RequestForm rf = detectiveRequestFormRepository.selectRequestFormByRequestFormIdx(requestFormIdx);
        SelectAllCompanysList c = companysService.selectCompanysByCompanysIdx(rf.getCompanysIdx());
        String companysName = c.getCompanysName();       
        String clienttoken = usersService.getTokenByUsersIdx(rf.getUsersIdx());
        String clientdevice = usersService.getDeviceByUsersIdx(rf.getUsersIdx());
        
        if(clienttoken != null) {
            try {
                firebaseCloudMessage.sendMessageTo(clienttoken, companysName + " 과 상담을 진행합니다.", "/", clientdevice);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        return detectiveRequestFormRepository.updateRequestFormStatusByRequestFormIdxCaseConsultComplete(requestFormIdx, requestFormAcceptDate, requestFormStatus, requestFormRejectMessage);
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
        return detectiveRequestFormRepository.updateRequestFormStatusByRequestFormIdxCaseMissionComplete(requestFormIdx, requestFormCompDate, requestFormStatus, requestFormRejectMessage);
    }
    
    /** 
     * @return List<RequestForm>
     */
    @Override
    public List<RequestForm> findAll() {
        return null;
    }
    
    /** 
     * @param sort
     * @return List<RequestForm>
     */
    @Override
    public List<RequestForm> findAll(Sort sort) {
        return null;
    }
    
    /** 
     * @param pageable
     * @return Page<RequestForm>
     */
    @Override
    public Page<RequestForm> findAll(Pageable pageable) {
        return null;
    }
    
    /** 
     * @param longs
     * @return List<RequestForm>
     */
    @Override
    public List<RequestForm> findAllById(Iterable<Long> longs) {
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
    public void delete(RequestForm entity) {

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
    public void deleteAll(Iterable<? extends RequestForm> entities) {

    }

    @Override
    public void deleteAll() {

    }
    
    /** 
     * @param entity
     * @return S
     */
    @Override
    public <S extends RequestForm> S save(S entity) {
        return null;
    }
    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends RequestForm> List<S> saveAll(Iterable<S> entities) {
        return null;
    }
    
    /** 
     * @param aLong
     * @return Optional<RequestForm>
     */
    @Override
    public Optional<RequestForm> findById(Long aLong) {
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
    public <S extends RequestForm> S saveAndFlush(S entity) {
        return null;
    }
    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends RequestForm> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }
    
    /** 
     * @param entities
     */
    @Override
    public void deleteAllInBatch(Iterable<RequestForm> entities) {

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
     * @return RequestForm
     */
    @Override
    public RequestForm getOne(Long aLong) {
        return null;
    }

    
    /** 
     * @param aLong
     * @return RequestForm
     */
    @Override
    public RequestForm getById(Long aLong) {
        return null;
    }

    
    /** 
     * @param aLong
     * @return RequestForm
     */
    @Override
    public RequestForm getReferenceById(Long aLong) {
        return null;
    }

    
    /** 
     * @param example
     * @return Optional<S>
     */
    @Override
    public <S extends RequestForm> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    
    /** 
     * @param example
     * @return List<S>
     */
    @Override
    public <S extends RequestForm> List<S> findAll(Example<S> example) {
        return null;
    }

    
    /** 
     * @param example
     * @param sort
     * @return List<S>
     */
    @Override
    public <S extends RequestForm> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    
    /** 
     * @param example
     * @param pageable
     * @return Page<S>
     */
    @Override
    public <S extends RequestForm> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    
    /** 
     * @param example
     * @return long
     */
    @Override
    public <S extends RequestForm> long count(Example<S> example) {
        return 0;
    }
    
    /** 
     * @param example
     * @return boolean
     */
    @Override
    public <S extends RequestForm> boolean exists(Example<S> example) {
        return false;
    }

    
    /** 
     * @param queryFunction
     * @return R
     */
    @Override
    public <S extends RequestForm, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

}
