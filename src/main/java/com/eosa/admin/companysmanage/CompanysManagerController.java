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

import com.eosa.web.companys.Companys;
import com.eosa.web.users.Users;

import io.swagger.v3.oas.annotations.Operation;

@Controller
@RequestMapping("/admin/detectiveManage")
public class CompanysManagerController {

    @Autowired private CompanysManageService companysManageService;
    
    final int POST_COUNT = 10;
    final int BLOCK_COUNT = 10;

    public List<Companys> postList(int currentPage) {				
        int currentStartPost = (currentPage - 1) * BLOCK_COUNT; 
        List<Companys> answer = companysManageService.findAllDetective(currentStartPost, POST_COUNT);
		
		return answer;
	}
	public Map<String, Integer> pageList(int currentPage) {
		Map<String, Integer> result = new HashMap<>();
		
		int allPostCount = companysManageService.findAllDetectiveCount(); // 모든 포스트의 수
		
		int blockFirst = ((currentPage - 1) / BLOCK_COUNT) * BLOCK_COUNT + 1;
		int blockLast = blockFirst + BLOCK_COUNT - 1;
		
		if(allPostCount < blockLast) {
			blockLast = BLOCK_COUNT;
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
        List<Companys> companysList = postList(currentPage);
        Map<String, Integer> pagination = pageList(currentPage);
        
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("companysList", companysList);
        model.addAttribute("pagination", pagination);

        return "admin/companysmanage/CompanysList";
    }

}
