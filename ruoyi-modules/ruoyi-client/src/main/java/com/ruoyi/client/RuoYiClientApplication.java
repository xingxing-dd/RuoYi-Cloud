package com.ruoyi.client;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import static com.ruoyi.common.core.constant.MarketConstant.PRODUCT_PRICE_INFO_KEY;

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
        SpringApplication.run(RuoYiClientApplication.class, args);
    }

}
