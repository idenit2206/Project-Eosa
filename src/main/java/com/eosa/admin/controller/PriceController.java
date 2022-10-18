package com.eosa.admin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.eosa.admin.dto.PriceDTO;
import com.eosa.admin.service.PriceService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin/manage/price")
public class PriceController {

    @Autowired private PriceService priceService;
    
    /** 
     * @param model
     * @return String
     */
    // private JsonParser jsonParser;

    @GetMapping("/list")
    public String priceList(Model model) {
        return priceService.priceList(model);
    }

    
    /** 
     * @return Map<String, Object>
     */
    @GetMapping("/selectPrice")
    @ResponseBody
    public Map<String, Object> selectPrice() {
        return priceService.selectPrice();
    }

    
    /** 
     * 지역&분야별 가격 관리 페이지에서 은행 정보를 수정하는 컨트롤러
     * @param priceDTO
     * @param model
     * @return String
     */
    @PostMapping("/updatePrice")
    public String updatePrice(
        PriceDTO priceDTO,
        Model model
    ) {
        return priceService.updatePrice(priceDTO, model);
    }

    
    /** 
     * @param model
     * @return String
     */
    @PostMapping("/updateRegion")
    public String priceUpdateRegion(
        // @RequestParam(name="region", required = false) String region,
        @RequestPart String region,
        Model model
    ) {
        return priceService.priceUpdateRegion(region, model);
    }

    
    /** 
     * @param model
     * @return String
     */
    @PutMapping("/lockRegion")
    public String lockRegion(
        @RequestParam List<Long> regionIdxList,
        Model model
    ) {
        return priceService.lockRegion(regionIdxList, model);
    }

    
    /** 
     * @param model
     * @return String
     */
    @PutMapping("/unlockRegion")
    public String unlockRegion(
        @RequestParam List<Long> regionIdxList,
        Model model
    ) {
        return priceService.unlockRegion(regionIdxList, model);
    }

    
    /** 
     * @param model
     * @return String
     */
    @DeleteMapping("/deleteRegion")
    public String deleteRegion(
        @RequestParam List<Long> regionIdxList,
        Model model
    ) {
        return priceService.deleteRegion(regionIdxList, model);
    }

    
    
    /** 
     * 지역&분야별 가격 관리 페이지에서 업무분야를 수정하는 컨트롤러
     * @param model
     * @return String
     */
    @PostMapping("/updateCategory")
    public String priceUpdateCategory(
        @RequestPart String category,
        @RequestParam(name="categoryIcon", required = false) List<MultipartFile> categoryIcon,
        Model model
    ) {
        // if(categoryIcon != null) log.info("[priceUpdateCategory] {}", categoryIcon.get(0).getOriginalFilename());
        return priceService.priceUpdateCategory(category, categoryIcon, model);
    }

    
    /** 
     * @param model
     * @return String
     */
    @DeleteMapping("/deleteCategory")
    public String deleteCategory(
        @RequestParam List<Long> categoryIdxList,
        Model model
    ) {
        return priceService.deleteCategory(categoryIdxList, model);
    }
    
}
