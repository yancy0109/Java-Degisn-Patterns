## 代理模式

### 目的

为其他对象提供一种代理以控制这个对象的访问。

此时，访问对象不适合或者不能直接引用目的对象，代理对象作为访问对象和目的对象之间的中介。

代理方式：

- 静态代理：编译器生成
- 动态代理：运行时间动态生成
  - JDK代理
  - CGLib代理
  - More？

### 结构

* 抽象主题（Subject）类： 通过接口或抽象类声明真实主题和代理对象实现的业务方法。
* 真实主题（Real Subject）类： 实现了抽象主题中的具体业务，是代理对象所代表的真实对象，是最终要引用的对象。
* 代理（Proxy）类 ： 提供了与真实主题相同的接口，其内部含有对真实主题的引用，它可以访问、控制或扩展真实主题的功能。

### 静态代理

Eg. 假如存在一座巫师塔，巫师们会进入巫师塔学习，但是巫师塔最多只能容纳三名巫师，我们可以通过代理类进行控制。

![image-20230113224859174](https://raw.githubusercontent.com/yancy0109/image/main/11721/image-20230113224859174.png)

#### 抽象主题

```java
/**
 * 抽象主题
 * 象牙塔
 * @author yancy0109
 */
public interface WizardTower {
    void enter(Wizard wizard);
}
```

#### 真实主题

```java
/**
 * 真实主题
 * 巫师塔
 * @author yancy0109
 */
public class IvoryTower implements WizardTower {

    @Override
    public void enter(Wizard wizard) {
        System.out.println(wizard + "进入了巫师塔");
    }
}
```

#### 代理类

```java
/**
 * 代理类
 * 巫师塔代理类
 * @author yancy0109
 */
public class WizadTowerProxy implements WizardTower{

    // 允许进入最大人数
    private static final int MAX_WIZARDS_ALLOWED = 3;

    // 当前人数
    private int numWizards;

    private final WizardTower tower;

    public WizadTowerProxy(WizardTower tower) {
        this.tower = tower;
    }

    @Override
    public void enter(Wizard wizard) {
        if (numWizards < MAX_WIZARDS_ALLOWED) {
            tower.enter(wizard);
            numWizards++;
        } else {
            System.out.println("巫师塔人数已满，不允许进入");
        }
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
        WizadTowerProxy wizadTowerProxy = new WizadTowerProxy(new IvoryTower());
        wizadTowerProxy.enter(new Wizard("奇异博士"));  // 奇异博士进入了巫师塔
        wizadTowerProxy.enter(new Wizard("甘道夫"));  // 甘道夫进入了巫师塔
        wizadTowerProxy.enter(new Wizard("邓布利多"));  // 邓布利多进入了巫师塔
        wizadTowerProxy.enter(new Wizard("哈利波特"));  // 巫师塔人数已满，不允许进入
    }
}
```

### 动态代理

#### JDK动态代理

Java中提供了一个动态代理类Proxy，Proxy并不是我们上述所说的代理对象的类，而是提供了一个创建代理对象的静态方法（newProxyInstance方法）来获取代理对象。

##### 代理工厂

```java
/**
 * 动态代理：JDK动态代理
 * 通过代理工厂直接生成指定代理对象
 * @author yancy0109
 */
public class JdkProxyFactory {

    // 塔对象
    private IvoryTower ivoryTower;

    // 塔内最大人数
    // 仅方便演示
    private static final int MAX_NUM = 3;

    // 当前塔内人数
    // 其实放在这里也许不太合适，不过仅做演示不再完善
    private int num;

    public JdkProxyFactory(IvoryTower ivoryTower) {
        this.ivoryTower = ivoryTower;
    }

    WizardTower getWizardTowerProxy() {
        WizardTower proxyInstance = (WizardTower) Proxy.newProxyInstance(
                // ClassLoader
                IvoryTower.class.getClassLoader(),
                // interfaces
                IvoryTower.class.getInterfaces(),
                // Invocationhandler
                (Object proxy, Method method, Object[] args) -> {
                    if (num < MAX_NUM) {
                        Object result = method.invoke(ivoryTower, args);
                        num++;
                        return result;
                    } else {
                        System.out.println("巫师塔人数已满，不允许进入");
                        return null;
                    }
                }
        );
        return proxyInstance;
    }
}
```

##### 代理类

通过arthas反编译获得动态生成的代码

```java
public final class $Proxy0
        extends Proxy
        implements WizardTower {
    private static Method m1;
    private static Method m2;
    private static Method m3;
    private static Method m0;

    // 初始化时赋值自定义InvocationHandler
    public $Proxy0(InvocationHandler invocationHandler) {
        super(invocationHandler);
    }

    static {
        try {
            m1 = Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
            m2 = Class.forName("java.lang.Object").getMethod("toString", new Class[0]);
            m3 = Class.forName("com.yancy.WizardTower").getMethod("enter", Class.forName("com.yancy.Wizard"));
            m0 = Class.forName("java.lang.Object").getMethod("hashCode", new Class[0]);
            return;
        }
        catch (NoSuchMethodException noSuchMethodException) {
            throw new NoSuchMethodError(noSuchMethodException.getMessage());
        }
        catch (ClassNotFoundException classNotFoundException) {
            throw new NoClassDefFoundError(classNotFoundException.getMessage());
        }
    }

    public final boolean equals(Object object) {
        try {
            return (Boolean)this.h.invoke(this, m1, new Object[]{object});
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public final String toString() {
        try {
            return (String)this.h.invoke(this, m2, null);
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public final int hashCode() {
        try {
            return (Integer)this.h.invoke(this, m0, null);
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public final void enter(Wizard wizard) {
        try {
            // 执行InvocationHandler中实现方法
            this.h.invoke(this, m3, new Object[]{wizard});
            return;
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }
}
```

###### 总结

- 代理类与真实对象实现了同样的接口
- 提供了InvocationHandler对象传递给了父类。

##### 测试类

```java
/**
 * 测试类
 * @author yancy0109
 */
public class App {
    public static void main(String[] args) {
        // 动态代理
        // JDK代理
        JdkProxyFactory jdkProxyFactory = new JdkProxyFactory(new IvoryTower());
        WizardTower wizardTowerJDKProxy = jdkProxyFactory.getWizardTowerProxy();
        wizardTowerJDKProxy.enter(new Wizard("奇异博士"));  // 奇异博士进入了巫师塔
        wizardTowerJDKProxy.enter(new Wizard("甘道夫"));  // 甘道夫进入了巫师塔
        wizardTowerJDKProxy.enter(new Wizard("邓布利多"));  // 邓布利多进入了巫师塔
        wizardTowerJDKProxy.enter(new Wizard("哈利波特"));  // 巫师塔人数已满，不允许进入
        wizardTowerJDKProxy.enter(new Wizard("赫敏"));  // 巫师塔人数已满，不允许进入
    }
}
```

#### CGlib动态代理

JDK动态代理要求我们必须定义接口，对接口进行代理。

CGLIB是一个功能强大，高性能的代码生成包。它为没有实现接口的类提供代理，为JDK的动态代理提供了很好的补充。

##### 依赖

```xml
<dependency>
    <groupId>cglib</groupId>
    <artifactId>cglib</artifactId>
    <version>3.3.0</version>
</dependency>
```

##### 代理工厂

```java
/**
 * 动态代理：cglib
 * 通过代理工厂直接生成指定代理对象
 * @author yancy0109
 */
public class CglibProxyFactory {

  // 塔内最大人数
  // 仅方便演示
  private static final int MAX_NUM = 3;

  // 当前塔内人数
  // 其实放在这里也许不太合适，不过仅做演示不再完善
  private int num;

  IvoryTower getWizardTowerProxy() {
    Enhancer enhancer = new Enhancer();

    enhancer.setSuperclass(IvoryTower.class);

    enhancer.setCallback(new MethodInterceptor() {
      @Override
      public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if (num < MAX_NUM) {
          Object result = methodProxy.invokeSuper(o, objects);
          num++;
          return result;
        } else {
          System.out.println("巫师塔人数已满，不允许进入");
          return null;
        }
      }
    });

    IvoryTower ivoryTower = (IvoryTower) enhancer.create();
    return ivoryTower;
  }
}
```

##### 代理类

通过arthas反编译获得动态生成的代码

```java
public class IvoryTower$$EnhancerByCGLIB$$55243c99
extends IvoryTower  // 通过继承真实类的方法进行代理
implements Factory {
    private boolean CGLIB$BOUND;
    public static Object CGLIB$FACTORY_DATA;
    private static final ThreadLocal CGLIB$THREAD_CALLBACKS;
    private static final Callback[] CGLIB$STATIC_CALLBACKS;
    private MethodInterceptor CGLIB$CALLBACK_0;
    private static Object CGLIB$CALLBACK_FILTER;
    private static final Method CGLIB$enter$0$Method;
    private static final MethodProxy CGLIB$enter$0$Proxy;
    private static final Object[] CGLIB$emptyArgs;
    private static final Method CGLIB$equals$1$Method;
    private static final MethodProxy CGLIB$equals$1$Proxy;
    private static final Method CGLIB$toString$2$Method;
    private static final MethodProxy CGLIB$toString$2$Proxy;
    private static final Method CGLIB$hashCode$3$Method;
    private static final MethodProxy CGLIB$hashCode$3$Proxy;
    private static final Method CGLIB$clone$4$Method;
    private static final MethodProxy CGLIB$clone$4$Proxy;

    public IvoryTower$$EnhancerByCGLIB$$55243c99() {
        IvoryTower$$EnhancerByCGLIB$$55243c99 ivoryTower$$EnhancerByCGLIB$$55243c99 = this;
        IvoryTower$$EnhancerByCGLIB$$55243c99.CGLIB$BIND_CALLBACKS(ivoryTower$$EnhancerByCGLIB$$55243c99);
    }

    static {
        IvoryTower$$EnhancerByCGLIB$$55243c99.CGLIB$STATICHOOK1();
    }

    public final boolean equals(Object object) {
        MethodInterceptor methodInterceptor = this.CGLIB$CALLBACK_0;
        if (methodInterceptor == null) {
            IvoryTower$$EnhancerByCGLIB$$55243c99.CGLIB$BIND_CALLBACKS(this);
            methodInterceptor = this.CGLIB$CALLBACK_0;
        }
        if (methodInterceptor != null) {
            Object object2 = methodInterceptor.intercept(this, CGLIB$equals$1$Method, new Object[]{object}, CGLIB$equals$1$Proxy);
            return object2 == null ? false : (Boolean)object2;
        }
        return super.equals(object);
    }

    public final String toString() {
        MethodInterceptor methodInterceptor = this.CGLIB$CALLBACK_0;
        if (methodInterceptor == null) {
            IvoryTower$$EnhancerByCGLIB$$55243c99.CGLIB$BIND_CALLBACKS(this);
            methodInterceptor = this.CGLIB$CALLBACK_0;
        }
        if (methodInterceptor != null) {
            return (String)methodInterceptor.intercept(this, CGLIB$toString$2$Method, CGLIB$emptyArgs, CGLIB$toString$2$Proxy);
        }
        return super.toString();
    }

    public final int hashCode() {
        MethodInterceptor methodInterceptor = this.CGLIB$CALLBACK_0;
        if (methodInterceptor == null) {
            IvoryTower$$EnhancerByCGLIB$$55243c99.CGLIB$BIND_CALLBACKS(this);
            methodInterceptor = this.CGLIB$CALLBACK_0;
        }
        if (methodInterceptor != null) {
            Object object = methodInterceptor.intercept(this, CGLIB$hashCode$3$Method, CGLIB$emptyArgs, CGLIB$hashCode$3$Proxy);
            return object == null ? 0 : ((Number)object).intValue();
        }
        return super.hashCode();
    }

    protected final Object clone() throws CloneNotSupportedException {
        MethodInterceptor methodInterceptor = this.CGLIB$CALLBACK_0;
        if (methodInterceptor == null) {
            IvoryTower$$EnhancerByCGLIB$$55243c99.CGLIB$BIND_CALLBACKS(this);
            methodInterceptor = this.CGLIB$CALLBACK_0;
        }
        if (methodInterceptor != null) {
            return methodInterceptor.intercept(this, CGLIB$clone$4$Method, CGLIB$emptyArgs, CGLIB$clone$4$Proxy);
        }
        return super.clone();
    }

    @Override
    public Object newInstance(Callback[] callbackArray) {
        IvoryTower$$EnhancerByCGLIB$$55243c99.CGLIB$SET_THREAD_CALLBACKS(callbackArray);
        IvoryTower$$EnhancerByCGLIB$$55243c99 ivoryTower$$EnhancerByCGLIB$$55243c99 = new IvoryTower$$EnhancerByCGLIB$$55243c99();
        IvoryTower$$EnhancerByCGLIB$$55243c99.CGLIB$SET_THREAD_CALLBACKS(null);
        return ivoryTower$$EnhancerByCGLIB$$55243c99;
    }

    @Override
    public Object newInstance(Callback callback) {
        IvoryTower$$EnhancerByCGLIB$$55243c99.CGLIB$SET_THREAD_CALLBACKS(new Callback[]{callback});
        IvoryTower$$EnhancerByCGLIB$$55243c99 ivoryTower$$EnhancerByCGLIB$$55243c99 = new IvoryTower$$EnhancerByCGLIB$$55243c99();
        IvoryTower$$EnhancerByCGLIB$$55243c99.CGLIB$SET_THREAD_CALLBACKS(null);
        return ivoryTower$$EnhancerByCGLIB$$55243c99;
    }

    @Override
    public Object newInstance(Class[] classArray, Object[] objectArray, Callback[] callbackArray) {
        IvoryTower$$EnhancerByCGLIB$$55243c99 ivoryTower$$EnhancerByCGLIB$$55243c99;
        IvoryTower$$EnhancerByCGLIB$$55243c99.CGLIB$SET_THREAD_CALLBACKS(callbackArray);
        Class[] classArray2 = classArray;
        switch (classArray.length) {
            case 0: {
                ivoryTower$$EnhancerByCGLIB$$55243c99 = new IvoryTower$$EnhancerByCGLIB$$55243c99();
                break;
            }
            default: {
                throw new IllegalArgumentException("Constructor not found");
            }
        }
        IvoryTower$$EnhancerByCGLIB$$55243c99.CGLIB$SET_THREAD_CALLBACKS(null);
        return ivoryTower$$EnhancerByCGLIB$$55243c99;
    }

    public final void enter(Wizard wizard) {
        // 通过MethodInterceptor调用增强方法
        MethodInterceptor methodInterceptor = this.CGLIB$CALLBACK_0;
        if (methodInterceptor == null) {
            IvoryTower$$EnhancerByCGLIB$$55243c99.CGLIB$BIND_CALLBACKS(this);
            methodInterceptor = this.CGLIB$CALLBACK_0;
        }
        if (methodInterceptor != null) {
            Object object = methodInterceptor.intercept(this, CGLIB$enter$0$Method, new Object[]{wizard}, CGLIB$enter$0$Proxy);
            return;
        }
        super.enter(wizard);
    }

    @Override
    public void setCallbacks(Callback[] callbackArray) {
        Callback[] callbackArray2 = callbackArray;
        IvoryTower$$EnhancerByCGLIB$$55243c99 ivoryTower$$EnhancerByCGLIB$$55243c99 = this;
        this.CGLIB$CALLBACK_0 = (MethodInterceptor)callbackArray[0];
    }

    /**
     * 设置回调函数
     */
    @Override
    public void setCallback(int n, Callback callback) {
        switch (n) {
            case 0: {
                this.CGLIB$CALLBACK_0 = (MethodInterceptor)callback;
                break;
            }
        }
    }

    public static void CGLIB$SET_THREAD_CALLBACKS(Callback[] callbackArray) {
        CGLIB$THREAD_CALLBACKS.set(callbackArray);
    }

    public static void CGLIB$SET_STATIC_CALLBACKS(Callback[] callbackArray) {
        CGLIB$STATIC_CALLBACKS = callbackArray;
    }

    @Override
    public Callback[] getCallbacks() {
        IvoryTower$$EnhancerByCGLIB$$55243c99.CGLIB$BIND_CALLBACKS(this);
        IvoryTower$$EnhancerByCGLIB$$55243c99 ivoryTower$$EnhancerByCGLIB$$55243c99 = this;
        return new Callback[]{this.CGLIB$CALLBACK_0};
    }

    @Override
    public Callback getCallback(int n) {
        MethodInterceptor methodInterceptor;
        IvoryTower$$EnhancerByCGLIB$$55243c99.CGLIB$BIND_CALLBACKS(this);
        switch (n) {
            case 0: {
                methodInterceptor = this.CGLIB$CALLBACK_0;
                break;
            }
            default: {
                methodInterceptor = null;
            }
        }
        return methodInterceptor;
    }

    public static MethodProxy CGLIB$findMethodProxy(Signature signature) {
        String string = ((Object)signature).toString();
        switch (string.hashCode()) {
            case -987288480: {
                if (!string.equals("enter(Lcom/yancy/Wizard;)V")) break;
                return CGLIB$enter$0$Proxy;
            }
            case -508378822: {
                if (!string.equals("clone()Ljava/lang/Object;")) break;
                return CGLIB$clone$4$Proxy;
            }
            case 1826985398: {
                if (!string.equals("equals(Ljava/lang/Object;)Z")) break;
                return CGLIB$equals$1$Proxy;
            }
            case 1913648695: {
                if (!string.equals("toString()Ljava/lang/String;")) break;
                return CGLIB$toString$2$Proxy;
            }
            case 1984935277: {
                if (!string.equals("hashCode()I")) break;
                return CGLIB$hashCode$3$Proxy;
            }
        }
        return null;
    }

    static void CGLIB$STATICHOOK1() {
        CGLIB$THREAD_CALLBACKS = new ThreadLocal();
        CGLIB$emptyArgs = new Object[0];
        Class<?> clazz = Class.forName("com.yancy.IvoryTower$$EnhancerByCGLIB$$55243c99");
        Class<?> clazz2 = Class.forName("java.lang.Object");
        Method[] methodArray = ReflectUtils.findMethods(new String[]{"equals", "(Ljava/lang/Object;)Z", "toString", "()Ljava/lang/String;", "hashCode", "()I", "clone", "()Ljava/lang/Object;"}, clazz2.getDeclaredMethods());
        CGLIB$equals$1$Method = methodArray[0];
        CGLIB$equals$1$Proxy = MethodProxy.create(clazz2, clazz, "(Ljava/lang/Object;)Z", "equals", "CGLIB$equals$1");
        CGLIB$toString$2$Method = methodArray[1];
        CGLIB$toString$2$Proxy = MethodProxy.create(clazz2, clazz, "()Ljava/lang/String;", "toString", "CGLIB$toString$2");
        CGLIB$hashCode$3$Method = methodArray[2];
        CGLIB$hashCode$3$Proxy = MethodProxy.create(clazz2, clazz, "()I", "hashCode", "CGLIB$hashCode$3");
        CGLIB$clone$4$Method = methodArray[3];
        CGLIB$clone$4$Proxy = MethodProxy.create(clazz2, clazz, "()Ljava/lang/Object;", "clone", "CGLIB$clone$4");
        clazz2 = Class.forName("com.yancy.IvoryTower");
        CGLIB$enter$0$Method = ReflectUtils.findMethods(new String[]{"enter", "(Lcom/yancy/Wizard;)V"}, clazz2.getDeclaredMethods())[0];
        CGLIB$enter$0$Proxy = MethodProxy.create(clazz2, clazz, "(Lcom/yancy/Wizard;)V", "enter", "CGLIB$enter$0");
    }

    private static final void CGLIB$BIND_CALLBACKS(Object object) {
        block2: {
            Object object2;
            block3: {
                IvoryTower$$EnhancerByCGLIB$$55243c99 ivoryTower$$EnhancerByCGLIB$$55243c99 = (IvoryTower$$EnhancerByCGLIB$$55243c99)object;
                if (ivoryTower$$EnhancerByCGLIB$$55243c99.CGLIB$BOUND) break block2;
                ivoryTower$$EnhancerByCGLIB$$55243c99.CGLIB$BOUND = true;
                object2 = CGLIB$THREAD_CALLBACKS.get();
                if (object2 != null) break block3;
                object2 = CGLIB$STATIC_CALLBACKS;
                if (CGLIB$STATIC_CALLBACKS == null) break block2;
            }
            ivoryTower$$EnhancerByCGLIB$$55243c99.CGLIB$CALLBACK_0 = (MethodInterceptor)((Callback[])object2)[0];
        }
    }

    final String CGLIB$toString$2() {
        return super.toString();
    }

    final void CGLIB$enter$0(Wizard wizard) {
        super.enter(wizard);
    }

    final boolean CGLIB$equals$1(Object object) {
        return super.equals(object);
    }

    final Object CGLIB$clone$4() throws CloneNotSupportedException {
        return super.clone();
    }

    final int CGLIB$hashCode$3() {
        return super.hashCode();
    }
}
```

###### 总结

代理类并不是通过接口代理，而是通过继承真实类，对方法进行重写，通过通过MethodInterceptor调用原方法以及代理类增加的逻辑。

##### 测试类

```java
/**
 * 测试类
 * @author yancy0109
 */
public class App {
    public static void main(String[] args) {
        // 动态代理
        // CGlib代理
        CglibProxyFactory cglibProxyFactory = new CglibProxyFactory();
        IvoryTower wizardTowerCglibProxy = cglibProxyFactory.getWizardTowerProxy();
        wizardTowerCglibProxy.enter(new Wizard("奇异博士"));  // 奇异博士进入了巫师塔
        wizardTowerCglibProxy.enter(new Wizard("甘道夫"));  // 甘道夫进入了巫师塔
        wizardTowerCglibProxy.enter(new Wizard("邓布利多"));  // 邓布利多进入了巫师塔
        wizardTowerCglibProxy.enter(new Wizard("哈利波特"));  // 巫师塔人数已满，不允许进入
        wizardTowerCglibProxy.enter(new Wizard("赫敏"));  // 巫师塔人数已满，不允许进入
    }
}
```

### 三种代理的对比

* jdk代理和CGLIB代理

  使用CGLib实现动态代理，CGLib底层采用ASM字节码生成框架，使用字节码技术生成代理类，在JDK1.6之前比使用Java反射效率要高。唯一需要注意的是，CGLib不能对声明为final的类或者方法进行代理，因为CGLib原理是动态生成被代理类的子类。

  在JDK1.6、JDK1.7、JDK1.8逐步对JDK动态代理优化之后，在调用次数较少的情况下，JDK代理效率高于CGLib代理效率，只有当进行大量调用的时候，JDK1.6和JDK1.7比CGLib代理效率低一点，但是到JDK1.8的时候，JDK代理效率高于CGLib代理。所以如果有接口使用JDK动态代理，如果没有接口使用CGLIB代理。

* 动态代理和静态代理

  动态代理与静态代理相比较，最大的好处是接口中声明的所有方法都被转移到调用处理器一个集中的方法中处理（InvocationHandler.invoke）。这样，在接口方法数量比较多的时候，我们可以进行灵活处理，而不需要像静态代理那样每一个方法进行中转。

  如果接口增加一个方法，静态代理模式除了所有实现类需要实现这个方法外，所有代理类也需要实现此方法。增加了代码维护的复杂度。而动态代理不会出现该问题

### 优缺点

**优点：**

- 代理模式在客户端与目标对象之间起到一个中介作用和保护目标对象的作用
- 代理对象可以扩展目标对象的功能
- 代理模式能将客户端与目标对象分离，在一定程度上降低了系统的耦合度

**缺点：**

* 增加了系统的复杂度

### 使用场景 

* 远程（Remote）代理

  本地服务通过网络请求远程服务。为了实现本地到远程的通信，我们需要实现网络通信，处理其中可能的异常。为良好的代码设计和可维护性，我们将网络通信部分隐藏起来，只暴露给本地服务一个接口，通过该接口即可访问远程服务提供的功能，而不必过多关心通信部分的细节。

* 虚拟代理

* 防火墙（Firewall）代理

  当你将浏览器配置成使用代理功能时，防火墙就将你的浏览器的请求转给互联网；当互联网返回响应时，代理服务器再把它转给你的浏览器。

* 保护（Protect or Access）代理

  控制对一个对象的访问，如果需要，可以给不同的用户提供不同级别的使用权限。

- Copy-on-Write代理
- 同步化代理
- 智能引用代理

- Cache代理
