## 迭代器模式

### 目的

提供一种在不暴露其基础表示的情况下顺序访问聚合对象的元素的方法。

### 结构

* 抽象聚合（Aggregate）角色：定义存储、添加、删除聚合元素以及创建迭代器对象的接口。

* 具体聚合（ConcreteAggregate）角色：实现抽象聚合类，返回一个具体迭代器的实例。
* 抽象迭代器（Iterator）角色：定义访问和遍历聚合元素的接口，通常包含 hasNext()、next() 等方法。
* 具体迭代器（Concretelterator）角色：实现抽象迭代器接口中所定义的方法，完成对聚合对象的遍历，记录遍历的当前位置。

### 实例
> 假设我们有一个存有物品的物品箱，我们可以设置一个迭代器，来对物品箱中的物品进行遍历。
#### 抽象聚合
```java
/**
 * 抽象聚合角色
 * 物品箱📦
 */

public interface ItemChest {

    public Iterator<Item> getItr();
}
```
#### 具体聚合
```java
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
```
#### 抽象迭代器
```java
/**
 * 抽象迭代器
 * 迭代器接口
 */
public interface Iterator<T> {

    boolean hasNext();

    T next();
}
```
#### 具体迭代器
```java
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
```
#### 测试类
```java
/**
 * 测试类
 */
public class App {
    public static void main(String[] args) {
        ItemChest itemChest = new ItemChestImpl();
        Iterator<Item> itr = itemChest.getItr();
        while(itr.hasNext()) {
            Item next = itr.next();
            System.out.println(next);
        }
        // sout
        // Item(name=魔法药水)
        // Item(name=智力药水)
        // Item(name=变大药水)
        // Item(name=宝剑)
        // Item(name=BAT Offer)
    }
}
```
#### 总结
提供一个对存储一定对象容器的迭代器对象，我们可以忽略其容器的表示形式，直接对元素进行访问。
### 优缺点
#### 优点
* 它支持以不同的方式遍历一个聚合对象，在同一个聚合对象上可以定义多种遍历方式。在迭代器模式中只需要用一个不同的迭代器来替换原有迭代器即可改变遍历算法，我们也可以自己定义迭代器的子类以支持新的遍历方式。
* 迭代器简化了聚合类。由于引入了迭代器，在原有的聚合对象中不需要再自行提供数据遍历等方法，这样可以简化聚合类的设计。
* 在迭代器模式中，由于引入了抽象层，增加新的聚合类和迭代器类都很方便，无须修改原有代码，满足 “开闭原则” 的要求。
#### 缺点
* 增加了类的个数，这在一定程度上增加了系统的复杂性。
### 使用场景
* 当需要为聚合对象提供多种遍历方式时。
* 当需要为遍历不同的聚合结构提供一个统一的接口时。
* 当访问一个聚合对象的内容而无须暴露其内部细节的表示时。
