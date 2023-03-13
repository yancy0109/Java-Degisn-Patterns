package com.yancy;

import java.util.List;

/**
 * 具体迭代器
 * 物品迭代器
 */
public class ItemIterator implements Iterator<Item>{

    private List<Item> itemList;

    private int location;

    public ItemIterator(List<Item> itemList) {
        this.itemList = itemList;
        this.location = 0;
    }

    @Override
    public boolean hasNext() {
        return itemList.size() > location;
    }

    @Override
    public Item next() {
        return itemList.get(this.location++);
    }
}