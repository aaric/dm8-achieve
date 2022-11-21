package com.sample.dm8.common;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * SexEnum
 *
 * @author Aaric, created on 2022-11-21T15:23.
 * @version 0.2.0-SNAPSHOT
 */
public enum SexEnum {

    UNKNOWN(0, "未知"),
    MALE(1, "男"),
    FEMALE(2, "女");

    SexEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @EnumValue
    private final int code;
    private final String desc;
}
