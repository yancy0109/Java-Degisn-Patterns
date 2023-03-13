package com.yancy;

/**
 * 测试类
 */
public class App {
    public static void main(String[] args) {
        ItemChest itemChest = new ItemChestImpl();
        Iterator<Item> itr = itemChest.getItr();
        while(itr.hasNext()) {
            Item next = itr.next();
            System.out.println(next);
        }
        // sout
        // Item(name=魔法药水)
        // Item(name=智力药水)
        // Item(name=变大药水)
        // Item(name=宝剑)
        // Item(name=BAT Offer)
    }
}
