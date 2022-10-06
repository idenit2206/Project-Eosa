package com.eosa.web.policy;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eosa.admin.dto.PolicyDTO;
import com.eosa.admin.service.PolicyService;
import com.eosa.web.util.CustomResponseData;

@RestController
@RequestMapping("/api/policy")
public class PolicyServiceController {

    @Autowired private PolicyService policyService;
    
    
    /** 
     * @return CustomResponseData
     */
    @GetMapping("/selectPolicy")
    public CustomResponseData selectPolicyDTO() {
        CustomResponseData result = new CustomResponseData();

        List<PolicyDTO> items = policyService.selecPolicyDTO();

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
