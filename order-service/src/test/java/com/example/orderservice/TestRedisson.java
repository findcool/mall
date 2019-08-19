package com.example.orderservice;

import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;


public class TestRedisson {
    public static void main(String[] args) {
        // 1. Create config object
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.85.156:6379").setPassword("myreids");

// or read config from file
//        config = Config.fromYAML(new File("config-file.yaml"));
// 2. Create Redisson instance

// Sync and Async API
        RedissonClient redisson = Redisson.create(config);

// 3. Get Redis based Map
        RMap<String, String> map = redisson.getMap("myMap");
        for (String key : map.keySet()) {
            System.out.println(key + ": " + map.get(key));
        }

        map.put("k1", "nihao");
        map.put("k2", "ihao");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("**********************************");
// 4. Get Redis based Lock

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                RLock lock = redisson.getLock("myLock");
                lock.lock();

                System.out.println("thread  获取锁了----------------------------");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
                System.out.println("thread   释放锁---------------------");

            }
        });

        thread.start();
        RLock lock = redisson.getLock("myLock");
        lock.lock();
        System.out.println("main 获取锁了-----------------------");
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock.unlock();
        System.out.println("main   释放锁---------------------");
// 4. Get Redis based ExecutorService
//        RExecutorService executor = redisson.getExecutorService("myExecutorService");
    }

}
