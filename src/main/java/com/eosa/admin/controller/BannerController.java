package com.eosa.admin.controller;

import com.eosa.admin.dto.BannerDTO;
import com.eosa.admin.service.BannerService;
import com.eosa.web.util.file.AwsS3Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/admin/manage/banner")
public class BannerController {

    @Autowired private BannerService bannerService;
    @Autowired private AwsS3Service awsS3Service;

    @GetMapping("/list")
    public String bannerList(Model model) {
        
        List<BannerDTO> items = bannerService.bannerList();
        List<BannerDTO> pc = new ArrayList<>();
        List<BannerDTO> mobile = new ArrayList<>();

        for(int i = 0; i < items.size(); i++) {
            if(items.get(i).getBannerTag().equals("pc")) {
                pc.add(items.get(i));
            }
            else if(items.get(i).getBannerTag().equals("mobile")) {
                mobile.add(items.get(i));
            }
        }

        model.addAttribute("items", items);
        model.addAttribute("pc", pc);
        model.addAttribute("mobile", mobile);
        
        return "admin/banner/list";
    }

    @PutMapping("/update")
    public String bannerUpdate(
        @RequestParam(name="fileForDesktop", required = false) List<MultipartFile> fileForDesktop,
        @RequestParam(name="fileForMobile", required = false) List<MultipartFile> fileForMobile,
        @RequestParam(name="bannerUrlDesktop", required = false) List<String> bannerUrlDesktop,
        @RequestParam(name="bannerUrlMobile", required = false) List<String> bannerUrlMobile
    ) {
        if(fileForDesktop != null) { log.debug("[bannerUpdate] fileForDesktop: {}", fileForDesktop.toString()); }
        if(fileForMobile != null) { log.debug("[bannerUpdate] fileForMobile: {}", fileForMobile.toString()); }
        if(bannerUrlDesktop != null) { log.debug("[bannerUpdate] bannerUrlDesktop: {}", bannerUrlDesktop.toString()); }
        if(bannerUrlMobile != null) { log.debug("[bannerUpdate] bannerUrlMobile: {}", bannerUrlMobile.toString()); }

        Map<String, Object> files = new HashMap<>();
        Map<String, String> fileTags = new HashMap<>();
        List<Integer> resultList = new ArrayList<>();     

        if(fileForDesktop != null) {
            for(int i = 0; i < fileForDesktop.size(); i++) {
                List<String> fileObject = awsS3Service.uploadSingleFile(fileForDesktop.get(i), "banner/desktop", Long.valueOf(i));
                String fileURL = fileObject.get(1);
                String fileName = fileObject.get(0);
                files.put(fileName, fileURL);
                fileTags.put(fileName, "pc");
            }
        }

        if(fileForDesktop != null) {
            for(int i = 0; i < bannerUrlDesktop.size(); i++) {
                String fileUrl = bannerUrlDesktop.get(i);                
                files.put(String.valueOf(i + 1), fileUrl);
                fileTags.put(String.valueOf(i + 1), "pc");
            }
        }

        if(fileForMobile != null) {
            for(int i = 0; i < fileForMobile.size(); i++) {
                List<String> fileObject = awsS3Service.uploadSingleFile(fileForMobile.get(i), "banner/mobile", Long.valueOf(i));
                String fileURL = fileObject.get(1);
                String fileName = fileObject.get(0);
                files.put(fileName, fileURL);
                fileTags.put(fileName, "mobile");
            }
        }
        
        if(fileForMobile != null) {
            for(int i = 0; i < bannerUrlMobile.size(); i++) {
                String fileUrl = bannerUrlMobile.get(i);                
                files.put(String.valueOf(i + 1), fileUrl);
                fileTags.put(String.valueOf(i + 1), "mobile");
            }
        }

        log.debug("[bannerUpdate] files: {}", files.toString());
        log.debug("[bannerUpdate] fileTags: {}", fileTags.toString());

        Iterator<String> keys = files.keySet().iterator();
        while(keys.hasNext()) {
            String key = keys.next();
            BannerDTO banner = new BannerDTO();
            banner.setBannerTag(fileTags.get(key));
            banner.setBannerFileName(key);
            banner.setBannerFileLink(files.get(key).toString());

            int insertRow = bannerService.insertBanner(banner);
            resultList.add(insertRow);
        }

        return "admin/banner/list";

    }


}
