package com.eosa.admin.mapper;

import com.eosa.admin.dto.TempUserDTO;
import com.eosa.admin.dto.UsersDTO;
import com.eosa.admin.dto.UsersTerminateDTO;
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
     * @return UsersTerminateDTO
     */
    UsersTerminateDTO selectUsersDetails(long usersIdx);

    /**
     * 회원 수정 매퍼
     *
     * @param usersDTO
     * @return int
     */
    int updateUsers(UsersDTO usersDTO);

    /**
     * 탈퇴 회원 목록 개수 조회 매퍼
     *
     * @param map
     * @return int
     */
    int countTerminateList(Map<String, Object> map);

    /**
     * 탈퇴 회원 목록 조회 매퍼
     *
     * @param map
     * @return UsersTerminateDTO
     */
    List<UsersTerminateDTO> selectTerminateList(Map<String, Object> map);

    /**
     * 회원 탈퇴 상태 변경 매퍼
     *
     * @param usersDTO
     * @return int
     */
    int updateUsersTerminate(UsersDTO usersDTO);

    /**
     * 회원 탈퇴 매퍼
     *
     * @param usersTerminateDTO
     * @return int
     */
    int insertTerminate(UsersTerminateDTO usersTerminateDTO);

    /**
     * 회원 탈퇴 취소 매퍼
     *
     * @param usersIdx
     * @return int
     */
    int deleteTerminate(long usersIdx);

    /**
     * 회원 삭제 매퍼
     *
     * @param usersIdx
     * @return int
     */
    int deleteUsers(long usersIdx);

    /**
     * 비회원 목록 개수 조회 매퍼
     *
     * @return int
     */
    int countTempList();

    /**
     * 비회원 목록 조회 매퍼
     *
     * @param map
     * @return TempUserDTO
     */
    List<TempUserDTO> selectTempList(Map<String, Object>map);

    /**
     * 비회원 삭제 매퍼
     *
     * @param arr
     * @return int
     */
    int deleteTemp(String[] arr);

}
