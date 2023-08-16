package com.ruoyi.client.websocket;

import com.ruoyi.client.websocket.core.ClientUserSession;
import com.ruoyi.client.websocket.core.constant.RegisterMsgType;
import com.ruoyi.common.core.utils.StringUtils;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.timeout.IdleStateEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.yeauty.annotation.*;
import org.yeauty.pojo.Session;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint(value = "/websocket/{userId}", port = "9001")
public class WebSocketServer {

    public final static ConcurrentHashMap<String, ClientUserSession> sessions = new ConcurrentHashMap<>(16);

    @BeforeHandshake
    public void handshake(Session session, HttpHeaders headers, @RequestParam String req, @RequestParam MultiValueMap reqMap, @PathVariable("userId") String userId, @PathVariable Map pathMap){
        session.setSubprotocols("stomp");
        if (StringUtils.isEmpty(userId)){
            System.out.println("Authentication failed!");
            session.close();
        }
    }
    @OnOpen
    public void onOpen(Session session, HttpHeaders headers, @RequestParam String req, @RequestParam MultiValueMap reqMap, @PathVariable("userId") String userId, @PathVariable Map pathMap){
        if (sessions.containsKey(userId)) {
            return;
        }
        sessions.put(userId, ClientUserSession.builder().session(session).build());
    }

    @OnClose
    public void onClose(Session session, @PathVariable("userId") String userId) throws IOException {
        System.out.println("one connection closed");
        if (!sessions.containsKey(userId)) {
            return;
        }
        sessions.remove(userId);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    @OnMessage
    public void onMessage(Session session, String message, @PathVariable("userId") String userId) {
        System.out.println(message);
        if (!RegisterMsgType.exist(message)) {
            session.sendText("Invalid message!");
            return;
        }
        ClientUserSession clientUserSession = sessions.get(userId);
        if (clientUserSession == null) {
            clientUserSession = ClientUserSession.builder().session(session).build();
        }
        clientUserSession.setType(message);
        session.sendText("Register success!");
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
