package com.eosa.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.eosa.admin.dto.CompanysHiddencamDTO;
import com.eosa.admin.mapper.CompanysHiddencamMapper;
import com.eosa.admin.pagination.Pagination;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CompanysHiddencamAdminService {
    
    @Autowired private CompanysHiddencamMapper companyHiddencamMapper;

    
    /** 
     * @param model
     * @param enabled
     * @param sort
     * @param search
     * @param page
     * @return String
     */
    public String companysHiddencamList(Model model, int enabled, String sort, String search, int page) {

        Map<String, Object> map = new HashMap<>();

        int count = companyHiddencamMapper.countCompanysHiddencamList();

        Pagination pagination = new Pagination(count, page);

        map.put("startIndex", pagination.getStartIndex());
        map.put("pageSize", pagination.getPageSize());

        List<CompanysHiddencamDTO> list = companyHiddencamMapper.selectCompanysHiddencamList(map);

        model.addAttribute("list", list);
        model.addAttribute("pagination", pagination);
        model.addAttribute("count", count);
        model.addAttribute("enabled", enabled);
        model.addAttribute("sort", sort);
        model.addAttribute("search", search);

        return "admin/company/companysHiddencamList";
    }

    
    /** 
     * @param model
     * @param companysHiddencamIdx
     * @return String
     */
    public String companysHiddencamDetails(Model model, long companysHiddencamIdx) {
        // CompanysHiddencamDTO details = companyHiddencamMapper.selectCompanysHiddencamDetails(companysHiddencamIdx);
        CompanysHiddencamDTO details = companyHiddencamMapper.selectCompanysHiddencamDetails(companysHiddencamIdx);
        model.addAttribute("details", details);

        return "admin/company/companysHiddencamDetails";
    }

    
    /** 
     * @param model
     * @param companysHiddencamDTO
     * @return String
     */
    public String companysHiddencamUpdate(Model model, CompanysHiddencamDTO companysHiddencamDTO) {
        log.debug("[update] dto: {}", companysHiddencamDTO.toString());
        int updateQuery = companyHiddencamMapper.hiddencamUpdate(companysHiddencamDTO);

        CompanysHiddencamDTO details = companyHiddencamMapper.selectCompanysHiddencamDetails(companysHiddencamDTO.getCompanysHiddencamIdx());      
        model.addAttribute("details", details);

        return "admin/company/companysHiddencamDetails";
    }

    
    /** 
     * @param model
     * @return String
     */
    public String companysHiddencamDelete(
        @RequestParam List<Long> companysHiddencamIdxList,
        @RequestParam(defaultValue = "1") int enabled,
        @RequestParam(defaultValue = "name") String sort,
        @RequestParam(defaultValue = "") String search,
        @RequestParam(defaultValue = "1") int page,
        Model model
    ) {
        // log.debug("[deleteRegion]: {}", companysHiddencamIdxList.toString());
        for(int i = 0; i < companysHiddencamIdxList.size(); i++) {
            companyHiddencamMapper.deleteHiddencamList(Long.valueOf(companysHiddencamIdxList.get(i)));
        }
        
        Map<String, Object> map = new HashMap<>();

        int count = companyHiddencamMapper.countCompanysHiddencamList();

        Pagination pagination = new Pagination(count, page);

        map.put("startIndex", pagination.getStartIndex());
        map.put("pageSize", pagination.getPageSize());

        List<CompanysHiddencamDTO> list = companyHiddencamMapper.selectCompanysHiddencamList(map);

        model.addAttribute("list", list);
        model.addAttribute("pagination", pagination);
        model.addAttribute("count", count);
        model.addAttribute("enabled", enabled);
        model.addAttribute("sort", sort);
        model.addAttribute("search", search);

        return "admin/company/companysHiddencamList";
    }

}
