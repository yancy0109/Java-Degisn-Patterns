package com.yancy;

/**
 * @author yancy0109
 */
public class App {

    public static void main(String[] args) {
        WechatUser wechatUser1 = new WechatUser();
        WechatUser wechatUser2 = new WechatUser();
        WechatUser wechatUser3 = new WechatUser();

        // 创建订阅对象
        SubScriptionSubject subScriptionSubject = new SubScriptionSubject();

        // 添加观察者
        subScriptionSubject.attach(wechatUser1);
        subScriptionSubject.attach(wechatUser2);
        subScriptionSubject.attach(wechatUser3);

        subScriptionSubject.notify("更新完成");

        //  ----> PRINT
        //        com.yancy.WechatUser@74a14482接收到通知：更新完成
        //        com.yancy.WechatUser@1540e19d接收到通知：更新完成
        //        com.yancy.WechatUser@677327b6接收到通知：更新完成
    }
}
