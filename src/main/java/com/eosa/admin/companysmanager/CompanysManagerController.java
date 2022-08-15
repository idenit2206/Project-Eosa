package com.eosa.admin.companysmanager;

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

import com.eosa.web.companys.Companys;

import io.swagger.v3.oas.annotations.Operation;

@Controller
@RequestMapping("/admin/companysManage")
public class CompanysManagerController {

    @Autowired private CompanysManageService companysManageService;
    
    final int POST_COUNT = 10;
    final int BLOCK_COUNT = 10;

    public List<Companys> postList(int currentPage) {
		PageRequest pageRequest = PageRequest.of(currentPage-1, POST_COUNT, Sort.by(Sort.Direction.DESC, "companysIdx"));
		// Page<Users> list =  usersManageService.findAll(pageRequest);
		// List<Users> answer = list.getContent();
        int currentStartPost = (currentPage - 1) * BLOCK_COUNT; 

        List<Companys> answer = companysManageService.findAllCompany(currentStartPost, POST_COUNT);
		
		return answer;
	}
	public Map<String, Integer> pageList(int currentPage) {
		Map<String, Integer> result = new HashMap<>();
		PageRequest pageRequest = PageRequest.of(currentPage - 1, POST_COUNT, Sort.by(Sort.Direction.DESC, "companysIdx"));
		Page<Companys> list = companysManageService.findAll(pageRequest);
		int blockCount = list.getSize();
		
		int blockFirst = ((currentPage - 1) / BLOCK_COUNT) * BLOCK_COUNT + 1;
		int blockLast = blockFirst + BLOCK_COUNT - 1;
		
		if(blockCount < blockLast) {
			blockLast = blockCount;
		}
		
		int previousBlock = blockFirst - BLOCK_COUNT ;
		int nextBlock = blockFirst + BLOCK_COUNT ;
		
		result.put("currentpage", currentPage);
		result.put("blockCount", BLOCK_COUNT);
		result.put("fistBlock", 1); // 모든 페이지 중 가장 첫번째 페이지
		result.put("lastBlock", blockCount); // 모든 페이지 중 가장 마지막 페이지
		result.put("blockFirst", blockFirst); // 페이지네이션 목록에서 가장 첫번째 페이지
		result.put("blockLast", blockLast); // 페이지네이션 목록에서 가장 마지막 페이지
		result.put("previousBlock", previousBlock); // 이전 10개의 페이지네이션 블록에서 가장 첫번째 페이지
		result.put("nextBlock", nextBlock); // 이후 10개의 페이지네이션 블록에서 가장 첫번째 페이지
		
		return result;
	}

    /**
     * 모든 업체명단을 출력합니다.
     * @return
     */
    @Operation(summary = "업체 전체 목록 조회", description="모든 업체를 목록으로 출력합니다.")
    @GetMapping("/companysList")
    public String showCompanysList(
        @RequestParam(value="currentPage") int currentPage,
        Model model
    ) {
        // log.info("showUsersList currentPage: {}", currentPage);
        // List<Users> usersList = usersManageService.findAll();
        List<Companys> companysList = postList(currentPage);
        Map<String, Integer> pagination = pageList(currentPage);
        
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("companysList", companysList);
        model.addAttribute("pagination", pagination);

        return "admin/companysmanage/CompanysList";
    }

}
