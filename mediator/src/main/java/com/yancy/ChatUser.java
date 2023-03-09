package com.yancy;

import java.util.Date;

/**
 * 具体同事类
 * 聊天室用户
 * @author yancy0109
 */
public class ChatUser extends User {

    private String name;

    public ChatUser(Chat chat, String username) {
        super(chat);
        this.name = username;
        // 注册到中介者
        chat.loginUser(this);
    }

    @Override
    public void sendMeg(String msg) {
        String sendMsg = name + ":" + msg;
        System.out.println(name + "send : " + msg);
        this.chat.sendMsg(this, sendMsg);
    }

    @Override
    public void receiveMeg(String msg) {
        System.out.println("[" + new Date() + "]" + "[" + this.name +  "]" + ": 收到消息" + msg);
    }
}
