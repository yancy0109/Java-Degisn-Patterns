## 工厂方法模式

简单工厂角色：

- 具体工厂
- 抽象产品
- 具体产品

我们在具体工厂逻辑之中包含了一定与具体产品耦合的逻辑，而在这基础上，我们改进成为了工厂方法模式。

### 概念

定义一个工厂接口，由其子类决定工厂生成的具体产品。工厂方法使一个产品类的实例化延迟到其工厂的子类。

###  结构

工厂方法模式的主要角色：

* 抽象工厂（Abstract Factory）：提供了创建产品的接口，调用者通过它访问具体工厂的工厂方法来创建产品。
* 具体工厂（ConcreteFactory）：主要是实现抽象工厂中的抽象方法，完成具体产品的创建。
* 抽象产品（Product）：定义了产品的规范，描述了产品的主要特性和功能。
* 具体产品（ConcreteProduct）：实现了抽象产品角色所定义的接口，由具体工厂来创建，它同具体工厂之间一一对应。

### 实现

![image-20230110103756371](https://raw.githubusercontent.com/yancy0109/image/main/11721/image-20230110103756371.png)Eg.

#### 抽象产品

咖啡抽象类

```java
/**
 * 咖啡抽象类
 * @author yancy0109
 */
public interface Coffee {

    String getName();

    void addMilk();

    void addSugar();
}
```

#### 具体产品

```java
/**
 * 具体产品
 * 美式咖啡
 * @author yancy0109
 */
public class AmericanCoffee implements Coffee{

    @Override
    public String getName() {
        return "美式咖啡";
    }

    @Override
    public void addMilk() {

    }

    @Override
    public void addSugar() {

    }
}

/**
 * 具体产品
 * 拿铁咖啡
 * @author yancy0109
 */
public class LatteCoffee implements Coffer{

    @Override
    public String getName() {
        return "拿铁咖啡";
    }

    @Override
    public void addMilk() {

    }

    @Override
    public void addSugar() {

    }
}
```

#### 抽象工厂

```java
/**
 * 抽象工厂
 * 咖啡工厂接口
 * @author yancy0109
 */
public interface CoffeeFactory {
    Coffee getCoffer();
}
```

#### 具体工厂

```java
/**
 * 具体工厂
 * 拿铁咖啡工厂
 * @author yancy0109
 */
public class LatteCoffeeFactory implements CoffeeFactory{

    @Override
    public Coffee getCoffer() {
        return new LatteCoffee();
    }
}

/**
 * 具体工厂
 * 美式咖啡工厂
 * @author yancy0109
 */
public class AmericanCoffeeFactory implements CoffeeFactory{

    @Override
    public Coffee getCoffer() {
        return new AmericanCoffee();
    }
}
```

#### 测试

```java
/**
 * 测试类
 * @author yancy0109
 */
public class CoffeeStore {

    static Coffee orderAmericanCoffee () {
        // 在Client决定具体工厂
        AmericanCoffeeFactory americanCoffeeFactory = new AmericanCoffeeFactory();
        return americanCoffeeFactory.getCoffer();
    }
    static Coffee orderLatteeCoffee () {
        // 在Client决定具体工厂
        LatteCoffeeFactory latteCoffeeFactory = new LatteCoffeeFactory();
        return latteCoffeeFactory.getCoffer();
    }

    public static void main(String[] args) {
        // 测试
        System.out.println(orderAmericanCoffee().getName());  // 美式咖啡
        System.out.println(orderLatteeCoffee().getName());  // 拿铁咖啡
    }
}
```

### 总结

由实现可见，我们在增加新产品时，并不需要修改现有工厂逻辑，解决了简单工厂模式下的具体产品与具体工厂的耦合。

#### 优点

- 用户只需要知道具体工厂的名称就可得到所要的产品，无须知道产品的具体创建过程；
- 在系统增加新的产品时只需要添加具体产品类和对应的具体工厂类，无须对原工厂进行任何修改，满足开闭原则；

#### 缺点

- 每增加一个产品就要增加一个具体产品类和一个对应的具体工厂类，这增加了系统的复杂度。
- 类爆炸
