package com.eosa.admin.companysmanage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eosa.admin.util.pagination.PageList;
import com.eosa.admin.util.pagination.PostList;
import com.eosa.web.companys.Companys;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin/detectiveManage")
public class CompanysManagerController {

    @Autowired private CompanysManageService companysManageService;
    
    final private int POST_COUNT = 10;
    final private int BLOCK_COUNT = 10;
    private PostList postList = new PostList(POST_COUNT, BLOCK_COUNT);

    /**
     * 모든 탐정 명단을 출력합니다.
     * @return
    */
    @Operation(summary = "탐정 전체 목록 조회", description="모든 탐정을 목록으로 출력합니다.")
    @GetMapping("/detectiveList")
    public String showAllDetectiveList(
        @RequestParam(value="currentPage", defaultValue="1") int currentPage,
        Model model
    ) {
        int currentPageStartPost = postList.getCurrentPageStartPost(currentPage);
        List<Companys> companysList = companysManageService.findAllDetective(currentPageStartPost, POST_COUNT);
        int allPostCount = companysManageService.findAllDetectiveCount();
        PageList pageList = new PageList(POST_COUNT, BLOCK_COUNT, currentPage, allPostCount);

        Map<String, Integer> pagination = new HashMap<>();
        pagination.put("blockCount", BLOCK_COUNT);
		pagination.put("fistBlock", pageList.getFirstBlock()); 
		pagination.put("lastBlock", pageList.getLastBlock());
		pagination.put("blockFirst", pageList.getBlockFirst());
		pagination.put("blockLast", pageList.getBlockLast());
		pagination.put("previousBlock", pageList.getPrevBlock());
		pagination.put("nextBlock", pageList.getNextBlock());        
        
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("companysList", companysList);
        model.addAttribute("pagination", pagination);

        return "admin/companysmanage/CompanysList";
    }

    @GetMapping("/findByCompanysCeoAccount")
    public String findByCompanysCeoAccount(
        @RequestParam("companysCeoAccount") String companysCeoAccount,
        @RequestParam(value="currentPage", defaultValue="1") int currentPage,
        Model model
    ) {
        int currentPageStartPost = postList.getCurrentPageStartPost(currentPage);
        List<Companys> companysList = null;
        int allPostCount =  0;
        PageList pageList = null;

        if(!companysCeoAccount.isBlank()) {
            currentPageStartPost = postList.getCurrentPageStartPost(currentPage);
            companysList = companysManageService.findByCompanysCeoAccount(companysCeoAccount, currentPageStartPost, POST_COUNT);
            allPostCount = companysManageService.findByCompanysCeoAccountCount(companysCeoAccount);
            pageList = new PageList(POST_COUNT, BLOCK_COUNT, currentPage, allPostCount);
        }
        else {
            pageList = new PageList(POST_COUNT, BLOCK_COUNT, currentPage, allPostCount);
        }     

        Map<String, Integer> pagination = new HashMap<>();
        pagination.put("blockCount", BLOCK_COUNT);
		pagination.put("fistBlock", pageList.getFirstBlock()); 
		pagination.put("lastBlock", pageList.getLastBlock());
		pagination.put("blockFirst", pageList.getBlockFirst());
		pagination.put("blockLast", pageList.getBlockLast());
		pagination.put("previousBlock", pageList.getPrevBlock());
		pagination.put("nextBlock", pageList.getNextBlock());

        if(companysList != null) {
            // log.debug("currentPage: {}", currentPage);
            // log.debug("usersList Size: {}", usersList.size());
            // log.debug("pagination: {}", pagination.toString());
            model.addAttribute("companysCeoAccount", companysCeoAccount);
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("companysList", companysList);
            model.addAttribute("pagination", pagination);
        }
        else {
            // log.debug("currentPage: {}", currentPage);
            // log.debug("usersList Size: {}", usersList.size());
            // log.debug("pagination: {}", pagination.toString());
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("companysList", companysList);
            model.addAttribute("pagination", pagination);
        }
           
        return "admin/companysmanage/FindByCompanysCeoAccount";
    }

    /**
     * 회사의 상세정보를 조회합니다.
     * @param companysName
     * @param model
     * @return
     */
    @GetMapping("/detectiveInfo")
    public String detectiveInfo(
        @RequestParam("companysName") String companysName,
        Model model
    ) {
        log.info("[DETECTIVE] {} 의 정보를 불러옵니다.", companysName);
        GetByCompanysName companysInfo = companysManageService.getByCompanysName(companysName);
        GetUserNamePhone usersInfo = companysManageService.getUserNamePhone(companysName);
        model.addAttribute("Users", usersInfo);
        model.addAttribute("Companys", companysInfo);

        return "admin/companysmanage/CompanysInfo";
    }

}
