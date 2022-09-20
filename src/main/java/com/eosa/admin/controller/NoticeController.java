package com.eosa.admin.controller;

import com.eosa.admin.dto.NoticeDTO;
import com.eosa.admin.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/manage/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @GetMapping("/list")
    public String noticeList(
        Model model,
        @RequestParam(defaultValue = "") String search,
        @RequestParam(defaultValue = "1") int page
    ) {
        return noticeService.noticeList(model, search, page);
    }

    @GetMapping("/register")
    public String noticeRegister(Model model, Authentication authentication) {
        return noticeService.noticeRegister(model, authentication);
    }

    @ResponseBody
    @PostMapping("/insert")
    public int insertNotice(NoticeDTO noticeDTO) {
        return noticeService.insertNotice(noticeDTO);
    }

}
