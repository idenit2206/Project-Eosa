package com.sherlockk.demo.testa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/TestA")
public class TestAController {

    @Autowired
    private TestAService testAService;
    
    @GetMapping("/method01")
    public String method01() {
        return "method01()";
    }

    @GetMapping("/method02")
    public String method02(TestA testA) {
        testAService.customAdd(testA);
        return testA.toString();
    }

}
