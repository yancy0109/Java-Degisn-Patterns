package com.yancy;

/**
 * 抽象类
 * @author yancy0109
 */
public abstract class AbstactClass {

    public void cook() {
        pourOil();
        pourSomething();
        if (isNeedSugar()) {
            addSugar();
        }
        fry();
    }

    // 钩子方法
    boolean isNeedSugar() {
        return true;
    }

    public void pourOil() {
        System.out.println("倒油");
    }

    abstract public void pourSomething();

    public void addSugar() {
        System.out.println("加糖");
    }

    public void fry() {
        System.out.println("翻炒");
    }


}
