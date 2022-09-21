package com.eosa.admin.mapper;

import com.eosa.admin.dto.NoticeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoticeMapper {

    int countNoticeList();

    List<NoticeDTO> selectNoticeList(Map<String, Object> map);

    int insertNotice(NoticeDTO noticeDTO);

    int updateNoticeByNoticeIdx(NoticeDTO noticeDTo);

    int deleteByNoticeIdx(Long idx);

}
