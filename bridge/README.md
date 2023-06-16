## 桥接模式

桥接（Bridge）模式

### 目的

将抽象部分与它的实现部分分离，使它们可以独立变化。

当一个抽象可能有多个实现时，通常用继承来协调它们，但通过继承会造成类爆炸问题，扩展也并不灵活。



### 结构

- 抽象化角色（Abstraction）：定义抽象类，并包含一个对是实现化对象的引用。
- 扩展抽象化接口角色（Refined Abstraction）：是抽象化角色的子类，实现父类中的业务方法，并通过组合关系调用实现化角色中的业务方法。
- 实现化角色（Implementor）：定义实现化角色的接口，供扩展抽象化角色的调用。
- 具体实现化角色（Concrete Implementor）：给出实现化角色接口的具体实现。



### 实现

> 背景：
>
> 考虑一下你拥有一种具有不同附魔的武器，并且应该允许将具有不同附魔的不同武器混合使用。 你会怎么做？ 为每个附魔创建每种武器的多个副本，还是只是创建单独的附魔并根据需要为武器设置它？ 桥接模式使您可以实现第二种方式。

![image-20230118201145755](https://raw.githubusercontent.com/yancy0109/image/main/11721/image-20230118201145755.png)

#### Abstraction

```java
/**
 * 实现化角色
 * 定义了引用对象的接口
 * 武器附魔接口
 * @author yancy0109
 */
public interface Enchantment {

    void onActivate();

    void apply();

    void onDeactivate();
}
```

#### Refined Abstraction

```java
/**
 * 具体实现化角色
 * 飞行属性附魔
 * @author yancy0109
 */
public class FlyingEnchantment implements Enchantment {
    @Override
    public void onActivate() {
        System.out.println("武器开始微微发光");
    }

    @Override
    public void apply() {
        System.out.println("武器飞向了敌人并再攻击后返回了你的手中");
    }

    @Override
    public void onDeactivate() {
        System.out.println("武器光芒消失了");
    }
}

/**
 * 具体实现化角色
 * 灵魂吞噬属性附魔
 * @author yancy0109
 */
public class SoulEatingEnchantment implements Enchantment {

    @Override
    public void onActivate() {
        System.out.println("武器散发了杀戮的欲望");
    }

    @Override
    public void apply() {
        System.out.println("武器吞噬了敌人的灵魂");
    }

    @Override
    public void onDeactivate() {
        System.out.println("杀戮欲消失了");
    }
}
```

#### Implementor

```java
/**
 * 抽象化角色
 * 持有了一个实现化角色
 * @author yancy0109
 */
@Data
public abstract class Weapon {

    private Enchantment enchantment;

    public Weapon(Enchantment enchantment) {
        this.enchantment = enchantment;
    }

    abstract void wield();

    abstract void swing();

    abstract void unwield();
}
```


#### Concrete Implementor

```java
/**
 *  扩展抽象化接口角色
 *  剑🗡
 *  实现了接口中的方法，并根据业务调用了实现化角色的方法。
 * @author yancy0109
 */
public class Sword extends Weapon {

    public Sword(Enchantment enchantment) {
        super(enchantment);
    }

    @Override
    void wield() {
        System.out.println("你拿起了剑");
        getEnchantment().onActivate();
    }

    @Override
    void swing() {
        System.out.println("你挥动了手中的剑");
        getEnchantment().apply();
    }

    @Override
    void unwield() {
        System.out.println("你放下了剑");
        getEnchantment().onDeactivate();

    }
}

/**
 *  扩展抽象化接口角色
 *  锤子🔨
 *  实现了接口中的方法，并根据业务调用了实现化角色的方法。
 * @author yancy0109
 */
public class Hammer extends Weapon {

    public Hammer(Enchantment enchantment) {
        super(enchantment);
    }

    @Override
    void wield() {
        System.out.println("你拿起了锤子");
        getEnchantment().onActivate();
    }

    @Override
    void swing() {
        System.out.println("你挥动了手中的锤子");
        getEnchantment().apply();
    }

    @Override
    void unwield() {
        System.out.println("你放下了锤子");
        getEnchantment().onDeactivate();

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
        // 剑
        Weapon weapon = new Sword(new SoulEatingEnchantment());
        weapon.wield();  // 你拿起了剑\n武器散发了杀戮的欲望
        weapon.swing();  // 你挥动了手中的剑\n武器吞噬了敌人的灵魂
        weapon.unwield();  // 你放下了剑\n杀戮欲消失了

        // 锤子
        weapon = new Hammer(new FlyingEnchantment());
        weapon.wield();  // 你拿起了锤子\n武器开始微微发光
        weapon.swing();  // 你挥动了手中的锤子\n武器飞向了敌人并再攻击后返回了你的手中
        weapon.unwield();  // 你放下了锤子\n武器光芒消失了
    }
}
```

### 特点

* 桥接模式提高了系统的可扩充性，在两个变化维度中任意扩展一个维度，都不需要修改原有系统。

* 实现细节对客户透明

### 适用场景

- 你不希望在抽象和它的实现部分之间有一个固定的绑定关系。例如这种情况可能是因为,在程序运行时刻实现部分应可以被选择或者切换、
- 当一个系统需要在构件的抽象化角色和具体化角色之间增加更多的灵活性时。避免在两个层次之间建立静态的继承联系，通过桥接模式可以使它们在抽象层建立一个关联关系。
- 类的抽象以及它的实现都应该可以通过生成子类的方法加以扩充。 这时 Bridge 模式使你可以对不同的抽象接口和实现部分进行组合， 并分别对它们进行扩充
- 对一个抽象的实现部分的修改应对客户不产生影响， 即客户端的代码不必重新编译。
- 你想在多个对象间共享实现（可能使用引用计数）， 但同时要求客户端并不知道这一点。
