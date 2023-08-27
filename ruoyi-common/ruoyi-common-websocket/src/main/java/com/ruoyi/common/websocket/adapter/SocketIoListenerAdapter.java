package com.ruoyi.common.websocket.adapter;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ExceptionListenerAdapter;
import com.ruoyi.common.websocket.session.SocketIOSessionPool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SocketIoListenerAdapter extends ExceptionListenerAdapter {

    private final SocketIOSessionPool socketIOSessionPool;

    @Override
    public void onConnectException(Exception e, SocketIOClient client) {
        log.warn("连接异常,sessionId={}", client.getSessionId().toString(), e);
    }

    @Override
    public void onDisconnectException(Exception e, SocketIOClient client) {
        log.warn("断开连接异常,sessionId={}", client.getSessionId().toString(), e);
    }

    @Override
    public void onEventException(Exception e, List<Object> data, SocketIOClient client) {
        log.warn("事件异常,sessionId={}", client.getSessionId().toString(), e);
    }

    @Override
    public void onPingException(Exception e, SocketIOClient client) {
        if (socketIOSessionPool == null || socketIOSessionPool.getSessions() == null) {
            return;
        }
        socketIOSessionPool.closeSession(client.getSessionId().toString());
        log.warn("ping超时,sessionId={},连接已断开", client.getSessionId().toString());
    }
}
