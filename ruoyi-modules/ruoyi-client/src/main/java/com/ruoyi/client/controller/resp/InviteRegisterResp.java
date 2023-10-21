package com.ruoyi.client.controller.resp;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class InviteRegisterResp implements Serializable {

    private String inviteUrl;

    private String inviteQrcode;

}
