package com.eosa.admin.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.eosa.admin.dto.CategoryDTO;
import com.eosa.admin.dto.PriceDTO;
import com.eosa.admin.dto.RegionDTO;
import com.eosa.admin.mapper.CategoryMapper;
import com.eosa.admin.mapper.PriceMapper;
import com.eosa.admin.mapper.RegionMapper;
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

    public String priceList(Model model) {
        PriceDTO price = priceMapper.selectPrice();
        List<RegionDTO> regionList = regionMapper.selectRegion();
        List<CategoryDTO> categoryList = categoryMapper.selectCategory();
       
        model.addAttribute("price", price);
        model.addAttribute("region", regionList);
        model.addAttribute("category", categoryList);

        return "admin/price/list";
    }

    public String updatePrice(PriceDTO priceDTO, Model model) {
        log.debug("[updatePrice] priceDTO: {}", priceDTO.toString());
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
            RegionDTO regionDTO = new RegionDTO(regionIdx, regionName, regionPrice);
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
        Model model
    ) {
        log.debug("[priceUpdateCategory] param String: {}", category.toString());
        JsonParser parser2 = new JsonParser();
        JsonArray categoryObject = (JsonArray) parser2.parse(category);

        for(int i = 0; i < categoryObject.size(); i++) {
            // log.debug(categoryObject.get(i).toString());
            JsonElement el = categoryObject.get(i);
            Long categoryIdx = el.getAsJsonObject().get("categoryIdx").getAsLong();
            String categoryName = el.getAsJsonObject().get("categoryName").getAsString();
            int categoryPrice = el.getAsJsonObject().get("categoryPrice").getAsInt();
            
            CategoryDTO categoryDTO = new CategoryDTO(categoryIdx, categoryName, categoryPrice);
            categoryMapper.priceUpdateCategory(categoryDTO);
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
