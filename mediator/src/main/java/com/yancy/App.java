package com.yancy;

/**
 * 测试类
 * @author yancy0109
 */
public class App {
    public static void main(String[] args) {
        // 中介者
        Chat chatRoom = new ChatRoom();
        // 同事类
        ChatUser user1 = new ChatUser(chatRoom, "小明");
        ChatUser user2 = new ChatUser(chatRoom, "小李");

        user1.sendMeg("哈哈");
        user2.sendMeg("哈哈");
        // 》》》》 输出
        // 小明send : 哈哈
        // [Thu Mar 09 18:30:30 GMT+08:00 2023][小李]: 收到消息小明:哈哈
        // 小李send : 哈哈
        // [Thu Mar 09 18:30:30 GMT+08:00 2023][小明]: 收到消息小李:哈哈
    }
}
