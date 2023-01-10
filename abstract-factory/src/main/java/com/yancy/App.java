package com.yancy;

/**
 * 测试类
 * @author yancy0109
 */
public class App {
    public static void main(String[] args) {

        // 美式甜品工厂
        AmericanDessertFactory americanDessertFactory = new AmericanDessertFactory();
        System.out.println(americanDessertFactory.getDessert().getName());  // 提拉米苏
        System.out.println(americanDessertFactory.getCoffer().getName());  // 美式咖啡

        // 意大利甜品工厂
        ItalyDessertFactory italyDessertFactory = new ItalyDessertFactory();
        System.out.println(italyDessertFactory.getDessert().getName());  // 抹茶慕斯
        System.out.println(italyDessertFactory.getCoffer().getName());  // 拿铁咖啡
    }
}
