package com.eosa.admin.controller;

import com.eosa.admin.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * packageName    : com.eosa.admin.controller
 * fileName       : BoardController
 * author         : Jihun Kim
 * date           : 2022-09-20
 * description    : 게시판 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-20        Jihun Kim       최초 생성
 */
@Controller
@RequestMapping("/admin/manage")
public class BoardController {

    @Autowired
    private BoardService boardService;

    /**
     * 리뷰 목록 조회 컨트롤러
     *
     * @param model
     * @param sort
     * @param search
     * @param page
     * @return String
     */
    @GetMapping("/review/list")
    public String reviewList(Model model,
                             @RequestParam(defaultValue = "company") String sort,
                             @RequestParam(defaultValue = "") String search,
                             @RequestParam(defaultValue = "1") int page) {

        return boardService.reviewList(model, sort, search, page);
    }

}
