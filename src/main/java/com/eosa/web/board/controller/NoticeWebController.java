package com.eosa.web.board.controller;

import com.eosa.web.board.entity.Notice;
import com.eosa.web.board.service.NoticeWebService;
import com.eosa.web.util.CustomResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/notice")
public class NoticeWebController {

    @Autowired
    NoticeWebService noticeWebService;

    @GetMapping("/selectNotice")
    public CustomResponseData selectNotice() {
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

}
