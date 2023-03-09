## 观察者模式

### 目的
定义一种一对多的对象依赖关系这样当一个对象改变状态时，所有依赖它的对象都将自动通知或更新。

又被称为发布-订阅（Publish/Subscribe）模式。

### 结构

* Subject：抽象主题（抽象被观察者），抽象主题角色把所有观察者对象保存在一个集合里，每个主题都可以有任意数量的观察者，抽象主题提供一个接口，可以增加和删除观察者对象。
* ConcreteSubject：具体主题（具体被观察者），该角色将有关状态存入具体观察者对象，在具体主题的内部状态发生改变时，给所有注册过的观察者发送通知。
* Observer：抽象观察者，是观察者的抽象类，它定义了一个更新接口，使得在得到主题更改通知时更新自己。
* ConcrereObserver：具体观察者，实现抽象观察者定义的更新接口，以便在得到主题更改通知时更新自身的状态。

### 实例

> 当一个微信公众号在内容更新后，它会推送给所有关注这个公众号的微信用户端。
>
> 在这里公众号就是被观察者，而关注的用户即为观察者。

#### 抽象主题

```java
/**
 * 抽象主题角色
 * @author yancy0109
 */
public interface Subject {

    /**
     * 添加订阅者（观察者）对象
     */
    void attach(observer observer);

    /**
     * 删除订阅者（观察者）对象
     */
    void detach(observer observer);

    /**
     * 通知订阅者更新消息
     */
    void notify(String msg);
}
```

#### 具体主题

```java
/**
 * 具体主题角色类
 * @author yancy0109
 */
public class SubScriptionSubject implements Subject {

    //定义集合存储多个观察者角色
    private List<observer> userObserverList = new ArrayList<>();

    @Override
    public void attach(observer observer) {
        userObserverList.add(observer);
    }

    @Override
    public void detach(observer observer) {
        userObserverList.remove(observer);
    }

    @Override
    public void notify(String msg) {
        // 遍历通知观察者对象
        for (observer observer : userObserverList) {
            observer.update(msg);
        }

    }
}
```

#### 抽象观察者

```java
/**
 * 抽象观察者
 * @author yancy0109
 */
public interface observer {
    public void update(String msg);
}
```

#### 具体观察者

```java
/**
 * 具体观察者角色类
 * @author yancy0109
 */
public class WechatUser implements observer{
    @Override
    public void update(String msg) {
        System.out.println(this + "接收到通知：" + msg);
    }
}
```

#### 测试类

```java
/**
 * @author yancy0109
 */
public class App {

    public static void main(String[] args) {
        WechatUser wechatUser1 = new WechatUser();
        WechatUser wechatUser2 = new WechatUser();
        WechatUser wechatUser3 = new WechatUser();

        // 创建订阅对象
        SubScriptionSubject subScriptionSubject = new SubScriptionSubject();

        // 添加观察者
        subScriptionSubject.attach(wechatUser1);
        subScriptionSubject.attach(wechatUser2);
        subScriptionSubject.attach(wechatUser3);

        subScriptionSubject.notify("更新完成");

        //  ----> PRINT
        //        com.yancy.WechatUser@74a14482接收到通知：更新完成
        //        com.yancy.WechatUser@1540e19d接收到通知：更新完成
        //        com.yancy.WechatUser@677327b6接收到通知：更新完成
    }
}
```

#### 总结

观察者模式中，被观察者通过聚合观察者对象，在自身发生更新需要通知时，调用观察者对象的通知方法，实现推送信息给每个被观察者。

### 优缺点

#### 优点

* 降低了目标与观察者之间的耦合关系，两者之间是抽象耦合关系。
* 被观察者发送通知，所有注册的观察者都会收到信息【可以实现广播机制】

#### 缺点

* 如果观察者非常多的话，那么所有的观察者收到被观察者发送的通知会耗时
* 如果被观察者有循环依赖的话，那么被观察者发送通知会使观察者循环调用，会导致系统崩溃

### 使用场景

- 当抽象具有两个方面时，一个方面依赖于另一个方面。将这些方面封装在单独的对象中，可以使你分别进行更改和重用
- 当一个对象的改变的同时需要改变其他对象，同时你又不知道有多少对象需要改变时
- 当一个对象可以通知其他对象而无需假设这些对象是谁时。换句话说，你不想让这些对象紧耦合。
