package com.example.wmsservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wms")
public class WmsController {

    @RequestMapping("ship")
    public String ship(@RequestParam Integer num) {
        System.out.println("仓储系统发货num个");
        return "success";
    }
}
