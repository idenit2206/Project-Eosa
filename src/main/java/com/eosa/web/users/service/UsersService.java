package com.eosa.web.users.service;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.eosa.security.CustomPrincipalDetails;
import com.eosa.web.users.entity.FindByUsersAccountEntity;
import com.eosa.web.users.entity.GetUsersInfoByUsersAccountEntity;
import com.eosa.web.users.entity.Users;
import com.eosa.web.users.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;

@Slf4j
@Service
public class UsersService implements UsersRepository {    

    @Autowired private UsersRepository usersRepository;    
    @Autowired private BCryptPasswordEncoder passwordEncoder;
    
    /**
     * 사용자 정보 DB저장 (회원가입) - JPA INSERT test용
     * @param Users
     * @return 1 | 0
    */
    public int userSave(Users param) {
        int result = 0;
        LocalDateTime currentTime = LocalDateTime.now();
        String prevPass = param.getUsersPass();
        if(prevPass != null) {
            param.setUsersPass(passwordEncoder.encode(param.getUsersPass()));
        }
        else {
            param.setUsersPass(passwordEncoder.encode("DkftndjqtsmsRoqkfwk"));
        }
        param.setUsersEnabled(1);
        if(param.getProvider().equals(null) || param.getProvider().equals("")) {
            param.setProvider("local");
        }

        param.setUsersProfile(null);
        param.setUsersJoinDate(currentTime);

        try {
            result = usersRepository.userSave(param);
        }
        catch(Exception e) {
            result = 0;
            log.error("[UsersService] {}\n[ERROR] {}", e, currentTime);
            // System.out.println("[Error] userSave(): " + e);
        }
               
        return result;
    }

    @Override
    public int selectUsersPhoneCheckByUsersPhone(String usersPhone) {
        return usersRepository.selectUsersPhoneCheckByUsersPhone(usersPhone);
    }

    @Override
    public Users usersAccountDupliCheck(String usersAccount) {
        return usersRepository.usersAccountDupliCheck(usersAccount);
    }

    @Override
    public Users selectUsersByUsersEmail(String usersEmail) {
        return usersRepository.selectUsersByUsersEmail(usersEmail);
    }


    /**
     * 로그인 할 때 활용하는 메서드(Spring Security formLogin()을 통해 로그인을 할때 사용하는 메서드)
     * @param usersAccount
     * @return Users
     */
    public Users findByUsersAccount(String usersAccount) {
        Users result = usersRepository.findByUsersAccount(usersAccount);
        if(result != null) {
            log.debug("[findByUsersAccount]: {}", result.toString());
            return result;
        } else {
            log.error("[findByUsersAccount] 해당 사용자가 존재하지 않습니다.");
            return null;
        }
    }
   
    /**
     * usersAccount에 일치하는 사용자의 정보 조회
     */
    public GetUsersInfoByUsersAccountEntity getUsersInfoByUsersAccount(String usersAccount) {
        GetUsersInfoByUsersAccountEntity result = usersRepository.getUsersInfoByUsersAccount(usersAccount);
        if(result == null) {
            log.error("[ERROR] SQL RESULT NULL findByUsersAccount()");
        }
        return result;
    }

    public Users selectByUsersEmail(String usersEmail) {
        return usersRepository.selectByUsersEmail(usersEmail);
    }

    /**
     * 회원정보(계정)를 분실한 사용자가 이메일을 활용해 계정의 유무를 확인합니다.
     * 계정이 있으면 1 을 출력합니다.
     */
    public int checkAccountByUsersEmail(String usersEmail) {
        return usersRepository.checkAccountByUsersEmail(usersEmail);
    }
    /**
     * 회원의 이메일주소를 활용해 회원의 계정을 찾습니다.
     */
    public String accountMailSend(String usersEmail) {
        return usersRepository.accountMailSend(usersEmail);
    }

    /**
     * 회원정보를 조회하기전에 비밀번호를 입력해 검증합니다.
     */
    public FindByUsersAccountEntity checkMyPageByPass(String usersAccount, String usersPass) {
        FindByUsersAccountEntity result = null;
        Users user = usersRepository.findByUsersAccount(usersAccount);
        UserDetails ud = new CustomPrincipalDetails(user);

        if(passwordEncoder.matches(usersPass, ud.getPassword())) {
            log.debug("{} 사용자가 사용자 정보를 조회합니다.", usersAccount);
            result = usersRepository.checkMyPageByPass(usersAccount, ud.getPassword());
            // checkMyPageByPass(usersAccount, usersPass);
        }
        else {
            log.error("# MyPage접근 Pass 불일치");
            result = null;
        }

        return result;
    }
    
    /**
     * Token 기반의 로그인을 수행할 때 활용
     * @param usersAccount
     * @return
     */
    public Long findUsersIdxByUsersAccount(String usersAccount) {
        return usersRepository.findUsersIdxByUsersAccount(usersAccount);
    }

    /**
     * 사용자 색인번호 기반 사용자(DETECTIVE) 존재 여부 조회
     */
    public int findDetectiveByUsersIdx(Long usersIdx) {
        return usersRepository.findDetectiveByUsersIdx(usersIdx);
    }


    /**
     * 모든 사용자 계정정보 조회(개발시 테스트용)
     */
    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }   

    /**
     * 사용자의 정보를 수정하는 메서드
     * @param Users param
     * @return int
    */
    public int updateUserInfo(Users param) {
        String newPass = passwordEncoder.encode(param.getUsersPass());
        param.setUsersPass(newPass);
        int tran = usersRepository.updateUserInfo(param);
        return tran;
    }

    public int updateAdminUserInfo(Users param) {
        String newPass = passwordEncoder.encode(param.getUsersPass());
        param.setUsersPass(newPass);
        int tran = usersRepository.updateAdminUserInfo(param);
        return tran;
    }

    /**
     * 사용자 서비스 탈퇴를 수행하는 메서드 
    */
    public int deleteUserInfo(Long usersIdx) {
        return usersRepository.deleteUserInfo(usersIdx);
    }

    @Override
    public Users selectUsersByUsersIdx(Long usersIdx) {
        return usersRepository.selectUsersByUsersIdx(usersIdx);
    }

    public String selectUsersAccountByUsersIdx(Long usersIdx) {
        return usersRepository.selectUsersAccountByUsersIdx(usersIdx);
    }

    @Override
    public int updateUsersPass(String usersAccount, String usersEmail, String encodedCode) {
        return usersRepository.updateUsersPass(usersAccount, usersEmail, encodedCode);
    }

    @Override
    public List<Users> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Users> findAllById(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Users> List<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void flush() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <S extends Users> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Users> List<S> saveAllAndFlush(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Users> entities) {
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
    public Users getOne(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Users getById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Users getReferenceById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Users> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Users> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<Users> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Users> S save(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Users> findById(Long id) {
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
    public void delete(Users entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll(Iterable<? extends Users> entities) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <S extends Users> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public <S extends Users> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Users> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <S extends Users> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <S extends Users, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Users findByUsersEmail(String usersEmail) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Users> selectAllDetective() {
        // TODO Auto-generated method stub
        return null;
    }

}
