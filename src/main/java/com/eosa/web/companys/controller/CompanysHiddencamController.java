package com.eosa.web.companys.controller;

import com.eosa.web.companys.entity.CompanysHiddencam;
import com.eosa.web.companys.service.CompanysHiddencamService;
import com.eosa.web.util.CustomResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/companysHiddenCam")
public class CompanysHiddencamController {

    @Autowired CompanysHiddencamService companysHiddencamService;

    @PostMapping("/insertCompanysHiddenCamRequest")
    public CustomResponseData insertCompanysHiddenCamRequest(CompanysHiddencam companysHiddencam) {
        CustomResponseData result = new CustomResponseData();

        CompanysHiddencam insertRows = companysHiddencamService.save(companysHiddencam);

        if(insertRows != null) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(insertRows);
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
