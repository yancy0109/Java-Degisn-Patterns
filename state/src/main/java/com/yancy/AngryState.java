package com.yancy;

/**
 * 具体状态
 * 愤怒状态
 * @author yancy0109
*/
public class AngryState implements State {

    private final Mammoth mammoth;

    public AngryState(Mammoth mammoth) {
        this.mammoth = mammoth;
    }

    @Override
    public void onEnterState() {
        System.out.println(this.mammoth + "愤怒了起来");
    }

    @Override
    public void observe() {
        System.out.println(this.mammoth + "正在暴怒");
    }
}

