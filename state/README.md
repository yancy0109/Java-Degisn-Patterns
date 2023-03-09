## 状态模式

### 目的

允许对象在内部状态改变时改变它的行为。

对象看起来好像改变了它的类。

### 结构

* 环境（Context）角色：也称为上下文，它定义了客户程序需要的接口，维护一个当前状态，并将与状态相关的操作委托给当前状态对象来处理。
* 抽象状态（State）角色：定义一个接口，用以封装环境对象中的特定状态所对应的行为。
* 具体状态（Concrete  State）角色：实现抽象状态所对应的行为。

### 实例

> 举个例子，假设我们看到了一个猛犸象，它会根据情况来改变自身的行为，且随着时间推移它的状态还会改变。

![alt text](https://raw.githubusercontent.com/yancy0109/image/main/img/state_urm-b512cb92.png)

#### 抽象状态

```java
/**
 * 抽象状态
 * @author yancy0109
 */
public interface State {

    void onEnterState();

    void observe();
}
```

#### 具体状态

```java
/**
 * 具体状态
 * 愤怒状态
 * @author yancy0109
*/
public class AngryState implements State {

    private final Mammoth mammoth;

    public AngryState(Mammoth mammoth) {
        this.mammoth = mammoth;
    }

    @Override
    public void onEnterState() {
        System.out.println(this.mammoth + "愤怒了起来");
    }

    @Override
    public void observe() {
        System.out.println(this.mammoth + "正在暴怒");
    }
}

/**
 * 具体状态
 * 和平状态
 * @author yancy0109
 */
public class PeacefulState implements State {

    private final Mammoth mammoth;

    public PeacefulState(Mammoth mammoth) {
        this.mammoth = mammoth;
    }

    @Override
    public void onEnterState() {
        System.out.println(this.mammoth + "平静了下来");
    }

    @Override
    public void observe() {
        System.out.println(this.mammoth + "非常平静与和平");
    }
}
```


#### 环境

```java
/**
 * 环境类
 * 猛犸象
 * @author yancy0109
 */
public class Mammoth {

    private State state;

    public Mammoth() {
        this.state = new PeacefulState(this);
    }

    public void timePasses() {
        if (state instanceof PeacefulState) {
            changeStateTo(new AngryState(this));
            return;
        }
        changeStateTo(new PeacefulState(this));
    }

    private void changeStateTo(State newState) {
        this.state = newState;
        this.state.onEnterState();
    }

    public void observe() {
        this.state.observe();
    }

    public String toString() {
        return "猛犸象";
    }
}
```

#### 测试类

```java
/**
 * 测试类
 * @author yancy0109
 */
public class App {
    public static void main(String[] args) {
        // 状态类
        Mammoth mammoth = new Mammoth();
        mammoth.observe();  // 猛犸象非常平静与和平
        // 状态改变
        mammoth.timePasses();   // 猛犸象愤怒了起来
        mammoth.observe();  // 猛犸象正在暴怒

        mammoth.timePasses();   // 猛犸象平静了下来
        mammoth.observe();  // 猛犸象非常平静与和平
    }
}
```

#### 总结

状态模式其实与策略模式有些类似，他把行为交由不同的状态对象实现，而通过环境类状态该换选择了不同的状态对象，从而调用不同状态对象的实现方法。

状态对象持有对环境类的引用，同时环境类在切换不同状态时，也持有一个指向不同状态对象的属性。

### 优缺点

#### 优点

* 将所有与某个状态有关的行为放到一个类中，并且可以方便地增加新的状态，只需要改变对象状态即可改变对象的行为。
* 允许状态转换逻辑与状态对象合成一体，而不是某一个巨大的条件语句块。

#### 缺点

* 状态模式的使用必然会增加系统类和对象的个数。 
* 状态模式的结构与实现都较为复杂，如果使用不当将导致程序结构和代码的混乱。
* 状态模式对"开闭原则"的支持并不太好。

### 使用场景

- 对象的行为取决于它的状态，并且它必须在运行时根据状态更改其行为。
- 根据对象状态的不同，操作有大量的条件语句。此状态通常由一个或多个枚举常量表示。通常，几个操作将包含此相同的条件结构。状态模式把条件语句的分支分别放入单独的类中。这样一来，你就可以将对象的状态视为独立的对象，该对象可以独立于其他对象而变化。
