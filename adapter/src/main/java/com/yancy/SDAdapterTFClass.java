package com.yancy;

/**
 * 适配器类
 * 类适配器模式
 * TF读取适配器
 * @author yancy0109
 */
public class SDAdapterTFClass extends TFCardImpl implements SDCard{

    @Override
    public String readSD() {
        return "通过Adapter进行读取：" + this.readTF();
    }
}
