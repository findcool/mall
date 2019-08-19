package com.example.creditservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credit")
public class CreditController {
    @RequestMapping("/add")
    public String add(@RequestParam Integer num) {

        System.out.println("积分系统增加积分: " + num);
        return "success";
    }
}
