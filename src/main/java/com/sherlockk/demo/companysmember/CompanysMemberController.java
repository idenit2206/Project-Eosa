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
import com.sherlockk.demo.companys.CompanysService;
import com.sherlockk.demo.users.UsersService;

@RestController
@RequestMapping("/api/companysmember")
public class CompanysMemberController {

    @Autowired
    private CompanysMemberService companysMemberService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private CompanysService companysService;

    @PostMapping("/registdetective")
    public CustomResponseData RegistDetective(
        CompanysMember param
    ) {
        CustomResponseData result = new CustomResponseData();

        int httpCode = HttpStatus.OK.value();
        Map<String, String> items = new HashMap<>();
        LocalDateTime currentTime = LocalDateTime.now();

        Long usersIdx = param.getUsersIdx();
        Long companysIdx = param.getCompanysIdx();

        String errMsg1 = "";
        String errMsg2 = "";

        int valdiateUsersIdx = usersService.findDetectiveByUsersIdx(usersIdx);
        int valdiateCompanysIdx = companysService.findByCompanysIdx(companysIdx);

        System.out.println("###: " + valdiateUsersIdx + " : " + valdiateCompanysIdx);

        // if(valdiateUsersIdx != 1) {
        //     errMsg1 = usersIdx + " 는 존재하지 않는 회원입니다.";
        // }
        
        // if(valdiateCompanysIdx != 1) {
        //     errMsg2 = companysIdx + " 는 존재하지 않는 회사입니다.";
        // }

        // Long transaction = companysMemberService.save(param).getIdx();

        // if(transaction == null) {
        //     items.put("error01", errMsg1);
        //     items.put("error02", errMsg2);
        // }
        // else {
        //     items.put("result", Long.toString(transaction));
        // }

        result.setStatusCode(httpCode);
        result.setResultItem(items);
        result.setResponseDateTime(currentTime);

        return result;
    }
    
}
