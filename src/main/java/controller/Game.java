package controller;

import logic.bonus.Bonus;
import logic.bonus.DropTargetBonus;
import logic.bonus.ExtraBallBonus;
import logic.bonus.JackPotBonus;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.gameelements.target.Target;
import logic.table.GameTable;
import logic.table.Table;

import java.util.*;

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

    public Table createTable(String name, int numberOfBumpers, double prob, int numberOfSpotTargets, int numberOfDropTargets) {
        List<Bumper> bumpers = new ArrayList<>();
        List<Target> targets = new ArrayList<>();

        while (numberOfBumpers > 0) {
            double chance = new Random().nextDouble();
            if (chance <= prob) { bumpers.add(new PopBumper()); }
            else { bumpers.add(new KickerBumper());}
            numberOfBumpers--;
        }
        int dropTargets = numberOfDropTargets;

        while (numberOfSpotTargets > 0) {
            targets.add(new SpotTarget());
            numberOfSpotTargets--;
        }

        while (numberOfDropTargets > 0) {
            targets.add(new DropTarget());
            numberOfDropTargets--;
        }

        return new GameTable(name, dropTargets, bumpers, targets);
    }

    public int dropBall() {
        this.balls -= 1;
        return this.balls;
    }

    public boolean gameOver() {
        return this.balls == 0;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg.equals("triggerExtraBallBonus")) {
            this.getExtraBallBonus().trigger(this);
        }

        if (arg.equals("triggerJackPotBonus")) {
            this.getJackPotBonus().trigger(this);
        }

        if (arg.equals("triggerDropTargetBonus")) {
            this.getDropTargetBonus().trigger(this);
        }

        if (arg.equals("droppedDropTarget")) {
            this.score += 100;
        }
    }
}
