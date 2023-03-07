package com.yancy;

/**
 * 具体命令对象
 * 隐身咒
 * @author yancy0109
 */
public class InvisibilitySpell implements Command {

    private String name = "隐身咒";

    private Target target;
    private Visibility oldStatus;

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
        oldStatus = target.getVisibility();
        target.setVisibility(Visibility.Invisibility);
    }

    @Override
    public void undo() {
        if (oldStatus != null && target != null) {
            target.setVisibility(oldStatus);
        }
    }

    @Override
    public void redo() {
        if (target != null) {
            target.setVisibility(Visibility.Invisibility);
        }
    }
}
