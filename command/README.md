## 命令模式

### 目的

将请求封装为对象，从而使你可以将具有不同请求的客户端参数化，队列或记录请求，并且支持可撤销操作。


### 结构

- 抽象命令角色（Command）：定义命令接口，声明执行的方法。
- 具体命令角色（Concrete Command）：具体的命令，实现命令的接口；通常会持有接收者，并调用接收者的功能来完成命令要执行的操作。
- 实现者/接收者角色（Receiver）：接收者，真正执行命令的对象，任何类都可能成为一个接收者，只要它能够实现命令要求实现的相应功能。
- 调用者/请求者角色（Invoker）：要求命令对象执行请求，通常会持有命令对象，可以持有很多命令对象。这是客户端真正出发命令并执行相应操作的地方，

​	   也相当于使用命令对象的入口。

### 实例

> 在Github看到的一个例子，假如我们的命令对象是一个魔咒，那我们的接收者便可以指向一个地精，这时每个命令对象都会持有各自对应的接收者，而我们的调用者则是巫师，巫师持有了一系列命令对象，随后可以一一执行。

#### 抽象命令对象

```java
/**
 * 抽象命令类
 * @author yancy0109
 */
public interface Command {

    String getName();
    String getTargetName();

    /**
     * 在执行使，也将当前命令对象接收者进行了保存
     */
    void execute(Target target);

    void undo();

    void redo();

}
```

#### 具体命令对象

```java
/**
 * 具体命令对象
 * 隐身咒
 * @author yancy0109
 */
public class InvisibilitySpell implements Command {

    private String name = "隐身咒";

    private Target target;
    private Visibility oldStatus;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getTargetName() {
        return target.getName();
    }

    @Override
    public void execute(Target target) {
        this.target = target;
        oldStatus = target.getVisibility();
        target.setVisibility(Visibility.Invisibility);
    }

    @Override
    public void undo() {
        if (oldStatus != null && target != null) {
            target.setVisibility(oldStatus);
        }
    }

    @Override
    public void redo() {
        if (target != null) {
            target.setVisibility(Visibility.Invisibility);
        }
    }
}

/**
 * 具体命令对象
 * 变大咒
 * @author yancy0109
 */
public class MagnificationSpell implements Command {

    private String name = "变大咒";
    private Target target;

    private Size oldStatus;


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getTargetName() {
        return target.getName();
    }

    @Override
    public void execute(Target target) {
        this.target = target;
        oldStatus = target.getSize();
        target.setSize(Size.BIG);
    }

    @Override
    public void undo() {
        if (oldStatus != null && target != null) {
            target.setSize(oldStatus);
        }
    }

    @Override
    public void redo() {
        if (target != null) {
            target.setSize(Size.BIG);
        }
    }
}


```

#### 接收者对象

```java
/**
 * 对象状态枚举类
 * @author yancy0109
 */
public enum Size {

    BIG("大"),
    NORMAL("正常");

    private String value;

    Size(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

/**
 * 对象状态枚举类
 * @author yancy0109
 */
public enum Visibility {

    Invisibility("隐身"),
    NORMAL("可见");

    private String value;

    Visibility(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

/**
 * 接收者类
 * 抽象类
 * @author yancy0109
 */
public abstract class Target {

    private String name;

    public String getName() {
        return name;
    }

    public Target(String name) {
        this.name = name;
    }

    private Visibility visibility = Visibility.NORMAL;
    private Size size = Size.NORMAL;


    public String getStatus() {
        return name + "隐身状态:" + visibility.getValue() + "   大小:" + size.getValue();
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public Size getSize() {
        return size;
    }
}


/**
 * 接收者类
 * @author yancy0109
 */
public class Goblin extends Target {
    public Goblin() {
        super("哥布林");
    }
}
```

#### 请求者对象

```java
/**
 * 请求者对象
 * 巫师
 * @author yancy0109
 */
public class Wizard {

    private String name;

    // 记录命令请求
    private final Deque<Command> undoStack = new LinkedList<>();
    private final Deque<Command> redoStack = new LinkedList<>();

    public Wizard(String name) {
        this.name = name;
    }

    public void castSpell(Command command, Target target) {
        System.out.println(name + "使用了" + command.getName() + ", 作用在了" + target.getName());
        command.execute(target);
        undoStack.push(command);
        redoStack.push(command);
    }

    public void undoLastSpell() {
        if (!undoStack.isEmpty()) {
            Command previousSpell = undoStack.pollLast();
            previousSpell.undo();
            System.out.println(name + "撤销了使用" + previousSpell.getName() + ", 作用在了" + previousSpell.getTargetName());
        }
    }

    public void redoLastSpell() {
        if (!redoStack.isEmpty()) {
            Command previousSpell = redoStack.pollLast();
            previousSpell.redo();
            System.out.println(name + "重写使用了" + previousSpell.getName() + ", 作用在了" + previousSpell.getTargetName());
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
        // 接收者
        Target target = new Goblin();
        System.out.println(target.getStatus());  // 哥布林隐身状态:可见   大小:正常
        // 具体命令对象
        Command command1 = new InvisibilitySpell();  // 隐身咒
        Command command2 = new MagnificationSpell(); // 变大咒
        // 请求者
        Wizard wizard = new Wizard("甘道夫");

        wizard.castSpell(command1, target);  // 甘道夫使用了隐身咒, 作用在了哥布林
        System.out.println(target.getStatus());  // 哥布林隐身状态:隐身   大小:正常
        wizard.castSpell(command2, target);  // 甘道夫使用了变大咒, 作用在了哥布林
        System.out.println(target.getStatus());  // 哥布林隐身状态:隐身   大小:大
        wizard.undoLastSpell();  // 甘道夫撤销了使用隐身咒, 作用在了哥布林
        wizard.undoLastSpell();  // 甘道夫撤销了使用变大咒, 作用在了哥布林
        System.out.println(target.getStatus());  // 哥布林隐身状态:可见   大小:正常
        wizard.redoLastSpell();  // 甘道夫重写使用了隐身咒, 作用在了哥布林
        wizard.redoLastSpell();  // 甘道夫重写使用了变大咒, 作用在了哥布林
        System.out.println(target.getStatus()); // 哥布林隐身状态:隐身   大小:大
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
* 系统结构更加复杂


### 适用场景

* 系统需要将请求调用者和请求接收者解耦，使得调用者和接收者不直接交互。
* 系统需要在不同的时间指定请求、将请求排队和执行请求。
* 系统需要支持命令的撤销(Undo)操作和恢复(Redo)操作。
* 更多用例
  * 保留请求历史
  * 实现回调功能
  * 实现撤销功能