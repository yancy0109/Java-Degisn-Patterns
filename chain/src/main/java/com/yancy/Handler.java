package com.yancy;

/**
 * 抽象处理者
 * @author yancy0109
 */
public abstract class Handler {
    private Handler nextHandler;    // 下一个处理者

    private int maxLevel;   // 最大处理等级
    private int minLevel;   // 最大处理等级

    public Handler(Handler nextHandler, int maxLevel, int minLevel) {
        this.nextHandler = nextHandler;
        this.maxLevel = maxLevel;
        this.minLevel = minLevel;
    }

    public final void submit(LeaveRequest request) {
        int level = request.getDays();
        if (level < minLevel) {
            return;
        }
        if (level < maxLevel) {
            handler(request);
            return;
        }
        nextHandler.submit(request);
    }

    abstract void handler(LeaveRequest request);
}
