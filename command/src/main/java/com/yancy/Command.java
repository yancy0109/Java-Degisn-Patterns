package com.yancy;

/**
 * 抽象命令类
 * @author yancy0109
 */
public interface Command {

    String getName();
    String getTargetName();

    /**
     * 在执行使，也将当前命令对象接收者进行了保存
     */
    void execute(Target target);

    void undo();

    void redo();

}
