package com.eosa.admin.usersmanage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eosa.admin.usersmanage.entity.GetByUsersAccount;
import com.eosa.admin.usersmanage.entity.GetUsersList;
import com.eosa.admin.usersmanage.service.ClientService;
import com.eosa.admin.usersmanage.service.DetectiveService;
import com.eosa.admin.util.pagination.PageList;
import com.eosa.admin.util.pagination.PostList;
import com.eosa.web.users.Users;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/admin/usersManage")
public class DetectiveManageController {

    @Autowired private DetectiveService detectiveService;

    final private int POST_COUNT = 10;
    final private int BLOCK_COUNT = 10;
    private PostList postList = new PostList(POST_COUNT, BLOCK_COUNT);  

    @GetMapping("/detectiveList")
    public String showDetectiveList(
        @RequestParam(value="currentPage", defaultValue="1") int currentPage,
        Model model
    ) {
        int currentPageStartPost = postList.getCurrentPageStartPost(currentPage);
        List<GetUsersList> usersList = detectiveService.findAllDetective(currentPageStartPost, POST_COUNT);
        int allPostCount = detectiveService.findAllDetectiveCount();
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
        model.addAttribute("allPostCount", allPostCount);
        model.addAttribute("usersList", usersList);
        model.addAttribute("pagination", pagination);

        return "admin/usersmanage/detective/DetectiveList";
    }

     /**
     * 모든 탈퇴회원(DETECTIVE) 명단을 출력합니다.
     * @return
     */
    @Operation(summary = "탈퇴회원 전체 목록 조회", description="모든 탈퇴회원(CLIENT)의 명단을 출력합니다.")
    @GetMapping("/withdrawalDetectiveList")
    public String showWithdrawalDetectiveList(
        @RequestParam(value="currentPage", defaultValue="1") int currentPage,
        Model model
    ) {       
        int currentPageStartPost = postList.getCurrentPageStartPost(currentPage);
        List<GetUsersList> usersList = detectiveService.findAllWithdrawalDetective(currentPageStartPost, POST_COUNT);
        int allPostCount = detectiveService.findAllWithdrawalDetectiveCount();
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
        model.addAttribute("allPostCount", allPostCount);
        model.addAttribute("usersList", usersList);
        model.addAttribute("pagination", pagination);

        return "admin/usersmanage/detective/WithdrawalDetectiveList";
    }

   
}
