package com.eosa.admin.adminuser;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eosa.web.security.CustomPrincipalDetails;
import com.eosa.web.users.Users;
import com.eosa.web.users.UsersService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired private AdminUserService adminUserService;
    @Autowired UsersService usersService;

    @GetMapping("/test01")
    @ResponseBody
    public String test01() {
        return "/admin/test01";
    }

    @GetMapping("/sign/signUp")
    public String adminSignUpForm() {
        return "admin/signin/SignUp";
    }

    @PostMapping("/sign/signUp.do")
    public String adminSignUpDo(Users adminUser) {
        adminUserService.adminRegist(adminUser);
        return "admin/signin/SignIn";
    }

    @Operation(summary="관리자페이지 로그인 view", description="관리자 페이지의 로그인을 위한 Form 페이지")
    @GetMapping("/sign/signIn")
    public String adminSignInForm() {
        // log.info("## Someone Request /signInForm");
        return "admin/signin/SignIn";
    }

    @PostMapping(value="/sign/signIn.success")
    public ModelAndView adminSignInSuceess(
        @AuthenticationPrincipal CustomPrincipalDetails users, 
        @RequestParam("usersAccount") String usersAccount
    ) {
        ModelAndView mv = new ModelAndView();
        String author = users.customGetAuthorities();
        if(author.matches("ADMIN") || author.matches("SUPER_ADMIN")) {
            log.info("## 환영합니다 {} 님.", usersAccount);
            Users user = usersService.findByUsersAccount(usersAccount);

            mv.setViewName("admin/index");
            mv.addObject("user", user);
            
            return mv;
        }
        else {
            log.info("## 권한이 없는 사용자 입니다. 사용자명: {}.", usersAccount);
            mv.setViewName("admin/signin/SignIn");
            return mv;
        }        
    }

    @PostMapping(value="/sign/signIn.failure")
    public void adminSignInFailure(
        HttpServletRequest request, HttpServletResponse response,
        @RequestParam("usersAccount") String usersAccount,
        Model model
    ) throws IOException {
        log.error("## {} 님이 관리자 페이지 로그인에 실패했습니다.", usersAccount);
        model.addAttribute("msg", "Failed to SignIn");
        response.sendRedirect("admin/signIn");
    }

    final int POST_COUNT = 10;
    final int BLOCK_COUNT = 10;

    public List<Users> postList(int currentPage) {		
        int currentPageStartPost = (currentPage - 1) * POST_COUNT; 
        List<Users> answer = adminUserService.findAllAdmin(currentPageStartPost, POST_COUNT);
		
		return answer;
	}
	public Map<String, Integer> pageList(int currentPage) {
		Map<String, Integer> result = new HashMap<>();
        
		int allPostCount = adminUserService.findAllAdminCount(); // 모든 포스트의 수
		
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
        return adminUserService.findAllAdminCount();
    }

    @GetMapping(value="/adminList")
    public String adminList(
        @RequestParam(value="currentPage", defaultValue="1") int currentPage,
        Model model
    ) {
        List<Users> adminList = postList(currentPage);
        Map<String, Integer> pagination = pageList(currentPage);

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("usersList", adminList);
        model.addAttribute("pagination", pagination);

        return "admin/adminmanage/adminList";
    }
    @GetMapping(value="/adminInfo")
    public ModelAndView adminInfo(
        @RequestParam("usersAccount") String usersAccount
    ) {
        ModelAndView mv = new ModelAndView();

        Users user = adminUserService.findByAdminUserAccount(usersAccount);

        mv.addObject("user", user);
        mv.setViewName("admin/adminmanage/adminInfo");

        return mv;
    }
    
    @PostMapping(value="/adminUpdate")
    @ResponseBody
    public ModelAndView adminUpdate(
        Users param
    ) {
        ModelAndView mv = new ModelAndView();
        int transaction = usersService.updateAdminUserInfo(param);
        // log.info("# transaction {}", transaction);
        if(transaction != 0) {           
            log.info("{} 의 회원정보를 변경합니다.", param.getUsersAccount()); 
            Users user = usersService.findByUsersAccount(param.getUsersAccount());    
            mv.setViewName("admin/index");
            mv.addObject("user", user);
        }
        else {
            log.info("{} 의 회원정보 변경을 실패했습니다.", param.getUsersAccount());
            mv.setViewName("admin/index");
        }       

        return mv;
    }
}
