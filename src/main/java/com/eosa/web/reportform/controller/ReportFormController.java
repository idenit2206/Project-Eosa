package com.eosa.web.reportform.controller;

import com.eosa.web.reportform.entity.ReportForm;
import com.eosa.web.reportform.service.ReportFormService;
import com.eosa.web.util.CustomResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reportForm")
public class ReportFormController {

    @Autowired private ReportFormService reportFormService;

    @PostMapping("/insertReportForm")
    public CustomResponseData insertReportForm(ReportForm reportForm) {
        CustomResponseData result = new CustomResponseData();
        ReportForm insertRow = reportFormService.save(reportForm);

        if(insertRow != null) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(insertRow);
            result.setResponseDateTime(LocalDateTime.now());
        }
        else {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(null);
            result.setResponseDateTime(LocalDateTime.now());
        }


        return result;
    }

    @GetMapping("/selectAllReprotForm")
    public CustomResponseData selectAllReportForm() {
        CustomResponseData result = new CustomResponseData();

        List<ReportForm> items = reportFormService.findAll();

        if(items != null) {
            result.setStatusCode(HttpStatus.OK.value());
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

}
