package com.yancy;

/**
 * 测试类
 * @author yancy0109
 */
public class App {
    public static void main(String[] args) {
        System.out.println("===================== Before ======================");
        // 原对象
        Troll troll = new SimpleTroll();
        troll.attack();  // 巨魔尝试打你
        System.out.println(troll.getAttackPower());  // 10
        troll.fleeBattle();  // 巨魔尖叫着跑了

        System.out.println("===================== After ======================");

        // 装饰器修饰
        troll = new TrollClubDecorator(troll);
        troll.attack();  // 巨魔尝试打你\n巨魔挥舞木棍
        System.out.println(troll.getAttackPower());  // 20
        troll.fleeBattle();  // 巨魔尝试打你\n巨魔丢掉了木棍
    }
}
