package com.yancy;

/**
 *  扩展抽象化接口角色
 *  剑🗡
 *  实现了接口中的方法，并根据业务调用了实现化角色的方法。
 * @author yancy0109
 */
public class Sword extends Weapon {

    public Sword(Enchantment enchantment) {
        super(enchantment);
    }

    @Override
    void wield() {
        System.out.println("你拿起了剑");
        getEnchantment().onActivate();
    }

    @Override
    void swing() {
        System.out.println("你挥动了手中的剑");
        getEnchantment().apply();
    }

    @Override
    void unwield() {
        System.out.println("你放下了剑");
        getEnchantment().onDeactivate();

    }
}
