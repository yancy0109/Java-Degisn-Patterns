package com.yancy;

/**
 * Component
 * 定义了所有组件的公共接口
 * @author yancy0109
 */
public abstract class MenuComponent {

    // 菜单名
    protected String name;

    // 菜单级别
    protected int level;

    void add(MenuComponent component) {
        throw new UnsupportedOperationException();
    }

    void remove(MenuComponent component) {
        throw new UnsupportedOperationException();
    }

    MenuComponent getChild(int index) {
        throw new UnsupportedOperationException();
    }

    String getName() {
        return name;
    }

    abstract void print();
}
