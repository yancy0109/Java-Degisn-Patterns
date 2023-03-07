package com.yancy;

/**
 * 具体命令对象
 * 变大咒
 * @author yancy0109
 */
public class MagnificationSpell implements Command {

    private String name = "变大咒";
    private Target target;

    private Size oldStatus;


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getTargetName() {
        return target.getName();
    }

    @Override
    public void execute(Target target) {
        this.target = target;
        oldStatus = target.getSize();
        target.setSize(Size.BIG);
    }

    @Override
    public void undo() {
        if (oldStatus != null && target != null) {
            target.setSize(oldStatus);
        }
    }

    @Override
    public void redo() {
        if (target != null) {
            target.setSize(Size.BIG);
        }
    }
}
