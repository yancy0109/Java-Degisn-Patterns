package com.yancy;

/**
 *  扩展抽象化接口角色
 *  锤子🔨
 *  实现了接口中的方法，并根据业务调用了实现化角色的方法。
 * @author yancy0109
 */
public class Hammer extends Weapon {

    public Hammer(Enchantment enchantment) {
        super(enchantment);
    }

    @Override
    void wield() {
        System.out.println("你拿起了锤子");
        getEnchantment().onActivate();
    }

    @Override
    void swing() {
        System.out.println("你挥动了手中的锤子");
        getEnchantment().apply();
    }

    @Override
    void unwield() {
        System.out.println("你放下了锤子");
        getEnchantment().onDeactivate();

    }
}
