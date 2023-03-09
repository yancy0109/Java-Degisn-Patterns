package com.yancy;

/**
 * 具体中介者
 * @author yancy0109
 */
public class ChatRoom extends Chat {

    @Override
    public void sendMsg(User user, String msg) {
        for (User userItem : this.userList) {
            if (userItem != user) {
                userItem.receiveMeg(msg);
            }
        }
    }
}
