package com.eosa.admin.controller;

import com.eosa.admin.dto.NoticeDTO;
import com.eosa.admin.service.NoticeService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/admin/manage/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    
    /** 
     * @param model
     * @param 
     * @return String
     */
    @GetMapping("/list")
    public String noticeList(
        Model model,
        @RequestParam(defaultValue = "") String search,
        @RequestParam(defaultValue = "1") int page
    ) {
        return noticeService.noticeList(model, search, page);
    }

    
    /** 
     * @param model
     * @param authentication
     * @return String
     */
    @GetMapping("/register")
    public String noticeRegister(Model model, Authentication authentication) {
        return noticeService.noticeRegister(model, authentication);
    }

    
    /** 
     * @param noticeDTO
     * @return int
     */
    @ResponseBody
    @PostMapping("/insert")
    public int insertNotice(NoticeDTO noticeDTO) {
        return noticeService.insertNotice(noticeDTO);
    }

    
    /** 
     * @param noticeDTO
     * @return int
     */
    @ResponseBody
    @PutMapping("/updateByNoticeIdx")
    public int updateNoticeByNoticeIdx(NoticeDTO noticeDTO) {
        return noticeService.updateNoticeByNoticeIdx(noticeDTO);
    }


    
    /** 
     * @param idx
     */
    @ResponseBody
    @DeleteMapping("/deleteByNoticeIdx")
    public void deleteByNoticeIdx(@RequestParam("noticeIdx") Long idx) {
        int deleteRow =  noticeService.deleteByNoticeIdx(idx);
        log.debug("[deleteByNoticeIdx] delete result: {}", deleteRow);
    }

}
