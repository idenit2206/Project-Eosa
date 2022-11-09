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

import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.eosa.security.CustomPrincipalDetails;
import com.eosa.web.chatting.repository.ChatMessageRepository;
import com.eosa.web.chatting.repository.ChatRoomRepository;
import com.eosa.web.companys.repository.CompanysRepository;
import com.eosa.web.users.entity.FindByUsersAccountEntity;
import com.eosa.web.users.entity.GetUsersInfoByUsersAccountEntity;
import com.eosa.web.users.entity.Users;
import com.eosa.web.users.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsersService implements UsersRepository {    

    @Autowired private UsersRepository usersRepository;
    @Autowired private CompanysRepository companysRepository;
    @Autowired private ChatRoomRepository chatRoomRepository;
    @Autowired private ChatMessageRepository chatMessageRepository;
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

    
    /** 
     * @param usersPhone
     * @return int
     */
    @Override
    public int selectUsersPhoneCheckByUsersPhone(String usersPhone) {
        return usersRepository.selectUsersPhoneCheckByUsersPhone(usersPhone);
    }

    
    /** 
     * @param usersAccount
     * @return Users
     */
    @Override
    public Users usersAccountDupliCheck(String usersAccount) {
        return usersRepository.usersAccountDupliCheck(usersAccount);
    }

    
    /** 
     * 이메일로 유저유무 여부를 확인하는 서비스
     * @param usersEmail
     * @return Users
     */
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

    
    /** 
     * @param usersEmail
     * @return Users
     */
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

    /**
     * 사용자의 정보(패스워드 제외)를 수정하는 메서드
     * @param Users param
     * @return int
    */
    public int updateUserInfoExcludeUsersPass(Users param) {
        int tran = usersRepository.updateUserInfoExcludeUsersPass(param);
        return tran;
    }
    
    /** 
     * 관리자 계정정보 업데이트
     * @param param
     * @return int
     */
    public int updateAdminUserInfo(Users param) {
        String newPass = passwordEncoder.encode(param.getUsersPass());
        param.setUsersPass(newPass);
        int tran = usersRepository.updateAdminUserInfo(param);
        return tran;
    }

    /**
     * 사용자 서비스 탈퇴(정보 즉시 삭제)를 수행하는 메서드 
    */
    public int deleteUserInfo(Long usersIdx) {
        int companysDelete = companysRepository.deleteCompanysByCompanysCeoIdx(usersIdx);
        List<String> selectChatRoomList = chatRoomRepository.selectChatRoomIdListByUsersIdx(usersIdx);
        if(selectChatRoomList.size() > 0 || selectChatRoomList != null) {
            for(int i = 0; i < selectChatRoomList.size(); i++) {
                log.info("[deleteUserInfo] 회원탈퇴 usersIdx: {} 와 관련된 채팅데이터를 삭제합니다.", String.valueOf(usersIdx));
                chatMessageRepository.deleteByRoomId(selectChatRoomList.get(i));
                chatRoomRepository.deleteRoomByRoomId(selectChatRoomList.get(i));
            }
        }
        if(companysDelete == 1) {
            log.info("[deleteUserInfo] 회원탈퇴 usersIdx: {} 회원의 업체 정보를 삭제합니다.", String.valueOf(usersIdx));
        }
        return usersRepository.deleteUserInfo(usersIdx);
    }

    /**
     * 사용자 서비스 탈퇴(즉시 삭제를 하지 않고 숨김처리)를 수행하는 메서드 
    */
    public int deleteUserInfo02(Long usersIdx) {
        int companysDelete = companysRepository.deleteCompanysByCompanysCeoIdx02(usersIdx);
        List<String> selectChatRoomList = chatRoomRepository.selectChatRoomIdListByUsersIdx(usersIdx);
        if(selectChatRoomList.size() > 0 || selectChatRoomList != null) {
            for(int i = 0; i < selectChatRoomList.size(); i++) {
                log.info("[deleteUserInfo] 회원탈퇴 usersIdx: {} 와 관련된 채팅데이터를 삭제합니다.", String.valueOf(usersIdx));
                chatMessageRepository.deleteByRoomId(selectChatRoomList.get(i));
                chatRoomRepository.deleteRoomByRoomId(selectChatRoomList.get(i));
            }
        }
        if(companysDelete == 1) {
            log.info("[deleteUserInfo] 회원탈퇴 usersIdx: {} 회원의 업체 정보를 삭제합니다.", String.valueOf(usersIdx));
        }
        return usersRepository.deleteUserInfo02(usersIdx);
    }

    
    /** 
     * @param usersIdx
     * @return Users
     */
    @Override
    public Users selectUsersByUsersIdx(Long usersIdx) {
        return usersRepository.selectUsersByUsersIdx(usersIdx);
    }

    
    /** 
     * @param usersIdx
     * @return String
     */
    public String selectUsersAccountByUsersIdx(Long usersIdx) {
        return usersRepository.selectUsersAccountByUsersIdx(usersIdx);
    }

    
    /** 
     * @param usersAccount
     * @param usersEmail
     * @param encodedCode
     * @return int
     */
    @Override
    public int updateUsersPass(String usersAccount, String usersEmail, String encodedCode) {
        return usersRepository.updateUsersPass(usersAccount, usersEmail, encodedCode);
    }
    
    public String getTokenCheck(Long usersIdx, String token, String device) {
        log.info("[getTokenCheck] {}, {}, {}", usersIdx, token, device);
        Users checkToken = usersRepository.getUsersByToken(token);
        int removeToken = 0;
        
        if(checkToken != null) {
            log.info("token: {} 을 usersIdx: {} 가 이미 보유중이기 때문에 삭제합니다.", token, checkToken.getUsersIdx());
            removeToken = usersRepository.removeUsersTokenDevice(checkToken.getUsersIdx());
        }        

        int tokenSave = usersRepository.updateUsersTokenDevice(usersIdx, token, device);
        return token;
    }

    // firebase를 활용한 모바일 푸시알림을 위한 서비스
    // Token 조회
    @Override
    public String getTokenByUsersIdx(Long usersIdx) {
        String token = usersRepository.getTokenByUsersIdx(usersIdx);       
        return token;      
    }

    @Override
    public String getDeviceByUsersIdx(Long usersIdx) {
        return usersRepository.getDeviceByUsersIdx(usersIdx);
    }
        
    /** 
     * @param sort
     * @return List<Users>
     */
    @Override
    public List<Users> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param ids
     * @return List<Users>
     */
    @Override
    public List<Users> findAllById(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends Users> List<S> saveAll(Iterable<S> entities) {
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
    public <S extends Users> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param entities
     * @return List<S>
     */
    @Override
    public <S extends Users> List<S> saveAllAndFlush(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param entities
     */
    @Override
    public void deleteAllInBatch(Iterable<Users> entities) {
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
     * @return Users
     */
    @Override
    public Users getOne(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param id
     * @return Users
     */
    @Override
    public Users getById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param id
     * @return Users
     */
    @Override
    public Users getReferenceById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param example
     * @return List<S>
     */
    @Override
    public <S extends Users> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param example
     * @param sort
     * @return List<S>
     */
    @Override
    public <S extends Users> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param pageable
     * @return Page<Users>
     */
    @Override
    public Page<Users> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param entity
     * @return S
     */
    @Override
    public <S extends Users> S save(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param id
     * @return Optional<Users>
     */
    @Override
    public Optional<Users> findById(Long id) {
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
    public void delete(Users entity) {
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
    public void deleteAll(Iterable<? extends Users> entities) {
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
    public <S extends Users> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    
    /** 
     * @param example
     * @param pageable
     * @return Page<S>
     */
    @Override
    public <S extends Users> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param example
     * @return long
     */
    @Override
    public <S extends Users> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    
    /** 
     * @param example
     * @return boolean
     */
    @Override
    public <S extends Users> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }

    
    /** 
     * @param queryFunction
     * @return R
     */
    @Override
    public <S extends Users, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param usersEmail
     * @return Users
     */
    @Override
    public Users findByUsersEmail(String usersEmail) {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @return List<Users>
     */
    @Override
    public List<Users> selectAllDetective() {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public int updateUsersTokenDevice(Long usersIdx, String token, String device) {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * token을 보유중은 Users를 조회하는 서비스
     */
    @Override
    public Users getUsersByToken(String token) {
        return usersRepository.getUsersByToken(token);
    }

    /**
     * usersIdx의 token과 device를 삭제하는 서비스
     */
    @Override
    public int removeUsersTokenDevice(Long usersIdx) {
        return usersRepository.removeUsersTokenDevice(usersIdx);
    }

}
