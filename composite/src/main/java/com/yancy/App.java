package com.yancy;

/**
 * 测试类
 * @author yancy0109
 */
public class App {
    public static void main(String[] args) {
        MenuComponent menu = new Menu("系统菜单", 1);
        Menu component = new Menu("人员管理", 2);
        component.add(new MenuItem("添加人员", 3));
        component.add(new MenuItem("删除人员", 3));
        menu.add(component);
        menu.add(new MenuItem("开启系统", 2));
        menu.add(new MenuItem("关闭系统", 2));

        menu.print();
//>>>>>>>>>>>>>>>>>> 输出：
//        系统菜单
//         人员管理
//          添加人员
//          删除人员
//         开启系统
//         关闭系统
    }
}
