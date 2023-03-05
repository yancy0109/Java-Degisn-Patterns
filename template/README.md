## 模板方法模式

### 概念

在一个操作中定义算法的骨架，将某些步骤推迟到子类。模板方法允许子类重新定义算法的某些步骤，而无需更改算法的结构

### 结构

- 抽象类：负责给出一个算法的轮廓和骨架，它由一个模板方法和若干个基本方法构成。
  - 模板方法：定义了算法的骨架，按某种顺序调用其包含的基本方法。
  - 基本方法：是实现算法各个步骤的方法，是模板方法的组成部分
    - 抽象方法：一个抽象方法由抽象类声明，由具体子类实现。
    - 具体方法：一个具体方法由一个抽象类或具体类声明并实现，其子类可以进行覆盖也可以直接继承。
    - 钩子方法：在抽象类中已经实现，包含用于判断的逻辑方法或者需要子类重写的空方法两种。钩子方法一般用于判断的逻辑，一般为isXXXX，返回boolean类型。
- 具体子类：实现抽象类中所定义的抽象方法和钩子方法，它们是一个顶级逻辑的组成步骤。

### 实现

图例仅供参考，因为我懒得自己再画

![image-20230305145206393](https://raw.githubusercontent.com/yancy0109/image/main/img/image-20230305145206393.png)

#### 抽象类

```java
/**
 * 抽象类
 * @author yancy0109
 */
public abstract class AbstactClass {

    public void cook() {
        pourOil();
        pourSomething();
        if (isNeedSugar()) {
            addSugar();
        }
        fry();
    }

    // 钩子方法
    boolean isNeedSugar() {
        return true;
    }

    public void pourOil() {
        System.out.println("倒油");
    }

    abstract public void pourSomething();

    public void addSugar() {
        System.out.println("加糖");
    }

    public void fry() {
        System.out.println("翻炒");
    }


}
```

#### 具体子类

```java
/**
 * 具体子类
 * @author yancy0109
 */
public class ConcreteDish extends AbstactClass {

    // 重写钩子方法
    @Override
    boolean isNeedSugar() {
        return false;
    }

    @Override
    public void pourSomething() {
        System.out.println("加点肉and菜");
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
        AbstactClass dish = new ConcreteDish();
        dish.cook();

        // out >>>>>>
        // 倒油
        // 加点肉and菜
        // 翻炒
    }
}
```



#### 实例

InputSteam就提供了抽象方法，再由子类具体实现，但是其他方法都对子类实现方法进行了调用。

```java
// 交由子类实现
public abstract int read() throws IOException;
public int read(byte b[]) throws IOException {
    return read(b, 0, b.length);
}
public int read(byte b[], int off, int len) throws IOException {
    if (b == null) {
        throw new NullPointerException();
    } else if (off < 0 || len < 0 || len > b.length - off) {
        throw new IndexOutOfBoundsException();
    } else if (len == 0) {
        return 0;
    }

    int c = read();
    if (c == -1) {
        return -1;
    }
    b[off] = (byte)c;

    int i = 1;
    try {
        for (; i < len ; i++) {
            c = read();
            if (c == -1) {
                break;
            }
            b[off + i] = (byte)c;
        }
    } catch (IOException ee) {
    }
    return i;
}
```



### 总结

#### 优点
- 提高代码复用性，将相同部分的代码放在抽象的父类中，而将不同的代码放入不同的子类中。

-  实现了反向控制， 通过一个父类调用其子类的操作，通过对子类的具体实现扩展不同的行为，实现了反向控制 ，并符合“开闭原则”。
#### 缺点
- 对每个不同的实现都需要定义一个子类，这会导致类的个数增加，系统更加庞大，设计也更加抽象。
- 父类中的抽象方法由子类实现，子类执行的结果会影响父类的结果，这导致一种反向的控制结构，它提高了代码阅读的难度。


###  适用场景

* 算法的整体步骤很固定，但其中个别部分易变时，这时候可以使用模板方法模式，将容易变的部分抽象出来，供子类实现。
* 需要通过子类来决定父类算法中某个步骤是否执行，实现子类对父类的反向控制。