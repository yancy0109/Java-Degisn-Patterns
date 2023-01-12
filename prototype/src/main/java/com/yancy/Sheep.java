package com.yancy;

/**
 * 原型模式
 * 原型实例：多利羊
 * @author yancy0109
 */
public class Sheep extends ProtoType<Sheep>{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
