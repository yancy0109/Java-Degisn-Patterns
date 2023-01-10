package com.yancy;

/**
 * 具体工厂
 * 美式咖啡工厂
 * @author yancy0109
 */
public class AmericanCoffeeFactory implements CoffeeFactory{

    @Override
    public Coffee getCoffer() {
        return new AmericanCoffee();
    }
}
