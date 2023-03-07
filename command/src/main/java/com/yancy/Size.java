package com.yancy;

/**
 * 对象状态枚举类
 * @author yancy0109
 */
public enum Size {

    BIG("大"),
    NORMAL("正常");

    private String value;

    Size(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
