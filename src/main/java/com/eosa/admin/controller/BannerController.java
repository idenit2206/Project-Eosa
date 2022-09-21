package com.eosa.admin.controller;

import com.eosa.admin.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin/manage/banner")
public class BannerController {

    @Autowired private BannerService bannerService;

    @GetMapping("/list")
    public String bannerList(Model model) {
        return "admin/banner/list";
    }

//    @ResponseBody
//    @PostMapping("/update")

}
