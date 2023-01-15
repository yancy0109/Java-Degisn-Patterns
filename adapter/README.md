## 适配器模式



### 目的

将一个类的接口转换成客户希望的另外的一个接口，使得原本由于接口不兼容而不能一起工作的那些类可以一起工作。



### 结构

- 目标Target：当前系统业务所期待的接口，它可以是抽象类或者接口
- 适配者Adaptee：它是被访问和适配的现存组件库中的组件接口
- 适配器Adapter：它是一个转换器，通过继承或引用适配者的对象，把适配者接口转换成目标接口，让客户按目标接口的格式访问适配者。



### 类适配器模式

> 实例：当前一台电脑只能读取SD卡，而读取TF卡中内容就需要通过适配器模式实现，通过创建读卡器，读取TF卡中的内容。

![image-20230115111306872](https://xingqiu-tuchuang-1256524210.cos.ap-shanghai.myqcloud.com/11721/image-20230115111306872.png)

#### 目标接口

```java
/**
 * SDCard接口
 * 目标接口
 * @author yancy0109
 */
public interface SDCard {

    String readSD();
}

/**
 * SDCardImpl实现类
 * 目标接口可用实现类
 * @author yancy0109
 */
public class SDCardImpl implements SDCard {
    @Override
    public String readSD() {
        return "Read from SD";
    }
}
```

#### 适配者类

```java
/**
 * TFCard接口
 * 适配者类接口
 * @author yancy0109
 */
public interface TFCard {

    String readTF();
}


/**
 * TFCard接口实现类
 * 适配者类实现类
 * @author yancy0109
 */
public class TFCardImpl implements TFCard {

    @Override
    public String readTF() {
        return "Read from TF";
    }
}
```

#### 适配器类

**通过继承适配者类，并对目标接口进行重写，在接口内调用继承适配适配者类得到的方法，可以加工后再返回。**

```java
/**
 * 适配器类
 * 类适配器模式
 * TF读取适配器
 * @author yancy0109
 */
public class SDAdapterTFClass extends TFCardImpl implements SDCard{

    @Override
    public String readSD() {
        return "通过Adapter进行读取：" + this.readTF();
    }
}
```

#### App

```java
/**
 * 调用者类
 * 负责调用SDCard对象进行读取
 * @author yancy0109
 */
public class Computer {

    public String readSD(SDCard sdCard) {
        return sdCard.readSD();
    }
}

/**
 * 测试类
 * @author yancy0109
 */
public class App {
    public static void main(String[] args) {
        // 通过调用类进行测试
        Computer computer = new Computer();
        // 可用类
        SDCardImpl sdCard = new SDCardImpl();  // Read from SD
        System.out.println(computer.readSD(sdCard));
        // 适配器类
        SDAdapterTF sdAdapterTFClass = new SDAdapterTF();
        System.out.println(computer.readSD(sdAdapterTFClass));  // 通过Adapter进行读取：Read from TF
    }
}
```

#### 总结

**通过继承适配者类，并对目标接口进行重写，在接口内调用继承适配适配者类得到的方法，可以加工后再返回。**

这种模式**违背了合成复用原则**，类适配器是客户类**在有一个接口规范的情况下可用**，否则不可用。

### 对象适配器模式

![image-20230115113712935](https://xingqiu-tuchuang-1256524210.cos.ap-shanghai.myqcloud.com/11721/image-20230115113712935.png)

### 适配器类

```java
/**
 * 适配器类
 * 对象适配器模式
 * TF读取适配器
 * @author yancy0109
 */
@AllArgsConstructor
public class SDAdapterTFObject implements SDCard{

    private TFCard tfCard;
    @Override
    public String readSD() {
        return "通过Adapter进行读取：" + tfCard.readTF();
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
        // 通过调用类进行测试
        Computer computer = new Computer();
        // 可用类
        SDCardImpl sdCard = new SDCardImpl();  // Read from SD
        System.out.println(computer.readSD(sdCard));
        // 对象适配器模式 适配器类
        SDAdapterTFObject sdAdapterTFObject = new SDAdapterTFObject(new TFCardImpl());
        System.out.println(sdAdapterTFObject.readSD());  // 通过Adapter进行读取：Read from TF
    }
}
```

#### 总结

与类适配器模式不同，通过对象依赖于适配者类，较继承关系而言，属性的依赖耦合度更低。

### 补充

接口适配器模式，对目标接口通过抽象接口适配器默认实现空方法，在调用适配器时根据自己想要的方法进行重写 。

### 应用场景

* 以前开发的系统存在满足新系统功能需求的类，但其接口同新系统的接口不一致
* 使用第三方提供的组件，但组件接口定义和自己要求的接口定义不同



