package com.yancy;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象中介者
 * @author yancy0109
 */
public abstract class Chat {

    protected List<User> userList = new ArrayList<>();

    public final void loginUser(User user) {
        userList.add(user);
    }

    public abstract void sendMsg(User user, String msg);
}
