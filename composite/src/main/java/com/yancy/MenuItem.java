package com.yancy;

/**
 * Leaf
 * @author yancy0109
 */
public class MenuItem extends MenuComponent {

    public MenuItem(String name, int level) {
        this.name = name;
        this.level = level;
    }

    @Override
    void print() {
        for (int i = 0; i < level; i++) {
            System.out.print(" ");
        }
        System.out.println(name);
    }
}
