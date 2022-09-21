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
    public void bannerUpdate(
        @RequestParam(name="fileForDesktop", required = false) List<MultipartFile> fileForDesktop,
        @RequestParam(name="fileForMobile", required = false) List<MultipartFile> fileForMobile,
        @RequestParam(name="bannerUrlDesktop", required = false) List<String> bannerUrlDesktop,
        @RequestParam(name="bannerUrlMobile", required = false) List<String> bannerUrlMobile
    ) {
        if(fileForDesktop != null) { log.debug("[bannerUpdate] fileForDesktop: {}", fileForDesktop.toString()); }
        if(fileForMobile != null) { log.debug("[bannerUpdate] fileForMobile: {}", fileForMobile.toString()); }
        if(bannerUrlDesktop != null) { log.debug("[bannerUpdate] bannerUrlDesktop: {}", bannerUrlDesktop.toString()); }
        if(bannerUrlMobile != null) { log.debug("[bannerUpdate] bannerUrlMobile: {}", bannerUrlMobile.toString()); }
    }

}
