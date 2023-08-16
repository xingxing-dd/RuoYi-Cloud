package com.ruoyi.common.websocket.session;

import com.ruoyi.common.websocket.session.model.ClientSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.yeauty.pojo.Session;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户会话池
 */
@Slf4j
@Component
public class ClientSessionPool {

    private static final ConcurrentHashMap<String, ClientSession> sessions = new ConcurrentHashMap<>();

    public void createSession(String token, ClientSession clientSession) {
        if (sessions.containsKey(token)) {
            return;
        }
        sessions.put(token, clientSession);
    }

    public void subscribe(String token, String topic) {
        if (sessions.containsKey(token)) {
            return;
        }
        //subscribe msg
        sessions.get(token).setTopic(topic);
    }

    public void closeSession(String token) {
        if (!sessions.containsKey(token)) {
            return;
        }
        //destroy session，release resource
        sessions.get(token).destroy();
        sessions.remove(token);
    }

    public ClientSession getSession(String token) {
        return sessions.get(token);
    }

    public int sessionCount() {
        return sessions.size();
    }

    @Scheduled(fixedDelay = 60 * 1000)
    public void scanSession() {
        Iterator<Map.Entry<String, ClientSession>> iterator = sessions.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, ClientSession> entry = iterator.next();
            if (entry.getValue().isDeath()) {
                return;
            }
            log.info("Session timed out, closing soon,{}", entry.getKey());
            iterator.remove();
        }
    }

}
