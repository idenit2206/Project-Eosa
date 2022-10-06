package com.eosa.web.reportform.service;

import com.eosa.web.reportform.entity.ReportForm;
import com.eosa.web.reportform.repository.ReportFormRepository;
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
public class ReportFormService implements ReportFormRepository {

    @Autowired private ReportFormRepository reportFormRepository;

    
    /** 
     * @param entity
     * @return S
     */
    @Override
    public <S extends ReportForm> S save(S entity) {
        entity.setReportCheckState(0);
        entity.setReportDate(LocalDateTime.now());
        return reportFormRepository.save(entity);
    }

    
    /** 
     * @param entity
     * @return int
     */
    @Override
    public int updateReportFormStatus(ReportForm entity) {
        entity.setReportCheckState(1);
        entity.setReportCheckDate(LocalDateTime.now());
        return reportFormRepository.updateReportFormStatus(entity);
    }

    
    /** 
     * @return List<ReportForm>
     */
    @Override
    public List<ReportForm> findAll() {
        return reportFormRepository.findAll();
    }

    
    /** 
     * @param sort
     * @return List<ReportForm>
     */
    @Override
    public List<ReportForm> findAll(Sort sort) {
        return null;
    }

    
    /** 
     * @param pageable
     * @return Page<ReportForm>
     */
    @Override
    public Page<ReportForm> findAll(Pageable pageable) {
        return null;
    }

    
    /** 
     * @param longs
     * @return List<ReportForm>
     */
    @Override
    public List<ReportForm> findAllById(Iterable<Long> longs) {
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
    public void delete(ReportForm entity) {

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
    public void deleteAll(Iterable<? extends ReportForm> entities) {

    }

    @Override
    public void deleteAll() {

    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends ReportForm> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    
    /** 
     * @param aLong
     * @return Optional<ReportForm>
     */
    @Override
    public Optional<ReportForm> findById(Long aLong) {
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
    public <S extends ReportForm> S saveAndFlush(S entity) {
        return null;
    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends ReportForm> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    
    /** 
     * @param entities
     */
    @Override
    public void deleteAllInBatch(Iterable<ReportForm> entities) {

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
     * @return ReportForm
     */
    @Override
    public ReportForm getOne(Long aLong) {
        return null;
    }

    
    /** 
     * @param aLong
     * @return ReportForm
     */
    @Override
    public ReportForm getById(Long aLong) {
        return null;
    }

    
    /** 
     * @param aLong
     * @return ReportForm
     */
    @Override
    public ReportForm getReferenceById(Long aLong) {
        return null;
    }

    
    /** 
     * @param example
     * @return Optional<S>
     */
    @Override
    public <S extends ReportForm> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    
    /** 
     * @param example
     * @return List<S>
     */
    @Override
    public <S extends ReportForm> List<S> findAll(Example<S> example) {
        return null;
    }

    
    /** 
     * @param example
     * @param sort
     * @return List<S>
     */
    @Override
    public <S extends ReportForm> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    
    /** 
     * @param example
     * @param pageable
     * @return Page<S>
     */
    @Override
    public <S extends ReportForm> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    
    /** 
     * @param example
     * @return long
     */
    @Override
    public <S extends ReportForm> long count(Example<S> example) {
        return 0;
    }

    
    /** 
     * @param example
     * @return boolean
     */
    @Override
    public <S extends ReportForm> boolean exists(Example<S> example) {
        return false;
    }

    
    /** 
     * @param queryFunction
     * @return R
     */
    @Override
    public <S extends ReportForm, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
