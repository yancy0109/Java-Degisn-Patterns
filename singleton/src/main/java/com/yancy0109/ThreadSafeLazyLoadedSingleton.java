package com.yancy0109;

/**
 * 单例模式
 * 实现方式：
 * 懒汉式，线程安全
 * @author yancy0109
 */
public class ThreadSafeLazyLoadedSingleton {

    // 私有构造方法
    private ThreadSafeLazyLoadedSingleton() {
        // 防止通过反射进行实例化
        if (null != instance) {
            throw new IllegalStateException("该实例已经存在");
        }
    }

    // 单例对象
    private static ThreadSafeLazyLoadedSingleton instance;

    // 获取对象的方法 synchronized 只有一个线程可以调用此方法 避免了多线程同时访问方法导致线程不安全
    public static synchronized ThreadSafeLazyLoadedSingleton getInstance() {
        // 如果当前对象未被使用，即对象引用未NULL
        if (instance == null) {
            // 在这里存在线程不安全的问题
            // 给对象赋值
            instance = new ThreadSafeLazyLoadedSingleton();
        }
        return instance;
    }
}
