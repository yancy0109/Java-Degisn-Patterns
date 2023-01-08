package com.yancy0109;

/**
 * 单例模式
 * 实现方式：
 * 懒汉式，线程不安全
 * @author yancy0109
 */
public class LazilyInitializedSingleton {

    // 私有构造方法
    private LazilyInitializedSingleton() {

    }

    // 单例对象
    private static LazilyInitializedSingleton instance;

    // 获取对象的方法
    public static LazilyInitializedSingleton getInstance(){
        // 如果当前对象未被使用，即对象引用未NULL
        if (instance == null) {
            // 在这里存在线程不安全的问题
            // 给对象赋值
            instance = new LazilyInitializedSingleton();
        }
        return instance;
    }
}
