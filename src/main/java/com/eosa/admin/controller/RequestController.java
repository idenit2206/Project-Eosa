package com.eosa.admin.controller;

import com.eosa.admin.dto.RequestDTO;
import com.eosa.admin.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 의뢰 삭제 컨트롤러
     *
     * @param requestFormIdx
     * @return int
     */
    @ResponseBody
    @PostMapping("/delete")
    public int deleteRequest(@RequestParam long requestFormIdx) {

        return requestService.deleteRequest(requestFormIdx);
    }

    /**
     * 의뢰 수정 컨트롤러
     *
     * @param requestDTO
     * @return int
     */
    @ResponseBody
    @PostMapping("/update")
    public int updateRequest(RequestDTO requestDTO) {

        return requestService.updateRequest(requestDTO);
    }

    /**
     * requestFormIdx와 일치하는 RequestContract를 조회하는 컨트롤러
     * @param requestFormIdx
     * @return
     */
    @ResponseBody
    @PostMapping("/selectRequestContract")
    public String selectRequestContract(Long requestFormIdx) {
        return requestService.selectRequestContract(requestFormIdx);
    }

}
