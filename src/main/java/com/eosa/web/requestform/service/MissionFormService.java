// package com.eosa.web.requestform.service;

// import com.eosa.web.requestform.entity.MissionForm;
// import com.eosa.web.requestform.entity.SelectMissionFormList;
// import com.eosa.web.requestform.entity.SelectRequestFormList;
// import com.eosa.web.requestform.repository.MissionFormRepository;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Example;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.domain.Sort;
// import org.springframework.data.repository.query.FluentQuery;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Optional;
// import java.util.function.Function;

// @Slf4j
// @Service
// public class MissionFormService implements MissionFormRepository {

//     @Autowired private MissionFormRepository missionFormRepository;

//     @Override
//     public <S extends MissionForm> S save(S entity) {
//         return missionFormRepository.save(entity);
//     }

//     @Override
//     public List<SelectMissionFormList> selectAllDetectiveMissionFormListByCompanysIdxOrderByDESC(Long companysIdx) {
//         return missionFormRepository.selectAllDetectiveMissionFormListByCompanysIdxOrderByDESC(companysIdx);
//     };

//     @Override
//     public MissionForm selectDetectiveMissionFormInfoByMissionFormIdx(Long missionFormIdx) {
//         return missionFormRepository.selectDetectiveMissionFormInfoByMissionFormIdx(missionFormIdx);
//     }

//     @Override
//     public List<MissionForm> findAll() {
//         return null;
//     }

//     @Override
//     public List<MissionForm> findAll(Sort sort) {
//         return null;
//     }

//     @Override
//     public Page<MissionForm> findAll(Pageable pageable) {
//         return null;
//     }

//     @Override
//     public List<MissionForm> findAllById(Iterable<Long> longs) {
//         return null;
//     }

//     @Override
//     public long count() {
//         return 0;
//     }

//     @Override
//     public void deleteById(Long aLong) {

//     }

//     @Override
//     public void delete(MissionForm entity) {

//     }

//     @Override
//     public void deleteAllById(Iterable<? extends Long> longs) {

//     }

//     @Override
//     public void deleteAll(Iterable<? extends MissionForm> entities) {

//     }

//     @Override
//     public void deleteAll() {

//     }

//     @Override
//     public <S extends MissionForm> List<S> saveAll(Iterable<S> entities) {
//         return null;
//     }

//     @Override
//     public Optional<MissionForm> findById(Long aLong) {
//         return Optional.empty();
//     }

//     @Override
//     public boolean existsById(Long aLong) {
//         return false;
//     }

//     @Override
//     public void flush() {

//     }

//     @Override
//     public <S extends MissionForm> S saveAndFlush(S entity) {
//         return null;
//     }

//     @Override
//     public <S extends MissionForm> List<S> saveAllAndFlush(Iterable<S> entities) {
//         return null;
//     }

//     @Override
//     public void deleteAllInBatch(Iterable<MissionForm> entities) {

//     }

//     @Override
//     public void deleteAllByIdInBatch(Iterable<Long> longs) {

//     }

//     @Override
//     public void deleteAllInBatch() {

//     }

//     @Override
//     public MissionForm getOne(Long aLong) {
//         return null;
//     }

//     @Override
//     public MissionForm getById(Long aLong) {
//         return null;
//     }

//     @Override
//     public MissionForm getReferenceById(Long aLong) {
//         return null;
//     }

//     @Override
//     public <S extends MissionForm> Optional<S> findOne(Example<S> example) {
//         return Optional.empty();
//     }

//     @Override
//     public <S extends MissionForm> List<S> findAll(Example<S> example) {
//         return null;
//     }

//     @Override
//     public <S extends MissionForm> List<S> findAll(Example<S> example, Sort sort) {
//         return null;
//     }

//     @Override
//     public <S extends MissionForm> Page<S> findAll(Example<S> example, Pageable pageable) {
//         return null;
//     }

//     @Override
//     public <S extends MissionForm> long count(Example<S> example) {
//         return 0;
//     }

//     @Override
//     public <S extends MissionForm> boolean exists(Example<S> example) {
//         return false;
//     }

//     @Override
//     public <S extends MissionForm, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
//         return null;
//     }
// }
