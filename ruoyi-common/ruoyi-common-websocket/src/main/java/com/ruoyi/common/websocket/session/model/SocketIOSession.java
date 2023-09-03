package com.ruoyi.common.websocket.session.model;

import com.corundumstudio.socketio.SocketIOClient;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SocketIOSession {

    /**
     * create time
     */
    @Builder.Default
    private long createTime = System.currentTimeMillis();

    /**
     * heartbeat time
     */
    @Builder.Default
    private long heartbeatTime = System.currentTimeMillis();

    /**
     * survival time
     */
    @Builder.Default
    private long survivalTime = 60000L;

    /**
     * 会话类型
     */
    private String topic;

    /**
     * 时间间隔
     */
    private String interval;

    /**
     * 会话
     */
    private SocketIOClient client;

    /**
     * 销毁会话
     */
    public void destroy() {
        this.heartbeatTime = -1;
        this.topic = null;
        if (this.client.isChannelOpen()) {
            this.client.disconnect();
        }
        this.client = null;
    }

    public boolean isDeath() {
        if (this.client == null || !this.client.isChannelOpen()) {
            return true;
        }
        return System.currentTimeMillis() - this.heartbeatTime > this.survivalTime;
    }

}
