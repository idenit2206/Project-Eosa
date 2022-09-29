package com.eosa.admin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eosa.admin.dto.PriceDTO;
import com.eosa.admin.service.PriceService;


@Controller
@RequestMapping("/admin/manage/price")
public class PriceController {

    @Autowired private PriceService priceService;
    // private JsonParser jsonParser;

    @GetMapping("/list")
    public String priceList(Model model) {
        return priceService.priceList(model);
    }

    @GetMapping("/selectPrice")
    @ResponseBody
    public Map<String, Object> selectPrice() {
        return priceService.selectPrice();
    }

    @PostMapping("/updatePrice")
    public String updatePrice(
        PriceDTO priceDTO,
        Model model
    ) {
        return priceService.updatePrice(priceDTO, model);
    }

    @PostMapping("/updateRegion")
    public String priceUpdateRegion(
        // @RequestParam(name="region", required = false) String region,
        @RequestPart String region,
        Model model
    ) {
        return priceService.priceUpdateRegion(region, model);
    }

    @PostMapping("/updateCategory")
    public String priceUpdateCategory(
        @RequestPart String category,
        Model model
    ) {
        return priceService.priceUpdateCategory(category, model);
    }

    @DeleteMapping("/deleteRegion")
    public String deleteRegion(
        @RequestParam List<Long> regionIdxList,
        Model model
    ) {
        return priceService.deleteRegion(regionIdxList, model);
    }

    @DeleteMapping("/deleteCategory")
    public String deleteCategory(
        @RequestParam List<Long> categoryIdxList,
        Model model
    ) {
        return priceService.deleteCategory(categoryIdxList, model);
    }
    
}
