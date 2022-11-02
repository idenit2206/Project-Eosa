package com.eosa.admin.service;

import com.eosa.admin.dto.RequestBackupDTO;
import com.eosa.admin.dto.RequestContractDTO;
import com.eosa.admin.dto.RequestDTO;
import com.eosa.admin.mapper.RequestBackupMapper;
import com.eosa.admin.mapper.RequestContractMapper;
import com.eosa.admin.mapper.RequestMapper;
import com.eosa.admin.pagination.Pagination;
import com.eosa.web.requestcontract.entity.RequestContract;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName    : com.eosa.admin.service
 * fileName       : RequestService
 * author         : Jihun Kim
 * date           : 2022-09-21
 * description    : 의뢰 서비스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-21        Jihun Kim       최초 생성
 */
@Slf4j
@Service
public class RequestBackupService {

    @Autowired
    private RequestBackupMapper requestMapper;

    @Autowired
    private RequestContractMapper requestContractMapper;

    /**
     * 의뢰 목록 조회 서비스
     *
     * @param model
     * @param state
     * @param sort
     * @param search
     * @param page
     * @return String
     */
    public String requestList(Model model, String state, String sort, String search, int page) {

        Map<String, Object> map = new HashMap<>();

        if (!state.equals("")) {
            map.put("state", state);
        }

        if (search != "" && !search.equals("")) {
            if (sort.equals("client") || sort.equals("company")) {
                map.put("sort", sort);
            }
            map.put("search", search);
        }

        int count = requestMapper.countRequestList(map);

        Pagination pagination = new Pagination(count, page);

        map.put("startIndex", pagination.getStartIndex());
        map.put("pageSize", pagination.getPageSize());

        List<RequestBackupDTO> list = requestMapper.selectRequestList(map);

        model.addAttribute("requestList", list);
        model.addAttribute("pagination", pagination);
        model.addAttribute("count", count);
        model.addAttribute("state", state);
        model.addAttribute("sort", sort);
        model.addAttribute("search", search);

        return "admin/board/request/list";
    }

    /**
     * 의뢰 삭제 서비스
     *
     * @param requestFormIdx
     * @return int
     */
    public int deleteRequest(long requestFormIdx) {

        requestMapper.deleteRequestCategory(requestFormIdx);

        return requestMapper.deleteRequest(requestFormIdx);
    }

    /**
     * 의뢰 수정 서비스
     *
     * @param requestDTO
     * @return int
     */
    public int updateRequest(RequestBackupDTO requestDTO) {

        requestMapper.deleteRequestCategory(requestDTO.getRequestFormIdx());

        String category[] = requestDTO.getRequestFormCategoryValue().split(",");
        for (int i = 0; i < category.length; i++) {
            requestDTO.setRequestFormCategoryValue(category[i]);
            requestMapper.insertRequestCategory(requestDTO);
        }

        return requestMapper.updateRequest(requestDTO);
    }


    /**
     * requestFormIdx가 일치하는 RequestContract를 조회하는 서비스
     * @param requestFormIdx
     * @return String
     */
    public String selectRequestContract(Long requestFormIdx) {
        RequestContractDTO selectDTO = requestContractMapper.selectRequestContract(requestFormIdx);
        String result = "";
        if(selectDTO != null) {            
            log.info("[selectRequestContract] result: {}", result);
            result = selectDTO.getRequestContractContractId();
        }
        else {            
            log.info("[selectRequestContract] result: {}", result);
            result = null;
        }
        
        return result;
    }

    /**
     * requestFormIdx가 일치하는 RequestContract를 조회하는 서비스
     * @param requestFormIdx
     * @return RequestContractDTO
     */
    public RequestContractDTO seleRequestContractDTO2(Long requestFormIdx) {
        return requestContractMapper.selectRequestContract(requestFormIdx);
    }

    /**
     * companysIdx와 일치하는 RequestDTO List를 출력하는 서비스
     * @param {Long} companysIdx
     * @return List<RequestDTO>
     */
    public List<RequestBackupDTO> requestDTOCompanysIdx(Long companysIdx) {
        return requestMapper.requestDTOByCompanysIdx(companysIdx);
    }

    /**
     * 모든 RequestDTO List를 출력하는 서비스
     * @return
     */
    public List<RequestBackupDTO> selectAllRequestDTO() {
        return requestMapper.selectAllRequestDTO();
    }

    /**
     * 모든 RequestDTO List를 출력하는 서비스 (사용자의 성별추가)
     * @return
     */
    public List<RequestBackupDTO> selectAllRequestDTO2() {
        return requestMapper.selectAllRequestDTO2();
    }

}
