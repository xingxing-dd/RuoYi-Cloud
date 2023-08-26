package com.ruoyi.common.websocket.handler;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.ruoyi.common.core.constant.TokenConstants;
import com.ruoyi.common.websocket.session.SocketIOSessionPool;
import com.ruoyi.common.websocket.session.model.SocketIOSession;
import com.sun.nio.sctp.MessageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.annotation.Resource;
import java.util.HashMap;

@Slf4j
@Component
public class SocketIOServerHandler {

    @Resource
    private SocketIOSessionPool sessionPool;

    @OnConnect
    public void onConnect(SocketIOClient socketIOClient) {
        log.info("连接成功：{}", socketIOClient.getSessionId());
        sessionPool.createSession(SocketIOSession.builder().client(socketIOClient).build());
        socketIOClient.sendEvent("message", "connection success");
    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient socketIOClient) {
        log.info("连接断开：{}", socketIOClient.getSessionId());
        sessionPool.closeSession(socketIOClient.getSessionId().toString());
    }

    @OnEvent("heartbeat")
    public void onHeartbeat(SocketIOClient socketIOClient, AckRequest ackRequest) {
        log.info("接受到心跳信息");
        SocketIOSession socketIOSession = sessionPool.getSession(socketIOClient.getSessionId().toString());
        if (socketIOSession == null) {
            return;
        }
        socketIOSession.setHeartbeatTime(System.currentTimeMillis());
        socketIOClient.sendEvent("message", "ok");
    }

    @OnEvent("registry")
    public void onRegistry(SocketIOClient socketIOClient, String registry) {
        SocketIOSession socketIOSession = sessionPool.getSession(socketIOClient.getSessionId().toString());
        if (socketIOSession == null) {
            return;
        }
        socketIOSession.setTopic(registry);
        socketIOSession.setHeartbeatTime(System.currentTimeMillis());
        socketIOClient.sendEvent("message", "ok");
    }

}
