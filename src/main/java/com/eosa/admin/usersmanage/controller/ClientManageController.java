package com.eosa.admin.usersmanage.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.eosa.admin.util.random.AddressTempData;
import com.eosa.admin.util.random.RandomGenAccount;
import com.eosa.admin.util.random.RandomGenKorName;
import com.eosa.admin.util.random.RandomGenMobileNumber;
import com.eosa.web.users.entity.Users;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/admin/usersManage")
public class ClientManageController {

    @Autowired private ClientService usersManageService;
    @Autowired BCryptPasswordEncoder passwordEncoder;

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
    public String userRegist() {
        int size = 20;
        log.debug("[임시] {}개의 유저데이터를 생성합니다.", size);
        Set<String> tempUser = new HashSet<>();
        ArrayList<String> tempName = new ArrayList<>();
        Set<String> tempMobile = new HashSet<>();
        AddressTempData atd = new AddressTempData();
        ArrayList<String> tempRegion = new ArrayList<>();
        while(tempUser.size() < size) {
            RandomGenAccount rgu = new RandomGenAccount();
            String usersAccount = rgu.generateAccount();
            tempUser.add(usersAccount);
        }
        while(tempName.size() < size) {
            RandomGenKorName rgkn = new RandomGenKorName();
            String usersName = rgkn.RandomGenKorName(3);
            tempName.add(usersName);
        }
        while(tempMobile.size() < size) {
            RandomGenMobileNumber rgmn = new RandomGenMobileNumber();
            String usersPhone = rgmn.RandomGenMobileNumber();
            tempMobile.add(usersPhone);
        }
        while(tempRegion.size() < size) {
            int RegionTemp = (int) Math.floor(Math.random() * atd.getREGIONAL_LOCAL_NAME().length);
            String usersRegion1 = atd.getREGIONAL_LOCAL_NAME()[RegionTemp];
            String usersRegion2 = atd.getREGION2()[RegionTemp][(int) Math.floor(Math.random() * atd.getREGION2()[RegionTemp].length)];
            tempRegion.add(usersRegion1+":"+usersRegion2);
        }

        List<String> usersAccountList = new ArrayList<>();
        List<String> usersPhoneList = new ArrayList<>();
        for (String u : tempUser) {
            // log.info("account: {}", u);
            usersAccountList.add(u);
        }
        for (String p : tempMobile) {
            usersPhoneList.add(p);
        }

        for(int i = 0; i < usersAccountList.size(); i++) {
            Users entity = new Users();
            String usersAccount = usersAccountList.get(i);
            String usersPass = passwordEncoder.encode(usersAccount);
            String usersEmail = usersAccount + "@email.com";
            LocalDateTime usersJoinDate = LocalDateTime.now();
            int usersNotice = (int) Math.floor(Math.random() * 1);

            entity.setUsersAccount(usersAccount);
            entity.setUsersPass(usersPass);
            entity.setUsersName(tempName.get(i));
            entity.setUsersNick(usersAccount.substring(0, 5));
            entity.setUsersPhone(usersPhoneList.get(i));
            entity.setUsersEmail(usersEmail);
            // entity.setUsersRole(usersRole[(int) Math.floor(Math.random() * 2)]);
            entity.setUsersRole("DETECTIVE");
            entity.setUsersAge((int) Math.floor(Math.random() * 9 + 1) * 10);
            int tempRegionSeperatorIndex = tempRegion.get(i).indexOf(":");
            entity.setUsersRegion1(tempRegion.get(i).substring(0, tempRegionSeperatorIndex));
            entity.setUsersRegion2(tempRegion.get(i).substring(tempRegionSeperatorIndex+1, tempRegion.get(i).length()));
            entity.setUsersGender((int) Math.floor(Math.random() * 2));
            entity.setUsersJoinDate(usersJoinDate);
            entity.setUsersNotice(usersNotice);
            entity.setUsersEnabled(1);

            usersManageService.save(entity);
        }
        return "임시회원등록 완료";
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
