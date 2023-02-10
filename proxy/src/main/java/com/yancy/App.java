package com.yancy;

/**
 * 测试类
 * @author yancy0109
 */
public class App {
    public static void main(String[] args) {
        // 静态代理
        WizardTowerProxy wizardTowerProxy = new WizardTowerProxy(new IvoryTower());
        wizardTowerProxy.enter(new Wizard("奇异博士"));  // 奇异博士进入了巫师塔
        wizardTowerProxy.enter(new Wizard("甘道夫"));  // 甘道夫进入了巫师塔
        wizardTowerProxy.enter(new Wizard("邓布利多"));  // 邓布利多进入了巫师塔
        wizardTowerProxy.enter(new Wizard("哈利波特"));  // 巫师塔人数已满，不允许进入

        // 动态代理

        // JDK代理
        JdkProxyFactory jdkProxyFactory = new JdkProxyFactory(new IvoryTower());
        WizardTower wizardTowerJDKProxy = jdkProxyFactory.getWizardTowerProxy();
        wizardTowerJDKProxy.enter(new Wizard("奇异博士"));  // 奇异博士进入了巫师塔
        wizardTowerJDKProxy.enter(new Wizard("甘道夫"));  // 甘道夫进入了巫师塔
        wizardTowerJDKProxy.enter(new Wizard("邓布利多"));  // 邓布利多进入了巫师塔
        wizardTowerJDKProxy.enter(new Wizard("哈利波特"));  // 巫师塔人数已满，不允许进入
        wizardTowerJDKProxy.enter(new Wizard("赫敏"));  // 巫师塔人数已满，不允许进入

        // CGlib代理
        CglibProxyFactory cglibProxyFactory = new CglibProxyFactory();
        IvoryTower wizardTowerCglibProxy = cglibProxyFactory.getWizardTowerProxy();
        wizardTowerCglibProxy.enter(new Wizard("奇异博士"));  // 奇异博士进入了巫师塔
        wizardTowerCglibProxy.enter(new Wizard("甘道夫"));  // 甘道夫进入了巫师塔
        wizardTowerCglibProxy.enter(new Wizard("邓布利多"));  // 邓布利多进入了巫师塔
        wizardTowerCglibProxy.enter(new Wizard("哈利波特"));  // 巫师塔人数已满，不允许进入
        wizardTowerCglibProxy.enter(new Wizard("赫敏"));  // 巫师塔人数已满，不允许进入=
    }
}
