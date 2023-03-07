package com.yancy;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体主题角色类
 * @author yancy0109
 */
public class SubScriptionSubject implements Subject {

    //定义集合存储多个观察者角色
    private List<observer> userObserverList = new ArrayList<>();

    @Override
    public void attach(observer observer) {
        userObserverList.add(observer);
    }

    @Override
    public void detach(observer observer) {
        userObserverList.remove(observer);
    }

    @Override
    public void notify(String msg) {
        // 遍历通知观察者对象
        for (observer observer : userObserverList) {
            observer.update(msg);
        }

    }
}
