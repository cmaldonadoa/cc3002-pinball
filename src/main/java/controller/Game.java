package controller;

import logic.bonus.Bonus;
import logic.bonus.DropTargetBonus;
import logic.bonus.ExtraBallBonus;
import logic.bonus.JackPotBonus;
import logic.table.GameTable;
import logic.table.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Game logic controller class.
 *
 * @author Juan-Pablo Silva
 */
public class Game implements Observer{
    private List<Bonus> bonuses = new ArrayList<>();
    private Table table;
    private int balls;
    private int score;

    public Game() {
        bonuses.add(new DropTargetBonus());
        bonuses.add(new ExtraBallBonus());
        bonuses.add(new JackPotBonus());
        this.balls = 3;
        this.score = 0;
        this.table = new GameTable("",0, new ArrayList<>(), new ArrayList<>());
    }

    public int getScore() {
        return score;
    }

    public int getBalls() {
        return this.balls;
    }

    public Table getTable() {
        return table;
    }

    public Bonus getDropTargetBonus() {
        return bonuses.get(0);
    }

    public Bonus getExtraBallBonus() {
        return bonuses.get(1);
    }

    public Bonus getJackPotBonus() {
        return bonuses.get(2);
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public void setBalls(int balls) {
        this.balls = balls;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg.equals("triggerExtraBallBonus")) {
            this.getExtraBallBonus().trigger(this);
        }

        if (arg.equals("triggerJackPotBonus")) {
            this.getJackPotBonus().trigger(this);
        }
    }
}
