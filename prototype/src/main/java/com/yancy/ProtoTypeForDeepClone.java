package com.yancy;

import lombok.SneakyThrows;

import java.io.*;

/**
 * 原型抽象类
 * 对Cloneable接口封装
 * clone采用深克隆
 * @author yancy0109
 */
public abstract class ProtoTypeForDeepClone<T> implements Cloneable{

    @SneakyThrows
    public T copy() {
        // 缓冲输出流
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // 对象输出流
        ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
        outputStream.writeObject(this);
        // 从缓冲输出流中获取缓冲输出流
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        // 获取对象输出流
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return (T) objectInputStream.readObject();
    }
}
