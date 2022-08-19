package com.eosa.admin.companysmanager.controller;

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
import com.eosa.admin.companysmanager.service.CompanysManagerService;
import com.eosa.web.companys.entity.Companys;
import com.eosa.web.companys.entity.CompanysActiveRegion;
import com.eosa.web.companys.entity.CompanysCategory;
import com.eosa.web.companysmember.CompanysMember;
import com.eosa.web.companysmember.CompanysMemberService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin/companysManager")
public class CompanysManagerController {

    @Autowired private CompanysManagerService companysManagerService;
	@Autowired private CompanysMemberService companysMemberService;
   

    /**
     * 모든 업체명단을 출력합니다.
     * @return
     */
    @Operation(summary = "업체 전체 목록 조회", description="모든 업체를 목록으로 출력합니다.")
    @GetMapping("/companysList")
    public String showCompanysList(
        @RequestParam(value="currentPage", defaultValue="1") int currentPage,
        Model model
    ) {
        // log.info("showUsersList currentPage: {}", currentPage);
		// log.info(companysList.toString());        

		// List<GetCompanysList> companysList = companysManagerService.viewFindAll();
		List<Companys> companysList = companysManagerService.viewFindAll();
		// log.info(companysList.toString());		
        // Map<String, Integer> pagination = pageList(currentPage);
		
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("companysList", companysList);
        // model.addAttribute("pagination", pagination);

        return "admin/companysmanage/CompanysList";
    }

	/**
	 * [관리자 페이지] 의뢰수행이 가능한 탐정 등록 FORM view 출력
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

		//SQL 부분
		companysManagerService.save(companys);
		Long companysIdx = companysManagerService.findCompanysIdxByCeoIdx(companysRegister.getCompanysCeoIdx());
		log.info("new companysIdx: {}", companysIdx);

		for(int i = 0; i < category.size(); i++) {
			companysManagerService.insertCompanysCategory(companysIdx, category.get(i));
		}
		for(int i = 0; i < activeRegion.size(); i++) {
			companysManagerService.insertCompanysActiveRegion(companysIdx, activeRegion.get(i));
		}

		CompanysMember companysMember = new CompanysMember();
		companysMember.setUsersIdx(companysRegister.getCompanysCeoIdx());
		companysMember.setCompanysIdx(companysIdx);
		companysMember.setRegistDate(LocalDateTime.now());
		companysMember.setStatusValue(1);
		companysMemberService.save(companysMember);

		return "redirect:/admin/companysManager/companysList";
	}

	@GetMapping("/companysInfo")
	public String viewCompanysInfo(
		@RequestParam(value="companysIdx") String companysIdx,
	Model model
	) {
		log.info("CompanysIdx: {} 의 업체 정보를 불러옵니다.", companysIdx);
		Long longCompanysIdx = Long.parseLong(companysIdx);
		Companys company = companysManagerService.findByCompanysIdx(longCompanysIdx);
		
		model.addAttribute("Company", company);
		return "admin/companysmanage/CompanysInfo";
	}

}
