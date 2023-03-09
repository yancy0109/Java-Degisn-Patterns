package com.yancy;

/**
 * 具体状态
 * 和平状态
 * @author yancy0109
 */
public class PeacefulState implements State {

    private final Mammoth mammoth;

    public PeacefulState(Mammoth mammoth) {
        this.mammoth = mammoth;
    }

    @Override
    public void onEnterState() {
        System.out.println(this.mammoth + "平静了下来");
    }

    @Override
    public void observe() {
        System.out.println(this.mammoth + "非常平静与和平");
    }
}
