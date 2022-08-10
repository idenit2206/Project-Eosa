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

import com.eosa.web.users.Users;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/admin/usersManage")
public class UsersManageController {

    @Autowired private UsersManageService usersManageService;

    final int POST_COUNT = 10;
    final int BLOCK_COUNT = 10;

    public List<Users> postList(int currentPage) {
		
        int currentPageStartPost = (currentPage - 1) * POST_COUNT; 
        List<Users> answer = usersManageService.findAllClient(currentPageStartPost, POST_COUNT);
		
		return answer;
	}
	public Map<String, Integer> pageList(int currentPage) {
        Map<String, Integer> result = new HashMap<>();
        
		int allPostCount = usersManageService.findAllClientCount(); // 모든 포스트의 수
		
		int blockFirst = ((currentPage - 1) / BLOCK_COUNT) * BLOCK_COUNT + 1;
		int blockLast = blockFirst + BLOCK_COUNT - 1;
		
		if(allPostCount < blockLast) {
			blockLast = allPostCount;
		}
		
		int previousBlock = blockFirst - BLOCK_COUNT ;
		int nextBlock = blockFirst + BLOCK_COUNT ;
		
		result.put("blockCount", BLOCK_COUNT);
		result.put("fistBlock", 1); // 모든 페이지 중 가장 첫번째 페이지
		result.put("lastBlock", (int) Math.ceil(allPostCount / POST_COUNT)); // 모든 페이지 중 가장 마지막 페이지
		result.put("blockFirst", blockFirst); // 페이지네이션 목록에서 가장 첫번째 페이지
		result.put("blockLast", blockLast); // 페이지네이션 목록에서 가장 마지막 페이지
		result.put("previousBlock", previousBlock); // 이전 10개의 페이지네이션 블록에서 가장 첫번째 페이지
		result.put("nextBlock", nextBlock); // 이후 10개의 페이지네이션 블록에서 가장 첫번째 페이지

                 
		// int allPostCount = usersManageService.findAllClientCount(); // 모든 포스트의 수
        int firstPage = 1; // 가장 첫 번째 페이지
		int lastPage = (int) Math.ceil(allPostCount / POST_COUNT); // 가장 마지막 페이지
        
		
		return result;
	}
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
        // log.info("showUsersList currentPage: {}", currentPage);
        // List<Users> usersList = usersManageService.findAll();
        List<Users> usersList = postList(currentPage);
        Map<String, Integer> pagination = pageList(currentPage);
        
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
