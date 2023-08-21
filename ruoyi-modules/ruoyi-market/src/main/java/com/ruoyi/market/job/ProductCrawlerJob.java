package com.ruoyi.market.job;

import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.common.websocket.session.SocketIOSessionPool;
import com.ruoyi.common.websocket.session.model.SocketIOSession;
import com.ruoyi.market.mapper.ProductPriceMapper;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

@Slf4j
@Component
public class ProductCrawlerJob {

    private static WebDriver webDriver;

    private static String lastPrice;

    @Resource
    private RedisService redisService;

    @Resource
    private ProductPriceMapper productPriceMapper;

    @Resource
    private SocketIOSessionPool sessionPool;

    @Scheduled(fixedDelay = 5000)
    public void crawler() throws Exception {
        try {
            createWebDriver();
            if (webDriver == null) {
                throw new RuntimeException("获取浏览器驱动失败");
            }
            doCrawler();
        } catch (Throwable e) {
            log.error("浏览器崩溃，重新构建驱动", e);
            if (webDriver != null) {
                webDriver.close();
            }
            webDriver = null;
            createWebDriver();
        }
    }

    private void createWebDriver() throws MalformedURLException {
        if (webDriver != null) {
            return;
        }
        log.info("开始构建web驱动");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        webDriver = new RemoteWebDriver(new URL("http://134.122.188.80:4444/wd/hub"), firefoxOptions);
        webDriver.get("https://cn.tradingview.com/symbols/BNBUSD/?exchange=CRYPTO");
        log.info("构建驱动成功");
    }

    private void doCrawler() {
        WebElement element = webDriver.findElement(By.className("last-JWoJqCpY"));
        if (element == null) {
            return;
        }
        String currentPrice = element.getText();
        if (currentPrice == null || currentPrice.equals(lastPrice)) {
            return;
        }
        String message = String.format("时间戳：%s,产品：%s,波动前价格：%s,当前价格：%s", System.nanoTime(), "BNBUSD", lastPrice, currentPrice);
        log.info("{}", message);
        lastPrice = currentPrice;
        if (sessionPool.sessionCount() <= 0) {
            return;
        }
        for (Map.Entry<String, SocketIOSession> entry: sessionPool.getSessions().entrySet()) {
            entry.getValue().getClient().sendEvent("message", message);
        }
    }

}
