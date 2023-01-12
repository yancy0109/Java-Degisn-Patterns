package com.yancy;

/**
 * 测试类
 * @author yancy0109
 */
public class App {
    public static void main(String[] args) {

        // 对复杂对象各部分进行创建的具体建造这类
        ConcreteBuilder concreteBuilder = new ConcreteBuilder();
        // 控制创建顺序及流程的指挥者类
        Director director = new Director(concreteBuilder);
        // 建造
        Bike bike = director.construct();
        System.out.println(bike);  // Bike(frame=frame, seat=seat)

        // Person类对象的构建过程
        Person person = new Person.Buidler()
                .gender("男")
                .name("哈哈")
                .hair("长发")
                .sage(18)
                .build();
        System.out.println(person);  // Person(gender=男, name=哈哈, age=18, hair=长发)
    }
}
