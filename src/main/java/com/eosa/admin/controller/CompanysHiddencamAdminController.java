package com.eosa.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eosa.admin.service.CompanysHiddencamAdminService;

@Controller
@RequestMapping("/admin/manage/companysHiddencam")
public class CompanysHiddencamAdminController {

    @Autowired private CompanysHiddencamAdminService companysHiddencamAdminService;

    @GetMapping("/list")
    public String companysHiddencamList(
        Model model,
        @RequestParam(defaultValue = "1") int enabled,
        @RequestParam(defaultValue = "name") String sort,
        @RequestParam(defaultValue = "") String search,
        @RequestParam(defaultValue = "1") int page
    ) {
        return companysHiddencamAdminService.companysHiddencamList(model, enabled, sort, search, page);
    }
    
    @GetMapping("/details")
    public String companysHiddencamDetails(Model model, @RequestParam long companysHiddencamIdx) {
        return companysHiddencamAdminService.companysHiddencamDetails(model, companysHiddencamIdx);
    }
}
