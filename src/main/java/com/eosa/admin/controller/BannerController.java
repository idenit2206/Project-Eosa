package com.eosa.admin.controller;

import com.eosa.admin.dto.BannerDTO;
import com.eosa.admin.service.BannerService;
import com.eosa.web.banner.Banner;
import com.eosa.web.util.file.AwsS3Service;

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

    @PutMapping("/update")
    public String bannerUpdate(
        @RequestParam(name="bannerFile", required = false) List<MultipartFile> bannerFile,
        @RequestPart(name="bannerItem", required = false) String bannerItem,
        Model model
    ) {
        if(bannerFile != null) { log.debug("[bannerUpdate] bannerFile[0]: {}", bannerFile.get(0).getOriginalFilename()); }
        // if(bannerItem != null) { log.debug("[bannerUpdate] bannerItem: {}", bannerItem); }
        return bannerService.bannerUpdate(bannerFile, bannerItem, model);
        // return "/admin/banner/list";
    }

    @GetMapping("/list")
    public String bannerList(Model model) {
        return bannerService.bannerList(model);
    }

    // 탐정 페이지 배너
    @PutMapping("/detectiveBannerUpdate")
    public String detectiveBannerUpdate(
        @RequestParam(name="bannerFile", required = false) List<MultipartFile> bannerFile,
        @RequestPart(name="bannerItem", required = false) String bannerItem,
        Model model
    ) {
        if(bannerFile != null) { log.debug("[bannerUpdate] bannerFile[0]: {}", bannerFile.get(0).getOriginalFilename()); }
        if(bannerItem != null) { log.debug("[bannerUpdate] bannerItem: {}", bannerItem); }
        return bannerService.detectiveBannerUpdate(bannerFile, bannerItem, model);
    }


    @GetMapping("/detectiveBannerList")
    public String detectiveBannerList(Model model) {
        return bannerService.detectiveBannerList(model);
    }

}
