package com.yancy;

/**
 * 抽象迭代器
 * 迭代器接口
 */
public interface Iterator<T> {

    boolean hasNext();

    T next();
}
