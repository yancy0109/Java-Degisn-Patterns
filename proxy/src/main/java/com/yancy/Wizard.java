package com.yancy;

/**
 * 实体类：巫师
 * @author yancy0109
 */
public class Wizard {
    private String name;

    public Wizard(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
