package com.ruoyi.market.crawler;

import com.ruoyi.common.websocket.session.SocketIOSessionPool;
import com.ruoyi.common.websocket.session.model.SocketIOSession;
import com.ruoyi.market.crawler.core.helper.ProductPriceHelper;
import com.ruoyi.market.domain.ProductInfo;
import com.ruoyi.market.websocket.WebSocketMessageSender;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class ProductPriceCrawlerJob {

    private RemoteWebDriver webDriver;

    private List<ProductInfo> productInfos;

    private List<String> windowHandlerList;

    @Resource
    private SocketIOSessionPool sessionPool;

    @Resource
    private ProductPriceHelper productPriceHelper;

    @Resource
    private WebSocketMessageSender webSocketMessageSender;

    @Scheduled(fixedDelay = 5000)
    public void crawler() throws Exception {
        try {
            createWebDriver();
            if (webDriver == null) {
                throw new RuntimeException("获取浏览器驱动失败");
            }
            for (int index = 0; index < windowHandlerList.size(); index++) {
                webDriver.switchTo().window(windowHandlerList.get(index));
                ProductInfo productInfo = productInfos.get(index);
                doCrawler(productInfo.getProductCode());
            }
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
        productInfos = productPriceHelper.getValidProduct();
        windowHandlerList = new ArrayList<>();
        log.info("开始构建web驱动");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        webDriver = new RemoteWebDriver(new URL("http://134.122.188.80:4444/wd/hub"), firefoxOptions);
        String blankPageWindow = webDriver.getWindowHandle();
        for(int index = 0; index < productInfos.size(); index++) {
            webDriver.switchTo().newWindow(WindowType.WINDOW).get(productInfos.get(index).getSource());
            windowHandlerList.add(webDriver.getWindowHandle());
        }
        webDriver.switchTo().window(blankPageWindow);
        webDriver.close();
        log.info("构建驱动成功");
    }

    private void doCrawler(String productCode) {
        WebElement element = webDriver.findElement(By.className("last-JWoJqCpY"));
        if (element == null) {
            return;
        }
        String currentPrice = element.getText();
        if (currentPrice == null) {
            return;
        }
        boolean hasPriceChange = productPriceHelper.doRefreshPrice(productCode, new BigDecimal(currentPrice), new Date((System.currentTimeMillis() / 60000) * 60000));
        log.info("产品价格波动:{},准备发送通知,当前会话数量{}", productCode, sessionPool.sessionCount());
        if (!hasPriceChange || sessionPool.sessionCount() <= 0) {
            return;
        }
        for (Map.Entry<String, SocketIOSession> entry: sessionPool.getSessions().entrySet()) {
            webSocketMessageSender.send(entry.getValue(), productCode);
        }
    }


}
