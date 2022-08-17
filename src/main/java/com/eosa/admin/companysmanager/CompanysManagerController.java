package com.eosa.admin.companysmanager;

import java.time.LocalDateTime;
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

import com.eosa.admin.companysmanager.entity.CompanysRegister;
import com.eosa.admin.companysmanager.entity.GetCompanysList;
import com.eosa.web.companys.Companys;
import com.eosa.web.companys.entity.CompanysActiveRegion;
import com.eosa.web.companys.entity.CompanysCategory;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin/companysManager")
public class CompanysManagerController {

    @Autowired private CompanysManagerService companysManagerService;
    
    final int POST_COUNT = 10;
    final int BLOCK_COUNT = 10;

    public List<Companys> postList(int currentPage) {
		PageRequest pageRequest = PageRequest.of(currentPage-1, POST_COUNT, Sort.by(Sort.Direction.DESC, "companysIdx"));
		// Page<Users> list =  usersManageService.findAll(pageRequest);
		// List<Users> answer = list.getContent();
        int currentStartPost = (currentPage - 1) * BLOCK_COUNT; 

        List<Companys> answer = companysManagerService.findAllCompany(currentStartPost, POST_COUNT);
		
		return answer;
	}
	public Map<String, Integer> pageList(int currentPage) {
		Map<String, Integer> result = new HashMap<>();
		PageRequest pageRequest = PageRequest.of(currentPage - 1, POST_COUNT, Sort.by(Sort.Direction.DESC, "companysIdx"));
		Page<Companys> list = companysManagerService.findAll(pageRequest);
		int blockCount = list.getSize();

		if(blockCount == 0) { blockCount = 0; }
		
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
		// log.info(companysList.toString());        

		List<GetCompanysList> companysList = companysManagerService.viewFindAll();
		// log.info(companysList.toString());		
        // Map<String, Integer> pagination = pageList(currentPage);
		
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("companysList", companysList);
        // model.addAttribute("pagination", pagination);

        return "admin/companysmanage/CompanysList";
    }

	/**
	 * [관리자 페이지] 의뢰수행이 가능한 탐정등록 view 출력
	 * @return
	 */
	@GetMapping("/companysRegister")
	public String viewCompanysRegister() {
		return "admin/companysmanage/CompanysRegister";
	}

	/**
	 * [관리자 페이지] 의뢰수행이 가능한 탐정등록 수행
	 * @return
	 */
	@PostMapping("/companysRegister.process")
	public String companysRegisterProcess(CompanysRegister companysRegister) {
		log.info(companysRegister.toString());
		
		Companys companys = new Companys();
		companys.setCompanysName(companysRegister.getCompanysName());
		companys.setCompanysCeoIdx(companysRegister.getCompanysCeoIdx());
		companys.setCompanysCeoName(companysRegister.getCompanysCeoName());
		companys.setCompanysPhone(companysRegister.getCompanysPhone());
		companys.setCompanysComment(companysRegister.getCompanysComment());
		companys.setCompanysSpec(companysRegister.getCompanysSpec());
		companys.setCompanysRegion1(companysRegister.getCompanysRegion1());
		companys.setCompanysRegion2(companysRegister.getCompanysRegion2());
		companys.setCompanysRegion3(companysRegister.getCompanysRegion3());
		companys.setCompanysRegistDate(LocalDateTime.now());
		
		CompanysCategory companysCategory = new CompanysCategory();
		List<String> category = companysRegister.getCompanysCategory();

		CompanysActiveRegion companysActiveRegion = new CompanysActiveRegion();
		List<String> activeRegion = companysRegister.getCompanysActiveRegion();
		
		log.info("{}", companys.toString());
		log.info("{}", category.toString());
		log.info("{}", activeRegion.toString());

		companysManagerService.save(companys);
		Long companysIdx = companysManagerService.findCompanysIdxByCeoIdx(companysRegister.getCompanysCeoIdx());
		log.info("new companysIdx: {}", companysIdx);

		for(int i = 0; i < category.size(); i++) {
			companysManagerService.insertCompanysCategory(companysIdx, category.get(i));
		}
		for(int i = 0; i < activeRegion.size(); i++) {
			companysManagerService.insertCompanysActiveRegion(companysIdx, activeRegion.get(i));
		}

		return "admin/companysmanage/CompanysList";
	}

}
