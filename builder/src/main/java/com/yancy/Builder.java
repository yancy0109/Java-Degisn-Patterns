package com.yancy;

/**
 * Builder
 * @author yancy0109
 */
public abstract class Builder {

    protected Bike bike = new Bike();

    abstract void setFrame();

    abstract void setSeat();

    abstract Bike build();
}
