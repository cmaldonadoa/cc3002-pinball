package logic.table;

import controller.Game;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Game table class which contains game elements. A game table is observed by {@link Game}.
 *
 * @author Cristobal Maldonado
 */
public class GameTable extends Observable implements Table {
    private String tableName;
    private int numberOfDropTargets;
    private int droppedDropTargets;
    private List<Bumper> bumpers;
    private List<Target> targets;

    /**
     * The constructor of a game table. It adds itself to the observers of the bumpers contained in the table.
     *
     * @param name                the name of the table
     * @param numberOfDropTargets the number of drop targets that will be available
     * @param bumpers             the list of bumpers in the table
     * @param targets             the list of targets in the table
     *
     * @see Target
     * @see Bumper
     */
    public GameTable(String name, int numberOfDropTargets, List<Bumper> bumpers, List<Target> targets) {
        this.tableName = name;
        this.numberOfDropTargets = numberOfDropTargets;
        this.droppedDropTargets = 0;
        this.bumpers = bumpers;
        this.targets = targets;
        for(Target target : targets) { target.attachObserver(this); }
    }

    @Override
    public String getTableName() { return this.tableName; }

    @Override
    public int getNumberOfDropTargets() { return this.numberOfDropTargets; }

    @Override
    public int getCurrentlyDroppedDropTargets() { return this.droppedDropTargets; }

    @Override
    public List<Bumper> getBumpers() { return this.bumpers; }

    @Override
    public List<Target> getTargets() { return this.targets; }

    @Override
    public void resetDropTargets() {
        for (Target target : targets) {
            target.notifyType();
        }
    }

    @Override
    public void upgradeAllBumpers() {
        for (Bumper bumper : this.bumpers) {
            bumper.upgrade();
        }
    }

    @Override
    public boolean isPlayableTable(){ return !(this.bumpers.isEmpty() && this.targets.isEmpty() && this.tableName.equals("")); }

    @Override
    public void attachObserver(Observer o) { this.addObserver(o); }

    @Override
    public void update(Observable o, Object arg) {
        if (arg.equals("DropTarget")) {
                Target target = (Target) o;
                target.reset();
        }

        if (arg.equals("hitDropTarget")) {
            this.droppedDropTargets += 1;
            if (this.droppedDropTargets == this.numberOfDropTargets) { notifyObservers("triggerDropTargetBonus"); }
        }
    }

}
