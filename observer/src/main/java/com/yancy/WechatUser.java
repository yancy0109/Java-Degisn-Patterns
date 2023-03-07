package com.yancy;

/**
 * 具体观察者角色类
 * @author yancy0109
 */
public class WechatUser implements observer{
    @Override
    public void update(String msg) {
        System.out.println(this + "接收到通知：" + msg);
    }
}
