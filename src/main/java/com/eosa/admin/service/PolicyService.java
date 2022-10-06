package com.eosa.admin.service;

import com.eosa.admin.dto.PolicyDTO;
import com.eosa.admin.mapper.PolicyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

/**
 * packageName    : com.eosa.admin.service
 * fileName       : PolicyService
 * author         : Jihun Kim
 * date           : 2022-09-20
 * description    : 정책 서비스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-20        Jihun Kim       최초 생성
 */
@Service
public class PolicyService {

    @Autowired
    private PolicyMapper policyMapper;

    /**
     * 정책 조회 서비스
     *
     * @param model
     * @return String
     */
    public String policy(Model model) {

        List<PolicyDTO> policy = policyMapper.selectPolicy();
        model.addAttribute("policy", policy);

        return "admin/policy/details";
    }

    /**
     * 정책 수정 서비스
     *
     * @param policyDTO
     * @return int
     */
    public int updatePolicy(PolicyDTO policyDTO) {

        return policyMapper.updatePolicy(policyDTO);
    }


    
    /** 
     * @return List<PolicyDTO>
     */
    public List<PolicyDTO> selecPolicyDTO() {
        return policyMapper.selectPolicy();
    }

}
