package com.yancy;

/**
 * 具体处理者
 * 小组长
 * @author yancy0109
 */
public class GroupLeader extends Handler {

    public GroupLeader(Handler nextHandler, int maxLevel, int minLevel) {
        super(nextHandler, maxLevel, minLevel);
    }

    @Override
    void handler(LeaveRequest request) {
        System.out.println("小组长已批准" + request.getDays() + "天的假期");
    }
}
