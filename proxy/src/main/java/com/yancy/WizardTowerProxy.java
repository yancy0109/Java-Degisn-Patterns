package com.yancy;

/**
 * 代理类
 * 巫师塔代理类
 * @author yancy0109
 */
public class WizardTowerProxy implements WizardTower{

    // 允许进入最大人数
    private static final int MAX_WIZARDS_ALLOWED = 3;

    // 当前人数
    private int numWizards;

    private final WizardTower tower;

    public WizardTowerProxy(WizardTower tower) {
        this.tower = tower;
    }

    @Override
    public void enter(Wizard wizard) {
        if (numWizards < MAX_WIZARDS_ALLOWED) {
            tower.enter(wizard);
            numWizards++;
        } else {
            System.out.println("巫师塔人数已满，不允许进入");
        }
    }
}
