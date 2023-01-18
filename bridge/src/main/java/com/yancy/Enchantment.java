package com.yancy;

/**
 * 实现化角色
 * 定义了引用对象的接口
 * 武器附魔接口
 * @author yancy0109
 */
public interface Enchantment {

    void onActivate();

    void apply();

    void onDeactivate();
}
