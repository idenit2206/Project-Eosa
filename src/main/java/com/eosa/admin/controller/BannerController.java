package com.eosa.admin.controller;

import com.eosa.admin.service.BannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/manage/banner")
public class BannerController {

    @Autowired private BannerService bannerService;

    @GetMapping("/list")
    public String bannerList(Model model) {
        return "admin/banner/list";
    }

    @ResponseBody
    @PutMapping("/update")
    public void bannerUpdate(@RequestParam(value="fileForDesktop")List<MultipartFile> fileForDesktop) {
        log.debug(fileForDesktop.toString());
    }

}
