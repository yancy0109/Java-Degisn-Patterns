## 建造者模式



### 目的

建造者模式是一步一步创建一个复杂的对象，它允许用户通过指定复杂对象的类型和内容就可以创建它们，用户不需要知道内部具体构建细节。

将一个复杂对象的构建与表示分离，使得同样的构建过程可以创建不同的表示。

### 结构

* 抽象建造者类（Builder）：这个接口规定要实现复杂对象的那些部分的创建，并不涉及具体的部件对象的创建。 

* 具体建造者类（ConcreteBuilder）：实现 Builder 接口，完成复杂产品的各个部件的具体创建方法。在构造过程完成后，提供产品的实例。 

* 产品类（Product）：要创建的复杂对象。

* 指挥者类（Director）：调用具体建造者来创建复杂对象的各个部分，在指导者中不涉及具体产品的信息，只负责保证对象各部分完整创建或按某种顺序创建。 

### 实例

Eg. 以自行车为例子，我们需要创建车子不同的车架以及车座。
#### Product

```java
/**
 * Product
 * @author yancy0109
 */
@Data
@ToString
public class Bike {

    // 车架
    private String frame;

    // 车座
    private String seat;
}
```

#### Builder

```java
/**
 * Builder
 * @author yancy0109
 */
public abstract class Builder {

    protected Bike bike = new Bike();

    abstract void setFrame();

    abstract void setSeat();

    abstract Bike build();
}
```

#### ConcreteBuilder

```java
/**
 * ConcreteBuilder
 * @author yancy0109
 */
@NoArgsConstructor
public class ConcreteBuilder extends Builder{

    @Override
    public void setFrame() {
        bike.setFrame("frame");
    }

    @Override
    public void setSeat() {
        bike.setSeat("seat");
    }

    // 创建方法
    public Bike build() {
        return bike;
    }
}
```

Director

```java
/**
 * Director
 * @author yancy0109
 */
@AllArgsConstructor
public class Director {

    private Builder builder;

    public Bike construct() {
        builder.setSeat();
        builder.setFrame();
        return builder.build();
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

        // 对复杂对象各部分进行创建的具体建造这类
        ConcreteBuilder concreteBuilder = new ConcreteBuilder();
        // 控制创建顺序及流程的指挥者类
        Director director = new Director(concreteBuilder);
        // 建造
        Bike bike = director.construct();
        System.out.println(bike);  // Bike(frame=frame, seat=seat)
    }
}
```

#### 总结

在我看来，通过具体实体建造类指定了产品创建所使用部分组件细节，使用了建造者抽象类限定了属性内容，通过指挥者对对象进行装配再获得最终对象，即可把对复杂对象属性的指定与固定创建流程分离开，进行了一定的解耦，再创建新产品，只需要创建新的创建者类，仍旧可以使用统一的创建流程，即指挥者类。

#### 补充

如果**构建对象流程简单，并且希望简化一定系统的结构**，我们可以把指挥者类与抽象构造者进行结合

```java
/**
 * 将Builder与Director融合，简化结构
 * @author yancy0109
 */
public abstract class SimpleBuilder {

    protected Bike bike = new Bike();

    abstract void setFrame();

    abstract void setSeat();

    abstract Bike build();

    public Bike construct() {
        this.setFrame();
        this.setSeat();
        return this.build();
    }
}
```

### 优缺点

#### 优点

- 建造者模式的封装性很好。使用建造者模式可以有效的封装变化，在使用建造者模式的场景中，一般产品类和建造者类是比较稳定的，因此，将主要的业务逻辑封装在指挥者类中对整体而言可以取得比较好的稳定性。
- 在建造者模式中，客户端不必知道产品内部组成的细节，将产品本身与产品的创建过程解耦，使得相同的创建过程可以创建不同的产品对象。
- 可以更加精细地控制产品的创建过程 。将复杂产品的创建步骤分解在不同的方法中，使得创建过程更加清晰，也更方便使用程序来控制创建过程。
- 建造者模式很容易进行扩展。如果有新的需求，通过实现一个新的建造者类就可以完成，基本上不用修改之前已经测试通过的代码，因此也就不会对原有功能引入风险。符合开闭原则。

#### 缺点

建造者模式所创建的产品一般具有较多的共同点，其组成部分相似，如果产品之间的差异性很大，则不适合使用建造者模式，因此其使用范围受到一定的限制。

### 扩展（常用）

当我们在一个类构造器需要传入很多参数，随着参数的数量越来越多，构造方法的参数数量也会越来越多，对属性的修改使得构造方法的维护难度增加了许多，这被称为”反可伸缩构造方法模式“，我们可以利用建造者模式进行重构。

#### 创建对象

```java
/**
 * 实例对象：Person
 * @author yancy0109
 */
@ToString
public class Person {

    private String gender;
    private String name;
    private int age;
    private String hair;

    // 私有构造方法
    private Person(Buidler buidler) {
        this.gender = buidler.gender;
        this.name = buidler.name;
        this.age = buidler.age;
        this.hair = buidler.hair;
    }

    // Person类的创建者
    public static class Buidler {
        private String gender;
        private String name;
        private int age;
        private String hair;

        public Buidler gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Buidler name(String name) {
            this.name = name;
            return this;
        }

        public Buidler sage(int age) {
            this.age = age;
            return this;
        }

        public Buidler hair(String hair) {
            this.hair = hair;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
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
        // Person类对象的构建过程
        Person person = new Person.Buidler()
                .gender("男")
                .name("哈哈")
                .hair("长发")
                .sage(18)
                .build();
        System.out.println(person);  // Person(gender=男, name=哈哈, age=18, hair=长发)
    }
}
```

这样的代码在使用起来更方便，某种程度上也可以提高开发效率。

----

### 使用场景

建造者模式创建的是复杂对象，其产品的各个部分经常面临着剧烈的变化，但将它们组合在一起的算法却相对稳定，所以它通常在以下场合使用：

- 创建的对象较复杂，由多个部件构成，各部件面临着复杂的变化，但构件间的建造顺序是稳定的。
- 创建一个复杂对象的算法应该独立于组成对象的组成部分以及它们是如何组合的

- 构建过程必须为所构造的对象提供不同的表示形式

### 对比

- 与工厂方法模式对比：工厂方法注重于创建整体对象，建造者模式注重于部件构建的过程，一步步创建出复杂的对象。
- 与抽象工厂模式对比：抽象工厂不关心构建过程，它生成了一系列同族产品，而建造者则对一系列部件组装返回了完整对象。

