package com.yancy;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 请求者对象
 * 巫师
 * @author yancy0109
 */
public class Wizard {

    private String name;

    // 记录命令请求
    private final Deque<Command> undoStack = new LinkedList<>();
    private final Deque<Command> redoStack = new LinkedList<>();

    public Wizard(String name) {
        this.name = name;
    }

    public void castSpell(Command command, Target target) {
        System.out.println(name + "使用了" + command.getName() + ", 作用在了" + target.getName());
        command.execute(target);
        undoStack.push(command);
        redoStack.push(command);
    }

    public void undoLastSpell() {
        if (!undoStack.isEmpty()) {
            Command previousSpell = undoStack.pollLast();
            previousSpell.undo();
            System.out.println(name + "撤销了使用" + previousSpell.getName() + ", 作用在了" + previousSpell.getTargetName());
        }
    }

    public void redoLastSpell() {
        if (!redoStack.isEmpty()) {
            Command previousSpell = redoStack.pollLast();
            previousSpell.redo();
            System.out.println(name + "重写使用了" + previousSpell.getName() + ", 作用在了" + previousSpell.getTargetName());
        }
    }
}
