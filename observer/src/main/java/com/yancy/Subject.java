package com.yancy;

/**
 * 抽象主题角色
 * @author yancy0109
 */
public interface Subject {

    /**
     * 添加订阅者（观察者）对象
     */
    void attach(observer observer);

    /**
     * 删除订阅者（观察者）对象
     */
    void detach(observer observer);

    /**
     * 通知订阅者更新消息
     */
    void notify(String msg);
}
