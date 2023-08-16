package com.ruoyi.common.websocket;

import com.ruoyi.common.core.constant.CacheConstants;
import com.ruoyi.common.core.constant.TokenConstants;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.common.websocket.session.ClientSessionPool;
import com.ruoyi.common.websocket.session.model.ClientSession;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.yeauty.annotation.*;
import org.yeauty.pojo.Session;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
@ServerEndpoint(value = "/webSocket", port = "9001")
public class WebSocketServer {

    private final RedisService redisService;

    private final ClientSessionPool clientSessionPool;

    @BeforeHandshake
    public void handshake(Session session, @RequestHeader HttpHeaders headers){
        String token = headers.get(TokenConstants.AUTHENTICATION);
        if (StringUtils.isBlank(token)) {
            session.close();
            return;
        }
        if (!redisService.hasKey(CacheConstants.RUOYI_AUTH_TOKEN + token)) {
            session.close();
            return;
        }
        session.setSubprotocols("stomp");
    }

    @OnOpen
    public void onOpen(Session session, @RequestHeader HttpHeaders headers){
        String token = headers.get(TokenConstants.AUTHENTICATION);
        if (StringUtils.isBlank(token)) {
            session.close();
            return;
        }
        clientSessionPool.createSession(token, ClientSession.builder().session(session).build());
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        System.out.println("one connection closed");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        System.out.println(message);
        session.sendText("Hello Netty!");
    }

    @OnBinary
    public void onBinary(Session session, byte[] bytes) {
        for (byte b : bytes) {
            System.out.println(b);
        }
        session.sendBinary(bytes);
    }

    @OnEvent
    public void onEvent(Session session, Object evt) {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            switch (idleStateEvent.state()) {
                case READER_IDLE:
                    System.out.println("read idle");
                    break;
                case WRITER_IDLE:
                    System.out.println("write idle");
                    break;
                case ALL_IDLE:
                    System.out.println("all idle");
                    break;
                default:
                    break;
            }
        }
    }
}
