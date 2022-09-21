package com.eosa.admin.controller;

import com.eosa.admin.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * packageName    : com.eosa.admin.controller
 * fileName       : RequestController
 * author         : Jihun Kim
 * date           : 2022-09-21
 * description    : 의뢰 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-21        Jihun Kim       최초 생성
 */
@Controller
@RequestMapping("/admin/manage/request")
public class RequestController {

    @Autowired
    private RequestService requestService;

    /**
     * 의뢰 목록 조회 컨트롤러
     *
     * @param model
     * @param state
     * @param sort
     * @param search
     * @param page
     * @return String
     */
    @GetMapping("/list")
    public String requestList(Model model,
                              @RequestParam(defaultValue = "") String state,
                              @RequestParam(defaultValue = "client") String sort,
                              @RequestParam(defaultValue = "") String search,
                              @RequestParam(defaultValue = "1") int page) {

        return requestService.requestList(model, state, sort, search, page);
    }

}
