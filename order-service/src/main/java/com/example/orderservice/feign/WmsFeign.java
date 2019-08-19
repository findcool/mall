package com.example.orderservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "wms-service")
public interface WmsFeign {
    @RequestMapping("/wms/ship")
    String ship(@RequestParam(name = "num") Integer num);
}
