package com.yancy;

/**
 * 具体策略
 * 法术策略
 * @author yancy0109
 */
public class SpellStrategy implements DragonSlayingStrategy {
    @Override
    public void execute() {
        System.out.println("用法术攻击巨龙");
    }
}
