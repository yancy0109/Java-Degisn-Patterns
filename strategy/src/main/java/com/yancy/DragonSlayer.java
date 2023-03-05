package com.yancy;

/**
 * 环境类
 * 屠龙者
 * @author yancy0109
 */
public class DragonSlayer {
    private DragonSlayingStrategy strategy;

    public DragonSlayer(DragonSlayingStrategy strategy) {
        this.strategy = strategy;
    }

    public void changeStrategy(DragonSlayingStrategy strategy) {
        this.strategy = strategy;
    }

    public void battle() {
        strategy.execute();
    }
}
