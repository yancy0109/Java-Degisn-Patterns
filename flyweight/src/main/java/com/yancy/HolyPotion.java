package com.yancy;

/**
 * 神圣药剂
 * 具体享元角色
 * @author yancy0109
 */
public class HolyPotion implements Potion{
    public void drink() {
        System.out.println("You feel blessed.  Potion={ " +"HolyPotion"+"}");
    }
}
