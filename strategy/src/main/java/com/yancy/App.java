package com.yancy;

/**
 * 测试类
 * @author yancy0109
 */
public class App {
    public static void main(String[] args) {
        // 近战战略
        DragonSlayingStrategy strategy = new MeleeStrategy();
        DragonSlayer slayer = new DragonSlayer(strategy);
        slayer.battle();  // 用圣剑攻击巨龙的头部
        // 更改法术策略
        strategy = new SpellStrategy();
        slayer.changeStrategy(strategy);
        slayer.battle();  // 用法术攻击巨龙
    }
}
