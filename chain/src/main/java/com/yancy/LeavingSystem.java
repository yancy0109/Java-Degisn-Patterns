package com.yancy;

/**
 * 请假系统
 * 管理责任链
 * @author yancy0109
 */
public class LeavingSystem {

    private Handler chain;

    public LeavingSystem() {
        buildChain();
    }

    private void buildChain() {
        GeneralManager generalManager = new GeneralManager(null, 7, 3);
        Manager manager = new Manager(generalManager, 3, 2);
        GroupLeader groupLeader = new GroupLeader(manager, 2, 1);
        this.chain = groupLeader;
    }

    public void makeRequest(LeaveRequest request) {
        chain.submit(request);
    }
}
