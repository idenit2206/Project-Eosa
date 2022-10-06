package com.eosa.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eosa.admin.dto.CompanysHiddencamDTO;
import com.eosa.admin.service.CompanysHiddencamAdminService;

@Controller
@RequestMapping("/admin/manage/companysHiddencam")
public class CompanysHiddencamAdminController {

    @Autowired private CompanysHiddencamAdminService companysHiddencamAdminService;

    
    /** 
     * @param model
     * @param 
     * @return String
     */
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
    
    
    /** 
     * @param model
     * @param companysHiddencamIdx
     * @return String
     */
    @GetMapping("/details")
    public String companysHiddencamDetails(Model model, @RequestParam long companysHiddencamIdx) {
        return companysHiddencamAdminService.companysHiddencamDetails(model, companysHiddencamIdx);
    }

    
    /** 
     * @param model
     * @param companysHiddencamDTO
     * @return String
     */
    @PutMapping("/update")
    public String companysHiddencamUpdate(Model model, CompanysHiddencamDTO companysHiddencamDTO) {
        return companysHiddencamAdminService.companysHiddencamUpdate(model, companysHiddencamDTO);
    }

    
    /** 
     * @param model
     * @return String
     */
    @DeleteMapping("/delete")
    public String companysHiddencamDelete(
        @RequestParam List<Long> companysHiddencamIdxList,
        @RequestParam(defaultValue = "1") int enabled,
        @RequestParam(defaultValue = "name") String sort,
        @RequestParam(defaultValue = "") String search,
        @RequestParam(defaultValue = "1") int page,
        Model model
    ) {
        return companysHiddencamAdminService.companysHiddencamDelete(companysHiddencamIdxList, enabled, sort, search, page, model);
    }
}
