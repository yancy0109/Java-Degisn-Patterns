package com.yancy;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

/**
 * Coin枚举类
 * @author yancy0109
 */
@Getter
@RequiredArgsConstructor
public enum CoinType {

    COPPER(CopperCoin::new),
    GOLD(GoldCoin::new);

    // 通过Suppler返回实例
    private final Supplier<Coin> constructor;
}
