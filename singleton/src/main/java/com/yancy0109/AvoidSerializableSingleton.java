package com.yancy0109;

import java.io.Serializable;

/**
 * 单例模式
 * 防止反序列化破坏单例
 * @author yancy0109
 */
public class AvoidSerializableSingleton implements Serializable {

    private AvoidSerializableSingleton() {

    }

    // 静态内部类
    private static class HelperHolder {
        // 生命外部类并初始化外部类对象
        private static final AvoidSerializableSingleton INSTANCE = new AvoidSerializableSingleton();
    }

    // 外部方法
    public static AvoidSerializableSingleton getInstance() {
        return HelperHolder.INSTANCE;
    }

    // 在进行反序列化时，会自动调用该方法，将该方法的返回值直接返回
    public Object readResolve() {
        return HelperHolder.INSTANCE;
    }
}
