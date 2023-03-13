package com.yancy;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体聚合
 * 百宝箱实现类
 */
public class ItemChestImpl implements ItemChest{
    private List<Item> items;

    protected ItemChestImpl() {
        items = new ArrayList<>();
        items.add(new Item("魔法药水"));
        items.add(new Item("智力药水"));
        items.add(new Item("变大药水"));
        items.add(new Item("宝剑"));
        items.add(new Item("BAT Offer"));
    }

    @Override
    public Iterator<Item> getItr() {
        return new ItemIterator(this.items);
    }
}
