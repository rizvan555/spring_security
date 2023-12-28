package com.rizvankarimov.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @GetMapping("/")
    public String getInfoForAllEmps(){
        return "view-for-all-employees";
    }

    @GetMapping("/hr_info")
    public String getHrInfo(){
        return "view-for-hr";
    }

    @GetMapping("/manager_info")
    public String getManagerInfo(){
        return "view-for-managers";
    }
}
