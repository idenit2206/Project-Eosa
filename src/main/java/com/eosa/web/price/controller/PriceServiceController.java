package com.eosa.web.price.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eosa.web.price.entity.Category;
import com.eosa.web.price.entity.Price;
import com.eosa.web.price.entity.Region;
import com.eosa.web.price.service.CategoryService;
import com.eosa.web.price.service.PriceServiceService;
import com.eosa.web.price.service.RegionService;
import com.eosa.web.util.CustomResponseData;

@RestController
@RequestMapping("/api/price")
public class PriceServiceController {

    @Autowired private PriceServiceService priceServiceService;
    @Autowired private CategoryService categoryService;
    @Autowired private RegionService regionService;
    
    @GetMapping("/selectBankInfo")
    public CustomResponseData selectBankInfo() {
        CustomResponseData result = new CustomResponseData();

        Price item = priceServiceService.findPrice();
        if(item != null) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(item);
            result.setResponseDateTime(LocalDateTime.now());
        }
        else {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(null);
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }
    
    /** 
     * @return CustomResponseData
     */
    @GetMapping("/selectCategoryRegion")
    public CustomResponseData selectCategoryRegion() {
        CustomResponseData result = new CustomResponseData();

        List<Category> categories = categoryService.findAll();
        List<Region> regiones = regionService.findAll();

        if(categories != null && regiones != null) {
            Map<String, Object> items = new HashMap<>();
            result.setStatusCode(HttpStatus.OK.value());
            items.put("category", categories);
            items.put("region", regiones);
            result.setResultItem(items);
            result.setResponseDateTime(LocalDateTime.now());
        }
        else {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(null);
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

    
    /** 
     * @return CustomResponseData
     */
    @GetMapping("/selectCategory")
    public CustomResponseData selectCategory() {
        CustomResponseData result = new CustomResponseData();
        
        List<Category> categories = categoryService.findAllASC();

        if(categories != null) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(categories);
            result.setResponseDateTime(LocalDateTime.now());
        }
        else {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(null);
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

}
