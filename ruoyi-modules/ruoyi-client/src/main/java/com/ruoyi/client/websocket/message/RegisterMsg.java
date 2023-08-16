package com.ruoyi.client.websocket.message;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class RegisterMsg implements Serializable {

    private String type;

}
