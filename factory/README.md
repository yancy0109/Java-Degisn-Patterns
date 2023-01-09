## 简单工厂模式

它并不是设计模式中的一种，但工厂方法模式与抽象工厂模式其实可以说是在这一基础上的升级。

### 实现方式

- 简单工厂
- 静态工厂方法

我们在这里将使用静态工厂方法进行实现。

### 目的

隐藏创建实现逻辑使得客户端更着重于实现而不是对象的实例化，将提供一个返回实例的静态方法的类，称之为工厂。

### 结构

简单工厂包含如下角色：

* 抽象产品 ：定义了产品的规范，描述了产品的主要特性和功能。
* 具体产品 ：实现或者继承抽象产品的子类
* 具体工厂 ：提供了创建产品的方法，调用者通过该方法来获取产品。

### Eg.

![image-20230109155923275](https://xingqiu-tuchuang-1256524210.cos.ap-shanghai.myqcloud.com/11721/image-20230109155923275.png)

#### 抽象产品 

```java
/**
 * 硬币接口实现类
 * @author yancy0109
 */
public interface Coin {

    /**
     * 返回对硬币的描述
     * @return Description
     */
    public String getDescription();
}
```

#### 具体产品 

GoldCoin && CopperCoin

```java
/**
 * 工厂模式 - 具体产品
 * 铜币
 * @author yancy0109
 */
@Data
public class CopperCoin implements Coin{
    @Override
    public String getDescription() {
        return "这是一枚铜币";
    }
}

/**
 * 工厂模式 - 具体产品
 * 金币
 * @author yancy0109
 */
@Data
public class GoldCoin implements Coin{
    @Override
    public String getDescription() {
        return "这是一枚金币";
    }
}
```

#### 扩展 

CoinType

在这里通过枚举类对应了对应Coin类型，**定义Supplier<Coin子类>属性，实例对Supplier方法进行重写，定义其接口方法为返回具体产品实例**

```java
/**
 * Coin枚举类
 * @author yancy0109
 */
@Getter
@RequiredArgsConstructor
public enum CoinType {

    COPPER(CopperCoin::new),
    GOLD(GoldCoin::new);

    // 通过Suppler返回实例
    private final Supplier<Coin> constructor;
}
```

#### 具体工厂

CoinFactory

```java
/**
 * CoinFactory
 * @author yancy0109
 */
public class CoinFactory {

    /**
     * 静态工厂方法，根据CoinType返回对应的Coin实现类
     */
    public static Coin getCoin(CoinType coinType) {
        return coinType.getConstructor().get();
    }
}
```

#### 测试

```java
// 通过工厂返回对应实例
Coin copper = CoinFactory.getCoin(CoinType.COPPER);
System.out.println(copper.getDescription());  // 这是一枚铜币
Coin gold = CoinFactory.getCoin(CoinType.GOLD);
System.out.println(gold.getDescription());  // 这是一枚金币
```

### 特点

#### 优点

封装了创建对象的过程，可以通过参数直接获取对象。把对象的创建和业务逻辑层分开，这样以后就避免了修改客户代码，如果要实现新产品直接修改工厂类，而不需要在原代码中修改，这样就降低了客户代码修改的可能性，更加容易扩展。

#### 缺点

增加新产品时还是需要修改代码，违背了“开闭原则”。

### 模式扩展

#### 目的

**简单工厂 + 配置文件 **

简单工厂的问题在于具体工厂耦合于具体产品，如果我们将具体产品写入配置文件中，在工厂类中加载配置文件中的全类名，并创建对象进行存储，客户端需要对象，直接进行获取，这种在增加新具体产品时，我们并不需要对现有代码进行修改，只需要在配置文件中指明即可，这样在即解除了两者之间的耦合。

#### 实现

##### 配置文件

bean.properties：指明了对象名即对应实现类

```properties
copper=com.yancy.CopperCoin
gold=com.yancy.GoldCoin
```

##### 改进工厂类

```java
/**
 * CoinExtendFactory
 * 通过配置文件接触耦合
 * @author yancy0109
 */
public class CoinExtendFactory {

    // 存储对应Coin对象的容器
    private static HashMap<String, Coin> coinContainer = new HashMap<>();

    // 读取Properties，对coinContainer进行初始化
    static {
        Properties properties = new Properties();
        InputStream is = null;
        try {
            // 读取对应配置文件流
            is = CoinExtendFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            properties.load(is);
            Set<Object> classNames = properties.keySet();
            for (Object className : classNames) {
                String clasPath = (String) properties.get(className);
                // 获取配置文件中对应类 Class对象
                Class<?> aClass = Class.forName(clasPath);
                Coin newInstance = (Coin) aClass.newInstance();
                // 在容器中存入对应实例
                coinContainer.put((String) className, newInstance);
            }
        } catch (IOException e) {
            throw new RuntimeException("读取配置文件异常");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("不存在对应类");
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    throw new RuntimeException("关闭输入流异常");
                }
            }
        }
    }

    /**
     * 静态工厂方法，根据coinName返回对应的Coin实现类
     */
    public static Coin getCoin(String coinName) {
        return coinContainer.get(coinName);
    }
}
```

##### 测试

```java
// 通过扩展工厂（配置文件）获取对应实例
Coin copper1 = CoinExtendFactory.getCoin("copper");
System.out.println(copper1.getDescription());  // 这是一枚铜币
Coin gold1 = CoinExtendFactory.getCoin("gold");
System.out.println(gold1.getDescription());  // 这是一枚金币
```





