package com.yancy;


/**
 * 接收者类
 * 抽象类
 * @author yancy0109
 */
public abstract class Target {

    private String name;

    public String getName() {
        return name;
    }

    public Target(String name) {
        this.name = name;
    }

    private Visibility visibility = Visibility.NORMAL;
    private Size size = Size.NORMAL;


    public String getStatus() {
        return name + "隐身状态:" + visibility.getValue() + "   大小:" + size.getValue();
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public Size getSize() {
        return size;
    }
}
