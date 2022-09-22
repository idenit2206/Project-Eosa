package com.eosa.admin.mapper;

import com.eosa.admin.dto.ChatDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * packageName    : com.eosa.admin.mapper
 * fileName       : ChatMapper
 * author         : Jihun Kim
 * date           : 2022-09-22
 * description    : 채팅 매퍼
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-22        Jihun Kim       최초 생성
 */
@Mapper
public interface ChatMapper {

    /**
     * 채팅 목록 개수 조회 매퍼
     *
     * @param map
     * @return int
     */
    int countChatList(Map<String, Object> map);

    /**
     * 채팅 목록 조회 매퍼
     *
     * @param map
     * @return ChatDTO
     */
    List<ChatDTO> selectChatList(Map<String, Object> map);

}
