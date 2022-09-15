package com.eosa.admin.service;

import com.eosa.admin.dto.CompanysDTO;
import com.eosa.admin.mapper.CompanyMapper;
import com.eosa.admin.pagination.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName    : com.eosa.admin.service
 * fileName       : CompanyService
 * author         : Jihun Kim
 * date           : 2022-09-15
 * description    : 업체 서비스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-15        Jihun Kim       최초 생성
 */
@Service
public class CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    /**
     * 업체 목록 조회 서비스
     *
     * @param model
     * @param enabled
     * @param sort
     * @param search
     * @param page
     * @return String
     */
    public String companyList(Model model, int enabled, String sort, String search, int page) {

        Map<String, Object> cntMap = new HashMap<>();
        Map<String, Object> map = new HashMap<>();

        cntMap.put("companysEnabled", enabled);
        map.put("companysEnabled", enabled);

        // 필터 검색 조건
        if (search != "" && !search.equals("")) {
            if (sort.equals("name")) {
                cntMap.put("name", sort);
                map.put("name", sort);
            } else if (sort.equals("ceo")) {
                cntMap.put("ceo", sort);
                map.put("ceo", sort);
            } else if (sort.equals("phone")) {
                cntMap.put("phone", sort);
                map.put("phone", sort);
            }
            cntMap.put("search", search);
            map.put("search", search);
        }

        int count = companyMapper.countCompanyList(cntMap);

        Pagination pagination = new Pagination(count, page);

        map.put("startIndex", pagination.getStartIndex());
        map.put("pageSize", pagination.getPageSize());

        List<CompanysDTO> list = companyMapper.selectCompanyList(map);

        model.addAttribute("companyList", list);
        model.addAttribute("pagination", pagination);
        model.addAttribute("count", count);
        model.addAttribute("enabled", enabled);
        model.addAttribute("sort", sort);
        model.addAttribute("search", search);

        return "admin/company/list";
    }

    /**
     * 업체 상세 조회 서비스
     *
     * @param model
     * @param companysIdx
     * @return String
     */
    public String companyDetails(Model model, long companysIdx) {

        CompanysDTO details = companyMapper.selectCompanyDetails(companysIdx);

        model.addAttribute("details", details);

        return "admin/company/details";
    }

}
