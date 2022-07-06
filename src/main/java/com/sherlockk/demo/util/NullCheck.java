package com.sherlockk.demo.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NullCheck {

    private Logger logger = LoggerFactory.getLogger(NullCheck.class);

    // private String input01;
    
    // public String getInput01() {
    //     return input01;
    // }
    // public void setInput01(String input01) {
    //     this.input01 = input01;
    // }

    public NullCheck() {}

    // public NullCheck(String input01) {
    //     this.input01 = input01;
    // }

    public boolean StringNullCheck(String inputString) {
        boolean result = false;
        if(inputString.isBlank()) {
            result = true;
        }
        else {
            result = false;
        }
        return result;
    }

    public Map<String, Object> ObjectNullCheck(Object object, String[] targets) {
        Map<String, Object> result = new HashMap<>();
        // boolean result = false;
        try {
            List<String> temp = new LinkedList<>();
            for(Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                for(String s : targets) {
                    if(s.equals(field.getName())) {
                        Object value = field.get(object);
                        if(value == null) {
                            logger.info("{} value is NULL", field.getName());
                            temp.add(field.getName());                            
                        }                        
                    }
                }                
            }
            if(temp.isEmpty()) {
                result.put("result", "SUCCESS");
                result.put("message", "Valdiate Complete!! Null does not exist");
            }
            else {
                result.put("result", "FAILURE");
                result.put("message", "Null Column(s)");
                result.put("item", temp);         
            }
        }
        catch(Exception e) {
            logger.error("[ERROR] {}", e);
        }

        return result;
    }

}
