package com.eosa.admin.reviewmanage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eosa.admin.util.pagination.PageList;
import com.eosa.admin.util.pagination.PostList;
import com.eosa.web.usersreview.UsersReview;

@Controller
@RequestMapping("/admin/reviewManage")
public class ReviewManageController {

    @Autowired ReviewManageService reviewManageService;

    final private int POST_COUNT = 10;
    final private int BLOCK_COUNT = 10;
    private PostList postList = new PostList(POST_COUNT, BLOCK_COUNT);   

    @GetMapping("/reviewList")
    public String showReviewList(
        @RequestParam(value="currentPage", defaultValue="1") int currentPage,
        Model model
    ) {       
        int currentPageStartPost = postList.getCurrentPageStartPost(currentPage);
        List<UsersReview> usersReviewList = reviewManageService.findAllUsersReview(currentPageStartPost, POST_COUNT);
        int allPostCount = reviewManageService.findAllReviewCount();
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
        model.addAttribute("reviewList", usersReviewList);
        model.addAttribute("pagination", pagination);

        return "admin/reviewmanage/ReviewList";
    }

}
