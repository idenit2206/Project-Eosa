package com.eosa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eosa.web.users.entity.Users;
import com.eosa.web.users.repository.UsersRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Spring Security FormLogin시 활용되는 서비스
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired UsersRepository usersRepository;

    
    /** 
     * @param username
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("[loadUserByUsername 27] {}", username);
        // UserDetails result = new CustomPrincipalDetails(usersRepository.findByUsersAccount(username));
        Users result = usersRepository.findByUsersAccount(username);        
    
        if(result == null) {
            throw new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다.");
        }
        else {
            return new CustomPrincipalDetails(result);
        }
    }
    
}
