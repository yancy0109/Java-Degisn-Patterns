package com.yancy;

/**
 * 测试类
 * @author yancy0109
 */
public class App {
    public static void main(String[] args) {
        // 剑
        Weapon weapon = new Sword(new SoulEatingEnchantment());
        weapon.wield();  // 你拿起了剑\n武器散发了杀戮的欲望
        weapon.swing();  // 你挥动了手中的剑\n武器吞噬了敌人的灵魂
        weapon.unwield();  // 你放下了剑\n杀戮欲消失了

        // 锤子
        weapon = new Hammer(new FlyingEnchantment());
        weapon.wield();  // 你拿起了锤子\n武器开始微微发光
        weapon.swing();  // 你挥动了手中的锤子\n武器飞向了敌人并再攻击后返回了你的手中
        weapon.unwield();  // 你放下了锤子\n武器光芒消失了
    }
}