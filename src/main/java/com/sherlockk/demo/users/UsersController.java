package com.sherlockk.demo.users;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping(value="/api/v1/user")
public class UsersController {

    @Autowired
    private UsersService usersService;
    
    @Operation(summary="Get test", description="Get test")
    @GetMapping("/testGet")
    public String TestGet(
        @Parameter(name="usersIdx", description="조회할 유저 인덱스")
        @RequestParam("usersIdx") String usersIdx
    ) {
        Optional<Users> query = usersService.findById(Long.parseLong(usersIdx));
        return query.toString();
    }

    @Operation(summary="Post test", description="Post test")
    @PostMapping("/testPost")
    public Users TestPost(Users param) {
        System.out.printf("# %s", param.toString());
        return usersService.save(param);
    }

    @Operation(summary="Post test02", description="Post test02")
    @PostMapping("/testPost02")
    public String TestPost(@RequestParam(value="column1")String column1, @RequestParam(value="column2")String column2) {
        return column1; 
    }

}
