package com.sherlockk.demo.companysmember;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sherlockk.demo.util.CustomResponseData;

@RestController
@RequestMapping("/api/companysmember")
public class CompanysMemberController {

    @Autowired
    private CompanysMemberService companysMemberService;

    /**
     * @param CompanysMember 업체에 등록할 회원의 색인번호화 참가할 업체의 색인번호로 구성
     */
    @PostMapping("/registdetective")
    public CustomResponseData RegistDetective(
        CompanysMember param
    ) {
        CustomResponseData result = new CustomResponseData();

        int httpCode = HttpStatus.OK.value();
        Map<String, String> items = new HashMap<>();
        LocalDateTime currentTime = LocalDateTime.now();

        // System.out.println("[log] param: " + param.toString());
        int transaction = companysMemberService.customValdSave(param);
       
        items.put("result", Integer.toString(transaction));

        result.setStatusCode(httpCode);
        result.setResultItem(items);
        result.setResponseDateTime(currentTime);

        return result;
    }
    
}
