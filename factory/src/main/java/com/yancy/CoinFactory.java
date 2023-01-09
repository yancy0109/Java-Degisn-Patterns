package com.yancy;

/**
 * CoinFactory
 * @author yancy0109
 */
public class CoinFactory {

    /**
     * 静态工厂方法，根据CoinType返回对应的Coin实现类
     */
    public static Coin getCoin(CoinType coinType) {
        return coinType.getConstructor().get();
    }
}
