package com.eosa.admin.usersmanage;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.eosa.admin.util.random.AddressTempData;
import com.eosa.admin.util.random.RandomGenAccount;
import com.eosa.admin.util.random.RandomGenKorName;
import com.eosa.admin.util.random.RandomGenMobileNumber;
import com.eosa.web.users.Users;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class UsersManageService implements UsersManageRepository {

    @Autowired private UsersManageRepository usersManageRepository;

    @Autowired BCryptPasswordEncoder passwordEncoder;

    @Override
    public <S extends Users> S save(S entity) {
        RandomGenAccount rgu = new RandomGenAccount();
        String usersAccount = rgu.generateAccount();
        String usersPass = passwordEncoder.encode(usersAccount);

        RandomGenKorName rgkn = new RandomGenKorName();
        String usersName = rgkn.RandomGenKorName(3);

        RandomGenMobileNumber rgmn = new RandomGenMobileNumber();
        String usersPhone = rgmn.RandomGenMobileNumber();

        String usersEmail = usersAccount + "@email.com";

        String[] usersRole = {"CLIENT", "DETECTIVE"};

        AddressTempData atd = new AddressTempData();
        int RegionTemp = (int) Math.floor(Math.random() * atd.getREGIONAL_LOCAL_NAME().length);
        String usersRegion1 = atd.getREGIONAL_LOCAL_NAME()[RegionTemp];
        String usersRegion2 = atd.getREGION2()[RegionTemp][(int) Math.floor(Math.random() * atd.getREGION2()[RegionTemp].length)];
        LocalDateTime usersJoinDate = LocalDateTime.now();
        int usersNotice = (int) Math.floor(Math.random() * 1);

        entity.setUsersAccount(usersAccount);
        entity.setUsersPass(usersPass);
        entity.setUsersName(usersName);
        entity.setUsersNick(usersAccount.substring(0, 5));
        entity.setUsersPhone(usersPhone);
        entity.setUsersEmail(usersEmail);
        entity.setUsersRole(usersRole[(int) Math.floor(Math.random() * 2)]);
        entity.setUsersAge((int) Math.floor(Math.random() * 9) * 10);
        entity.setUsersRegion1(usersRegion1);
        entity.setUsersRegion2(usersRegion2);
        entity.setUsersJoinDate(usersJoinDate);
        entity.setUsersNotice(usersNotice);
        entity.setUsersEnabled(1);

        return usersManageRepository.save(entity);
    }

    @Override
    public List<Users> findAll() {
        return usersManageRepository.findAll();
    }

    @Override
    public Page<Users> findAll(Pageable pageable) {
        return usersManageRepository.findAll(pageable);
    }

    public Users getByUsersAccount(String usersAccount) {
        return usersManageRepository.getByUsersAccount(usersAccount);
    }

    public int modifyUsersInfo(Users user) {
        String encryptUsersPass = passwordEncoder.encode(user.getUsersPass());
        user.setUsersPass(encryptUsersPass);
        return usersManageRepository.modifyUsersInfo(user);
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
    
}
