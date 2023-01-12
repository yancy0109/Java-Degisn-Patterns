package com.yancy;

import lombok.ToString;

/**
 * 实例对象：Person
 * @author yancy0109
 */
@ToString
public class Person {

    private String gender;
    private String name;
    private int age;
    private String hair;

    // 私有构造方法
    private Person(Buidler buidler) {
        this.gender = buidler.gender;
        this.name = buidler.name;
        this.age = buidler.age;
        this.hair = buidler.hair;
    }

    // Person类的创建者
    public static class Buidler {
        private String gender;
        private String name;
        private int age;
        private String hair;

        public Buidler gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Buidler name(String name) {
            this.name = name;
            return this;
        }

        public Buidler sage(int age) {
            this.age = age;
            return this;
        }

        public Buidler hair(String hair) {
            this.hair = hair;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}
