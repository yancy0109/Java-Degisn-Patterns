package com.yancy;

import lombok.NoArgsConstructor;

/**
 * ConcreteBuilder
 * @author yancy0109
 */
@NoArgsConstructor
public class ConcreteBuilder extends Builder{

    @Override
    public void setFrame() {
        bike.setFrame("frame");
    }

    @Override
    public void setSeat() {
        bike.setSeat("seat");
    }

    // 创建方法
    public Bike build() {
        return bike;
    }
}
