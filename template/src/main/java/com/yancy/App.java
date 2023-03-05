package com.yancy;

/**
 * 测试类
 * @author yancy0109
 */
public class App {
    public static void main(String[] args) {
        AbstactClass dish = new ConcreteDish();
        dish.cook();

        // out >>>>>>
        // 倒油
        // 加点肉and菜
        // 翻炒
    }
}
