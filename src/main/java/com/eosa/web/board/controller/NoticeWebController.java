package com.eosa.web.board.controller;

import com.eosa.web.board.entity.Notice;
import com.eosa.web.board.service.NoticeWebService;
import com.eosa.web.util.CustomResponseData;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/notice")
public class NoticeWebController {

    @Autowired
    NoticeWebService noticeWebService;
    
    /** 
     * 모든 공지사항을 조회하는 컨트롤러
     * @return CustomResponseData
     */
    @GetMapping("/selectNotice")
    public CustomResponseData selectNotice() {
        log.info("[selectNotice] 공지사항을 불러옵니다.");
        CustomResponseData result = new CustomResponseData();

        List<Notice> items = noticeWebService.findAll();

        if(items != null) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(items);
            result.setResponseDateTime(LocalDateTime.now());
        }
        else {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(null);
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

    
    /** 
     * idx가 일치하는 공지사항을 불러오는 컨트롤러
     * @param idx
     * @return CustomResponseData
     */
    @GetMapping("/selectNoticeByNoticeIdx")
    public CustomResponseData selectNoticeByNoticeIdx(@RequestParam("idx") Long idx) {
        log.info("[selectNotice] 공지사항 번호: {} 을 불러옵니다.", idx);
        CustomResponseData result = new CustomResponseData();
        Notice item = noticeWebService.selectNoticeByNoticeIdx(idx);

        if(item != null) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(item);
            result.setResponseDateTime(LocalDateTime.now());
        }
        else {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(null);
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

}
