package com.ruoyi.client;

import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 客户端相关服务
 */
@EnableScheduling
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class RuoYiClientApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(RuoYiClientApplication.class, args);
        RedisService redisService = applicationContext.getBean(RedisService.class);
        Object obj = redisService.getCacheObject("ruoyi-market:product:price:BTCUSD:20231007");
        System.out.println(obj);
    }

}
