package com.yancy0109;

/**
 * 单例模式
 * 懒汉式
 * 静态内部类实现
 * JVM在加载外部类的过程中，不会加载静态内部类
 * @author yancy0109
 */
public class InitializingOnDemandHolderIdiom {

    private InitializingOnDemandHolderIdiom() {

    }

    // 静态内部类
    private static class HelperHolder {
        // 生命外部类并初始化外部类对象
        private static final InitializingOnDemandHolderIdiom INSTANCE = new InitializingOnDemandHolderIdiom();
    }

    // 外部方法
    public static InitializingOnDemandHolderIdiom getInstance() {
        return HelperHolder.INSTANCE;
    }
}
