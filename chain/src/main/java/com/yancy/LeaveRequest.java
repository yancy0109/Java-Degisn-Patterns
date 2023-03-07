package com.yancy;

/**
 * 请求实体类
 * @author yancy0109
 */
public class LeaveRequest {

    private int days;   // 请假时间

    public LeaveRequest(int days) {
        this.days = days;
    }

    public int getDays() {
        return days;
    }
}
