package com.ruoyi.client.job;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.client.websocket.WebSocketServer;
import com.ruoyi.client.websocket.core.ClientUserSession;
import com.ruoyi.client.websocket.message.IndexKLineMsg;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestJob {

    @Scheduled(fixedDelay = 5000)
    public void test() {
        if (WebSocketServer.sessions.isEmpty()) {
            return;
        }
        for (ClientUserSession session: WebSocketServer.sessions.values()) {
            IndexKLineMsg kLineMsg = IndexKLineMsg.builder()
                    .productCode("EURUSD")
                    .low(1.1)
                    .high(1.2)
                    .open(1.12)
                    .close(1.2)
                    .volume(1.11)
                    .timestamp(System.currentTimeMillis())
                    .build();
            session.getSession().sendText(JSONObject.toJSONString(kLineMsg));
        }
    }

}
