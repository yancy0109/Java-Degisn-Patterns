package com.yancy;

/**
 * SDCardImpl实现类
 * 目标接口可用实现类
 * @author yancy0109
 */
public class SDCardImpl implements SDCard {
    @Override
    public String readSD() {
        return "Read from SD";
    }
}
