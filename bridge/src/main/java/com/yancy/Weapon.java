package com.yancy;

import lombok.Data;

/**
 * 抽象化角色
 * 持有了一个实现化角色
 * @author yancy0109
 */
@Data
public abstract class Weapon {

    private Enchantment enchantment;

    public Weapon(Enchantment enchantment) {
        this.enchantment = enchantment;
    }

    abstract void wield();

    abstract void swing();

    abstract void unwield();
}
