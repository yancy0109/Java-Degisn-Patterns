package com.yancy;

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
