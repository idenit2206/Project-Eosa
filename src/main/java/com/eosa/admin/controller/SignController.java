package com.eosa.admin.controller;

import com.eosa.admin.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * packageName    : com.eosa.admin.controller
 * fileName       : SignController
 * author         : Jihun Kim
 * date           : 2022-10-05
 * description    : Sign 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-05        Jihun Kim       최초 생성
 */
@Controller
public class SignController {

    @Autowired
    private SignService signService;

    /**
     * 사인 성공 컨트롤러
     *
     * @param model
     * @param requestContractIdx
     * @param requestFormIdx
     * @param path
     * @return String
     */
    @GetMapping("/sign/success")
    public String successSign(Model model, @RequestParam long requestContractIdx, @RequestParam long requestFormIdx, @RequestParam(required = false) String path) {

        return signService.successSign(model, requestContractIdx, requestFormIdx, path);
    }

}
