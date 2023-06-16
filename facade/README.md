## 外观模式



### 目的

对外提供子系统的一致接口/接口集合。外观模式定义了高层接口，使得子系统使用更加容易。

### 结构

- 外观角色：为多个子系统对外提供一个共同的接口
- 子系统角色：实现系统的部分功能，客户通过外观角色访问它。

### 案例

> 智能家具系统就是对外提供了一个统一的接口，可以协调多个子系统运作。

![image-20230125212236484](https://raw.githubusercontent.com/yancy0109/image/main/11721/image-20230125212236484.png)

#### 子系统角色

```java
/**
 * @author yancy0109
 * 子系统角色：电视
 */
public class Tv {
    void on () {
        System.out.println("开电视");
    }
    void off () {
        System.out.println("关电视");
    }
}

/**
 * @author yancy0109
 *  子系统角色：灯
 */
public class Light {
    void on () {
        System.out.println("开灯");
    }
    void off () {
        System.out.println("开灯");
    }
}

/**
 * @author yancy0109
 * 子系统角色：空调
 */
public class AirCondition {
    void on () {
        System.out.println("开空调");
    }
    void off () {
        System.out.println("关空调");
    }
}
```

#### 外观角色

```java
/**
 * @author yancy0109
 * 外观角色：智能家居系统
 */
public class SmartAppFacade {

    private Tv tv = new Tv();

    private Light light = new Light();

    private AirCondition airCondition = new AirCondition();

    void on () {
        tv.on();
        light.on();
        airCondition.on();
        System.out.println("智能家居开启完毕");
    }
    void off () {
        tv.off();
        light.off();
        airCondition.off();
        System.out.println("智能家居关闭完毕");
    }
}
```

#### App

```java
/**
 * 测试
 * @author yancy0109
 */
public class App {
    public static void main(String[] args) {
        // 创建外观对象
        SmartAppFacade smartAppFacade = new SmartAppFacade();

        // 开启
        smartAppFacade.on();
        // 输出：
        // 开电视
        // 开灯
        // 开空调
        //  智能家居开启完毕

        // 关闭
        smartAppFacade.off();
        // 输出：
        // 关电视
        // 开灯
        // 关空调
        // 智能家居关闭完毕
    }
}
```

### 特点

#### 好处

* 降低了子系统与客户端之间的耦合度，使得子系统的变化不会影响调用它的客户类。
* 对客户屏蔽了子系统组件，减少了客户处理的对象数目，并使得子系统使用起来更加容易。

#### 缺点

* 不符合开闭原则，修改很麻烦

### 使用

* 对分层结构系统构建时，使用外观模式定义子系统中每层的入口点可以简化子系统之间的依赖关系。
* 当一个复杂系统的子系统很多时，外观模式可以为系统设计一个简单的接口供外界访问。
* 当客户端与多个子系统之间存在很大的联系时，引入外观模式可将它们分离，从而提高子系统的独立性和可移植性。



