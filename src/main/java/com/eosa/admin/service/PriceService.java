package com.eosa.admin.service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
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
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PriceService {
    
    @Autowired private PriceMapper priceMapper;
    @Autowired private RegionMapper regionMapper;
    @Autowired private CategoryMapper categoryMapper;
    @Autowired private AwsS3Service awsS3Service;

    public String priceList(Model model) {
        PriceDTO price = priceMapper.selectPrice();
        List<RegionDTO> regionList = regionMapper.selectRegion();
        List<CategoryDTO> categoryList = categoryMapper.selectCategory();
       
        model.addAttribute("price", price);
        model.addAttribute("region", regionList);
        model.addAttribute("category", categoryList);

        return "admin/price/list";
    }

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
            Long regionIdx = el.getAsJsonObject().get("regionIdx").getAsLong();
            String regionName = el.getAsJsonObject().get("regionName").getAsString();
            int regionPrice = el.getAsJsonObject().get("regionPrice").getAsInt();
            int regionSelectable = el.getAsJsonObject().get("regionSelectable").getAsInt();
            RegionDTO regionDTO = new RegionDTO(regionIdx, regionName, regionPrice, regionSelectable);
            log.debug("[updateRegionPrice] regionDTO: {}", regionDTO.toString());
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

    public String priceUpdateCategory(
        String category,
        List<MultipartFile> categoryIconList,
        Model model
    ) {
        log.debug("[priceUpdateCategory] param String: {}", category.toString());
        JsonParser parser2 = new JsonParser();
        JsonArray categoryObject = (JsonArray) parser2.parse(category);

        categoryMapper.truncateCategory();

        for(int i = 0; i < categoryObject.size(); i++) {
            log.info(categoryObject.get(i).toString());
            JsonElement el = categoryObject.get(i);
            if(el.getAsJsonObject().get("categoryIcon").getAsString().equals("")) {
                for(int j = 0; j < categoryIconList.size(); j++) {
                    List<String> file = awsS3Service.uploadSingleFile(categoryIconList.get(j), "categoryicon", Long.valueOf(j));
                    String categoryIconName = file.get(0);
                    String categoryIcon = file.get(1);

                    Long categoryIdx = el.getAsJsonObject().get("categoryIdx").getAsLong();
                    String categoryName = el.getAsJsonObject().get("categoryName").getAsString();
                    int categoryPrice = el.getAsJsonObject().get("categoryPrice").getAsInt();
                    
                    CategoryDTO categoryDTO = new CategoryDTO(categoryIdx, categoryName, categoryPrice, categoryIcon, categoryIconName);
                    categoryMapper.priceUpdateCategory(categoryDTO);
                }
            }
            else {
                Long categoryIdx = el.getAsJsonObject().get("categoryIdx").getAsLong();
                String categoryName = el.getAsJsonObject().get("categoryName").getAsString();
                int categoryPrice = el.getAsJsonObject().get("categoryPrice").getAsInt();
                String categoryIcon = el.getAsJsonObject().get("categoryIcon").getAsString();
                String categoryIconName = el.getAsJsonObject().get("categoryIconName").getAsString();

                CategoryDTO categoryDTO = new CategoryDTO(categoryIdx, categoryName, categoryPrice, categoryIcon, categoryIconName);
                categoryMapper.priceUpdateCategory(categoryDTO);
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
