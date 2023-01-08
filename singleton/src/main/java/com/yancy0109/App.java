package com.yancy0109;

import java.lang.reflect.InvocationTargetException;

/**
 * 单例模式
 * 测试类
 * @author yancy0109
 */
public class App {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        // 饿汉式 静态内部类
        EagerlyInitializedSingleton eagerlyInitializedSingleton1 = EagerlyInitializedSingleton.getInstance();
        EagerlyInitializedSingleton eagerlyInitializedSingleton2 = EagerlyInitializedSingleton.getInstance();
        System.out.println(eagerlyInitializedSingleton1 == eagerlyInitializedSingleton2);  // TRUE

        // 饿汉式 枚举类
        EnumSingleton enumSingleton1 = EnumSingleton.INSTANCE;
        EnumSingleton enumSingleton2 = EnumSingleton.INSTANCE;
        System.out.println(enumSingleton1 == enumSingleton2);  //TRUE

        // 懒汉式 线程不安全
        LazilyInitializedSingleton lazilyInitializedSingleton1 = LazilyInitializedSingleton.getInstance();
        LazilyInitializedSingleton lazilyInitializedSingleton2 = LazilyInitializedSingleton.getInstance();
        System.out.println(lazilyInitializedSingleton1 == lazilyInitializedSingleton2);  // TRUE

        // 懒汉式 线程略安全 可能会因指令重拍而发生异常
        ThreadSafeLazyLoadedSingleton threadSafeLazyLoadedSingleton1 = ThreadSafeLazyLoadedSingleton.getInstance();
        ThreadSafeLazyLoadedSingleton threadSafeLazyLoadedSingleton2 = ThreadSafeLazyLoadedSingleton.getInstance();
        System.out.println(threadSafeLazyLoadedSingleton1 == threadSafeLazyLoadedSingleton2); // TRUE

        // 懒汉式 双重检查 线程安全
        ThreadSafeDoubleCheckLockSingleton threadSafeDoubleCheckLockSingleton1 = ThreadSafeDoubleCheckLockSingleton.getInstance();
        ThreadSafeDoubleCheckLockSingleton threadSafeDoubleCheckLockSingleton2 = ThreadSafeDoubleCheckLockSingleton.getInstance();
        System.out.println(threadSafeDoubleCheckLockSingleton1 == threadSafeDoubleCheckLockSingleton2); //TRUE

        // 懒汉式 静态内部类实现 线程安全
        InitializingOnDemandHolderIdiom initializingOnDemandHolderIdiom1 = InitializingOnDemandHolderIdiom.getInstance();
        InitializingOnDemandHolderIdiom initializingOnDemandHolderIdiom2 = InitializingOnDemandHolderIdiom.getInstance();
        System.out.println(initializingOnDemandHolderIdiom1 == initializingOnDemandHolderIdiom2); //TRUE
    }
}
