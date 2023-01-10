package com.yancy;

/**
 * 抽象工厂
 * 咖啡工厂接口
 * @author yancy0109
 */
public interface DessertFactory {
    Coffee getCoffer();

    Dessert getDessert();
}
