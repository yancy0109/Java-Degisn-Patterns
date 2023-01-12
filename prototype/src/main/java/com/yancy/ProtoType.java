package com.yancy;

import lombok.SneakyThrows;

/**
 * 原型抽象类
 * 对Cloneable接口封装
 * @author yancy0109
 */
public abstract class ProtoType<T> implements Cloneable{

    @SneakyThrows
    public T copy() {
        return (T) super.clone();
    }
}
