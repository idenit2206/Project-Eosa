package com.eosa.admin.controller;

import com.eosa.admin.dto.NoticeDTO;
import com.eosa.admin.service.NoticeService;
import com.eosa.web.util.file.AwsS3Service;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
@RequestMapping("/admin/manage/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;
    @Autowired AwsS3Service awsS3Service;
    
    /**
     * 공지사항 목록 출력하기
     * @param model
     * @param 
     * @return String
     */
    @GetMapping("/list")
    public String noticeList(
        Model model,
        @RequestParam(defaultValue = "") String search,
        @RequestParam(defaultValue = "1") int page
    ) {
        return noticeService.noticeList(model, search, page);
    }

    /**
     * 공지사항 목록에서 공지사항 삭제하기
     * @param model
     * @param companysIdx
     * @return
     */
    @PutMapping("/listDelete")
    public String noticeListDelete(
        @RequestParam List<Long> listCheckValueList,
        @RequestParam(defaultValue = "") String search,
        @RequestParam(defaultValue = "1") int page,
        Model model
    ) {
        return noticeService.noticeListDelete(listCheckValueList, search, page, model);
    }

    
    /** 
     * 공지사항 등록하기
     * @param model
     * @param authentication
     * @return String
     */
    @GetMapping("/register")
    public String noticeRegister(Model model, Authentication authentication) {
        return noticeService.noticeRegister(model, authentication);
    }

    
    /** 
     * 공지사항 등록하기 RESTful 
     * @param noticeDTO
     * @return int
     */
    @ResponseBody
    @PostMapping("/insert")
    public int insertNotice(NoticeDTO noticeDTO) {
        return noticeService.insertNotice(noticeDTO);
    }

    /**
     * 공지사항 상세 조회 컨트롤러
     *
     * @param model
     * @param companysIdx
     * @return String
     */
    @GetMapping("/details")
    public String noticeDetails(
        Model model,
        @RequestParam(name="idx") long idx
    ) {
        return noticeService.noticeDetails(model, idx);
    }

    /** 
     * 공지사항 수정하기
     * @param noticeDTO
     * @return int
     */
    @ResponseBody
    @PutMapping("/updateByNoticeIdx")
    public int updateNoticeByNoticeIdx(NoticeDTO noticeDTO) {
        return noticeService.updateNoticeByNoticeIdx(noticeDTO);
    }
    
    /** 
     * 공지사항 상세보기에서 삭제하기
     * @param idx
     */
    @ResponseBody
    @DeleteMapping("/deleteByNoticeIdx")
    public void deleteByNoticeIdx(@RequestParam("noticeIdx") Long idx) {
        int deleteRow =  noticeService.deleteByNoticeIdx(idx);
        log.debug("[deleteByNoticeIdx] delete result: {}", deleteRow);
    }

    /**
     * 업체 에디터 이미지 저장 서비스
     *
     * @param file
     * @return String
     */
    @PostMapping("/noticeEditor")
    @ResponseBody
    public String uploadNoticeEditor(
        @RequestParam("file") List<MultipartFile> file,
        @RequestParam("noticeIdx") Long noticeIdx
    ) {
        log.info("[관리자] 새로운 공지사항 - noticeIdx: {}, fileName: {}", noticeIdx, file.get(0).getOriginalFilename());
        List<String> uploadImage = awsS3Service.uploadSingleFile(file.get(0), "adminnotice", noticeIdx);
        return "https://eosawebbucket.s3.ap-northeast-2.amazonaws.com/adminnotice/" + uploadImage.get(0);
    } 

}
