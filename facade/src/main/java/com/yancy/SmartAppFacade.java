package com.yancy;

/**
 * @author yancy0109
 * 外观角色：智能家居系统
 */
public class SmartAppFacade {

    private Tv tv = new Tv();

    private Light light = new Light();

    private AirCondition airCondition = new AirCondition();

    void on () {
        tv.on();
        light.on();
        airCondition.on();
        System.out.println("智能家居开启完毕");
    }
    void off () {
        tv.off();
        light.off();
        airCondition.off();
        System.out.println("智能家居关闭完毕");
    }
}
