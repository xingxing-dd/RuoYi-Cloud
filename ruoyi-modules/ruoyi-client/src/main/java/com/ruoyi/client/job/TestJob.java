package com.ruoyi.client.job;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.websocket.session.SocketIOSessionPool;
import com.ruoyi.common.websocket.session.model.SocketIOSession;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class TestJob {

    private final SocketIOSessionPool sessionPool;

    @Scheduled(fixedDelay = 5000)
    public void test() {
        if (sessionPool.sessionCount() <= 0) {
            return;
        }
        for (Map.Entry<String, SocketIOSession> entry: sessionPool.getSessions().entrySet()) {
            entry.getValue().getClient().sendEvent("message", System.currentTimeMillis());
        }
    }

}
