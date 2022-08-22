package com.eosa.admin.requestmanage;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eosa.admin.util.random.AddressTempData;
import com.eosa.admin.util.random.RandomDate;
import com.eosa.web.requestform.RequestForm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin/requestFormManage/")
public class RequestFormManageController {

    @Autowired private RequestFormManageService requestFormManageService;
    private final String[] REQUEST_CATEGORY = {
        "가정분야", "기업문제", "안전분야조사", "민원대행", "금융", "사이버",
        "범죄조사", "부동산", "불법탐지기", "사기분쟁", "스토킹경호", "경비업",
        "일반조사", "해외조사", "의료", "보험문제"
    };
    private final String[] REQUEST_STATUS = {
        "상담대기", "상담수락", "상담거절", 
        "의뢰대기", "의뢰수락", "의뢰거절",
        "의뢰진행중", "의뢰완료", "의뢰실패"
    };
    public RandomDate rd = new RandomDate();

    @GetMapping(value="/addTempData")
    @ResponseBody
    public String addTempData() {        
        log.info("requestForm 임시데이터를 생성합니다.");
        AddressTempData atd = new AddressTempData();
        ArrayList<String> tempRegion = new ArrayList<>();
        while(tempRegion.size() < 100) {
            int RegionTemp = (int) Math.floor(Math.random() * atd.getREGIONAL_LOCAL_NAME().length);
            String usersRegion1 = atd.getREGIONAL_LOCAL_NAME()[RegionTemp];
            String usersRegion2 = atd.getREGION2()[RegionTemp][(int) Math.floor(Math.random() * atd.getREGION2()[RegionTemp].length)];
            tempRegion.add(usersRegion1+":"+usersRegion2);
        }
        
        for(int i = 0; i < 100; i++) {
            RequestForm entity = new RequestForm();
            entity.setUsersIdx(Long.valueOf(((int)(Math.floor(Math.random()*10000)))));
            entity.setDetectiveIdx(Long.valueOf(((int)(Math.floor(Math.random()*10000)))));
            entity.setRequestFormCategory(REQUEST_CATEGORY[(int) Math.floor(Math.random() * REQUEST_CATEGORY.length)]);
            entity.setRequestFormStatus(REQUEST_STATUS[(int) Math.floor(Math.random() * REQUEST_STATUS.length)]);
            int tempRegionSeperatorIndex = tempRegion.get(i).indexOf(":");
            entity.setRequestFormRegion1(tempRegion.get(i).substring(0, tempRegionSeperatorIndex));
            entity.setRequestFormRegion2(tempRegion.get(i).substring(tempRegionSeperatorIndex+1, tempRegion.get(i).length()));
            entity.setRequestFormDate(rd.createRandomLocalDateTime(2019, 2021));
            // log.debug("requset: {}", entity.toString());
            requestFormManageService.save(entity);
        }
        return "으딜...";
    }
    
}
