package com.yancy;

/**
 * 具体处理者
 * 小组长
 * @author yancy0109
 */
public class GeneralManager extends Handler {

    public GeneralManager(Handler nextHandler, int maxLevel, int minLevel) {
        super(nextHandler, maxLevel, minLevel);
    }

    @Override
    void handler(LeaveRequest request) {
        System.out.println("总经理已批准" + request.getDays() + "天的假期");
    }
}
