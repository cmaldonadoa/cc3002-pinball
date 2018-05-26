package logic.table;

import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;

import java.util.List;
import java.util.Observable;

public class GameTable extends Observable implements Table {
    private String tableName;
    private int numberOfDropTargets;
    private int droppedDropTargets;
    private List<Bumper> bumpers;
    private List<Target> targets;

    public GameTable(String name, int numberOfDropTargets, List<Bumper> bumpers, List<Target> targets) {
        this.tableName = name;
        this.numberOfDropTargets = numberOfDropTargets;
        this.droppedDropTargets = 0;
        this.bumpers = bumpers;
        this.targets = targets;
    }

    @Override
    public String getTableName() {
        return this.tableName;
    }

    @Override
    public int getNumberOfDropTargets() {
        return this.numberOfDropTargets;
    }

    @Override
    public int getCurrentlyDroppedDropTargets() { return this.droppedDropTargets; }

    @Override
    public List<Bumper> getBumpers() {
        return this.bumpers;
    }

    @Override
    public List<Target> getTargets() {
        return this.targets;
    }

    @Override
    public void resetDropTargets() {
        for (Target target : targets) {
            target.getTargetType();
        }
    }

    @Override
    public void upgradeAllBumpers() {
        for (Bumper bumper : this.bumpers) {
            bumper.upgrade();
        }
    }

    @Override
    public boolean isPlayableTable(){
        return !(this.bumpers.isEmpty() && this.targets.isEmpty() && this.tableName.equals(""));
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg.equals("DropTarget")) {
                Target target = (Target) o;
                target.reset();
        }

        if (arg.equals("droppedDropTarget")) {
            this.droppedDropTargets += 1;
            if (this.droppedDropTargets == this.numberOfDropTargets) { notifyObservers("triggerDropTargetBonus"); }
        }
    }

}
