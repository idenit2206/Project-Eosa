package com.eosa.admin.service;

import com.eosa.admin.mapper.SignMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * packageName    : com.eosa.admin.service
 * fileName       : SignService
 * author         : Jihun Kim
 * date           : 2022-10-05
 * description    : Sign 서비스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-05        Jihun Kim       최초 생성
 */
@Service
public class SignService {

    @Autowired
    private SignMapper signMapper;

    /**
     * 사인 성공 서비스
     *
     * @param model
     * @param request
     * @param response
     * @param requestContractIdx
     * @param requestFormIdx
     * @return String
     */
    public String successSign(Model model, HttpServletRequest request, HttpServletResponse response, long requestContractIdx, long requestFormIdx, String path) {

        Cookie[] cookies = request.getCookies();
        int visitor = 0;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("visit")) {
                    visitor = 1;

                    if (cookie.getValue().contains(request.getParameter("requestContractIdx"))) {

                    } else {
                        cookie.setValue(cookie.getValue() + "_" + request.getParameter("requestContractIdx"));
                        response.addCookie(cookie);
                        signMapper.updateTurn(requestContractIdx);
                    }

                }
            }
        }

        if (visitor == 0) {
            Cookie cookie1 = new Cookie("visit", request.getParameter("requestContractIdx"));
            response.addCookie(cookie1);
            signMapper.updateTurn(requestContractIdx);
        }

        int turn = signMapper.selectTurn(requestContractIdx);

        if (turn == 3) {
            signMapper.updateRequestStatus(requestFormIdx);
        }

        model.addAttribute("path", path);

        return "admin/sign/success";
    }

}
