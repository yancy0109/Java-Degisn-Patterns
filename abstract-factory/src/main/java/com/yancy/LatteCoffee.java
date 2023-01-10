package com.yancy;

/**
 * 具体产品
 * 拿铁咖啡
 * @author yancy0109
 */
public class LatteCoffee implements Coffee {

    @Override
    public String getName() {
        return "拿铁咖啡";
    }

    @Override
    public void addMilk() {

    }

    @Override
    public void addSugar() {

    }
}
