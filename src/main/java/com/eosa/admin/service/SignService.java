package com.eosa.admin.service;

import com.eosa.admin.mapper.SignMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @param requestContractIdx
     * @param requestFormIdx
     * @return String
     */
    public String successSign(long requestContractIdx, long requestFormIdx) {

        signMapper.updateTurn(requestContractIdx);

        int turn = signMapper.selectTurn(requestContractIdx);

        if (turn == 3) {
            signMapper.updateRequestStatus(requestFormIdx);
        }

        return "admin/sign/success";
    }

}
