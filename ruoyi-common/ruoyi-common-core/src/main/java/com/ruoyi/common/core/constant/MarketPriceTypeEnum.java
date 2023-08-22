package com.ruoyi.common.core.constant;

import jdk.nashorn.internal.objects.annotations.Getter;

public enum MarketPriceTypeEnum {

    MK_1M("1m", 60000L, ""),
    MK_5M("5m", 5 * 60000L, ""),
    MK_15M("15m", 15 * 60000L, ""),
    MK_30M("30m", 30 * 60000L, ""),
    MK_1H("1h", 60 * 60000L, ""),
    MK_1D("1d", 24 * 60 * 60000L, "");

    private String key;

    private long interval;

    private String desc;

    MarketPriceTypeEnum(String key, long interval, String desc) {
        this.key = key;
        this.interval = interval;
        this.desc = desc;
    }

    public long getInterval() {
        return interval;
    }

    public String getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }
}
