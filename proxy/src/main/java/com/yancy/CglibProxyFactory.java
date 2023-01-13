package com.yancy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 动态代理：cglib
 * 通过代理工厂直接生成指定代理对象
 * @author yancy0109
 */
public class CglibProxyFactory {

    // 塔对象
    private IvoryTower ivoryTower;

    // 塔内最大人数
    // 仅方便演示
    private static final int MAX_NUM = 3;

    // 当前塔内人数
    // 其实放在这里也许不太合适，不过仅做演示不再完善
    private int num;

    public CglibProxyFactory(IvoryTower ivoryTower) {
        this.ivoryTower = ivoryTower;
    }

    IvoryTower getWizardTowerProxy() {
        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(ivoryTower.getClass());

        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                if (num < MAX_NUM) {
                    Object result = method.invoke(ivoryTower, objects);
                    num++;
                    return result;
                } else {
                    System.out.println("巫师塔人数已满，不允许进入");
                    return null;
                }
            }
        });

         IvoryTower ivoryTower = (IvoryTower) enhancer.create();
        return ivoryTower;
    }
}