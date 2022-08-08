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
import org.springframework.web.bind.annotation.ModelAttribute;
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
		PageRequest pageRequest = PageRequest.of(currentPage-1, POST_COUNT, Sort.by(Sort.Direction.DESC, "usersIdx"));
		Page<Users> list =  usersManageService.findAll(pageRequest);
		List<Users> answer = list.getContent();
		
		return answer;
	}
	public Map<String, Integer> pageList(int currentPage) {
		Map<String, Integer> result = new HashMap<>();
		PageRequest pageRequest = PageRequest.of(currentPage - 1, POST_COUNT, Sort.by(Sort.Direction.DESC, "usersIdx"));
		Page<Users> list = usersManageService.findAll(pageRequest);
		int blockCount = list.getTotalPages();
		
		int blockFirst = ((currentPage - 1) / BLOCK_COUNT) * BLOCK_COUNT + 1;
		int blockLast = blockFirst + BLOCK_COUNT - 1;
		
		if(blockCount < blockLast) {
			blockLast = blockCount;
		}
		
		int previousBlock = blockFirst - BLOCK_COUNT ;
		int nextBlock = blockFirst + BLOCK_COUNT ;
		
		result.put("blockCount", BLOCK_COUNT);
		result.put("fistBlock", 1); // 모든 페이지 중 가장 첫번째 페이지
		result.put("lastBlock", blockCount); // 모든 페이지 중 가장 마지막 페이지
		result.put("blockFirst", blockFirst); // 페이지네이션 목록에서 가장 첫번째 페이지
		result.put("blockLast", blockLast); // 페이지네이션 목록에서 가장 마지막 페이지
		result.put("previousBlock", previousBlock); // 이전 10개의 페이지네이션 블록에서 가장 첫번째 페이지
		result.put("nextBlock", nextBlock); // 이후 10개의 페이지네이션 블록에서 가장 첫번째 페이지
		
		return result;
	}

    @GetMapping("/tempUserRegist")
    @ResponseBody
    public void userRegist() {
        Users user = new Users();
        usersManageService.save(user);
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

        return "/usersmanage/UsersList";
    }

    /**
     * 회원정보 조회
     * @param usersAccount
     * @param model
     * @return
     */
    @GetMapping("/usersInfo")
    public String showUsersInfo(
        @RequestParam(value="usersAccount") String usersAccount,
        Model model
    ) {
        log.info("% {} 유저의 UsersInfo를 불러옵니다.", usersAccount);
        Users usersInfo = usersManageService.getByUsersAccount(usersAccount);
        model.addAttribute("Users", usersInfo);
        return "/usersmanage/UsersInfo";
    }

    /**
     * 회원정보 수정을 실행하는 메서드
     * @param response
     * @param param
     */
    @PostMapping(value="/usersInfo.up{Users}")
    public String modifyUsersInfo(
        @ModelAttribute Users param
        // @RequestParam(value="usersAccount") String usersAccount
    ) {
        log.info("# modify Users Info: {}", param.toString());
        // log.info("# {}", usersAccount);
        // return "/usersmanage/UsersList";
        // usersManageService.modifyUsersInfo(param);
        return "redirect:/usersManage/usersList?currentPage=1";
    }
    
}
