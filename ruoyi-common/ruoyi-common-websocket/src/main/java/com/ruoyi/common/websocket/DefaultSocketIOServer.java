package com.ruoyi.common.websocket;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.Transport;
import com.ruoyi.common.websocket.handler.SocketIOServerHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class DefaultSocketIOServer implements InitializingBean {

    @Resource
    private SocketIOServerHandler socketIOServerHandler;

    @Value("${socket.io.host:0.0.0.0}")
    private String host;

    @Value("${socket.io.port:9001}")
    private Integer port;

    @Value("${socket.io.bossCount:100}")
    private int bossCount;

    @Value("${socket.io.workCount:100}")
    private int workCount;

    @Value("${socket.io.allowCustomRequests:true}")
    private boolean allowCustomRequests;

    @Value("${socket.io.upgradeTimeout:100000}")
    private int upgradeTimeout;

    @Value("${socket.io.pingTimeout:600000}")
    private int pingTimeout;

    @Value("${socket.io.pingInterval:20000}")
    private int pingInterval;

    @Override
    public void afterPropertiesSet() throws Exception {
        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setReuseAddress(true);
        socketConfig.setTcpNoDelay(true);
        socketConfig.setSoLinger(0);

        Configuration configuration = new Configuration();
        configuration.setSocketConfig(socketConfig);
        // host在本地测试可以设置为localhost或者本机IP，在Linux服务器跑可换成服务器IP
        configuration.setHostname(host);
        configuration.setPort(port);
        // socket连接数大小（如只监听一个端口boss线程组为1即可）
        configuration.setBossThreads(bossCount);
        configuration.setWorkerThreads(workCount);
        configuration.setAllowCustomRequests(allowCustomRequests);
        // 协议升级超时时间（毫秒），默认10秒。HTTP握手升级为ws协议超时时间
        configuration.setUpgradeTimeout(upgradeTimeout);
        // Ping消息超时时间（毫秒），默认60秒，这个时间间隔内没有接收到心跳消息就会发送超时事件
        configuration.setPingTimeout(pingTimeout);
        // Ping消息间隔（毫秒），默认25秒。客户端向服务器发送一条心跳消息间隔
        configuration.setPingInterval(pingInterval);

        configuration.setTransports(Transport.POLLING, Transport.WEBSOCKET);
        configuration.setOrigin(":*:");
        SocketIOServer socketIOServer = new SocketIOServer(configuration);
        socketIOServer.addListeners(socketIOServerHandler);
        socketIOServer.start();
        log.info("socket io server start successfully!");
    }
}
