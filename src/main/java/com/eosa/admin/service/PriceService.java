package com.eosa.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.eosa.admin.dto.CategoryDTO;
import com.eosa.admin.dto.RegionDTO;
import com.eosa.admin.mapper.CategoryMapper;
import com.eosa.admin.mapper.RegionMapper;

@Service
public class PriceService {
    
    @Autowired private RegionMapper regionMapper;
    @Autowired private CategoryMapper categoryMapper;

    public String priceList(Model model) {
        List<RegionDTO> regionList = regionMapper.selectRegion();
        List<CategoryDTO> categoryList = categoryMapper.selectCategory();

        model.addAttribute("region", regionList);
        model.addAttribute("category", categoryList);

        return "admin/price/list";
    }

    public String priceUpdate(
        @RequestParam List<RegionDTO> region,
        @RequestParam List<CategoryDTO> category,
        Model model
    ) {
        List<RegionDTO> regionList = regionMapper.selectRegion();
        List<CategoryDTO> categoryList = categoryMapper.selectCategory();
        

        model.addAttribute("region", regionList);
        model.addAttribute("category", categoryList);
        return "admin/price/list";
    }

}
