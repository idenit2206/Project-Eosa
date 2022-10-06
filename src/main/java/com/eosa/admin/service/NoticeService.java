package com.eosa.admin.service;

import com.eosa.admin.dto.NoticeDTO;
import com.eosa.admin.mapper.NoticeMapper;
import com.eosa.admin.pagination.Pagination;
import com.eosa.web.board.entity.Notice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    
    /** 
     * @param model
     * @param search
     * @param page
     * @return String
     */
    public String noticeList(Model model, String search, int page) {
        Map<String, Object> map = new HashMap<>();
        int count = noticeMapper.countNoticeList();
        Pagination pagination = new Pagination(count, page);

        map.put("startIndex", pagination.getStartIndex());
        map.put("pageSize", pagination.getPageSize());

        List<NoticeDTO> list = noticeMapper.selectNoticeList(map);

        model.addAttribute("noticeList", list);
        model.addAttribute("pagination", pagination);
        model.addAttribute("count", count);

        return "admin/board/notice/list";
    }

    
    /** 
     * @param model
     * @param authentication
     * @return String
     */
    public String noticeRegister(Model model, Authentication authentication) {
        if(authentication != null) {
            model.addAttribute("usersName", authentication.getName());
            return "admin/board/notice/register";
        }
        else {
            model.addAttribute("usersName", "ADMIN");
            return "admin/board/notice/register";
        }

    }

    
    /** 
     * @param noticeDTO
     * @return int
     */
    public int insertNotice(NoticeDTO noticeDTO) {
        return noticeMapper.insertNotice(noticeDTO);
    }

    
    /** 
     * @param noticeDTO
     * @return int
     */
    public int updateNoticeByNoticeIdx(NoticeDTO noticeDTO) {
        log.debug("[updateNoticeByNoticeIdx] noticeDto: {}", noticeDTO.toString());
        return noticeMapper.updateNoticeByNoticeIdx(noticeDTO);
    }

    
    /** 
     * @param idx
     * @return int
     */
    public int deleteByNoticeIdx(Long idx) {
        int deleteRows = noticeMapper.deleteByNoticeIdx(idx);
        return deleteRows;
    }

}
