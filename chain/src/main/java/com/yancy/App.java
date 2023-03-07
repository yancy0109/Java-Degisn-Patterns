package com.yancy;

/**
 * 测试类
 * @author yancy0109
 */
public class App {
    public static void main(String[] args) {
        // 客户端类
        LeavingSystem leavingSystem = new LeavingSystem();
        LeaveRequest request1 = new LeaveRequest(1);
        LeaveRequest request2 = new LeaveRequest(2);
        LeaveRequest request3 = new LeaveRequest(5);
        leavingSystem.makeRequest(request1);    // 小组长已批准1天的假期
        leavingSystem.makeRequest(request2);    // 部门经理已批准2天的假期
        leavingSystem.makeRequest(request3);    // 总经理已批准5天的假期
    }
}
