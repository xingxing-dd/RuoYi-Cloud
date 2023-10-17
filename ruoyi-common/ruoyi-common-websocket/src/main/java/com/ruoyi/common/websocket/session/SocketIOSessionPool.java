package com.ruoyi.common.websocket.session;

import com.ruoyi.common.websocket.session.model.SocketIOSession;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户会话池
 */
@Slf4j
@Component
public class SocketIOSessionPool {

    private static final ConcurrentHashMap<String, SocketIOSession> sessions = new ConcurrentHashMap<>();

    public void createSession(SocketIOSession socketIOSession) {
        String uuid = socketIOSession.getClient().getSessionId().toString();
        if (sessions.containsKey(uuid)) {
            return;
        }
        sessions.put(uuid, socketIOSession);
    }

    public ConcurrentHashMap<String, SocketIOSession> getSessions() {
        return sessions;
    }

    public void closeSession(String uuid) {
        if (!sessions.containsKey(uuid)) {
            return;
        }
        //destroy session，release resource
        sessions.get(uuid).destroy();
        sessions.remove(uuid);
    }

    public SocketIOSession getSession(String token) {
        return sessions.get(token);
    }

    public int sessionCount() {
        return sessions.size();
    }

}
