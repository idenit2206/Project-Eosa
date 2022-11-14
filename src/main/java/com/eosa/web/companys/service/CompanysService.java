package com.eosa.web.companys.service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import com.eosa.admin.dto.CompanysDTO;
import com.eosa.admin.safety.Safety;
import com.eosa.web.companys.entity.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.eosa.web.companys.repository.CompanysRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CompanysService implements CompanysRepository {

    @Autowired
    private CompanysRepository companysRepository;
    
    /** 
     * Companys를 저장하는 서비스
     * @param entity
     * @return S
     */
    @Override
    public <S extends Companys> S save(S entity) {
        LocalDateTime currentTime = LocalDateTime.now();
        entity.setCompanysRegistDate(currentTime);
        return companysRepository.save(entity);        
    }

    
    /** 
     * CompanysIdx가 일치하는 Companys의 사업자 등록증 파일과 프로필 이미지 파일을 업데이트 하는 서비스
     * Companys를 최초로 저장할 때 사용
     * @param companysIdx
     * @param file1URL
     * @param file3URL
     * @param file1Name
     * @param file3Name
     * @return int
     */
    @Override
    public int updateRegistCertiAndProfileImage(Long companysIdx, String file1URL, String file3URL, String file1Name, String file3Name) {
        return companysRepository.updateRegistCertiAndProfileImage(companysIdx, file1URL, file3URL, file1Name, file3Name);
    }

    
    /** 
     * CompanysIdx가 일치하는 Companys의 사업자 등록증을 업데이트 하는 서비스
     * @param companysIdx
     * @param file1URL
     * @param file1Name
     * @return int
     */
    @Override
    public int updateRegistCerti(Long companysIdx, String file1URL, String file1Name) {
        return companysRepository.updateRegistCerti(companysIdx, file1URL, file1Name);
    }

    
    /** 
     * CompanysIdx가 일치하는 Companys의 업무관련 자격증을 업데이트 하는 서비스
     * @param companysIdx
     * @param file2URL
     * @param file2Name
     * @return int
     */
    @Override
    public int updateLicense(Long companysIdx, String file2URL, String file2Name) {
        return companysRepository.updateLicense(companysIdx, file2URL, file2Name);
    }

    
    /** 
     * 모든 companysCategoryValue를 중복제거하여 List로 조회하는 서비스
     * @return List<String>
     */
    @Override
    public List<String> selectAllCategory() {
        return companysRepository.selectAllCategory();
    }

    /**
     * 모든 Companys를 List로 조회하는 서비스
     * @return List<SelectCompanys>
    */
    @Override
    public List<SelectCompanys> selectAllCompanys() {
        return companysRepository.selectAllCompanys();
    }

    
    /** 
     * 모든 Companys를 임의의 순서 List로 조회하는 서비스
     * @return List<SelectCompanys>
     */
    @Override
    public List<SelectCompanys> selectAllCompanysRandom() {
        return companysRepository.selectAllCompanysRandom();   
    }

    
    /** 
     * usersIdx와 CompanysIdx가 일치하는 Companys를 List로 조회하는 서비스
     * @param usersIdx
     * @param companysIdx
     * @return List<SelectAllCompanysList>
     */
    @Override
    public List<SelectAllCompanysList> selectAllCompanysListByUsersIdxAndCompanysIdx(Long usersIdx, Long companysIdx) {
        return companysRepository.selectAllCompanysListByUsersIdxAndCompanysIdx(usersIdx, companysIdx);
    }

    
    /** 
     * companysCategoryValue가 일치하는 companysIdx List를 조회하는 서비스
     * @param companysCategoryValue
     * @return List<Long>
     */
    @Override
    public List<Long> selectCompanysIdxByCompanysCategory(String companysCategoryValue) {
        return companysRepository.selectCompanysIdxByCompanysCategory(companysCategoryValue);
    }

    
    /** 
     * companysIdx가 일치하는 Companys를 조회하는 서비스
     * @param companysIdx
     * @return SelectAllCompanysList
     */
    @Override
    public SelectAllCompanysList selectCompanysByCompanysIdx(Long companysIdx) {
        return companysRepository.selectCompanysByCompanysIdx(companysIdx);
    }

    
    /** 
     * companysIdx와 companysRegion1이 일치하는 Companys를 조회하는 서비스
     * @param companysIdx
     * @param companysRegion1
     * @return SelectAllCompanysList
     */
    @Override
    public SelectAllCompanysList selectCompanysByCompanysIdxAndCompanysRegion1(Long companysIdx, String companysRegion1) {
        return companysRepository.selectCompanysByCompanysIdxAndCompanysRegion1(companysIdx, companysRegion1);
    }

    
    /** 
     * companysIdx가 일치하는 Companys를 조회하는 서비스 
     * @param companysIdx
     * @return List<SelectAllCompanysList>
     */
    @Override
    public List<SelectAllCompanysList> selectCompanysByCategory(Long companysIdx) {
        return companysRepository.selectCompanysByCategory(companysIdx);
    }

    
    /** 
     * companysRegion1이 일치하는 Companys의 companysIdx를 List로 조회하는 서비스
     * @param companysRegion1
     * @return List<Long>
     */
    public List<Long> selectCompanysIdxByRegion1(String companysRegion1) {
        return companysRepository.selectCompanysIdxByRegion1(companysRegion1);
    }

    
    /** 
     * companysRegion1이 일치하는 Companys의 정보를 조회하는 서비스
     * @param companysRegion1
     * @return List<SelectAllCompanysList>
     */
    @Override
    public List<SelectAllCompanysList> selectCompanysByCompanysRegion1(String companysRegion1) {
        return companysRepository.selectCompanysByCompanysRegion1(companysRegion1);
    }

    
    /** 
     * companysCeoIdx(Users.usersIdx) 가 일치하는 Companys의 정보를 조회하는 서비스
     * @param usersIdx
     * @return Companys
     */
    @Override
    public Companys selectCompanyInfoByUsersIdx(Long usersIdx) {
        return companysRepository.selectCompanyInfoByUsersIdx(usersIdx);
    }

    
    /** 
     * companysIdx가 일치하는 Companys를 조회하는 서비스
     * @param companysIdx
     * @return SelectAllCompanysForNormal
     */
    @Override
    public SelectAllCompanysForNormal selectOneCompanyInfoByCompanysIdx(Long companysIdx) {
        return companysRepository.selectOneCompanyInfoByCompanysIdx(companysIdx);
    }

    
    /** 
     * companysName과 companysCeoName이 일치하는 companysIdx를 출력하는 서비스
     * @param companysName
     * @param companysCeoName
     * @return Long
     */
    @Override
    public Long selectCompanyIdxByComapnysNameAndCompanysCeoName(String companysName, String companysCeoName) {
        return companysRepository.selectCompanyIdxByComapnysNameAndCompanysCeoName(companysName, companysCeoName);
    }

    
    /** 
     * companysIdx가 일치하는 Companys를 조회하는 서비스
     * @param companysIdx
     * @return SelectCompanys
     */
    @Override
    public SelectCompanys selectOneCompanysByCompanysIdxTest(Long companysIdx) {
        return companysRepository.selectOneCompanysByCompanysIdxTest(companysIdx);
    }

    
    /** 
     * @param companysIdx
     * @param usersIdx
     * @return SelectCompanysUserLikeCompanyEnable
     */
    @Override
    public SelectCompanysUserLikeCompanyEnable selectOneCompanysUserLikeCompanyEnableByCompanysIdxUsersIdx(Long companysIdx, Long usersIdx) {
        return companysRepository.selectOneCompanysUserLikeCompanyEnableByCompanysIdxUsersIdx(companysIdx, usersIdx);
    }

    
    /** 
     * @param usersIdx
     * @return Companys
     */
    @Override
    public Companys selectCompanysPremiumEnabled(Long usersIdx) {
        return companysRepository.selectCompanysPremiumEnabled(usersIdx);
    }

    
    /** 
     * @param companysCategory
     * @param companysRegion1
     * @return List<Long>
     */
    @Override
    public List<Long> selectCompanysByFilter2(String companysCategory, String companysRegion1) {
        return companysRepository.selectCompanysByFilter2(companysCategory, companysRegion1);
    }

    
    /** 
     * @param companysCategory
     * @param companysRegion1
     * @return List<Long>
     */
    @Override
    public List<Long> selectCompanysFlagByFilter(String companysCategory, String companysRegion1) {
        return companysRepository.selectCompanysFlagByFilter(companysCategory, companysRegion1);
    }

    // 안심번호
    /**
     * 안심번호 추출 서비스
     *
     * @return String
     * @throws NoSuchAlgorithmException
     */
    public String safetyNumber() throws NoSuchAlgorithmException {

        Safety safety = new Safety();

        Map<String, String> map = safety.safetyEncode();

        String result = safety.safetyAPI("https://bizapi.callmix.co.kr/biz050/BZV100?secureCode=" + map.get("secureCode") + "&bizId=" + map.get("id") + "&monthDay=" + map.get("monthDay") + "&selGbn=3&seqNo=0&reqCnt=1");

        // 받아온 JSON에서 데이터 추출
        JSONObject json = new JSONObject(result);
        JSONArray jsonArray = json.getJSONArray("vnList");
        JSONObject obj = jsonArray.getJSONObject(0);
        String vn = obj.getString("vn");

        return vn;
    }

    /**
     * 안심번호 등록/삭제 서비스
     *
     * @param companysDTO
     * @return int
     * @throws NoSuchAlgorithmException
     */
    public int safetyMapping(CompanysDTO companysDTO) throws NoSuchAlgorithmException {

        Safety safety = new Safety();

        Map<String, String> map = safety.safetyEncode();

        String result = safety.safetyAPI("https://bizapi.callmix.co.kr/biz050/BZV210?secureCode=" + map.get("secureCode") + "&bizId=" + map.get("id") + "&monthDay=" + map.get("monthDay") + "&tkGbn=" + companysDTO.getTkGbn() + "&rn=" + companysDTO.getCompanysPhone() + "&vn=" + companysDTO.getCompanysDummyPhone());

        JSONObject json = new JSONObject(result);

        int num = json.getString("resCd").equals("SUCCESS") ? 1 : 0;

        return num;
    }


    
    /** 
     * @return List<Companys>
     */
    @Override
    public List<Companys> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param sort
     * @return List<Companys>
     */
    @Override
    public List<Companys> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param ids
     * @return List<Companys>
     */
    @Override
    public List<Companys> findAllById(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param usersIdx
     * @return Long
     */
    @Override
    public Long selectCompanysIdxByUsersIdx(Long usersIdx) {
        return companysRepository.selectCompanysIdxByUsersIdx(usersIdx);
    }

    
    /** 
     * 업체 정보를 수정하는 서비스
     * @param entity
     * @return int
     */
    @Override
    public int updateCompanys(Companys entity) {
        return companysRepository.updateCompanys(entity);
    }

    /** 
     * 반려당한 업체정보를 재등록하는 서비스
     * @param entity
     * @return int
     */
    @Override
    public int updateCompanys02(Companys entity) {
        return companysRepository.updateCompanys02(entity);
    }

    
    /** 
     * @param companysIdx
     * @return int
     */
    public int findByCompanysIdx(Long companysIdx) {
        return companysRepository.findByCompanysIdx(companysIdx);
    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends Companys> List<S> saveAll(Iterable<S> entities) {
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
    public <S extends Companys> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends Companys> List<S> saveAllAndFlush(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param entities
     */
    @Override
    public void deleteAllInBatch(Iterable<Companys> entities) {
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
     * @return Companys
     */
    @Override
    public Companys getOne(Long id) {
        return null;
    }

    
    /** 
     * @param id
     * @return Companys
     */
    @Override
    public Companys getById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param id
     * @return Companys
     */
    @Override
    public Companys getReferenceById(Long id) {
        return null;
    }

    
    /** 
     * @param example
     * @return List<S>
     */
    @Override
    public <S extends Companys> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param example
     * @param sort
     * @return List<S>
     */
    @Override
    public <S extends Companys> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param pageable
     * @return Page<Companys>
     */
    @Override
    public Page<Companys> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }    

    
    /** 
     * @param id
     * @return Optional<Companys>
     */
    @Override
    public Optional<Companys> findById(Long id) {
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
    public void delete(Companys entity) {
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
    public void deleteAll(Iterable<? extends Companys> entities) {
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
    public <S extends Companys> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    
    /** 
     * @param example
     * @param pageable
     * @return Page<S>
     */
    @Override
    public <S extends Companys> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param example
     * @return long
     */
    @Override
    public <S extends Companys> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    
    /** 
     * @param example
     * @return boolean
     */
    @Override
    public <S extends Companys> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }

    
    /** 
     * @param queryFunction
     * @return R
     */
    @Override
    public <S extends Companys, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * usersIdx와 일치하는 Companys 정보를 삭제
     */
    @Override
    public int deleteCompanysByCompanysCeoIdx(Long companysCeoIdx) {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public int deleteCompanysByCompanysCeoIdx02(Long companysCeoIdx) {
        return 0;
    }

}
