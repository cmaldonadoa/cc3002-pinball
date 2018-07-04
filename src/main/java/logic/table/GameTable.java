package logic.table;

import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;
import visitor.resetDropTargetsVisitor;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Game table class which contains game {@link Bumper}s and {@link Target}s. A game table is observed by {@link controller.Game}.
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
     * The constructor of a game table.
     *
     * @param name                the name of the table
     * @param numberOfDropTargets the number of drop targets in the table
     * @param bumpers             the list of bumpers in the table
     * @param targets             the list of targets in the table
     * @see Target
     * @see Bumper
     */
    public GameTable(String name, int numberOfDropTargets, List<Bumper> bumpers, List<Target> targets) {
        this.tableName = name;
        this.numberOfDropTargets = numberOfDropTargets;
        this.droppedDropTargets = 0;
        this.bumpers = bumpers;
        this.targets = targets;
    }

    /**
     * Sets the number of dropped {@link logic.gameelements.target.DropTarget} of the table to the given number.
     *
     * @param droppedDropTargets the number of dropped drop targets to be set
     */
    public void setDroppedDropTargets(int droppedDropTargets) {
        this.droppedDropTargets = droppedDropTargets;
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
        setChanged();
        notifyObservers(new resetDropTargetsVisitor());
        this.droppedDropTargets = 0;
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
    public void attachObserver(Observer o) {
        this.addObserver(o);
    }
}
