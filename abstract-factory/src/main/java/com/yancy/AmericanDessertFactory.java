package com.yancy;

/**
 * 具体工厂
 * 美式甜品工厂
 * @author yancy0109
 */
public class AmericanDessertFactory implements DessertFactory {

    @Override
    public Dessert getDessert() {
        return new Tiramisu();
    }

    @Override
    public Coffee getCoffer() {
        return new AmericanCoffee();
    }
}
