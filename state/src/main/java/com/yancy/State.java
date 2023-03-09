package com.yancy;

/**
 * 抽象状态
 * @author yancy0109
 */
public interface State {

    void onEnterState();

    void observe();
}
