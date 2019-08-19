package com.example.orderservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "credit-service")
public interface CreditFeign {
    @RequestMapping("/credit/add")
    String add(@RequestParam(name = "num") Integer num);
}
