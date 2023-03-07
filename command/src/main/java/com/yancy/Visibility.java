package com.yancy;

/**
 * 对象状态枚举类
 * @author yancy0109
 */
public enum Visibility {

    Invisibility("隐身"),
    NORMAL("可见");

    private String value;

    Visibility(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
