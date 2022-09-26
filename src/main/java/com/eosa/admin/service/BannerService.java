package com.eosa.admin.service;

import com.eosa.admin.dto.BannerDTO;
import com.eosa.admin.mapper.BannerMapper;

import java.util.*;

import com.eosa.web.banner.Banner;
import com.eosa.web.util.file.AwsS3Service;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

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
        List<MultipartFile> bannerFile,
        String bannerItem,
        Model model
    ) {
        JsonParser parser = new JsonParser();
        JsonArray bannerItemArray = (JsonArray) parser.parse(bannerItem);
        int fileIndex = 0;
        List<BannerDTO> bannerDTOList = new ArrayList<>();

        // for(int i = 0; i < bannerItemArray.size(); i++) {
        //     JsonElement el = bannerItemArray.get(i);
        //     int idx = el.getAsJsonObject().get("idx").getAsInt();
        //     String bannerFileName = el.getAsJsonObject().get("bannerFileName").getAsString();
        //     String bannerFileUrl = el.getAsJsonObject().get("bannerFileUrl").getAsString();
        //     BannerDTO b = new BannerDTO(idx, "banner", bannerFileName, bannerFileUrl);
        //     bannerDTOList.add(b);
        // }

        if(bannerFile != null) {
            bannerMapper.bannerTruncate();
            for(int i = 0; i < bannerFile.size(); i++) {
                List<String> file = awsS3Service.uploadSingleFile(bannerFile.get(i), "banner", Long.valueOf(i));
                String fileName = file.get(0);
                String fileUrl = file.get(1);
                BannerDTO bannerDTO = new BannerDTO();
                bannerDTO.setIdx(i+1);
                bannerDTO.setBannerTag("banner");
                bannerDTO.setBannerFileName(fileName);
                bannerDTO.setBannerFileLink(fileUrl);
                bannerMapper.bannerUpdate(bannerDTO);
            }
        }
        return "admin/banner/list";
    }

    public String bannerList(Model model) {

        List<BannerDTO> items = bannerMapper.bannerList();
      
        model.addAttribute("items", items);

        return "admin/banner/list";
    }

}
