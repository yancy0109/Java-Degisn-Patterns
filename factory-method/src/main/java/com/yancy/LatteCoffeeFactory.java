package com.yancy;

/**
 * 具体工厂
 * 拿铁咖啡工厂
 * @author yancy0109
 */
public class LatteCoffeeFactory implements CoffeeFactory{

    @Override
    public Coffee getCoffer() {
        return new LatteCoffee();
    }
}
