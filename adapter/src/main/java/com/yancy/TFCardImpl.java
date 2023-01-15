package com.yancy;

/**
 * TFCard接口实现类
 * 适配者类实现类
 * @author yancy0109
 */
public class TFCardImpl implements TFCard {

    @Override
    public String readTF() {
        return "Read from TF";
    }
}
