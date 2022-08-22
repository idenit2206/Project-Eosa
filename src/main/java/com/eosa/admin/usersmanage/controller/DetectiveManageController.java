package com.eosa.admin.usersmanage.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
import com.eosa.admin.usersmanage.service.DetectiveService;
import com.eosa.admin.util.pagination.PageList;
import com.eosa.admin.util.pagination.PostList;
import com.eosa.admin.util.random.AddressTempData;
import com.eosa.admin.util.random.RandomGenAccount;
import com.eosa.admin.util.random.RandomGenKorName;
import com.eosa.admin.util.random.RandomGenMobileNumber;
import com.eosa.web.users.Users;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/admin/usersManage")
public class DetectiveManageController {

    @Autowired private DetectiveService detectiveService;
    @Autowired BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/tempDetectiveRegister")
    @ResponseBody
    public void userRegist() {
        log.debug("[임시] 100명의 유저데이터를 생성합니다.");
        Set<String> usersAccountSet = new HashSet<>();
        while(usersAccountSet.size() < 100) {
            RandomGenAccount rgu = new RandomGenAccount();
            String usersAccount = rgu.generateAccount();
            usersAccountSet.add(usersAccount);
        }            
        for (String usersAccount : usersAccountSet) {
            
            Users entity = new Users();
            String usersPass = passwordEncoder.encode(usersAccount);
    
            RandomGenKorName rgkn = new RandomGenKorName();
            String usersName = rgkn.RandomGenKorName(3);
    
            RandomGenMobileNumber rgmn = new RandomGenMobileNumber();
            String usersPhone = rgmn.RandomGenMobileNumber();
    
            String usersEmail = usersAccount + "@email.com";
    
            AddressTempData atd = new AddressTempData();
            int RegionTemp = (int) Math.floor(Math.random() * atd.getREGIONAL_LOCAL_NAME().length);
            String usersRegion1 = atd.getREGIONAL_LOCAL_NAME()[RegionTemp];
            String usersRegion2 = atd.getREGION2()[RegionTemp][(int) Math.floor(Math.random() * atd.getREGION2()[RegionTemp].length)];
            LocalDateTime usersJoinDate = LocalDateTime.now();
            int usersNotice = (int) Math.floor(Math.random() * 1);
    
            entity.setUsersAccount(usersAccount);
            entity.setUsersPass(usersPass);
            entity.setUsersName(usersName);
            entity.setUsersNick(usersAccount.substring(0, 5));
            entity.setUsersPhone(usersPhone);
            entity.setUsersEmail(usersEmail);
            // entity.setUsersRole(usersRole[(int) Math.floor(Math.random() * 2)]);
            entity.setUsersRole("DETECTIVE");
            entity.setUsersAge((int) Math.floor(Math.random() * 9) * 10);
            entity.setUsersRegion1(usersRegion1);
            entity.setUsersRegion2(usersRegion2);
            entity.setUsersJoinDate(usersJoinDate);
            entity.setUsersNotice(usersNotice);
            entity.setUsersEnabled(1);
    
            detectiveService.save(entity);
        }        
    }

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
