package com.eosa.admin.controller;

import com.eosa.admin.dto.BannerDTO;
import com.eosa.admin.service.BannerService;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/manage/banner")
public class BannerController {

    @Autowired private BannerService bannerService;

    
    /** 
     * @param model
     * @return String
     * @throws IOException
     */
    @PutMapping("/update")
    public String bannerUpdate(
        @RequestParam(name="bannerFile", required = false) List<MultipartFile> bannerFile,
        @RequestPart(name="bannerItem", required = false) String bannerItem,
        Model model
    ) throws IOException {
        if(bannerFile != null) { log.debug("[bannerFile] bannerFile: {}", bannerFile.get(0).getOriginalFilename()); }
        log.debug("[bannerUpdate] bannerItem: {}", bannerItem.toString());
        return bannerService.bannerUpdate(bannerFile, bannerItem, model);
        // return "/admin/banner/list";
    }

    
    /** 
     * @param model
     * @return String
     */
    @GetMapping("/list")
    public String bannerList(Model model) {
        return bannerService.bannerList(model);
    }

    
    /** 
     * @param model
     * @return String
     */
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
    
    /** 
     * detective 페이지 banner 조회
     * @param model
     * @return String
     */
    @GetMapping("/detectiveBannerList")
    public String detectiveBannerList(Model model) {
        return bannerService.detectiveBannerList(model);
    }

}
