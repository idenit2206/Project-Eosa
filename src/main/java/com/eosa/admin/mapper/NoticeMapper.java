package com.eosa.admin.mapper;

import com.eosa.admin.dto.NoticeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoticeMapper {

    int insertNotice(NoticeDTO noticeDTO);

    int countNoticeList();

    List<NoticeDTO> selectNoticeList(Map<String, Object> map);    

    
    /**
     * 공지 상세 조회 매퍼
     * @param idx
     * @return
     */
    NoticeDTO noticeDetails(Long idx);

    int updateNoticeByNoticeIdx(NoticeDTO noticeDTo);

    int deleteByNoticeIdx(Long idx);

}
