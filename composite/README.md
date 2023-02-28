## 组合模式



### 概念

把一组相似的对象当作一个单一的对象，将对象组合成树形结构以表示“部分----整体”的层次结构，也使得单个对象与组合对象的使用具有一致性。

### 结构

- Component 抽象根节点：是组合中所有对象的统一接口，定义了特定情况下类应当缺省的行为。
- Composite 树枝节点：定义了有子部件的部件行为，同时存储子部件，实现了Component中与子部件有关的接口。
- Leaf ：通过Component接口，操纵组合部件的对象。

**协作原理**：

> 用户使用Component类接口与组合结构中的对象进行交互。 **如果接收者是一个叶节点，则直接处理请求。 如果接收者是Composite， 它通常将请求发送给它的子部件， 在转发请求之前与/或之后可能执行一些辅助操作。**



### 实现

![image-20230228150430063](https://xingqiu-tuchuang-1256524210.cos.ap-shanghai.myqcloud.com/11721/image-20230228150430063.png)

#### Component

```java
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
```

#### Composite

```java
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
```

#### leaf

```java
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
```

#### App

```java
/**
 * 测试类
 * @author yancy0109
 */
public class App {
    public static void main(String[] args) {
        MenuComponent menu = new Menu("系统菜单", 1);
        Menu component = new Menu("人员管理", 2);
        component.add(new MenuItem("添加人员", 3));
        component.add(new MenuItem("删除人员", 3));
        menu.add(component);
        menu.add(new MenuItem("开启系统", 2));
        menu.add(new MenuItem("关闭系统", 2));

        menu.print();
        //>>>>>>>>>>>>>>>>>> 输出：
        //        系统菜单
        //         人员管理
        //          添加人员
        //          删除人员
        //         开启系统
        //         关闭系统
    }
}
```

### 总结

#### 分类

- 透明组合模式

​		透明组合模式中，抽象根节点声明了用于管理成员对象的方法，确保了所有构建类都有相同的接口，透明组合模式也是组合模式的标准形式。

​		但它不足够安全，虽然在使用中没有不用，且不会引发编译出错，但是在运行期间调用方法仍有可能会发生错误（可以提供相应错误处理代码）。

- 安全组合模式

​		在抽象构建角色中不声明任何用于管理成员对象的方法，而是在树枝节点中实现这些方法，它由于叶子构建和容器构建具有不同的方法，且容器构建的处理对		象方法没有在抽象构建类中定义，所以不够透明，客户端不能抽象编程，而是要区别对待叶子构建和容器构建。

#### 优点

- 组合模式可以清楚定义分层次的复杂对象，表示对象的全部或部分层次，它让客户端忽略了层次的差异，方便对整个层次结构进行控制。
- 客户端可以一致使用一个组合结构或者其中单个对象，不必关系处理的是当个对象还是整体组合结构，简化了客户端代码。
- 在组合模式下新增叶子节点或者树枝节点都很方便，无需对现有类库进行修改，符合开闭原则，但是无法对子组件做过多的限制。
- 组合模式以树形结构的面向对象提供了一种灵活解决方案，通过叶子节点和树枝节点的递归组合，形成复杂的树形结构，但对树形结构的控制却比较简单。

### 使用场景

- 组合模式应树形结构而生，可以用在出现树形结构的地方。

- 表示对象的部分-整体层次结构。
- 希望用户忽略组合对象与单个对象的不同，用户将统一地使用组合结构中的所有对象。
