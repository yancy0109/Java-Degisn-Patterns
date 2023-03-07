package com.yancy;

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
