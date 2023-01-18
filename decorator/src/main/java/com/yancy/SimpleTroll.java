package com.yancy;

/**
 * 对对象接口的实现
 * 简单巨魔：没有武器
 * @author yancy0109
 */
public class SimpleTroll implements Troll {

    @Override
    public void attack() {
        System.out.println("巨魔尝试打你");
    }

    @Override
    public int getAttackPower() {
        return 10;
    }

    @Override
    public void fleeBattle() {
        System.out.println("巨魔尖叫着跑了");
    }
}
