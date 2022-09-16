package com.eosa.admin.service;

import com.eosa.admin.dto.CompanysDTO;
import com.eosa.admin.mapper.CompanyMapper;
import com.eosa.admin.pagination.Pagination;
import com.eosa.admin.safety.Safety;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.NoSuchAlgorithmException;
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

        Map<String, Object> map = new HashMap<>();

        map.put("companysEnabled", enabled);

        // 필터 검색 조건
        if (search != "" && !search.equals("")) {
            if (sort.equals("name")) {
                map.put("name", sort);
            } else if (sort.equals("ceo")) {
                map.put("ceo", sort);
            } else if (sort.equals("phone")) {
                map.put("phone", sort);
            }
            map.put("search", search);
        }

        int count = companyMapper.countCompanyList(map);

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

    /**
     * 업체 수정 서비스
     *
     * @param companysDTO
     * @return int
     */
    public int updateCompany(CompanysDTO companysDTO) {

        // 업체 활동 지역, 분야 삭제
        companyMapper.deleteCompanysRegion(companysDTO.getCompanysIdx());
        companyMapper.deleteCompanysCategory(companysDTO.getCompanysIdx());

        Map<String, Object> map = new HashMap<>();

        map.put("companysIdx", companysDTO.getCompanysIdx());

        String region[] = companysDTO.getActiveRegion().split(",");
        String category[] = companysDTO.getCompanysCategoryValue().split(",");

        for (int i = 0; i < region.length; i++) {
            map.put("activeRegion", region[i]);
            companyMapper.insertCompanysRegion(map);
        }
        for (int i = 0; i < category.length; i++) {
            map.put("companysCategoryValue", category[i]);
            companyMapper.insertCompanysCategory(map);
        }

        return companyMapper.updateCompanys(companysDTO);
    }

    /**
     * 안심번호 추출 서비스
     *
     * @return String
     * @throws NoSuchAlgorithmException
     */
    public String safetyNumber() throws NoSuchAlgorithmException {

        Safety safety = new Safety();

        Map<String, String> map = safety.safetyEncode();

        String result = safety.safetyAPI("https://bizapi.callmix.co.kr/biz050/BZV100?secureCode=" + map.get("secureCode") + "&bizId=" + map.get("id") + "&monthDay=" + map.get("monthDay") + "&selGbn=3&seqNo=0&reqCnt=1");

        // 받아온 JSON에서 데이터 추출
        JSONObject json = new JSONObject(result);
        JSONArray jsonArray = json.getJSONArray("vnList");
        JSONObject obj = jsonArray.getJSONObject(0);
        String vn = obj.getString("vn");

        return vn;
    }

    /**
     * 안심번호 등록/삭제 서비스
     *
     * @param companysDTO
     * @return int
     * @throws NoSuchAlgorithmException
     */
    public int safetyMapping(CompanysDTO companysDTO) throws NoSuchAlgorithmException {

        Safety safety = new Safety();

        Map<String, String> map = safety.safetyEncode();

        String result = safety.safetyAPI("https://bizapi.callmix.co.kr/biz050/BZV210?secureCode=" + map.get("secureCode") + "&bizId=" + map.get("id") + "&monthDay=" + map.get("monthDay") + "&tkGbn=" + companysDTO.getTkGbn() + "&rn=" + companysDTO.getCompanysPhone() + "&vn=" + companysDTO.getCompanysDummyPhone());

        JSONObject json = new JSONObject(result);

        int num = json.getString("resCd").equals("SUCCESS") ? 1 : 0;

        return num;
    }

}
