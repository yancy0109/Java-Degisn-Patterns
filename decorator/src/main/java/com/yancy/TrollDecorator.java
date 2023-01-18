package com.yancy;

import lombok.Data;

/**
 * 装饰器：包含一个对组件引用，并实现同样的接口
 * 巨魔装饰器
 * @author yancy0109
 */
@Data
public abstract class TrollDecorator implements Troll{

    private Troll troll;

    public TrollDecorator(Troll troll) {
        this.troll = troll;
    }

}
