package com.yancy;

import java.util.EnumMap;
import java.util.Map;

/**
 * 享元工厂
 * @author yancy0109
 */
public class PotionFactory {

    private Map<PotionType, Potion> potions;

    public PotionFactory() {
        potions = new EnumMap<PotionType, Potion>(PotionType.class);
    }

    Potion createPotion(PotionType type) {
        Potion potion = potions.get(type);
        if (potion == null) {
            switch (type) {
                case HEALING:
                    potion = new HealingPotion();
                    potions.put(PotionType.HEALING,potion);
                    break;
                case HOLY:
                    potion = new HolyPotion();
                    potions.put(PotionType.HOLY,potion);
                    break;
                case INVISIBILITY:
                    potion = new InvisibilityPotion();
                    potions.put(PotionType.INVISIBILITY,potion);
                    break;
                default:
                    break;
            }
        }
        return potion;
    }
}
