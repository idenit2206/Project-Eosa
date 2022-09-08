package com.eosa.admin.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * packageName    : com.eosa.admin.service
 * fileName       : AdminService
 * author         : Jihun Kim
 * date           : 2022-09-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-08        Jihun Kim       최초 생성
 */
@Service
public class AdminService {

    public String adminMain(Model model) {

        model.addAttribute("error", false);

        return "admin/index";
    }

}
