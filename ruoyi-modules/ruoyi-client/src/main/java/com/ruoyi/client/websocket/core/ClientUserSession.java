package com.ruoyi.client.websocket.core;

import lombok.Builder;
import lombok.Data;
import org.yeauty.pojo.Session;

@Data
@Builder
public class ClientUserSession {

    private String type;

    private Session session;

}
