package com.eosa.web.companysmember;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.eosa.web.users.FindByUsersAccount;

@Service
public class CompanysMemberService implements CompanysMemberRepository {

    Logger logger = LoggerFactory.getLogger(CompanysMemberService.class);

    @Autowired
    private CompanysMemberRepository companysMemberRepository;

    @Override
    public <S extends CompanysMember> S save(S entity) {
       return null;
    }

    public int customValdSave(CompanysMember entity) {
        int result = 0;
        LocalDateTime currentTime = LocalDateTime.now();
        entity.setRegistDate(currentTime);

        try {
            result = companysMemberRepository.customValdSave(entity);
        }
        catch(SQLException e) {
            result = 0;
            logger.error("{}", e);
        }

        return result;
    }

    /**
     * companysIdx에 일치하는 회사에 속한 탐정회원 정보를 조회하기 위한 메서드입니다.
     */
    public FindByUsersAccount selectDetectiveInCompany(Long companysIdx) {
        return companysMemberRepository.selectDetectiveInCompany(companysIdx);
    }

    /**
     * 탐정회원이 업체에서 탈퇴할때 사용하는 메서드입니다.
     */
    public int deleteDetective(Long usersIdx, Long companysIdx) {
        return companysMemberRepository.deleteDetective(usersIdx, companysIdx);
    }

}
