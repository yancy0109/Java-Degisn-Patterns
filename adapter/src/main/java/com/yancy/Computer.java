package com.yancy;

/**
 * 调用者类
 * 负责调用SDCard对象进行读取
 * @author yancy0109
 */
public class Computer {

    public String readSD(SDCard sdCard) {
        return sdCard.readSD();
    }
}
