package com.yancy;

/**
 * 测试类
 * @author yancy0109
 */
public class CoffeeStore {

    static Coffee orderAmericanCoffee () {
        // 在Client决定具体工厂
        AmericanCoffeeFactory americanCoffeeFactory = new AmericanCoffeeFactory();
        return americanCoffeeFactory.getCoffer();
    }
    static Coffee orderLatteeCoffee () {
        // 在Client决定具体工厂
        LatteCoffeeFactory latteCoffeeFactory = new LatteCoffeeFactory();
        return latteCoffeeFactory.getCoffer();
    }

    public static void main(String[] args) {
        // 测试
        System.out.println(orderAmericanCoffee().getName());  // 美式咖啡
        System.out.println(orderLatteeCoffee().getName());  // 拿铁咖啡
    }
}
