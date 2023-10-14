package com.ruoyi.common.websocket.handler;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.ruoyi.common.core.constant.TokenConstants;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.websocket.message.RegisterMessage;
import com.ruoyi.common.websocket.session.SocketIOSessionPool;
import com.ruoyi.common.websocket.session.model.SocketIOSession;
import com.sun.nio.sctp.MessageInfo;
import io.netty.handler.codec.base64.Base64Decoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.annotation.Resource;
import java.util.Base64;
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

    @OnEvent("registry")
    public void onRegistry(SocketIOClient socketIOClient, RegisterMessage message) {
        log.info("接收到消息注册：{}", message);
        SocketIOSession socketIOSession = sessionPool.getSession(socketIOClient.getSessionId().toString());
        if (socketIOSession == null) {
            return;
        }
        socketIOSession.setTopic(message.getProduct());
        socketIOSession.setInterval(message.getInterval());
        socketIOSession.setHeartbeatTime(System.currentTimeMillis());
        if (StringUtils.isNotEmpty(message.getToken())) {
            String token = new String(Base64.getDecoder().decode(message.getToken()));
            log.info("截取:{}", token.substring(token.lastIndexOf("{"), token.lastIndexOf("}")));
        }
        socketIOClient.sendEvent("message", "ok");
    }

}
