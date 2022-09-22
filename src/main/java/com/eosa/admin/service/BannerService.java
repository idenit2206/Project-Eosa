package com.eosa.admin.service;

import com.eosa.admin.dto.BannerDTO;
import com.eosa.admin.mapper.BannerMapper;

import java.util.*;

import com.eosa.web.util.file.AwsS3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class BannerService {

    @Autowired private BannerMapper bannerMapper;
    @Autowired private AwsS3Service awsS3Service;

    public String bannerUpdate(
        List<MultipartFile> fileForDesktop,
//        List<MultipartFile> fileForMobile,
        List<String> bannerUrlDesktop,
//        List<String> bannerUrlMobile,
        Model model
    ) {
        if(fileForDesktop != null) { log.debug("[bannerUpdate] fileForDesktop: {}", fileForDesktop.toString()); }
//        if(fileForMobile != null) { log.debug("[bannerUpdate] fileForMobile: {}", fileForMobile.toString()); }
        if(bannerUrlDesktop != null) { log.debug("[bannerUpdate] bannerUrlDesktop: {}", bannerUrlDesktop.toString()); }
//        if(bannerUrlMobile != null) { log.debug("[bannerUpdate] bannerUrlMobile: {}", bannerUrlMobile.toString()); }

        Map<String, Object> files = new HashMap<>();
        Map<String, String> fileTags = new HashMap<>();
        List<Integer> resultList = new ArrayList<>();

        if(fileForDesktop != null) {
            for(int i = 0; i < fileForDesktop.size(); i++) {
                List<String> fileObject = awsS3Service.uploadSingleFile(fileForDesktop.get(i), "banner", Long.valueOf(i + 1));
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

//        if(fileForMobile != null) {
//            for(int i = 0; i < fileForMobile.size(); i++) {
//                List<String> fileObject = awsS3Service.uploadSingleFile(fileForMobile.get(i), "banner/mobile", Long.valueOf(i));
//                String fileURL = fileObject.get(1);
//                String fileName = fileObject.get(0);
//                files.put(fileName, fileURL);
//                fileTags.put(fileName, "mobile");
//            }
//        }
//
//        if(fileForMobile != null) {
//            for(int i = 0; i < bannerUrlMobile.size(); i++) {
//                String fileUrl = bannerUrlMobile.get(i);
//                files.put(String.valueOf(i + 1), fileUrl);
//                fileTags.put(String.valueOf(i + 1), "mobile");
//            }
//        }

        log.debug("[bannerUpdate] files: {}", files.toString());
        log.debug("[bannerUpdate] fileTags: {}", fileTags.toString());

        int truncateRows = bannerMapper.bannerTruncate();
        Iterator<String> keys = files.keySet().iterator();
        while(keys.hasNext()) {
            String key = keys.next();
            BannerDTO banner = new BannerDTO();
            banner.setBannerTag(fileTags.get(key));
            banner.setBannerFileName(key);
            banner.setBannerFileLink(files.get(key).toString());

            int insertRow = bannerMapper.bannerUpdate(banner);
            resultList.add(insertRow);
        }

        return "admin/banner/list";
    }

    public String bannerList(Model model) {

        List<BannerDTO> items = bannerMapper.bannerList();
        List<BannerDTO> pc = new ArrayList<>();
        List<BannerDTO> mobile = new ArrayList<>();

        for(int i = 0; i < items.size(); i++) {
            if(items.get(i).getBannerTag().equals("pc")) {
                pc.add(items.get(i));
            }

//            if(items.get(i).getBannerTag().equals("mobile")) {
//                mobile.add(items.get(i));
//            }
        }

        model.addAttribute("items", items);
        model.addAttribute("pc", pc);
//        model.addAttribute("mobile", mobile);

        return "admin/banner/list";
    }

}
