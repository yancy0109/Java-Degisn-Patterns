package com.yancy;

/**
 * 将Builder与Director融合，简化结构
 * @author yancy0109
 */
public abstract class SimpleBuilder {

    protected Bike bike = new Bike();

    abstract void setFrame();

    abstract void setSeat();

    abstract Bike build();

    public Bike construct() {
        this.setFrame();
        this.setSeat();
        return this.build();
    }
}
