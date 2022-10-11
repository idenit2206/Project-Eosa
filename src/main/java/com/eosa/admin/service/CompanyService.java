package com.eosa.admin.service;

import com.eosa.admin.dto.*;
import com.eosa.admin.mapper.CategoryMapper;
import com.eosa.admin.mapper.CompanyMapper;
import com.eosa.admin.mapper.PriceMapper;
import com.eosa.admin.mapper.RegionMapper;
import com.eosa.admin.pagination.Pagination;
import com.eosa.admin.safety.Safety;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    private PriceMapper priceMapper;

    @Autowired
    private RegionMapper regionMapper;

    @Autowired
    private CategoryMapper categoryMapper;

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

        // 가격 조회
        PriceDTO price = priceMapper.selectPrice();
        List<RegionDTO> regionList = regionMapper.selectRegion();
        List<CategoryDTO> categoryList = categoryMapper.selectCategory();

        model.addAttribute("details", details);
        model.addAttribute("price", price);
        model.addAttribute("regionList", regionList);
        model.addAttribute("categoryList", categoryList);

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

    /**
     * 업체 인증 서비스
     *
     * @param companysIdx
     * @param sort
     * @return int
     */
    public int updateCheck(long companysIdx, int sort, int num) {

        Map<String, Object> map = new HashMap<>();

        if (sort == 0) {
            map.put("sort", sort);
        } else if (sort == 1) {
            map.put("sort", sort);
        }

        map.put("num", num);
        map.put("companysIdx", companysIdx);

        return companyMapper.updateCheck(map);
    }

    /**
     * 업체 제휴협회 신청 서비스
     *
     * @param companysDTO
     * @return int
     */
    public int requestPremium(CompanysDTO companysDTO) {

        return companyMapper.insertPremium(companysDTO);
    }

    /**
     * 업체 제휴협회 등록 서비스
     *
     * @param companysDTO
     * @return int
     */
    public int approvalPremium(CompanysDTO companysDTO) {

        Map<String, Object> map = new HashMap<>();
        map.put("sort", "premium");
        map.put("num", 1);
        map.put("companysIdx", companysDTO.getCompanysIdx());

        companyMapper.updateAd(map);

        return companyMapper.updatePremium(companysDTO);
    }

    /**
     * 업체 제휴협회 해지 서비스
     *
     * @param companysIdx
     * @return int
     */
    public int cancelPremium(long companysIdx) {

        Map<String, Object> map = new HashMap<>();
        map.put("sort", "premium");
        map.put("num", 0);
        map.put("companysIdx", companysIdx);

        companyMapper.updateAd(map);

        return companyMapper.cancelPremium(companysIdx);
    }

    /**
     * 업체 마패 신청 서비스
     *
     * @param companysDTO
     * @return int
     */
    public int requestFlag(CompanysDTO companysDTO) {

        int result = companyMapper.insertFlag(companysDTO);

        companyMapper.insertFlagRegion(companysDTO);

        String category[] = companysDTO.getCompanysFlagCategory().split(",");

        for (int i = 0; i < category.length; i++) {
            companysDTO.setCompanysFlagCategory(category[i]);
            companyMapper.insertFlagCategory(companysDTO);
        }

        return result;
    }

    /**
     * 업체 마패 등록 서비스
     *
     * @param companysDTO
     * @return int
     */
    public int approvalFlag(CompanysDTO companysDTO) {

        Map<String, Object> map = new HashMap<>();
        map.put("sort", "flag");
        map.put("num", 1);
        map.put("companysIdx", companysDTO.getCompanysIdx());

        companyMapper.updateAd(map);

        return companyMapper.updateFlag(companysDTO);
    }

    /**
     * 업체 마패 수정 서비스
     *
     * @param companysDTO
     * @return int
     */
    public int updateFlag(CompanysDTO companysDTO) {

        companyMapper.deleteFlagCategory(companysDTO.getCompanysFlagIdx());

        String category[] = companysDTO.getCompanysFlagCategory().split(",");

        for (int i = 0; i < category.length; i++) {
            companysDTO.setCompanysFlagCategory(category[i]);
            companyMapper.insertFlagCategory(companysDTO);
        }

        companyMapper.updateFlagPrice(companysDTO);

        return companyMapper.updateFlagRegion(companysDTO);
    }

    /**
     * 업체 마패 해지 서비스
     *
     * @param companysIdx
     * @return int
     */
    public int cancelFlag(long companysIdx) {

        Map<String, Object> map = new HashMap<>();
        map.put("sort", "flag");
        map.put("num", 0);
        map.put("companysIdx", companysIdx);

        companyMapper.updateAd(map);

        return companyMapper.cancelFlag(companysIdx);
    }

    /**
     * 제휴협회 목록 조회 서비스
     *
     * @param model
     * @param enabled
     * @param page
     * @return String
     */
    public String premiumList(Model model, String enabled, int page) {

        Map<String, Object> map = new HashMap<>();

        if (enabled != null) {
            if (!enabled.equals("")) {
                map.put("enabled", enabled);
            }
        }

        int count = companyMapper.countPremiumList(map);

        Pagination pagination = new Pagination(count, page);

        map.put("startIndex", pagination.getStartIndex());
        map.put("pageSize", pagination.getPageSize());

        List<CompanysDTO> list = companyMapper.selectPremiumList(map);

        model.addAttribute("premiumList", list);
        model.addAttribute("pagination", pagination);
        model.addAttribute("count", count);
        model.addAttribute("enabled", enabled);

        return "admin/company/premium";
    }

    /**
     * 마패 목록 조회 서비스
     *
     * @param model
     * @param enabled
     * @param page
     * @return String
     */
    public String flagList(Model model, String enabled, int page) {

        Map<String, Object> map = new HashMap<>();

        if (enabled != null) {
            if (!enabled.equals("")) {
                map.put("enabled", enabled);
            }
        }

        int count = companyMapper.countFlagList(map);

        Pagination pagination = new Pagination(count, page);

        map.put("startIndex", pagination.getStartIndex());
        map.put("pageSize", pagination.getPageSize());

        List<CompanysDTO> list = companyMapper.selectFlagList(map);

        model.addAttribute("flagList", list);
        model.addAttribute("pagination", pagination);
        model.addAttribute("count", count);
        model.addAttribute("enabled", enabled);

        return "admin/company/flag";
    }

    /**
     * 업체 리뷰 조회 서비스
     *
     * @param model
     * @param page
     * @return String
     */
    public String companyReview(Model model, long companysIdx, int page) {

        Map<String, Object> map = new HashMap<>();

        map.put("companysIdx", companysIdx);

        List<Integer> count = companyMapper.countReviewReport(map);

        Pagination pagination = new Pagination(count.get(0), page);

        map.put("startIndex", pagination.getStartIndex());
        map.put("pageSize", pagination.getPageSize());

        List<ReviewDTO> list = companyMapper.selectCompanysReview(map);

        // 평점 구하기
        BigDecimal total = new BigDecimal("0.00");
        BigDecimal resultScore = new BigDecimal("0.00");
        BigDecimal communicationScore = new BigDecimal("0.00");
        BigDecimal processScore = new BigDecimal("0.00");
        BigDecimal specialityScore = new BigDecimal("0.00");

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                total = total.add(list.get(i).getAverage());
                resultScore = resultScore.add(BigDecimal.valueOf(list.get(i).getResultScore()));
                communicationScore = communicationScore.add(BigDecimal.valueOf(list.get(i).getCommunicationScore()));
                processScore = processScore.add(BigDecimal.valueOf(list.get(i).getProcessScore()));
                specialityScore = specialityScore.add(BigDecimal.valueOf(list.get(i).getSpecialityScore()));
            }

            BigDecimal review = new BigDecimal(String.valueOf(count.get(0)));
            total = total.divide(review);
            resultScore = resultScore.divide(review);
            communicationScore = communicationScore.divide(review);
            processScore = processScore.divide(review);
            specialityScore = specialityScore.divide(review);

        }

        model.addAttribute("reviewList", list);
        model.addAttribute("pagination", pagination);
        model.addAttribute("count", count);
        model.addAttribute("companysIdx", companysIdx);
        model.addAttribute("total", total);
        model.addAttribute("resultScore", resultScore);
        model.addAttribute("communicationScore", communicationScore);
        model.addAttribute("processScore", processScore);
        model.addAttribute("specialityScore", specialityScore);

        return "admin/company/review";
    }

    /**
     * 업체 신고 조회 서비스
     *
     * @param model
     * @param companysIdx
     * @param state
     * @param page
     * @return String
     */
    public String companyReport(Model model, long companysIdx, String state, int page) {

        Map<String, Object> map = new HashMap<>();

        map.put("companysIdx", companysIdx);

        List<Integer> count = companyMapper.countReviewReport(map);

        if (state != null) {
            if (!state.equals("")) {
                map.put("state", state);
            }
        }

        int reportCount = companyMapper.countCompanysReport(map);

        Pagination pagination = new Pagination(reportCount, page);

        map.put("startIndex", pagination.getStartIndex());
        map.put("pageSize", pagination.getPageSize());

        List<ReportDTO> list = companyMapper.selectCompanysReport(map);

        model.addAttribute("reportList", list);
        model.addAttribute("pagination", pagination);
        model.addAttribute("count", count);
        model.addAttribute("reportCount", reportCount);
        model.addAttribute("companysIdx", companysIdx);
        model.addAttribute("state", state);

        return "admin/company/report";
    }

    /**
     * 통계 목록 조회 서비스
     *
     * @param model
     * @param sort
     * @param search
     * @param page
     * @return String
     */
    public String chartList(Model model, String sort, String search, int page) {

        Map<String, Object> map = new HashMap<>();

        if (!search.equals("")) {
            if (sort.equals("company")) {
                map.put("sort", sort);
            }
            map.put("search", search);
        }

        int count = companyMapper.countChartList(map);

        Pagination pagination = new Pagination(count, page);

        map.put("startIndex", pagination.getStartIndex());
        map.put("pageSize", pagination.getPageSize());

        List<ChartDTO> list = companyMapper.selectChartList(map);

        model.addAttribute("chartList", list);
        model.addAttribute("pagination", pagination);
        model.addAttribute("count", count);
        model.addAttribute("sort", sort);
        model.addAttribute("search", search);

        return "admin/board/chart/list";
    }

    /**
     * 전체 통계 조회 서비스
     *
     * @param model
     * @return String
     */
    public String wholeChart(Model model) {

        model.addAttribute("company", companyMapper.countCompany());

        return "admin/board/chart/whole";
    }

    /**
     * 통계 데이터 조회 서비스
     *
     * @return Map
     */
    public Map<String, Object> chartData(String sort, long companysIdx) {

        List<ChartDTO> list =  new ArrayList<>();
        List<ChartDataDTO> cateList = new ArrayList<>();

        if (sort.equals("whole")) {
            list = companyMapper.selectChart();
            cateList = companyMapper.selectCategoryChart();
        } else if (sort.equals("company")) {
            list = companyMapper.selectCompanyChart(companysIdx);
            cateList = companyMapper.selectCompanyCategoryChart(companysIdx);
        }

        int[] age = new int[8];
        int[] time = new int[12];
        int[] region = new int[11];
        int[] month = new int[12];
        List<String> category = new ArrayList<>();
        List<Integer> categoryNum = new ArrayList<>();

        SimpleDateFormat hour = new SimpleDateFormat("hh");
        SimpleDateFormat mFormat = new SimpleDateFormat("MM");

        for (int i = 0; i < list.size(); i++) {

            // 연령
            if (list.get(i).getUsersAge() == 10) {
                age[0] = age[0] + 1;
            } else if (list.get(i).getUsersAge() == 20) {
                age[1] = age[1] + 1;
            } else if (list.get(i).getUsersAge() == 30) {
                age[2] = age[2] + 1;
            } else if (list.get(i).getUsersAge() == 40) {
                age[3] = age[3] + 1;
            } else if (list.get(i).getUsersAge() == 50) {
                age[4] = age[4] + 1;
            } else if (list.get(i).getUsersAge() == 60) {
                age[5] = age[5] + 1;
            } else if (list.get(i).getUsersAge() == 70) {
                age[6] = age[6] + 1;
            } else if (list.get(i).getUsersAge() == 80) {
                age[7] = age[7] + 1;
            }

            // 시간
            String fTime = hour.format(list.get(i).getRequestFormDate());
            if (fTime.equals("07") || fTime.equals("08")) {
                time[0] = time[0] + 1;
            } else if (fTime.equals("09") || fTime.equals("10")) {
                time[1] = time[1] + 1;
            } else if (fTime.equals("11") || fTime.equals("12")) {
                time[2] = time[2] + 1;
            } else if (fTime.equals("13") || fTime.equals("14")) {
                time[3] = time[3] + 1;
            } else if (fTime.equals("15") || fTime.equals("16")) {
                time[4] = time[4] + 1;
            } else if (fTime.equals("17") || fTime.equals("18")) {
                time[5] = time[5] + 1;
            } else if (fTime.equals("19") || fTime.equals("20")) {
                time[6] = time[6] + 1;
            } else if (fTime.equals("21") || fTime.equals("22")) {
                time[7] = time[7] + 1;
            } else if (fTime.equals("23") || fTime.equals("00")) {
                time[8] = time[8] + 1;
            } else if (fTime.equals("01") || fTime.equals("02")) {
                time[9] = time[9] + 1;
            } else if (fTime.equals("03") || fTime.equals("04")) {
                time[10] = time[10] + 1;
            } else if (fTime.equals("05") || fTime.equals("06")) {
                time[11] = time[11] + 1;
            }

            // 지역
            if (list.get(i).getRequestFormRegion1().equals("서울")) {
                region[0] = region[0] + 1;
            } else if (list.get(i).getRequestFormRegion1().equals("경기")) {
                region[1] = region[1] + 1;
            } else if (list.get(i).getRequestFormRegion1().equals("대전/충남/세종")) {
                region[2] = region[2] + 1;
            } else if (list.get(i).getRequestFormRegion1().equals("인천/부천")) {
                region[3] = region[3] + 1;
            } else if (list.get(i).getRequestFormRegion1().equals("강원")) {
                region[4] = region[4] + 1;
            } else if (list.get(i).getRequestFormRegion1().equals("전주/전북")) {
                region[5] = region[5] + 1;
            } else if (list.get(i).getRequestFormRegion1().equals("청주/충북")) {
                region[6] = region[6] + 1;
            } else if (list.get(i).getRequestFormRegion1().equals("대구/경북")) {
                region[7] = region[7] + 1;
            } else if (list.get(i).getRequestFormRegion1().equals("부산/울산/경남")) {
                region[8] = region[8] + 1;
            } else if (list.get(i).getRequestFormRegion1().equals("광주/전남")) {
                region[9] = region[9] + 1;
            } else if (list.get(i).getRequestFormRegion1().equals("제주")) {
                region[10] = region[10] + 1;
            }

            // 월
            String fMonth = mFormat.format(list.get(i).getRequestFormDate());
            if (fMonth.equals("01")) {
                month[0] = month[0] + 1;
            } else if (fMonth.equals("02")) {
                month[1] = month[1] + 1;
            } else if (fMonth.equals("03")) {
                month[2] = month[2] + 1;
            } else if (fMonth.equals("04")) {
                month[3] = month[3] + 1;
            } else if (fMonth.equals("05")) {
                month[4] = month[4] + 1;
            } else if (fMonth.equals("06")) {
                month[5] = month[5] + 1;
            } else if (fMonth.equals("07")) {
                month[6] = month[6] + 1;
            } else if (fMonth.equals("08")) {
                month[7] = month[7] + 1;
            } else if (fMonth.equals("09")) {
                month[8] = month[8] + 1;
            } else if (fMonth.equals("10")) {
                month[9] = month[9] + 1;
            } else if (fMonth.equals("11")) {
                month[10] = month[10] + 1;
            } else if (fMonth.equals("12")) {
                month[11] = month[11] + 1;
            }

        }

        for (int i = 0; i < cateList.size(); i++) {
            category.add(cateList.get(i).getCategoryName());
            categoryNum.add(cateList.get(i).getNum());
        }

        Map<String, Object> map = new HashMap<>();
        map.put("size", list.size());
        map.put("age", age);
        map.put("time", time);
        map.put("region", region);
        map.put("month", month);
        map.put("category", category);
        map.put("categoryNum", categoryNum);

        return map;
    }

    /**
     * 업체 통계 조회 서비스
     *
     * @param model
     * @param companysIdx
     * @return String
     */
    public String chart(Model model, long companysIdx) {

        Map<String, Object> map = new HashMap<>();
        map.put("companysIdx", companysIdx);

        List<Integer> count = companyMapper.countReviewReport(map);

        model.addAttribute("count", count);
        model.addAttribute("companysIdx", companysIdx);

        return "admin/company/chart";
    }

}
