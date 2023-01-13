package com.yancy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理：JDK动态代理
 * 通过代理工厂直接生成指定代理对象
 * @author yancy0109
 */
public class JdkProxyFactory {

    // 塔对象
    private IvoryTower ivoryTower;

    // 塔内最大人数
    // 仅方便演示
    private static final int MAX_NUM = 3;

    // 当前塔内人数
    // 其实放在这里也许不太合适，不过仅做演示不再完善
    private int num;

    public JdkProxyFactory(IvoryTower ivoryTower) {
        this.ivoryTower = ivoryTower;
    }

    WizardTower getWizardTowerProxy() {
        WizardTower proxyInstance = (WizardTower) Proxy.newProxyInstance(
                // ClassLoader
                IvoryTower.class.getClassLoader(),
                // interfaces
                IvoryTower.class.getInterfaces(),
                // Invocationhandler
                (Object proxy, Method method, Object[] args) -> {
                    if (num < MAX_NUM) {
                        Object result = method.invoke(ivoryTower, args);
                        num++;
                        return result;
                    } else {
                        System.out.println("巫师塔人数已满，不允许进入");
                        return null;
                    }
                }
        );
        return proxyInstance;
    }
}