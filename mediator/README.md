## 中介者模式

### 目的
用一个中介对象来封装一系列的对象交互，中介者使各对象不需要显式地相互引用，从而使其耦合松散，而且可以独立地改变它们之间的交互。
### 结构

* 抽象中介者（Mediator）角色：它是中介者的接口，提供了同事对象注册与转发同事对象信息的抽象方法。

* 具体中介者（ConcreteMediator）角色：实现中介者接口，定义一个 List 来管理同事对象，协调各个同事角色之间的交互关系，因此它依赖于同事角色。
* 抽象同事类（Colleague）角色：定义同事类的接口，保存中介者对象，提供同事对象交互的抽象方法，实现所有相互影响的同事类的公共功能。
* 具体同事类（Concrete Colleague）角色：是抽象同事类的实现者，当需要与其他同事对象交互时，由中介者对象负责后续的交互。

### 实例

> 在一个聊天室中，有许多用户，每个用户都通过聊天室进行交流，而不是彼此间独立建立联系。

![中介者模式的 UML 图](https://raw.githubusercontent.com/yancy0109/image/main/img/mediator_pattern_uml_diagram.jpg)

#### 抽象中介者

```java
/**
 * 抽象中介者
 * @author yancy0109
 */
public abstract class Chat {

    protected List<User> userList = new ArrayList<>();

    public final void loginUser(User user) {
        userList.add(user);
    }

    public abstract void sendMsg(User user, String msg);
}
```

#### 具体中介者

```java
/**
 * 具体同事类
 * 聊天室用户
 * @author yancy0109
 */
public class ChatUser extends User {

    private String name;

    public ChatUser(Chat chat, String username) {
        super(chat);
        this.name = username;
        // 注册到中介者
        chat.loginUser(this);
    }

    @Override
    public void sendMeg(String msg) {
        String sendMsg = name + ":" + msg;
        System.out.println(name + "send : " + msg);
        this.chat.sendMsg(this, sendMsg);
    }

    @Override
    public void receiveMeg(String msg) {
        System.out.println("[" + new Date() + "]" + "[" + this.name +  "]" + ": 收到消息" + msg);
    }
}
```

#### 抽象同事类

```java
/**
 * 抽象同事类
 * 用户
 * @author yancy0109
 */
public abstract class User {

    protected Chat chat;

    public User(Chat chat) {
        this.chat = chat;
    }

    public abstract void sendMeg(String msg);

    public abstract void receiveMeg(String msg);

}
```


#### 具体同事类

```java
/**
 * 具体同事类
 * 聊天室用户
 * @author yancy0109
 */
public class ChatUser extends User {

    private String name;

    public ChatUser(Chat chat, String username) {
        super(chat);
        this.name = username;
        // 注册到中介者
        chat.loginUser(this);
    }

    @Override
    public void sendMeg(String msg) {
        String sendMsg = name + ":" + msg;
        System.out.println(name + "send : " + msg);
        this.chat.sendMsg(this, sendMsg);
    }

    @Override
    public void receiveMeg(String msg) {
        System.out.println("[" + new Date() + "]" + "[" + this.name +  "]" + ": 收到消息" + msg);
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
        // 中介者
        Chat chatRoom = new ChatRoom();
        // 同事类
        ChatUser user1 = new ChatUser(chatRoom, "小明");
        ChatUser user2 = new ChatUser(chatRoom, "小李");

        user1.sendMeg("哈哈");
        user2.sendMeg("哈哈");
        // 》》》》 输出
        // 小明send : 哈哈
        // [Thu Mar 09 18:30:30 GMT+08:00 2023][小李]: 收到消息小明:哈哈
        // 小李send : 哈哈
        // [Thu Mar 09 18:30:30 GMT+08:00 2023][小明]: 收到消息小李:哈哈
    }
}
```

#### 总结

同层次间的同事类关系耦合转向了所有同事类与中介者之间的耦合，通过中介者进行交流，减少了耦合。

### 总结

#### 优点

* 松散耦合

  中介者模式通过把多个同事对象之间的交互封装到中介者对象里面，从而使得同事对象之间松散耦合，基本上可以做到互补依赖。这样一来，同事对象就可以独立地变化和复用，而不再像以前那样“牵一处而动全身”了。

* 集中控制交互

  多个同事对象的交互，被封装在中介者对象里面集中管理，使得这些交互行为发生变化的时候，只需要修改中介者对象就可以了，当然如果是已经做好的系统，那么就扩展中介者对象，而各个同事类不需要做修改。

* 一对多关联转变为一对一的关联

  没有使用中介者模式的时候，同事对象之间的关系通常是一对多的，引入中介者对象以后，中介者对象和同事对象的关系通常变成双向的一对一，这会让对象的关系更容易理解和实现。

#### 缺点

- 当同事类太多时，中介者的职责将很大，它会变得复杂而庞大，以至于系统难以维护。


### 使用场景

* 系统中对象之间存在复杂的引用关系，系统结构混乱且难以理解。
* 当想创建一个运行于多个类之间的对象，又不想生成新的子类时。
