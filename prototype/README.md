## 原型模式

### 概念
用原型实例指定创建对象的种类，并且通过拷贝原型创建新的对象。

避免了复杂的创建与初始化，也可以基于克隆实例进行属性修改。

###  结构

- 抽象结构类Cloneable：调用clone方法的类必须实现该类。
- 具体实现类：实现Cloneable接口的类，他是可复制的对象。

### 实现（浅拷贝）

#### 浅拷贝

创建一个新对象，对当前对象的域进行同样的赋值，但他们域的内容并不会自我克隆。

#### Eg.

以多利羊为例，对它进行克隆操作。

##### 原型抽象类

对Cloneable接口封装，异常进行处理

```java
/**
 * 原型抽象类
 * 对Cloneable接口封装
 * @author yancy0109
 */
public abstract class ProtoType<T> implements Cloneable{

    @SneakyThrows
    public T copy() {
        return (T) super.clone();
    }
}
```

##### 原型实体类

```java
/**
 * 原型模式
 * 原型实例：多利羊
 * @author yancy0109
 */
public class Sheep extends ProtoType<Sheep>{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

##### 测试

```java
/**
 * 测试类
 * @author yancy0109
 */
public class App {
    public static void main(String[] args) {
        // 创建原型
        Sheep sheep = new Sheep();
        sheep.setName("多利");
        // 原型赋值
        Sheep cloneSheep = sheep.copy();
        System.out.println(sheep == cloneSheep);  // false
        System.out.println(sheep.getName());  // 多利
        System.out.println(cloneSheep.getName());  // 多利
    }
}
```

### 深拷贝

以Student类以及Pen类进行说明，学生对象拥有Pen对象属性。

#### 原型抽象类

实现Cloneable接口，通过序列化与反序列化的方式实现了深拷贝。

```java
/**
 * 原型抽象类
 * 对Cloneable接口封装
 * clone采用深克隆
 * @author yancy0109
 */
public abstract class ProtoTypeForDeepClone<T> implements Cloneable{

    @SneakyThrows
    public T copy() {
        // 缓冲输出流
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // 对象输出流
        ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
        outputStream.writeObject(this);
        // 从缓冲输出流中获取缓冲输出流
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        // 获取对象输出流
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return (T) objectInputStream.readObject();
    }
}
```

#### 原型实体类

Student实体类：实现浅拷贝

```java
/**
 * 原型：学生
 * 使用浅拷贝
 * @author yancy0109
 */
@Data
@ToString
@AllArgsConstructor
public class StudentShallowCopy extends ProtoType<StudentShallowCopy>{
    private Pen pen;
}
```

Student实体类：实现深拷贝

```java
/**
 * 原型：学生
 * 深拷贝
 * @author yancy0109
 */
@Data
@ToString
@AllArgsConstructor
public class StudentDeepCopy extends ProtoTypeForDeepClone<StudentDeepCopy> implements Serializable {
    private Pen pen;
}
```

Pen实体类

```java
/**
 * 原型：笔
 * @author yancy0109
 */
@Data
@ToString
@AllArgsConstructor
public class Pen implements Serializable {

    private String name;
}
```

#### 测试

```java
/**
 * 测试类
 * @author yancy0109
 */
public class App {
    public static void main(String[] args) {
        // 深拷贝与浅拷贝
        // 浅拷贝
        StudentShallowCopy studentShallow = new StudentShallowCopy(new Pen("浅拷贝"));
        System.out.println(studentShallow);  // StudentShallowCopy(pen=Pen(name=浅拷贝))
        StudentShallowCopy studentShallowCopy = studentShallow.copy();
        System.out.println(studentShallowCopy);  // StudentShallowCopy(pen=Pen(name=浅拷贝))
        // 对拷贝对象引用属性进行修改
        studentShallowCopy.getPen().setName("这是浅拷贝");
        // 输出原型对象
        System.out.println(studentShallow);  // StudentShallowCopy(pen=Pen(name=这是浅拷贝))
        System.out.println(studentShallow.getPen() == studentShallowCopy.getPen());  // true

        // 深拷贝
        StudentDeepCopy studentDeep = new StudentDeepCopy(new Pen("深拷贝"));
        System.out.println(studentDeep);  // StudentDeepCopy(pen=Pen(name=深拷贝))
        StudentDeepCopy studentDeepCopy = studentDeep.copy();
        System.out.println(studentDeepCopy);  // StudentDeepCopy(pen=Pen(name=深拷贝))
        // 对拷贝对象引用属性进行修改
        studentDeepCopy.getPen().setName("这是深拷贝");
        // 输出原型对象
        System.out.println(studentDeep);  // StudentDeepCopy(pen=Pen(name=深拷贝))
        System.out.println(studentDeep.getPen() == studentDeepCopy.getPen());  // false

    }
}
```

#### 总结

深拷贝与浅拷贝的区别就在于对于引用对象的处理，浅拷贝虽然得到了一个新对象，但是对象内的引用属性与原型对象指向的仍为同一个对象。

![image-20230112174714660](https://xingqiu-tuchuang-1256524210.cos.ap-shanghai.myqcloud.com/11721/image-20230112174714660.png)

[图片来源]: https://blog.csdn.net/m0_57711043/article/details/116976491	"原型模式（浅拷贝和深拷贝）纯干货"

### 适用场景

 当一个系统应该独立于它的产品创建、 构成和表示时， 要使用Prototype模式；

 以及

* 当要实例化的类是在运行时刻指定时， 例如， 通过动态装载
* 为了避免创建一个与产品类层次平行的工厂类层次时
* 当一个类的实例只能有几个不同状态组合中的一种时，建立相应数目的原型并克隆它们可能比每次用合适的状态手工实例化该类更方便一些
