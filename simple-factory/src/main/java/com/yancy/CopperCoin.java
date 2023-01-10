package com.yancy;

import lombok.Data;

/**
 * 工厂模式 - 具体产品
 * 铜币
 * @author yancy0109
 */
@Data
public class CopperCoin implements Coin{
    @Override
    public String getDescription() {
        return "这是一枚铜币";
    }
}
