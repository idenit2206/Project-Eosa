package com.eosa.admin.mapper;

import com.eosa.admin.dto.UsersDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * packageName    : com.eosa.admin.mapper
 * fileName       : UserMapper
 * author         : Jihun Kim
 * date           : 2022-09-13
 * description    : 회원 매퍼
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-13        Jihun Kim       최초 생성
 */
@Mapper
public interface UserMapper {

    /**
     * 회원 목록 개수 조회 매퍼
     *
     * @param map
     * @return int
     */
    int countUsersList(Map<String, Object> map);

    /**
     * 회원 목록 조회 매퍼
     *
     * @param map
     * @return UsersDTO
     */
    List<UsersDTO> selectUsersList(Map<String, Object> map);

    /**
     * 회원 상세 조회 매퍼
     *
     * @param usersIdx
     * @return UsersDTO
     */
    UsersDTO selectUsersDetails(long usersIdx);

}
