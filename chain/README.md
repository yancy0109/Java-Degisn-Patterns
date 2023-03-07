## 责任链模式

### 目的

通过给多个对象一个处理请求的机会，避免请求的发送者和它的接收者耦合。串联接收对象并在链条中传递请求直到一个对象处理它。


### 结构

* 抽象处理者（Handle）：定义一个处理请求的接口，包含抽象处理方法和一个后继连接。
* 具体处理者（Concrete Handle）：实现抽象处理者的处理方法，判断能否处理本次请求，如果可以处理请求则处理，否则将该请求转给它的后继者。

### 实例

> 假如我们现在想要请假，在公司内请假系统中，不同长度的假期需要不同的人来处理，如1天仅需要找小组长同意，2天则需要部门经理同意，请求3-7天则需要总经理同意

![image-20230307172303552](https://raw.githubusercontent.com/yancy0109/image/main/img/image-20230307172303552.png)

#### 抽象处理者

```java
/**
 * 抽象处理者
 * @author yancy0109
 */
public abstract class Handler {
    private Handler nextHandler;    // 下一个处理者

    private int maxLevel;   // 最大处理等级
    private int minLevel;   // 最大处理等级

    public Handler(Handler nextHandler, int maxLevel, int minLevel) {
        this.nextHandler = nextHandler;
        this.maxLevel = maxLevel;
        this.minLevel = minLevel;
    }

    public final void submit(LeaveRequest request) {
        int level = request.getDays();
        if (level < minLevel) {
            return;
        }
        if (level < maxLevel) {
            handler(request);
            return;
        }
        nextHandler.submit(request);
    }

    abstract void handler(LeaveRequest request);
}
```

#### 具体处理者

```java
/**
 * 具体处理者
 * 小组长
 * @author yancy0109
 */
public class GroupLeader extends Handler {

    public GroupLeader(Handler nextHandler, int maxLevel, int minLevel) {
        super(nextHandler, maxLevel, minLevel);
    }

    @Override
    void handler(LeaveRequest request) {
        System.out.println("小组长已批准" + request.getDays() + "天的假期");
    }
}

/**
 * 具体处理者
 * 小组长
 * @author yancy0109
 */
public class Manager extends Handler {

    public Manager(Handler nextHandler, int maxLevel, int minLevel) {
        super(nextHandler, maxLevel, minLevel);
    }

    @Override
    void handler(LeaveRequest request) {
        System.out.println("部门经理已批准" + request.getDays() + "天的假期");
    }
}
/**
 * 具体处理者
 * 小组长
 * @author yancy0109
 */
public class GeneralManager extends Handler {

    public GeneralManager(Handler nextHandler, int maxLevel, int minLevel) {
        super(nextHandler, maxLevel, minLevel);
    }

    @Override
    void handler(LeaveRequest request) {
        System.out.println("总经理已批准" + request.getDays() + "天的假期");
    }
}
```

#### Handler构建

```java
/**
 * 请假系统
 * 管理责任链
 * @author yancy0109
 */
public class LeavingSystem {

    private Handler chain;

    public LeavingSystem() {
        buildChain();
    }

    private void buildChain() {
        GeneralManager generalManager = new GeneralManager(null, 7, 3);
        Manager manager = new Manager(generalManager, 3, 2);
        GroupLeader groupLeader = new GroupLeader(manager, 2, 1);
        this.chain = groupLeader;
    }

    public void makeRequest(LeaveRequest request) {
        chain.submit(request);
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
        // 客户端类
        LeavingSystem leavingSystem = new LeavingSystem();
        LeaveRequest request1 = new LeaveRequest(1);
        LeaveRequest request2 = new LeaveRequest(2);
        LeaveRequest request3 = new LeaveRequest(5);
        leavingSystem.makeRequest(request1);    // 小组长已批准1天的假期
        leavingSystem.makeRequest(request2);    // 部门经理已批准2天的假期
        leavingSystem.makeRequest(request3);    // 总经理已批准5天的假期
    }
}
```

### 总结

#### 优点

* 降低系统的耦合度。命令模式能将调用操作的对象与实现该操作的对象解耦。
* 增加或删除命令非常方便。采用命令模式增加与删除命令不会影响其他类，它满足“开闭原则”，对扩展比较灵活。
* 可以实现宏命令。命令模式可以与组合模式结合，将多个命令装配成一个组合命令，即宏命令。
* 方便实现 Undo 和 Redo 操作。命令模式可以与后面介绍的备忘录模式结合，实现命令的撤销与恢复。

#### 缺点

* 使用命令模式可能会导致某些系统有过多的具体命令类。
* 系统结构更加复杂。


### 适用场景

* 系统需要将请求调用者和请求接收者解耦，使得调用者和接收者不直接交互。
* 系统需要在不同的时间指定请求、将请求排队和执行请求。
* 系统需要支持命令的撤销(Undo)操作和恢复(Redo)操作。
* 具体实例
  * Filter
  * ...
