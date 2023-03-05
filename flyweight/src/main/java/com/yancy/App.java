package com.yancy;

/**
 * 测试类
 * @author yancy0109
 */
public class App {
    public static void main(String[] args) {
        PotionFactory factory = new PotionFactory();
        Potion potion = factory.createPotion(PotionType.HEALING);
        potion.drink();  // You feel healed.  Potion={ HealingPotion}
        potion = factory.createPotion(PotionType.INVISIBILITY);
        potion.drink();  // You become invisible. Potion={ InvisibilityPotion}
    }
}
