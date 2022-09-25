package com.eosa.admin.controller;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

import com.eosa.admin.dto.CategoryDTO;
import com.eosa.admin.dto.RegionDTO;
import com.eosa.admin.service.PriceService;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nimbusds.jose.shaded.json.JSONObject;

import io.swagger.v3.oas.annotations.parameters.RequestBody;


@lombok.extern.slf4j.Slf4j
@Controller
@RequestMapping("/admin/manage/price")
public class PriceController {

    @Autowired private PriceService priceService;
    // private JsonParser jsonParser;

    @GetMapping("/list")
    public String priceList(Model model) {

        return priceService.priceList(model);

    }

    @PostMapping("/updateRegion")
    public String priceUpdateRegion(
        // @RequestParam(name="region", required = false) String region,
        @RequestPart String region,
        Model model
    ) {        
        log.debug("[priceUpdateRegion] param String: {}",region.toString());
        JsonParser parser = new JsonParser();
        JsonArray regionObject = (JsonArray) parser.parse(region);
        log.debug("[priceUpdateRegion] {}", regionObject.get(0));
       
        // JsonObject regionObject =  (JsonObject) jsonParser.parse(region);
        // for(int i = 0; i < region.size(); i++) {
        //     log.debug(region.get(i).toString());
        // }
      
        // for(int i = 0; i < region.size(); i++) {
        //    log.debug("region[{}]: {}", i, region.get(i).toString());
        //     // log.debug(jsonParser.parse(region.get(i)).toString());
        // }
        
        // log.debug(regionElement.getAsJsonObject().get("region").toString());
        
        
        return "admin/price/list";
        // return priceService.priceUpdate(region, category, model);

    }

    @PostMapping("/updateCategory")
    public String priceUpdateCategory(
        @RequestParam(name="category", required = false) List<String> category,
        Model model
    ) {
        log.debug(category.toString());

        return "admin/price/list";
    }
    
}
