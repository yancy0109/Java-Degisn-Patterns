package com.yancy;

/**
 * 具体工厂
 * 意大利甜品工厂
 * @author yancy0109
 */
public class ItalyDessertFactory implements DessertFactory {

    @Override
    public Coffee getCoffer() {
        return new LatteCoffee();
    }

    @Override
    public Dessert getDessert() {
        return new  MatchaMousse();
    }
}
