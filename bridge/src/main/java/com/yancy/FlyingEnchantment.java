package com.yancy;

/**
 * 具体实现化角色
 * 飞行属性附魔
 * @author yancy0109
 */
public class FlyingEnchantment implements Enchantment {
    @Override
    public void onActivate() {
        System.out.println("武器开始微微发光");
    }

    @Override
    public void apply() {
        System.out.println("武器飞向了敌人并再攻击后返回了你的手中");
    }

    @Override
    public void onDeactivate() {
        System.out.println("武器光芒消失了");
    }
}
