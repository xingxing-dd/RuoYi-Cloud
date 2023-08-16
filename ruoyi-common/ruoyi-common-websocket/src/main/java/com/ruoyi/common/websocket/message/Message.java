package com.ruoyi.common.websocket.message;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Builder
@ToString
public class Message<T> implements Serializable {

    private String type;

    private T body;

}
