package com.eosa.admin.mapper;

import com.eosa.admin.dto.UsersDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * packageName    : com.eosa.admin.mapper
 * fileName       : AdminMapper
 * author         : Jihun Kim
 * date           : 2022-09-13
 * description    : 관리자 매퍼
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-13        Jihun Kim       최초 생성
 */
@Mapper
public interface AdminMapper {

    /**
     * 관리자 등록 매퍼
     *
     * @param usersDTO
     * @return int
     */
    int insertAdmin(UsersDTO usersDTO);

    /**
     * 관리자 개수 조회 매퍼
     *
     * @return
     */
    int countAdmin();

    /**
     * 관리자 목록 조회 매퍼
     *
     * @param map
     * @return UsersDTO
     */
    List<UsersDTO> selectAdmin(Map<String, Object> map);

    /**
     * 관리자 상세 조회 매퍼
     *
     * @param usersIdx
     * @return UsersDTO
     */
    UsersDTO selectAdminDetails(long usersIdx);

    /**
     * 비밀번호 변경 매퍼
     *
     * @param usersDTO
     * @return int
     */
    int updatePwd(UsersDTO usersDTO);

    /**
     * 관리자 수정 매퍼
     *
     * @param usersDTO
     * @return int
     */
    int updateAdmin(UsersDTO usersDTO);

    /**
     * 아이디 중복검사 매퍼
     *
     * @param usersAccount
     * @return int
     */
    int countAccount(String usersAccount);

    /**
     * 연락처 중복검사 매퍼
     *
     * @param usersPhone
     * @return int
     */
    int countPhone(String usersPhone);

}
