package com.yancy;

/**
 * 测试类
 * @author yancy0109
 */
public class App {
    public static void main(String[] args) {
        // 通过调用类进行测试
        Computer computer = new Computer();
        // 可用类
        SDCardImpl sdCard = new SDCardImpl();  // Read from SD
        System.out.println(computer.readSD(sdCard));
        // 类适配器模式 适配器类
        SDAdapterTFClass sdAdapterTFClass = new SDAdapterTFClass();
        System.out.println(computer.readSD(sdAdapterTFClass));  // 通过Adapter进行读取：Read from TF

        // 对象适配器模式 适配器类
        SDAdapterTFObject sdAdapterTFObject = new SDAdapterTFObject(new TFCardImpl());
        System.out.println(sdAdapterTFObject.readSD());  // 通过Adapter进行读取：Read from TF
    }
}
