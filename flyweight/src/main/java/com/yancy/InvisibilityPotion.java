package com.yancy;

/**
 * 隐形药剂
 * 具体享元角色
 * @author yancy0109
 */
public class InvisibilityPotion implements Potion{

    public void drink() {
        System.out.println("You become invisible. Potion={ " +"InvisibilityPotion"+"}");
    }
}
