package com.yancy;

/**
 * 治疗药剂
 * 具体享元角色
 * @author yancy0109
 */
public class HealingPotion implements Potion{
    public void drink() {
        System.out.println("You feel healed.  Potion={ " +"HealingPotion"+"}");
    }
}
