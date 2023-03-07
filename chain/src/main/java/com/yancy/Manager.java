package com.yancy;

/**
 * 具体处理者
 * 小组长
 * @author yancy0109
 */
public class Manager extends Handler {

    public Manager(Handler nextHandler, int maxLevel, int minLevel) {
        super(nextHandler, maxLevel, minLevel);
    }

    @Override
    void handler(LeaveRequest request) {
        System.out.println("部门经理已批准" + request.getDays() + "天的假期");
    }
}
