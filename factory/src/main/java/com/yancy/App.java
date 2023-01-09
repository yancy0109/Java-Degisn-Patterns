package com.yancy;

/**
 * 测试类
 * @author yancy0109
 */
public class App {
    public static void main(String[] args) {

        // 通过工厂返回对应实例

        Coin copper = CoinFactory.getCoin(CoinType.COPPER);
        System.out.println(copper.getDescription());  // 这是一枚铜币

        Coin gold = CoinFactory.getCoin(CoinType.GOLD);
        System.out.println(gold.getDescription());  // 这是一枚金币

        // 通过扩展工厂（配置文件）获取对应实例
        Coin copper1 = CoinExtendFactory.getCoin("copper");
        System.out.println(copper1.getDescription());  // 这是一枚铜币
        Coin gold1 = CoinExtendFactory.getCoin("gold");
        System.out.println(gold1.getDescription());  // 这是一枚金币
    }
}
