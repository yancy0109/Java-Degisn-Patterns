## 单例模式

### 概念

单例模式（Singleton Pattern）是 Java 中最简单的设计模式之一。这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。

这种模式涉及到一个单一的类，该类负责创建自己的对象，同时确保只有单个对象被创建。这个类提供了一种访问其唯一的对象的方式，可以直接访问，不需要实例化该类的对象。

### 单例模式的结构

- 单例类。只能创建一个实例的类
- 访问类。使用单例类

### 实现方式

#### 饿汉式

类加载就会导致该单实例对象被创建

##### 问题

创建对象时随着类的加载而被创建，如果对象足够大，并且未被使用的话，就会导致内存的浪费

#### 懒汉式

类加载不会导致该单实例对象被创建，而是首次使用该对象时才会创建。

### 实现实例

#### 饿汉式

##### 实现

###### 静态成员变量

```java
/**
 * Singleton
 * 实现方式 --》
 * 饿汉式：静态成员变量，线程安全
 * @author yancy0109
 */
public class EagerlyInitializedSingleton {

    // 私有构造方法
    private EagerlyInitializedSingleton() {

    }

    // 在本类中创建本类对象,在类加载至内存后便会创建此实例
    // 也可以通过静态代码块给变量赋值
    private static EagerlyInitializedSingleton instance = new EagerlyInitializedSingleton();

    // 提供一个公共的访问方式，让外界获取该对象
    public static EagerlyInitializedSingleton getInstance() {
        return instance;
    }
}
```

###### 枚举类

基于Java语言特性。。。如此实现则无法破坏单例模式

```java
/**
 * 饿汉式
 * 枚举类实现
 * @author yancy0109
 */
public enum EnumSingleton {
    INSTANCE;
}
```

#### 懒汉式

##### 实现

###### 线程不安全

在获取Instance的注释里标出了该实现方法发生线程不安全问题的位置。

```java
/**
 * 单例模式
 * 实现方式：
 * 懒汉式，线程不安全
 * @author yancy0109
 */
public class LazilyInitializedSingleton {

    // 私有构造方法
    private LazilyInitializedSingleton() {

    }

    // 单例对象
    private static LazilyInitializedSingleton instance;

    // 获取对象的方法
    public static LazilyInitializedSingleton getInstance() {
        // 如果当前对象未被使用，即对象引用未NULL
        if (instance == null) {
            // 在这里存在线程不安全的问题
            // 给对象赋值
            instance = new LazilyInitializedSingleton();
        }
        return instance;
    }
}
```

###### 线程安全

```java
/**
 * 单例模式
 * 实现方式：
 * 懒汉式，线程安全
 * @author yancy0109
 */
public class ThreadSafeLazyLoadedSingleton {

    // 私有构造方法
    private ThreadSafeLazyLoadedSingleton() {
		// 防止通过反射进行实例化
        if (null != instance) {
            throw new IllegalStateException("该实例已经存在");
        }
    }

    // 单例对象
    private static ThreadSafeLazyLoadedSingleton instance;

    // 获取对象的方法 synchronized 只有一个线程可以调用此方法 避免了多线程同时访问方法导致线程不安全
    public static synchronized ThreadSafeLazyLoadedSingleton getInstance() {
        // 如果当前对象未被使用，即对象引用未NULL
        if (instance == null) {
            // 在这里存在线程不安全的问题
            // 给对象赋值
            instance = new ThreadSafeLazyLoadedSingleton();
        }
        return instance;
    }
}
```

这样的实现虽然避免了线程不安全情况，但是对于大多数操作只是读操作，并不会引发线程安全问题，所以**对方法加锁会导致性能降低**。
测试方法：

```java
// 线程安全 懒汉式
ThreadSafeLazyLoadedSingleton threadSafeLazyLoadedSingleton1 = ThreadSafeLazyLoadedSingleton.getInstance();
ThreadSafeLazyLoadedSingleton threadSafeLazyLoadedSingleton2 = ThreadSafeLazyLoadedSingleton.getInstance();
System.out.println(threadSafeLazyLoadedSingleton1 == threadSafeLazyLoadedSingleton2); // TRUE
```

###### 双重检查锁

```java
/**
 * 单例模式
 * 懒汉式
 * 双重检查锁
 * @author yancy0109
 */
public class ThreadSafeDoubleCheckLockSingleton {

    // 私有构造方法
    private ThreadSafeDoubleCheckLockSingleton() {
		// 防止通过反射进行实例化
        if (null != instance) {
            throw new IllegalStateException("该实例已经存在");
        }
    }

    // 实例
    private static volatile ThreadSafeDoubleCheckLockSingleton instance;

    // 对外公共方法
    public static ThreadSafeDoubleCheckLockSingleton getInstance() {
        // 第一次判断 只有instance需要创建 才会开启锁
        if (instance == null) {
            synchronized (ThreadSafeDoubleCheckLockSingleton.class) {
                // 第二次判断
                if (instance == null) {
                    instance = new ThreadSafeDoubleCheckLockSingleton();
                }
            }
        }
        return instance;
    }
}
```

双重检查锁是一种非常好的单例实现模式

这种实现方式有**两个关键点**

第一点是在getInstance方法内进行了两次判断，如果instance不为空，则根本不需要加锁，而是直接进行读操作，如果为空，则进入加锁代码块，二次判断，决定是否进行加锁。

第二点是在实例变量加上了volatile关键词修饰，作用是进行防止指令进行重排序

>下面将进行解释
>
>当我们在new一个对象时，会进行三步操作：
>
>1. 分配对象内存
>2. 调用构造器方法，执行初始化
>3. 将对象引用赋值给变量
>
>但是虚拟机在运行时，可能会发生重排序的情况----对2、3步骤进行重排序 --> 先将引用赋值，再进行初始化方法。如果时单线程执行则没有问题，但是如果是多线程执行，我们在加锁的时候会发生如下的情况：
>
>当加锁线程执行至 1，3，并未对对象进行初始化，而另一个线程恰巧进行对象判断，它发现此时对象指向的对象并非空，所以进行了返回调用，但是实际上另一个线程在执行时还未对实例进行初始化，于是发生了异常。
>
>图片来源 [[为什么双重检查锁模式需要 volatile ？](https://www.cnblogs.com/goodAndyxublog/p/11356402.html)](https://www.cnblogs.com/goodAndyxublog/p/11356402.html)
>
><img src="https://img2018.cnblogs.com/blog/1419561/201908/1419561-20190815102701585-1812374785.png">
>
>当我们加上volatile关键字后，虚拟机就不会发生重排序的情况，只会先初始化对象再进行引用赋值，如果线程二判断线程非NULL获取到的对象，一定是一个初始化后的对象，不会再发生异常的情况。

###### 静态内部类

**内部类在被引用之前不会被类加载器加载，直到客户端调用的时候才被加载**

```java
/**
 * 单例模式
 * 懒汉式
 * 静态内部类实现
 * JVM在加载外部类的过程中，不会加载静态内部类
 * @author yancy0109
 */
public class InitializingOnDemandHolderIdiom {

    private InitializingOnDemandHolderIdiom() {

    }

    // 静态内部类
    private static class HelperHolder {
        // 生命外部类并初始化外部类对象
        private static final InitializingOnDemandHolderIdiom INSTANCE = new InitializingOnDemandHolderIdiom();
    }

    // 外部方法
    public static InitializingOnDemandHolderIdiom getInstance() {
        return HelperHolder.INSTANCE;
    }
}
```

静态内部类是一种优秀的单例模式，也是开源项目中比较常用的一种单例模式。它在没有加任何锁的情况下，保证了多线程下的安全，并且没有任何性能影响和空间的浪费。依靠于**语言保证 language guarantees**

### 问题

#### 反序列化

```java
/**
 * 单例模式
 * 防止反序列化破坏单例
 * @author yancy0109
 */
public class AvoidSerializableSingleton implements Serializable {

    private AvoidSerializableSingleton() {

    }

    // 静态内部类
    private static class HelperHolder {
        // 生命外部类并初始化外部类对象
        private static final AvoidSerializableSingleton INSTANCE = new AvoidSerializableSingleton();
    }

    // 外部方法
    public static AvoidSerializableSingleton getInstance() {
        return HelperHolder.INSTANCE;
    }

    // 在进行反序列化时，会自动调用该方法，将该方法的返回值直接返回
    public Object readResolve() {
        return HelperHolder.INSTANCE;
    }
}
```

原因:

在ObjectInputStream类中，存在方法readObject()来对对象进行反序列化，如果需要反序列化为对象则会调用readOrdinaryObject()方法，在代码中存在如下逻辑：

```java
if (obj != null &&
    handles.lookupException(passHandle) == null &&
    // 如果存在ReadResolve方法
    desc.hasReadResolveMethod())
{
    Object rep = desc.invokeReadResolve(obj);
    if (unshared && rep.getClass().isArray()) {
        rep = cloneArray(rep);
    }
    if (rep != obj) {
        // Filter the replacement object
        if (rep != null) {
            if (rep.getClass().isArray()) {
                filterCheck(rep.getClass(), Array.getLength(rep));
            } else {
                filterCheck(rep.getClass(), -1);
            }
        }
        // 设置obj为调用方法返回的rep对象
        handles.setObject(passHandle, obj = rep);
    }
}
return obj;
```

在可序列化类中对readResolve方法进行重写，即可避免应反序列化而破坏单例模式。

#### 反射

我们的类可以通过反射来获取私有构造方法来实现再次创建，所以为了防止反射破坏单例模式，我们只需要在私有构造器加了步验证逻辑即可

Eg.

```java
// 私有构造方法
private ThreadSafeLazyLoadedSingleton() {
    // 防止通过反射进行实例化
    if (null != instance) {
        throw new IllegalStateException("该实例已经存在");
    }
}
```







 

