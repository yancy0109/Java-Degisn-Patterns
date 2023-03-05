package com.yancy;

/**
 * 近战策略
 * 具体策略
 * @author yancy0109
 */
public class MeleeStrategy implements DragonSlayingStrategy {

    @Override
    public void execute() {
        System.out.println("用圣剑攻击巨龙的头部");
    }
}
