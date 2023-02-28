package com.yancy;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite
 * 定义了有子部件的部件行为
 * @author yancy0109
 */
public class Menu extends MenuComponent {

    private List<MenuComponent> itemList = new ArrayList<>();

    public Menu(String name, int level) {
        this.name = name;
        this.level = level;
    }

    @Override
    void add(MenuComponent component) {
        itemList.add(component);
    }

    @Override
    void remove(MenuComponent component) {
        itemList.remove(component);
    }

    @Override
    MenuComponent getChild(int index) {
        return itemList.get(index);
    }

    @Override
    void print() {
        for (int i = 0; i < level; i++) {
            System.out.print(" ");
        }
        System.out.println(name);
        for (MenuComponent component : itemList) {
            component.print();
        }

    }
}
