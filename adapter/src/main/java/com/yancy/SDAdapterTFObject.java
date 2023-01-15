package com.yancy;

import lombok.AllArgsConstructor;

/**
 * 适配器类
 * 对象适配器模式
 * TF读取适配器
 * @author yancy0109
 */
@AllArgsConstructor
public class SDAdapterTFObject implements SDCard{

    private TFCard tfCard;
    @Override
    public String readSD() {
        return "通过Adapter进行读取：" + tfCard.readTF();
    }
}
