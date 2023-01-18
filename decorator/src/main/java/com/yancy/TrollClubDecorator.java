package com.yancy;

/**
 * 具体装饰类
 * 巨魔 武装Club 装饰
 * @author yancy0109
 */
public class TrollClubDecorator extends TrollDecorator {

    public TrollClubDecorator(Troll troll) {
        super(troll);
    }

    @Override
    public void attack() {
        this.getTroll().attack();
        System.out.println("巨魔挥舞木棍");
    }

    @Override
    public int getAttackPower() {
        return getTroll().getAttackPower() + 10;
    }

    @Override
    public void fleeBattle() {
        this.getTroll().attack();
        System.out.println("巨魔丢掉了木棍");
    }
}
