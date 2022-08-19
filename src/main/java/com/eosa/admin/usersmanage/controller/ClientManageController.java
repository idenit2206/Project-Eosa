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
import com.eosa.admin.util.pagination.PageList;
import com.eosa.admin.util.pagination.PostList;
import com.eosa.web.users.Users;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/admin/usersManage")
public class ClientManageController {

    @Autowired private ClientService usersManageService;

    final private int POST_COUNT = 10;
    final private int BLOCK_COUNT = 10;
    private PostList postList = new PostList(POST_COUNT, BLOCK_COUNT);   
   
    @GetMapping("/allClientCount")
    @ResponseBody
    public int allClientCount() {
        return usersManageService.findAllClientCount();
    } 

    @GetMapping("/tempUserRegist")
    @ResponseBody
    public void userRegist() {
        log.debug("[임시] 100명의 유저데이터를 생성합니다.");
        for(int i =0; i < 100; i++) {
            Users user = new Users();
            usersManageService.save(user);
        }        
    }

    /**
     * 모든 유저(CLIENT)의 명단을 출력합니다.
     * @return
     */
    @Operation(summary = "회원 전체 목록 조회", description="모든 유저(CLIENT)의 명단을 출력합니다.")
    @GetMapping("/clientList")
    public String showClientList(
        @RequestParam(value="currentPage", defaultValue="1") int currentPage,
        Model model
    ) {       
        int currentPageStartPost = postList.getCurrentPageStartPost(currentPage);
        List<GetUsersList> usersList = usersManageService.findAllClient(currentPageStartPost, POST_COUNT);
        int allPostCount = usersManageService.findAllClientCount();
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

        return "admin/usersmanage/client/ClientList";
    }

    /**
     * 모든 탈퇴회원(CLIENT)의 명단을 출력합니다.
     * @return
     */
    @Operation(summary = "탈퇴회원 전체 목록 조회", description="모든 탈퇴회원(CLIENT)의 명단을 출력합니다.")
    @GetMapping("/withdrawalClientList")
    public String showWithdrawalClientList(
        @RequestParam(value="currentPage", defaultValue="1") int currentPage,
        Model model
    ) {       
        int currentPageStartPost = postList.getCurrentPageStartPost(currentPage);
        List<GetUsersList> usersList = usersManageService.findAllWithdrawalUser(currentPageStartPost, POST_COUNT);
        int allPostCount = usersManageService.findAllWithdrawalUserCount();
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

        return "admin/usersmanage/client/WithdrawalClientList";
    }

    /**
     * 유저 계정기준의 사용자 검색 
    */
    @GetMapping("/findByClientAccount")
    public String findByClientAccount(
        @RequestParam("usersAccount") String usersAccount,
        @RequestParam(value="currentPage", defaultValue="1") int currentPage,
        Model model
    ) {
        int currentPageStartPost = postList.getCurrentPageStartPost(currentPage);
        List<Users> usersList = null;
        int allPostCount =  0;
        PageList pageList = null;

        if(!usersAccount.isBlank()) {
            currentPageStartPost = postList.getCurrentPageStartPost(currentPage);
            usersList = usersManageService.findByUsersAccount(usersAccount, currentPageStartPost, POST_COUNT);
            allPostCount =  usersManageService.findByUsersAccountCount(usersAccount);
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

        if(usersList != null) {
            // log.debug("currentPage: {}", currentPage);
            // log.debug("usersList Size: {}", usersList.size());
            // log.debug("pagination: {}", pagination.toString());
            model.addAttribute("usersAccount", usersAccount);
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("usersList", usersList);
            model.addAttribute("pagination", pagination);
        }
        else {
            // log.debug("currentPage: {}", currentPage);
            // log.debug("usersList Size: {}", usersList.size());
            // log.debug("pagination: {}", pagination.toString());
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("usersList", usersList);
            model.addAttribute("pagination", pagination);
        }
           
        return "admin/usersmanage/FindByClientAccount";
    }

    /**
     * 단일 회원정보 조회
     * @param usersAccount
     * @param model
     * @return
     */
    @GetMapping("/usersInfo")
    public String showUsersInfo(
        @RequestParam(value="usersAccount") String usersAccount,
        Model model
    ) {
        log.info("[CLIENT] {} 의 정보를 불러옵니다.", usersAccount);
        GetByUsersAccount usersInfo = usersManageService.getByUsersAccount(usersAccount);
        
        model.addAttribute("Users", usersInfo);
        return "admin/usersmanage/UsersInfo";
    }

    /**
     * 회원정보 수정을 실행하는 메서드
     * @param response
     * @param param
     */
    @PostMapping(value="/usersInfo.up")
    public String modifyUsersInfo(
        Users param
    ) {
        // log.debug("변경된 사용자 정보: {}", param.toString());
        // log.debug("{}", param.getUsersNotice());
        int transaction = usersManageService.updateUsersInfo(param);

        if(transaction != 0) {
            log.info("{} 님의 사용자 정보 갱신이 완료되었습니다.", param.getUsersAccount());
        }
        else {
            log.error("{} 님의 사용자 정보 갱신에 실패했습니다.", param.getUsersAccount());
        }

        return "redirect:/admin/usersManage/usersList?currentPage=1";
    }
    
}
