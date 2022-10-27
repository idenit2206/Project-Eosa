package com.eosa.admin.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminExceptionHandlerControllerAdvice implements ErrorController {
        
    
    @GetMapping("/error")
	public String handle404(HttpServletRequest req) {
        Object status = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if(status != null) {
            return "admin/error/404";
        }
        else {
            return "admin/error/404";
        }	    
	}

}
