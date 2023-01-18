## 装饰者模式

 

### 目的

动态给一个对象添加一些额外的职责。

从增加功能角度来讲，我们也可以通过继承生成子类来实现，但是这样无疑更为复杂，如果我们增加功能的对象是一个接口，那我们甚至要对这个接口的所有实现类生成子类，但是如果我们通过装饰者模式以对客户端透明的方式扩展对象的功能，更优于继承的方案，不会违背合成复用原则。

### 结构

- Component：定义一个对象接口，可以给这些对象动态添加职责。
- CencreteComponent：定义一个对象，可以给这个对象添加一些职责。
- Decorator：持有一个指向Component对象的引用，并定义一个和Component接口一致的接口。
- ConcreteDecorator：向组件添加职责



### 实例

> 一个巨魔，它有时空手而来，有时携带一根棍子，武装一个巨魔没有必要去创建一个新的巨魔，而是通过装饰器动态修饰它。

![image-20230118130231960](https://xingqiu-tuchuang-1256524210.cos.ap-shanghai.myqcloud.com/11721/image-20230118130231960.png)

#### Component

```java
/**
 * 对象接口
 * 巨魔接口
 * @author yancy0109
 */
public interface Troll {

    void attack();

    int getAttackPower();

    void fleeBattle();
}
```

#### CencreteComponent

```java
/**
 * 对对象接口的实现
 * 简单巨魔：没有武器
 * @author yancy0109
 */
public class SimpleTroll implements Troll {

    @Override
    public void attack() {
        System.out.println("巨魔尝试打你");
    }

    @Override
    public int getAttackPower() {
        return 10;
    }

    @Override
    public void fleeBattle() {
        System.out.println("巨魔尖叫着跑了");
    }
}
```

#### Decorator

```java
/**
 * 装饰器：包含一个对组件引用，并实现同样的接口
 * 巨魔装饰器
 * @author yancy0109
 */
@Data
public abstract class TrollDecorator implements Troll{

    private Troll troll;

    public TrollDecorator(Troll troll) {
        this.troll = troll;
    }

}
```

#### ConcreteDecorator

```java
/**
 * 具体装饰类
 * 巨魔 武装Club 装饰
 * @author yancy0109
 */
public class TrollClubDecorator extends TrollDecorator {

    public TrollClubDecorator(Troll troll) {
        super(troll);
    }

    @Override
    public void attack() {
        this.getTroll().attack();
        System.out.println("巨魔挥舞木棍");
    }

    @Override
    public int getAttackPower() {
        return getTroll().getAttackPower() + 10;
    }

    @Override
    public void fleeBattle() {
        this.getTroll().attack();
        System.out.println("巨魔丢掉了木棍");
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
        System.out.println("===================== Before ======================");
        // 原对象
        Troll troll = new SimpleTroll();
        troll.attack();  // 巨魔尝试打你
        System.out.println(troll.getAttackPower());  // 10
        troll.fleeBattle();  // 巨魔尖叫着跑了

        System.out.println("===================== After ======================");

        // 装饰器修饰
        troll = new TrollClubDecorator(troll);
        troll.attack();  // 巨魔尝试打你\n巨魔挥舞木棍
        System.out.println(troll.getAttackPower());  // 20
        troll.fleeBattle();  // 巨魔尝试打你\n巨魔丢掉了木棍
    }
}
```

#### 总结

装饰器特点在于**实现了原组件相同的接口并且依赖于一个原组件对象**，使得你可以用与原对象完全相同的方法去调用装饰器修饰后的对象，并且在实现装饰器方法逻中调用原组件对象的方法，达到不影响使用的前提下对方法的增强。

###  特点

####  优点

1. 装饰模式和静态继承的机制的作用都是对现有的类增加新的功能，但装饰模式有着比静态继承更灵活的组合方式。装饰模式可以在运行的时候决定需要增加还是去除一种“装饰”以及什么“装饰”。静态继承则没有这样的灵活性，它对类功能的扩展是在运行之前就确定了的。
2. 得益于装饰模式在组合上的灵活性和便利性，我们可以将各种装饰类进行组合，从而较为简单的创造各种不同的行为集合，实现多种多样的功能。

####  缺点

1. 装饰者的对象和它装饰的对象本质上是完全不同的，装饰模式会生成许多的对象，导致区分各种对象变得困难
2. 由于使用相同的标识，对于程序的理解和拍错过程的难度也会随之增加

### 代理和装饰者区别

静态代理和装饰者模式的区别：

* 相同点：
  * 都要实现与目标类相同的业务接口
  * 在两个类中都要声明目标对象
  * 都可以在不修改目标类的前提下增强目标方法
* 不同点：
  * 目的不同
    装饰者是为了增强目标对象
    静态代理是为了保护和隐藏目标对象
  * 获取目标对象构建的地方不同
    装饰者是由外界传递进来，可以通过构造方法传递
    静态代理是在代理类内部创建，以此来隐藏目标对象
  
### 适用场景

以下情况使用Decorator模式  
- 在不影响其他对象的情况下， 以动态、 透明的方式给单个对象添加职责。
- 处理那些可以撤消的职责。
- 当不能采用生成子类的方法进行扩充时。 一种情况是， 可能有大量独立的扩展， 为支持每一种组合将产生大量的子类， 使得子类数目呈爆炸性增长。 另一种情况可能是因为类定义被隐藏， 或类定义不能用于生成子类。