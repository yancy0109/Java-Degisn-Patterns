package com.yancy;

/**
 * 抽象聚合角色
 * 物品箱📦
 */

public interface ItemChest {

    public Iterator<Item> getItr();
}