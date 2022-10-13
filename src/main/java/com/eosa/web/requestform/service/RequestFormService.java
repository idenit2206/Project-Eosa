package com.eosa.web.requestform.service;

import java.io.IOException;
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

import com.eosa.web.companys.service.CompanysService;
import com.eosa.web.firebase.pushnoti.service.FirebaseCloudMessage;
import com.eosa.web.requestform.entity.RequestForm;
import com.eosa.web.requestform.entity.SelectRequestFormList;
import com.eosa.web.requestform.repository.RequestFormRepository;
import com.eosa.web.users.service.UsersService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestFormService implements RequestFormRepository {

    @Autowired private RequestFormRepository requestFormRepository;
    @Autowired private UsersService usersService;
    @Autowired private CompanysService companysService;
    private final FirebaseCloudMessage firebaseCloudMessage; 
    
    
    /** 
     * CLIENT의 의뢰 신청 서비스
     * @param entity
     * @return S
     */
    @Override
    public <S extends RequestForm> S save(S entity) {
        // log.info(entity.toString());
        Long companysIdx = entity.getCompanysIdx();
        Long companysCeoIdx = companysService.selectCompanysByCompanysIdx(companysIdx).getCompanysCeoIdx();

        String token = usersService.getTokenByUsersIdx(companysCeoIdx);
        String device = usersService.getDeviceByUsersIdx(companysCeoIdx);

        if(token != null) {
            try {
                firebaseCloudMessage.sendMessageTo(token,  "상담요청이 들어왔습니다.", "/", device);
            } catch (IOException e) {
                // e.printStackTrace();
                log.error("푸시 메시지 전송 실패 - logs: {}", e.toString());
            }
        } 

        entity.setRequestFormStatusChangeDate(LocalDateTime.now());
        return requestFormRepository.save(entity);
    }

    
    /** 
     * @param entity
     * @return int
     */
    public int requestFormRegister(RequestForm entity) {
        LocalDateTime currentTime = LocalDateTime.now();
        
        entity.setRequestFormStatus("REQUEST");
        entity.setRequestFormDate(currentTime);
    
        return requestFormRepository.requestFormRegister(entity);
    }

    
    /** 
     * @param companysIdx
     * @return List<SelectRequestFormList>
     */
    @Override
    public List<SelectRequestFormList> findByCompanysIdxIdx(Long companysIdx) {
        return requestFormRepository.findByCompanysIdxIdx(companysIdx);
    }

    
    /** 
     * @return List<RequestForm>
     */
    @Override
    public List<RequestForm> findAll() {
        return requestFormRepository.findAll();
    }

    
    /** 
     * @return List<SelectRequestFormList>
     */
    @Override
    public List<SelectRequestFormList> selectAllRequestFormList() {
        return requestFormRepository.selectAllRequestFormList();
    }
    
    /** 
     * @param usersIdx
     * @return List<SelectRequestFormList>
     */
    @Override
    public List<SelectRequestFormList> selectAllRequestFormListByUsersIdx(Long usersIdx) {
        return requestFormRepository.selectAllRequestFormListByUsersIdx(usersIdx);
    }
    
    /** 
     * @param usersIdx
     * @return List<SelectRequestFormList>
     */
    @Override
    public List<SelectRequestFormList> selectAllRequestFormListByUsersIdxOrderByRequestFormDateDESC(Long usersIdx) {
        return requestFormRepository.selectAllRequestFormListByUsersIdxOrderByRequestFormDateDESC(usersIdx);
    }
    
    /** 
     * @param usersIdx
     * @return List<SelectRequestFormList>
     */
    @Override
    public List<SelectRequestFormList> selectAllRequestFormListByUsersIdxOrderByRequestFormDateASC(Long usersIdx) {
        return requestFormRepository.selectAllRequestFormListByUsersIdxOrderByRequestFormDateASC(usersIdx);
    }

    
    /** 
     * @param requestFormIdx
     * @return RequestForm
     */
    @Override
    public RequestForm selectOneRequestFormByRequsetFormIdx(Long requestFormIdx) {
        return requestFormRepository.selectOneRequestFormByRequsetFormIdx(requestFormIdx);
    }

    
    /** 
     * @param sort
     * @return List<RequestForm>
     */
    @Override
    public List<RequestForm> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param ids
     * @return List<RequestForm>
     */
    @Override
    public List<RequestForm> findAllById(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends RequestForm> List<S> saveAll(Iterable<S> entities) {
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
    public <S extends RequestForm> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends RequestForm> List<S> saveAllAndFlush(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param entities
     */
    @Override
    public void deleteAllInBatch(Iterable<RequestForm> entities) {
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
     * @return RequestForm
     */
    @Override
    public RequestForm getOne(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param id
     * @return RequestForm
     */
    @Override
    public RequestForm getById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param id
     * @return RequestForm
     */
    @Override
    public RequestForm getReferenceById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param example
     * @return List<S>
     */
    @Override
    public <S extends RequestForm> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param example
     * @param sort
     * @return List<S>
     */
    @Override
    public <S extends RequestForm> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param pageable
     * @return Page<RequestForm>
     */
    @Override
    public Page<RequestForm> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param id
     * @return Optional<RequestForm>
     */
    @Override
    public Optional<RequestForm> findById(Long id) {
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
    public void delete(RequestForm entity) {
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
    public void deleteAll(Iterable<? extends RequestForm> entities) {
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
    public <S extends RequestForm> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    
    /** 
     * @param example
     * @param pageable
     * @return Page<S>
     */
    @Override
    public <S extends RequestForm> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param example
     * @return long
     */
    @Override
    public <S extends RequestForm> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    
    /** 
     * @param example
     * @return boolean
     */
    @Override
    public <S extends RequestForm> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }

    
    /** 
     * @param queryFunction
     * @return R
     */
    @Override
    public <S extends RequestForm, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param usersIdx
     * @return List<RequestForm>
     */
    @Override
    public List<RequestForm> selectRequestFormByUsersIdx(Long usersIdx) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param companysIdx
     * @return List<RequestForm>
     */
    @Override
    public List<RequestForm> selectRequestFormByCompanysIdx(Long companysIdx) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param requestFormIdx
     * @return int
     */
    @Override
    public int updateReadStateRead(Long requestFormIdx) {
        // TODO Auto-generated method stub
        return 0;
    }

    
    /** 
     * @param requestFormIdx
     * @param companysIdx
     * @return int
     */
    @Override
    public int updateReadStateReadDetective(Long requestFormIdx, Long companysIdx) {
        // TODO Auto-generated method stub
        return 0;
    }

    
    /** 
     * @param companysIdx
     * @return List<RequestForm>
     */
    @Override
    public List<RequestForm> selectPushNotificationForDetective(Long companysIdx) {
        // TODO Auto-generated method stub
        return null;
    }    

}
