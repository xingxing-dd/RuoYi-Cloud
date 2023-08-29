package com.ruoyi.client.domain.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class ClientUserInfoVo implements Serializable {

    /** 用户id */
    private Long userId;

    /** 用户名 */
    private String userName;

    /** 昵称 */
    private String nickName;

    /** 邮箱 */
    private String email;

    /** 电话号码 */
    private String phonenumber;

    /** 头像 */
    private String avatar;

    /** 余额 */
    private BigDecimal totalAmount;

    /** 币种 */
    private String currency;

}
