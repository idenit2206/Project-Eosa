package com.eosa.web.companys;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eosa.web.companys.entity.Companys;
import com.eosa.web.companys.entity.CompanysActiveRegion;
import com.eosa.web.companys.entity.CompanysCategory;
import com.eosa.web.companys.entity.SelectAllCompanysList;
import com.eosa.web.util.CustomResponseData;
import com.eosa.web.util.NullCheck;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nimbusds.oauth2.sdk.ParseException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value="/api/companys")
public class CompanysController {

    private Logger logger = LoggerFactory.getLogger(CompanysController.class);

    @Autowired
    private CompanysService companysService;

    @PostMapping("/insertCompanys")
    public CustomResponseData insertCompanys(
      @RequestBody String param
    ) throws JSONException, ParseException {
      JsonObject jsonObject = (JsonObject) JsonParser.parseString(param).getAsJsonObject();
      log.debug("param: {}", jsonObject.toString());
      
      Companys entity = new Companys();
        entity.setCompanysCeoIdx(jsonObject.get("companysCeoIdx").getAsLong());
        entity.setCompanysCeoName(jsonObject.get("companysCeoName").getAsString());
        entity.setCompanysName(jsonObject.get("companysName").getAsString());
        entity.setCompanysComment(jsonObject.get("companysComment").getAsString());
        entity.setCompanysSpec(jsonObject.get("companysSpec").getAsString());
        entity.setCompanysRegion1(jsonObject.get("companysRegion1").getAsString());
        entity.setCompanysRegion2(jsonObject.get("companysRegion2").getAsString());
        entity.setCompanysRegion3(jsonObject.get("companysRegion3").getAsString());
        entity.setCompanysRegistCerti(jsonObject.get("companysRegistCerti").getAsString());
        entity.setCompanysProfileImage(jsonObject.get("companysProfileImage").getAsString());
        entity.setCompanysBankName(jsonObject.get("companysBankName").getAsString());
        entity.setCompanysBankNumber(jsonObject.get("companysBankNumber").getAsString());
      Companys step1 = companysService.save(entity);

      CompanysCategory entity2 = new CompanysCategory();

      CompanysActiveRegion entity3 = new CompanysActiveRegion();
      
        CustomResponseData result = new CustomResponseData();
      return result;
    }
    
    @GetMapping("/selectAllCategory")
    public CustomResponseData selectAllCategory() {
      CustomResponseData result = new CustomResponseData();

      List<String> items = companysService.selectAllCategory();

      return result;
    }

    @GetMapping("/selectAllCompanys")
    public CustomResponseData selectAllCompanys() {
      CustomResponseData result = new CustomResponseData();
      List<SelectAllCompanysList> list = companysService.selectAllCompanysList();

      result.setStatusCode(HttpStatus.OK.value());
      result.setResultItem(list);
      result.setResponseDateTime(LocalDateTime.now());
      return result;
    }

}
