package com.eosa.web.companys.service;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Iterator;
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

    @Override
    public <S extends Companys> S save(S entity) {
        LocalDateTime currentTime = LocalDateTime.now();
        entity.setCompanysRegistDate(currentTime);
        return companysRepository.save(entity);        
    }

    @Override
    public int updateRegistCertiAndProfileImage(Long companysIdx, String file1URL, String file3URL, String file1Name, String file3Name) {
        return companysRepository.updateRegistCertiAndProfileImage(companysIdx, file1URL, file3URL, file1Name, file3Name);
    }

    @Override
    public int updateRegistCerti(Long companysIdx, String file1URL, String file1Name) {
        return companysRepository.updateRegistCerti(companysIdx, file1URL, file1Name);
    }

    @Override
    public int updateLicense(Long companysIdx, String file2URL, String file2Name) {
        return companysRepository.updateLicense(companysIdx, file2URL, file2Name);
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
    public List<SelectCompanys> selectAllCompanys() {
        return companysRepository.selectAllCompanys();
    }

    @Override
    public List<SelectAllCompanysList> selectAllCompanysListByUsersIdxAndCompanysIdx(Long usersIdx, Long companysIdx) {
        return companysRepository.selectAllCompanysListByUsersIdxAndCompanysIdx(usersIdx, companysIdx);
    }

    @Override
    public List<Long> selectCompanysIdxByCompanysCategory(String companysCategoryValue) {
        return companysRepository.selectCompanysIdxByCompanysCategory(companysCategoryValue);
    }

    @Override
    public SelectAllCompanysList selectCompanysByCompanysIdx(Long companysIdx) {
        return companysRepository.selectCompanysByCompanysIdx(companysIdx);
    }

    @Override
    public SelectAllCompanysList selectCompanysByCompanysIdxAndCompanysRegion1(Long companysIdx, String companysRegion1) {
        return companysRepository.selectCompanysByCompanysIdxAndCompanysRegion1(companysIdx, companysRegion1);
    }

    @Override
    public List<SelectAllCompanysList> selectCompanysByCategory(Long companysIdx) {
        return companysRepository.selectCompanysByCategory(companysIdx);
    }

    public List<Long> selectCompanysIdxByRegion1(String companysRegion1) {
        return companysRepository.selectCompanysIdxByRegion1(companysRegion1);
    }

    @Override
    public List<SelectAllCompanysList> selectCompanysByCompanysRegion1(String companysRegion1) {
        return companysRepository.selectCompanysByCompanysRegion1(companysRegion1);
    }

    @Override
    public Companys selectCompanyInfoByUsersIdx(Long usersIdx) {
        return companysRepository.selectCompanyInfoByUsersIdx(usersIdx);
    }

    @Override
    public SelectAllCompanysForNormal selectOneCompanyInfoByCompanysIdx(Long companysIdx) {
        return companysRepository.selectOneCompanyInfoByCompanysIdx(companysIdx);
    }

    @Override
    public Long selectCompanyIdxByComapnysNameAndCompanysCeoName(String companysName, String companysCeoName) {
        return companysRepository.selectCompanyIdxByComapnysNameAndCompanysCeoName(companysName, companysCeoName);
    }

    @Override
    public SelectCompanys selectOneCompanysByCompanysIdxTest(Long companysIdx) {
        return companysRepository.selectOneCompanysByCompanysIdxTest(companysIdx);
    }

    @Override
    public SelectCompanysUserLikeCompanyEnable selectOneCompanysUserLikeCompanyEnableByCompanysIdxUsersIdx(Long companysIdx, Long usersIdx) {
        return companysRepository.selectOneCompanysUserLikeCompanyEnableByCompanysIdxUsersIdx(companysIdx, usersIdx);
    }

    @Override
    public Companys selectCompanysPremiumEnabled(Long usersIdx) {
        return companysRepository.selectCompanysPremiumEnabled(usersIdx);
    }

    @Override
    public List<Long> selectCompanysByFilter2(String companysCategory, String companysRegion1) {
        return companysRepository.selectCompanysByFilter2(companysCategory, companysRegion1);
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
        return null;
    }

    @Override
    public Companys getById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Companys getReferenceById(Long id) {
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
