package com.yancy0109;

/**
 * 单例模式
 * 懒汉式
 * 双重检查锁
 * @author yancy0109
 */
public class ThreadSafeDoubleCheckLockSingleton {

    // 私有构造方法
    private ThreadSafeDoubleCheckLockSingleton() {
        // 防止通过反射进行实例化
        if (null != instance) {
            throw new IllegalStateException("该实例已经存在");
        }
    }

    // 实例
    private static volatile ThreadSafeDoubleCheckLockSingleton instance;

    // 对外公共方法
    public static ThreadSafeDoubleCheckLockSingleton getInstance() {
        // 第一次判断 只有instance需要创建 才会开启锁
        if (instance == null) {
            synchronized (ThreadSafeDoubleCheckLockSingleton.class) {
                // 第二次判断
                if (instance == null) {
                    instance = new ThreadSafeDoubleCheckLockSingleton();
                }
            }
        }
        return instance;
    }
}
