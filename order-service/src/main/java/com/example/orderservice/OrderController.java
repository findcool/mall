package com.example.orderservice;

import com.example.orderservice.feign.CreditFeign;
import com.example.orderservice.feign.WmsFeign;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private CreditFeign creditFeign;
    @Autowired
    private WmsFeign wmsFeign;

    @RequestMapping("/create")
    public String create(@RequestParam(name = "num",required = false,defaultValue = "10") Integer num) {

        System.out.println("下订单，num ：" + num);
        creditFeign.add(num);
        wmsFeign.ship(num);
        return "success";
    }

    @Autowired
    private RedissonClient redissonClient;
    private static final String LOCK_KEY = "myLock";

    @RequestMapping("/lock")
    public String lock(@RequestParam(name = "num",required = false,defaultValue = "10") Integer num) {

        RLock lock = redissonClient.getLock(LOCK_KEY);
        try {
            lock.lock();
            // 加锁成功
            System.out.println("加锁成功！！！");
            Thread.sleep(30000);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            lock.unlock();
        }
        return "success";
    }
}
