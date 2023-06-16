## 抽象工厂模式

工厂方法模式解决了具体工厂与具体产品之间的耦合，但是一旦我们创建了新的具体产品，我们就要为其创建一个新的具体工厂，具体产品与具体工厂呈现近一比一的关系，产生了类爆炸情况，为此提出了抽象工厂的概念。

### 概念

 抽象工厂是一种为访问类提供一个创建一组相关或相互依赖对象的接口，且访问类无须指定所要产品的具体类就能得到同族的不同等级的产品的模式结构。

抽象工厂模式是工厂方法模式的升级版本，工厂方法模式只生产一个等级的产品，而抽象工厂模式可生产多个等级的产品。

### 结构

抽象工厂模式的主要角色如下：

* 抽象工厂（Abstract Factory）：提供了创建产品的接口，它包含多个创建产品的方法，可以创建多个不同等级的产品。
* 具体工厂（Concrete Factory）：主要是实现抽象工厂中的多个抽象方法，完成具体产品的创建。
* 抽象产品（Product）：定义了产品的规范，描述了产品的主要特性和功能，抽象工厂模式有多个抽象产品。
* 具体产品（ConcreteProduct）：实现了抽象产品角色所定义的接口，由具体工厂来创建，它 同具体工厂之间是多对一的关系。

### 实现

![image-20230110111951941](https://raw.githubusercontent.com/yancy0109/image/main/11721/image-20230110111951941.png)

Eg.

#### 抽象工厂

```java
/**
 * 抽象工厂
 * 咖啡工厂接口
 * @author yancy0109
 */
public interface DessertFactory {
    Coffee getCoffer();

    Dessert getDessert();
}
```

#### 具体工厂

```java
/**
 * 具体工厂
 * 美式甜品工厂
 * @author yancy0109
 */
public class AmericanDessertFactory implements DessertFactory {

    @Override
    public Dessert getDessert() {
        return new Tiramisu();
    }

    @Override
    public Coffee getCoffer() {
        return new AmericanCoffee();
    }
}

/**
 * 具体工厂
 * 意大利甜品工厂
 * @author yancy0109
 */
public class ItalyDessertFactory implements DessertFactory {

    @Override
    public Coffee getCoffer() {
        return new LatteCoffee();
    }

    @Override
    public Dessert getDessert() {
        return new  MatchaMousse();
    }
}
```

#### 抽象产品

```java
/**
 * 抽象产品
 * 甜品接口
 * @author yancy0109
 */
public interface Dessert {

    String getName();
}

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

咖啡实现类

```java
/**
 * 具体产品
 * 美式咖啡
 * @author yancy0109
 */
public class AmericanCoffee implements Coffee {

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
public class LatteCoffee implements Coffee {

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

甜品实现类

```java
/**
 * 具体产品
 * 提拉米苏
 * @author yancy0109
 */
public class Tiramisu implements Dessert{
    @Override
    public String getName() {
        return "提拉米苏";
    }
}

/**
 * 具体产品
 * 抹茶慕斯
 * @author yancy0109
 */
public class MatchaMousse implements Dessert{
    @Override
    public String getName() {
        return "抹茶慕斯";
    }
}
```

### 总结

如果要加同一个产品族的话，只需要再加一个对应的工厂类即可，不需要修改其他的类，并且不需要为每个具体产品产生新的工厂，减少一定类的增加量。

#### 优点

当一个产品族中的多个对象被设计成一起工作时，它能保证客户端始终只使用同一个产品族中的对象。

#### 缺点

当产品族中需要增加一个新的产品时，所有的工厂类都需要进行修改。

#### 使用场景

* 当需要创建的对象是一系列相互关联或相互依赖的产品。

* 系统中有多个产品族，但每次只使用其中的某一族产品。

* 系统中提供了产品的类库，且所有产品的接口相同，客户端不依赖产品实例的创建细节和内部结构。
