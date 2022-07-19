package com.eosa.web.users;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.eosa.web.security.CustomDuplicateException;
import com.eosa.web.security.CustomUserDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsersService implements UsersRepository {    

    @Autowired private UsersRepository usersRepository;
    
    @Autowired private BCryptPasswordEncoder passwordEncoder;

    /**
     * 사용자 정보 DB저장 (회원가입) - JPA Repository 기본형
     * @param <Users>entity Users 형태의 데이터를 매개변수로 받는다.
    */
    @Override
    public <S extends Users> S save(S entity) {
        if(usersRepository.findOneWithAuthoritiesByUsersAccount(entity.getUsersAccount()).orElse(null) != null) {
            throw new CustomDuplicateException("이미 존재하는 회원입니다.");
        }

        LocalDateTime currentTime = LocalDateTime.now();
        entity.setUsersPass(passwordEncoder.encode(entity.getUsersPass()));
        entity.setUsersEnabled(1);
        entity.setUsersJoinDate(currentTime);
        
        return usersRepository.save(entity);
    }

    /**
     * 사용자 정보 DB저장 (회원가입) - JPA INSERT test용
     * @param <Users>entity
     * @return 1 | 0
    */
    public int userSave(Users param) {
        int result = 0;
        LocalDateTime currentTime = LocalDateTime.now();
        param.setUsersPass(passwordEncoder.encode(param.getUsersPass()));
        param.setUsersEnabled(1);
        param.setUsersJoinDate(currentTime);

        try {
            result = usersRepository.userSave(param);
        }
        catch(Exception e) {
            result = 0;
            log.error("[ERROR] {}\n[ERROR TIME] {}", e, currentTime);
            // System.out.println("[Error] userSave(): " + e);
        }
               
        return result;
    }    

    /**
     * 로그인 할 때 활용하는 메서드(Spring Security formLogin()을 통해 로그인을 할때 사용하는 메서드)
     * @param usersAccount
     * @return Users
     */
    public Users findByUsersAccount(String usersAccount) {
        return usersRepository.findByUsersAccount(usersAccount);
    }
   
    /**
     * 사용자 계정을 기반으로 해당 사용자의 정보 조회 (사용자 정보 조회시 사용)
    */
    public FindByUsersAccount selectByUsersAccount(String usersAccount) {
        FindByUsersAccount result = usersRepository.selectByUsersAccount(usersAccount);
        if(result == null) {
            log.error("[ERROR] SQL RESULT NULL findByUsersAccount()");
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
     * 모든 사용자 계정정보 조회 정렬하여 출력(개발 테스트용)
     */
    @Override
    public List<Users> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
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

    /**
     * 사용자 서비스 탈퇴를 수행하는 메서드 
    */
    public int deleteUserInfo(Long usersIdx) {
        return usersRepository.deleteUserInfo(usersIdx);
    }
    
}
