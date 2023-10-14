package com.ruoyi.market.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpringContextHolder {

    private final ConfigurableApplicationContext context;

    public <T> T getBean(String name, Class<T> c) {
        try {
            return context.getBean(name, c);
        } catch (Exception e) {
            return null;
        }
    }

}
