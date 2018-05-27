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
 * Game logic controller class. A game observers {@link Bumper}s, {@link Target}s and {@link GameTable}s.
 *
 * @author Juan-Pablo Silva
 */
public class Game implements Observer{
    private List<Bonus> bonuses = new ArrayList<>();
    private Table table;
    private int balls;
    private int score;

    /**
     * The constructor of a game.
     */
    public Game() {
        bonuses.add(new DropTargetBonus());
        bonuses.add(new ExtraBallBonus());
        bonuses.add(new JackPotBonus());
        this.balls = 3;
        this.score = 0;
        this.table = new GameTable("",0, new ArrayList<>(), new ArrayList<>());
    }

    /**
     * Gets the current score of the game.
     *
     * @return the current score of the game
     */
    public int getScore() {
        return score;
    }

    /**
     * Gets the current balls available in the game.
     *
     * @return the current balls available
     */
    public int getBalls() {
        return this.balls;
    }

    /**
     * Gets the current table being played.
     *
     * @return the current table being played
     * @see Table
     */
    public Table getTable() {
        return table;
    }

    /**
     * Gets the instance of {@link logic.bonus.DropTargetBonus} currently in the game.
     *
     * @return the DropTargetBonus instance
     */
    public Bonus getDropTargetBonus() {
        return bonuses.get(0);
    }

    /**
     * Gets the instance of {@link logic.bonus.ExtraBallBonus} currently in the game.
     *
     * @return the ExtraBallBonus instance
     */
    public Bonus getExtraBallBonus() {
        return bonuses.get(1);
    }

    /**
     * Gets the instance of {@link logic.bonus.JackPotBonus} currently in the game.
     *
     * @return the JackPotBonus instance
     */
    public Bonus getJackPotBonus() {
        return bonuses.get(2);
    }

    /**
     * Sets a table to be played. The game adds itself to the observers of this table and the bumpers and targets of
     * the table.
     *
     * @param table the table to be set
     * @see Table
     * @see Bumper
     * @see Target
     */
    public void setTable(Table table) {
        this.table = table;
        List<Bumper> bumpers = table.getBumpers();
        List<Target> targets = table.getTargets();
        table.attachObserver(this);
        for (Bumper bumper : bumpers) { bumper.attachObserver(this); }
        for (Target target : targets) { target.attachObserver(this); }
    }

    /**
     * Sets the number of balls available in the game.
     *
     * @param balls the number of balls to be set
     */
    public void setBalls(int balls) {
        this.balls = balls;
    }

    /**
     * Sets the current score in the game.
     *
     * @param score the score to be set
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Creates a new table with the given parameters.
     *
     * @param name                the name of the table
     * @param numberOfBumpers     the number of bumpers in the table
     * @param prob                the probability a {@link logic.gameelements.bumper.PopBumper}
     * @param numberOfSpotTargets the number of {@link logic.gameelements.target.SpotTarget}
     * @param numberOfDropTargets the number of {@link logic.gameelements.target.DropTarget}
     * @return a new table determined by the parameters
     */
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

    /**
     * Reduces the number of available balls and returns the new number.
     *
     * @return the new number of available balls
     */
    public int dropBall() {
        this.balls -= 1;
        return this.balls;
    }

    /**
     * Checks whether the game is over or not. A game is over when the number of available balls are 0.
     *
     * @return true if the game is over, false otherwise
     */
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

        if (arg.equals("hitDropTarget") || arg.equals("hitSpotTarget")) {
            Target target = (Target) o;
            this.score += target.getScore();
        }
    }
}
