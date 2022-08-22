package com.eosa.admin.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eosa.admin.requestmanage.RequestFormManageRepository;
import com.eosa.web.requestform.RequestForm;
import com.eosa.web.util.CustomResponseData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin/test")
public class TestController {

    @Autowired private RequestFormManageRepository requestFormManageRepository;
    
    @GetMapping("/testRequestFormByLocation")
    @ResponseBody
    public CustomResponseData testRequestFormByLocation(
        @RequestParam("keyword") String keyword
    ) {
        CustomResponseData result = new CustomResponseData();

        List<RequestForm> list = requestFormManageRepository.testRequestFormByLocation(keyword);

        return result;
    } 

}
