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

    @PutMapping("/update")
    public String bannerUpdate(
        @RequestParam(name="fileForDesktop", required = false) List<MultipartFile> fileForDesktop,
        @RequestParam(name="fileForMobile", required = false) List<MultipartFile> fileForMobile,
        @RequestParam(name="bannerUrlDesktop", required = false) List<String> bannerUrlDesktop,
        @RequestParam(name="bannerUrlMobile", required = false) List<String> bannerUrlMobile,
        Model model
    ) {
//        return bannerService.bannerUpdate(fileForDesktop, fileForMobile, bannerUrlDesktop, bannerUrlMobile, model);
        String result = bannerService.bannerUpdate(fileForDesktop, bannerUrlDesktop, model);
        return result;
    }

    @GetMapping("/list")
    public String bannerList(Model model) {
        return bannerService.bannerList(model);
    }


}
