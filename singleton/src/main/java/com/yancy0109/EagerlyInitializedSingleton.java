package com.yancy0109;

/**
 * Singleton
 * 实现方式
 * 饿汉式，线程安全
 * @author yancy0109
 */
public class EagerlyInitializedSingleton {

    // 私有构造方法
    private EagerlyInitializedSingleton() {
        if (instance != null) {
            throw new IllegalStateException("已存在实例");
        }
    }

    // 在本类中创建本类对象,在类加载至内存后便会创建此实例
    // 也可以通过静态代码块给变量赋值
    private static EagerlyInitializedSingleton instance = new EagerlyInitializedSingleton();

    // 提供一个公共的访问方式，让外界获取该对象
    public static EagerlyInitializedSingleton getInstance() {
        return instance;
    }


}
