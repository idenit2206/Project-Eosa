package com.eosa.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.eosa.admin.dto.CategoryDTO;
import com.eosa.admin.dto.PriceDTO;
import com.eosa.admin.dto.RegionDTO;
import com.eosa.admin.mapper.CategoryMapper;
import com.eosa.admin.mapper.PriceMapper;
import com.eosa.admin.mapper.RegionMapper;
import com.eosa.web.util.file.AwsS3Service;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PriceService {
    
    @Autowired private PriceMapper priceMapper;
    @Autowired private RegionMapper regionMapper;
    @Autowired private CategoryMapper categoryMapper;
    @Autowired private AwsS3Service awsS3Service;

    
    /** 
     * @param model
     * @return String
     */
    public String priceList(Model model) {
        PriceDTO price = priceMapper.selectPrice();
        List<RegionDTO> regionList = regionMapper.selectRegion();
        List<CategoryDTO> categoryList = categoryMapper.selectCategory();
       
        model.addAttribute("price", price);
        model.addAttribute("region", regionList);
        model.addAttribute("category", categoryList);

        return "admin/price/list";
    }

    
    /** 
     * @return Map<String, Object>
     */
    public Map<String, Object> selectPrice() {
        Map<String, Object> result = new HashMap<>();

        PriceDTO price = priceMapper.selectPrice();
        List<RegionDTO> regionList = regionMapper.selectRegion();
        List<CategoryDTO> categoryList = categoryMapper.selectCategory();

        result.put("price", price);
        result.put("region", regionList);
        result.put("category", categoryList);

        return result;
    }

    
    /** 
     * 지역&분야별 가격 관리 페이지에서 은행 정보를 수정하는 서비스
     * @param priceDTO
     * @param model
     * @return String
     */
    public String updatePrice(PriceDTO priceDTO, Model model) {
        // log.debug("[updatePrice] priceDTO: {}", priceDTO.toString());
        int updateRow = priceMapper.updatePrice(priceDTO);
        log.debug("{}",updateRow);

        PriceDTO price = priceMapper.selectPrice();
        List<RegionDTO> regionList = regionMapper.selectRegion();
        List<CategoryDTO> categoryList = categoryMapper.selectCategory();

        model.addAttribute("price", price);
        model.addAttribute("region", regionList);
        model.addAttribute("category", categoryList);
        return "admin/price/list";
    }

    
    /** 
     * 지역&분야별 가격 관리 페이지에서 지역 정보를 수정하는 서비스
     * @param region
     * @param model
     * @return String
     */
    public String priceUpdateRegion(
        String region,
        // @RequestParam List<CategoryDTO> category,
        Model model
    ) {
        // log.debug("[priceUpdateRegion] param String: {}",region.toString());
        JsonParser parser = new JsonParser();
        JsonArray regionObject = (JsonArray) parser.parse(region);  

        for(int i = 0; i < regionObject.size(); i++) {
            // log.debug(regionObject.get(i).toString());
            JsonElement el = regionObject.get(i);
            log.info("regionPrice Update value] : {}", el.getAsJsonObject().toString());
            Long regionIdx = el.getAsJsonObject().get("regionIdx").getAsLong();
            String regionName = el.getAsJsonObject().get("regionName").getAsString();
            int regionPrice = el.getAsJsonObject().get("regionPrice").getAsInt();
            int regionSelectable = el.getAsJsonObject().get("regionSelectable").getAsInt();
            RegionDTO regionDTO = new RegionDTO(regionIdx, regionName, regionPrice, regionSelectable);
            // log.info("[updateRegionPrice] regionDTO: {}", regionDTO.toString());
            regionMapper.priceUpdateRegion(regionDTO);
        }
        
        PriceDTO price = priceMapper.selectPrice();
        List<RegionDTO> regionList = regionMapper.selectRegion();
        List<CategoryDTO> categoryList = categoryMapper.selectCategory();
        model.addAttribute("price", price);
        model.addAttribute("region", regionList);
        model.addAttribute("category", categoryList);
        return "admin/price/list";
    }

    
    /** 
     * 지역&분야별 가격 관리 페이지에서 업무분야를 수정하는 서비스
     * @param category
     * @param categoryIconList
     * @param model
     * @return String
     */
    public String priceUpdateCategory(
        String category,
        List<MultipartFile> categoryIconList,
        Model model
    ) {
        log.info("[priceUpdateCategory] param String: {}", category);
        JsonParser parser2 = new JsonParser();
        JsonArray categoryObject = (JsonArray) parser2.parse(category);
        if(categoryObject != null) {
            for(int i = 0; i < categoryObject.size(); i++) {
                CategoryDTO insertCategory = new CategoryDTO();
                // log.info("categoryIdx: {}", categoryObject.get(i).getAsJsonObject().get("categoryIdx").getAsString());
                // log.info("categoryName: {}", categoryObject.get(i).getAsJsonObject().get("categoryName").getAsString());
                insertCategory.setCategoryIdx(categoryObject.get(i).getAsJsonObject().get("categoryIdx").getAsLong());
                insertCategory.setCategoryName(categoryObject.get(i).getAsJsonObject().get("categoryName").getAsString());
                if(categoryObject.get(i).getAsJsonObject().get("categoryPrice").getAsString().equals("")) {
                    // log.info("categoryPrice: {}", 0);
                    insertCategory.setCategoryPrice(0);
                }
                else {
                    insertCategory.setCategoryPrice(categoryObject.get(i).getAsJsonObject().get("categoryPrice").getAsInt());
                }
                if(categoryObject.get(i).getAsJsonObject().get("categoryIconName").equals("")) {
                    insertCategory.setCategoryIconName("");
                }
                else {
                    if(categoryIconList != null) {
                        for(int j = 0; j < categoryIconList.size(); j++) {
                            String fileNameOriginal = categoryIconList.get(j).getOriginalFilename();
                            if(fileNameOriginal.equals(categoryObject.get(i).getAsJsonObject().get("categoryIconOriginalName").getAsString())) {
                                List<String> file = awsS3Service.uploadSingleFile(categoryIconList.get(j), "categoryicon", Long.valueOf(j));
                                // String categoryIconName = file.get(0);
                                // String categoryIcon = file.get(1);
                                insertCategory.setCategoryIconName(file.get(0));
                                insertCategory.setCategoryIcon(file.get(1));                                
                                
                                log.info("신규파일이 포함된 DTO: {}", insertCategory.toString());
                                categoryMapper.priceUpdateCategory(insertCategory);
                            }
                            
                        }
                    }
                    else {
                        insertCategory.setCategoryIcon(categoryObject.get(i).getAsJsonObject().get("categoryIcon").getAsString());
                        log.info("신규파일이 없는 DTO: {}", insertCategory.toString());
                        categoryMapper.priceUpdateCategory(insertCategory);
                    }                    
                }
                
                
                // log.info("test: {}", insertCategory.toString());
            }
        }
            
        

        PriceDTO price = priceMapper.selectPrice();
        List<RegionDTO> regionList = regionMapper.selectRegion();
        List<CategoryDTO> categoryList = categoryMapper.selectCategory();
        model.addAttribute("price", price);
        model.addAttribute("region", regionList);
        model.addAttribute("category", categoryList);
        return "admin/price/list";
    }

    
    /** 
     * 지역&분야별 가격 관리 페이지에서 지역을 잠금처리하는 서비스
     * @param regionIdx
     * @param model
     * @return String
     */
    public String lockRegion(List<Long> regionIdx, Model model) {
        for(int i = 0; i < regionIdx.size(); i++) {
            regionMapper.lockRegion(Long.valueOf(regionIdx.get(i)));
        }
        
        PriceDTO price = priceMapper.selectPrice();
        List<RegionDTO> regionList = regionMapper.selectRegion();
        List<CategoryDTO> categoryList = categoryMapper.selectCategory();
        model.addAttribute("price", price);
        model.addAttribute("region", regionList);
        model.addAttribute("category", categoryList);
        return "admin/price/list";
    }

    
    /** 
     * @param regionIdx
     * @param model
     * @return String
     */
    public String unlockRegion(List<Long> regionIdx, Model model) {
        for(int i = 0; i < regionIdx.size(); i++) {
            regionMapper.unlockRegion(Long.valueOf(regionIdx.get(i)));
        }
        
        PriceDTO price = priceMapper.selectPrice();
        List<RegionDTO> regionList = regionMapper.selectRegion();
        List<CategoryDTO> categoryList = categoryMapper.selectCategory();
        model.addAttribute("price", price);
        model.addAttribute("region", regionList);
        model.addAttribute("category", categoryList);
        return "admin/price/list";
    }

    
    /** 
     * @param regionIdx
     * @param model
     * @return String
     */
    public String deleteRegion(List<Long> regionIdx, Model model) {
        log.debug("[deleteRegion]: {}",regionIdx.toString());
        for(int i = 0; i < regionIdx.size(); i++) {
            regionMapper.deleteRegion(Long.valueOf(regionIdx.get(i)));
        }
        
        PriceDTO price = priceMapper.selectPrice();
        List<RegionDTO> regionList = regionMapper.selectRegion();
        List<CategoryDTO> categoryList = categoryMapper.selectCategory();
        model.addAttribute("price", price);
        model.addAttribute("region", regionList);
        model.addAttribute("category", categoryList);
        return "admin/price/list";
    }

    
    /** 
     * @param categoryIdx
     * @param model
     * @return String
     */
    public String deleteCategory(List<Long> categoryIdx, Model model) {
        log.debug("[deleteRegion]: {}", categoryIdx.toString());
        for(int i = 0; i < categoryIdx.size(); i++) {
            categoryMapper.deleteCategory(Long.valueOf(categoryIdx.get(i)));
        }

        PriceDTO price = priceMapper.selectPrice();
        List<RegionDTO> regionList = regionMapper.selectRegion();
        List<CategoryDTO> categoryList = categoryMapper.selectCategory();
        model.addAttribute("price", price);
        model.addAttribute("region", regionList);
        model.addAttribute("category", categoryList);
        return "admin/price/list";
    }

}
