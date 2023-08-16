package com.ruoyi.common.websocket.constant;

import com.ruoyi.common.core.utils.StringUtils;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum RegisterMsgType {

    INDEX("index", "首页");

    private String type;

    private String desc;

    RegisterMsgType(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static boolean exist(String type) {
        for (RegisterMsgType value: RegisterMsgType.values()) {
            if (StringUtils.equals(type, value.type)) {
                return true;
            }
        }
        return false;
    }

}
