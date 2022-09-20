package com.eosa.admin.controller;

import com.eosa.admin.dto.PolicyDTO;
import com.eosa.admin.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * packageName    : com.eosa.admin.controller
 * fileName       : PolicyController
 * author         : Jihun Kim
 * date           : 2022-09-20
 * description    : 정책 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-20        Jihun Kim       최초 생성
 */
@Controller
@RequestMapping("/admin/manage/policy")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    /**
     * 정책 조회 컨트롤러
     *
     * @param model
     * @return String
     */
    @GetMapping({"", "/"})
    public String policy(Model model) {

        return policyService.policy(model);
    }

    /**
     * 정책 수정 컨트롤러
     *
     * @param policyDTO
     * @return int
     */
    @ResponseBody
    @PostMapping("/update")
    public int updatePolicy(PolicyDTO policyDTO) {

        return policyService.updatePolicy(policyDTO);
    }

}
