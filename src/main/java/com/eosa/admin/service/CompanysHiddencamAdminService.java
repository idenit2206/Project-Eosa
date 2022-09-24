package com.eosa.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.eosa.admin.dto.CompanysHiddencamDTO;
import com.eosa.admin.mapper.CompanysHiddencamMapper;
import com.eosa.admin.pagination.Pagination;

@Service
public class CompanysHiddencamAdminService {
    
    @Autowired private CompanysHiddencamMapper companyHiddencamMapper;

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

    public String companysHiddencamDetails(Model model, long companysHiddencamIdx) {
        CompanysHiddencamDTO details = companyHiddencamMapper.selectCompanysHiddencamDetails(companysHiddencamIdx);

        model.addAttribute("details", details);

        return "admin/company/companysHiddencamDetails";
    }

}
