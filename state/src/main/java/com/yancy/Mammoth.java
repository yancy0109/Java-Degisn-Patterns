package com.yancy;

/**
 * 环境类
 * 猛犸象
 * @author yancy0109
 */
public class Mammoth {

    private State state;

    public Mammoth() {
        this.state = new PeacefulState(this);
    }

    public void timePasses() {
        if (state instanceof PeacefulState) {
            changeStateTo(new AngryState(this));
            return;
        }
        changeStateTo(new PeacefulState(this));
    }

    private void changeStateTo(State newState) {
        this.state = newState;
        this.state.onEnterState();
    }

    public void observe() {
        this.state.observe();
    }

    public String toString() {
        return "猛犸象";
    }
}
