package com.yancy;

/**
 * 具体子类
 * @author yancy0109
 */
public class ConcreteDish extends AbstactClass {

    // 重写钩子方法
    @Override
    boolean isNeedSugar() {
        return false;
    }

    @Override
    public void pourSomething() {
        System.out.println("加点肉and菜");
    }
}
