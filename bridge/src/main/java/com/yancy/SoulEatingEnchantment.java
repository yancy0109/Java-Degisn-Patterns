package com.yancy;

/**
 * 具体实现化角色
 * 灵魂吞噬属性附魔
 * @author yancy0109
 */
public class SoulEatingEnchantment implements Enchantment {

    @Override
    public void onActivate() {
        System.out.println("武器散发了杀戮的欲望");
    }

    @Override
    public void apply() {
        System.out.println("武器吞噬了敌人的灵魂");
    }

    @Override
    public void onDeactivate() {
        System.out.println("杀戮欲消失了");
    }
}
