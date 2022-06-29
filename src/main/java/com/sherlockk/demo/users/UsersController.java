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
    
    @Operation(summary="infoUser", description="usersIdx기반 유저 정보 조회(테스트용)")
    @GetMapping("/infoUser")
    public String TestGet(
        @Parameter(name="usersIdx", description="조회할 유저 인덱스")
        @RequestParam("usersIdx") String usersIdx
    ) {
        Optional<Users> query = usersService.findById(Long.parseLong(usersIdx));
        return query.toString();
    }

    @Operation(summary="signUp", description="회원가입")
    @PostMapping("/signUp")
    public Users TestPost(Users param) {
        // System.out.printf("# %s", param.toString());
        return usersService.save(param);
    }


}
