package com.ruoyi.common.websocket.session.model;

import lombok.Builder;
import lombok.Data;
import org.yeauty.pojo.Session;

@Data
@Builder
public class ClientSession {

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
     * 会话
     */
    private Session session;

    /**
     * 销毁会话
     */
    public void destroy() {
        this.heartbeatTime = -1;
        this.topic = null;
        if (this.session.isActive()) {
            this.session.close();
        }
        this.session = null;
    }

    public boolean isDeath() {
        if (this.session == null || !this.session.isActive()) {
            return true;
        }
        return System.currentTimeMillis() - this.heartbeatTime > this.survivalTime;
    }

}
