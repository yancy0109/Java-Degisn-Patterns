package com.yancy;

/**
 * 测试
 * @author yancy0109
 */
public class App {
    public static void main(String[] args) {
        // 创建外观对象
        SmartAppFacade smartAppFacade = new SmartAppFacade();

        // 开启
        smartAppFacade.on();
        // 输出：
        // 开电视
        // 开灯
        // 开空调
        //  智能家居开启完毕

        // 关闭
        smartAppFacade.off();
        // 输出：
        // 关电视
        // 开灯
        // 关空调
        // 智能家居关闭完毕
    }
}
