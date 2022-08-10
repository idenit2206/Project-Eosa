package com.eosa.admin.usersmanage;

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
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eosa.admin.util.pagination.PageList;
import com.eosa.admin.util.pagination.PostList;
import com.eosa.web.users.Users;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/admin/usersManage")
public class UsersManageController {

    @Autowired private UsersManageService usersManageService;    
   
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
     * 모든 유저(CLIENT, DETECTIVE)의 명단을 출력합니다.
     * @return
     */
    @Operation(summary = "회원 전체 목록 조회", description="모든 유저(CLIENT, DETECTIVE)의 명단을 출력합니다.")
    @GetMapping("/usersList")
    public String showUsersList(
        @RequestParam(value="currentPage") int currentPage,
        Model model
    ) {
        final int POST_COUNT = 10;
        final int BLOCK_COUNT = 10;

        PostList postList = new PostList(POST_COUNT, BLOCK_COUNT);   
        
        int currentPageStartPost = postList.getCurrentPageStartPost(currentPage);
        List<Users> usersList = usersManageService.findAllClient(currentPageStartPost, POST_COUNT);
        int allPostCount = usersManageService.findAllClientCount();
        PageList pageList = new PageList(POST_COUNT, BLOCK_COUNT, currentPage, allPostCount);
        
        // log.info("showUsersList currentPage: {}", currentPage);
        // List<Users> usersList = usersManageService.findAll();

        // List<Users> usersList = postList(currentPage);        
        Map<String, Integer> pagination = new HashMap<>();
        pagination.put("blockCount", BLOCK_COUNT);
		pagination.put("fistBlock", pageList.getFirstBlock()); 
		pagination.put("lastBlock", pageList.getLastBlock());
		pagination.put("blockFirst", pageList.getBlockFirst());
		pagination.put("blockLast", pageList.getBlockLast());
		pagination.put("previousBlock", pageList.getPrevBlock());
		pagination.put("nextBlock", pageList.getNextBlock());
        
        
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("usersList", usersList);
        model.addAttribute("pagination", pagination);

        return "admin/usersmanage/UsersList";
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
        log.info("{} 님의 사용자 정보를 불러옵니다.", usersAccount);
        Users usersInfo = usersManageService.getByUsersAccount(usersAccount);
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
        log.debug("변경된 사용자 정보: {}", param.toString());
        log.debug("{}", param.getUsersNotice());
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
