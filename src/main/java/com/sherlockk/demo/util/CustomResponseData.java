package com.sherlockk.demo.util;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class CustomResponseData {
    
    private int statusCode;
    private Object resultItem;
    private LocalDateTime responseDateTime;

    public CustomResponseData() {}

    public CustomResponseData(int statusCode, Object resultItem, LocalDateTime responseDateTime) {
        this.statusCode = statusCode;
        this.resultItem = resultItem;
        this.responseDateTime = responseDateTime;
    }

}
