## 享元模式

### 概念

运用共享技术来有效支持大量细粒度对象的复用。

通过共享已经存在的对象来大幅度减少需要创建的对象，避免大量相似对象的开销，从而提高系统的资源利用率。

### 结构

#### 状态

- 内部状态：不会随着环境的改变而改变的可共享部分。

- 外部状态：随着环境改变而改变的不可以共享的部分。

>  享元模式的实现要领就是区分应用中这两种状态，并将外部状态外部化。

#### 角色

- 抽象享元角色（Flyweight）：通常是一个接口或抽象类，在抽象享元类中声明了具体享元类公共的方法，这些方法可以向外界提供享元对象的内部数据（内部状态），同时也可以通过这些方法来设置外部数据（外部状态）。
- 具体享元角色（Concrete Flyweight）：实现了抽象享元类，称为享元对象。在具体享元类中为**内部状态**提供了存储空间。我们可以结合单例模式，为每一个具体享元类提供唯一的共享对象。
- 非享元角色（Unsharable Flyweight）：并不是所有的抽象享元类的子类都需要被共享，不能被共享的子类可设计为非共享具体享元类，当需要此类时我们可以直接通过实例化创建。
- 享元工厂角色（Flyweight Factory）：负责创建和管理享元角色，当客户对象请求一个享元对象时，享元工厂检查系统中是否存在符合要求的想元对象，如果存在则提供给客户，不存在则创建一个新的享元对象。

### 实现

#### Flyweight

```java
/**
 * 药剂
 * 抽象享元角色
 * 定义了具体享元类公共的方法
 * @author yancy0109
 */
public interface Potion {

    void drink();

}
```

#### Concrete Flyweight

```java
/**
 * 治疗药剂
 * 具体享元角色
 * @author yancy0109
 */
public class HealingPotion implements Potion{
    public void drink() {
        System.out.println("You feel healed.  Potion={ " +"HealingPotion"+"}");
    }
}

/**
 * 神圣药剂
 * 具体享元角色
 * @author yancy0109
 */
public class HolyPotion implements Potion{
    public void drink() {
        System.out.println("You feel blessed.  Potion={ " +"HolyPotion"+"}");
    }
}

/**
 * 隐形药剂
 * 具体享元角色
 * @author yancy0109
 */
public class InvisibilityPotion implements Potion{

    public void drink() {
        System.out.println("You become invisible. Potion={ " +"InvisibilityPotion"+"}");
    }
}
```

#### Flyweight Factory

```java
/**
 * 享元工厂
 * @author yancy0109
 */
public class PotionFactory {

    private Map<PotionType, Potion> potions;

    public PotionFactory() {
        potions = new EnumMap<PotionType, Potion>(PotionType.class);
    }

    Potion createPotion(PotionType type) {
        Potion potion = potions.get(type);
        if (potion == null) {
            switch (type) {
                case HEALING:
                    potion = new HealingPotion();
                    potions.put(PotionType.HEALING,potion);
                    break;
                case HOLY:
                    potion = new HolyPotion();
                    potions.put(PotionType.HOLY,potion);
                    break;
                case INVISIBILITY:
                    potion = new InvisibilityPotion();
                    potions.put(PotionType.INVISIBILITY,potion);
                    break;
                default:
                    break;
            }
        }
        return potion;
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
        PotionFactory factory = new PotionFactory();
        Potion potion = factory.createPotion(PotionType.HEALING);
        potion.drink();  // You feel healed.  Potion={ HealingPotion}
        potion = factory.createPotion(PotionType.INVISIBILITY);
        potion.drink();  // You become invisible. Potion={ InvisibilityPotion}
    }
}
```



### 实例

Integer 对 -128 至 127 包装类对象进行了缓存。

```java
private static class IntegerCache {
    static final int low = -128;
    static final int high;
    static final Integer cache[];

    static {
        // high value may be configured by property
        int h = 127;
        String integerCacheHighPropValue =
            sun.misc.VM.getSavedProperty("java.lang.Integer.IntegerCache.high");
        if (integerCacheHighPropValue != null) {
            try {
                int i = parseInt(integerCacheHighPropValue);
                i = Math.max(i, 127);
                // Maximum array size is Integer.MAX_VALUE
                h = Math.min(i, Integer.MAX_VALUE - (-low) -1);
            } catch( NumberFormatException nfe) {
                // If the property cannot be parsed into an int, ignore it.
            }
        }
        high = h;

        cache = new Integer[(high - low) + 1];
        int j = low;
        for(int k = 0; k < cache.length; k++)
            cache[k] = new Integer(j++);

        // range [-128, 127] must be interned (JLS7 5.1.7)
        assert IntegerCache.high >= 127;
    }
    private IntegerCache() {}
}

public static Integer valueOf(int i) {
    if (i >= IntegerCache.low && i <= IntegerCache.high)
        return IntegerCache.cache[i + (-IntegerCache.low)];
    return new Integer(i);
}
```

### 总结

#### 优点

- 极大减少内存中相似或相同对象数量，节约系统资源，提供系统性能。

- 享元模式中的外部状态相对独立，且不影响内部状态。

#### 缺点

- 为了使对象可以共享，需要将享元对象部分状态外部化，分离内部状态与外部状态，使程序逻辑复杂（例子未体现）。

### 使用场景

- 系统中存在大量或者相似的对象，造成了内存的大量浪费。

- 对象的大部分状态都可以外部化，可以将这些外部状态传入对象中。
- 在使用享元模式时需要维护一个存储享元对象的享元池，而这需要耗费一定的系统资源，因此，应当在需要多次重复使用享元对象时才值得使用享元模式。