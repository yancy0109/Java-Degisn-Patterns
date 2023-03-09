package com.yancy;

/**
 * 测试类
 * @author yancy0109
 */
public class App {
    public static void main(String[] args) {
        // 状态类
        Mammoth mammoth = new Mammoth();
        mammoth.observe();  // 猛犸象非常平静与和平
        // 状态改变
        mammoth.timePasses();   // 猛犸象愤怒了起来
        mammoth.observe();  // 猛犸象正在暴怒

        mammoth.timePasses();   // 猛犸象平静了下来
        mammoth.observe();  // 猛犸象非常平静与和平
    }
}
