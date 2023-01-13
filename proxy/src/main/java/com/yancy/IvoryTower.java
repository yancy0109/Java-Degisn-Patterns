package com.yancy;

/**
 * 真实主题
 * 巫师塔
 * @author yancy0109
 */
public class IvoryTower implements WizardTower {

    @Override
    public void enter(Wizard wizard) {
        System.out.println(wizard + "进入了巫师塔");
    }
}
