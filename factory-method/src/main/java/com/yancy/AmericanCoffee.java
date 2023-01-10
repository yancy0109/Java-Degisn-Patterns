package com.yancy;

/**
 * 具体产品
 * 美式咖啡
 * @author yancy0109
 */
public class AmericanCoffee implements Coffee {

    @Override
    public String getName() {
        return "美式咖啡";
    }

    @Override
    public void addMilk() {

    }

    @Override
    public void addSugar() {

    }
}
