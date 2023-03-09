package com.yancy;

/**
 * 抽象同事类
 * 用户
 * @author yancy0109
 */
public abstract class User {

    protected Chat chat;

    public User(Chat chat) {
        this.chat = chat;
    }

    public abstract void sendMeg(String msg);

    public abstract void receiveMeg(String msg);

}
