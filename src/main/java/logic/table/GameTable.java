package logic.table;

import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.Target;

import java.util.List;

public class GameTable implements Table {
    private String name;
    private int dropTargets;
    private int droppedTargets;
    private List<Bumper> bumpers;
    private List<Target> targets;

    public GameTable(String name, int dropTargets, int droppedTargets, List<Bumper> bumpers, List<Target> targets) {
        this.name = name;
        this.dropTargets = dropTargets;
        this.droppedTargets = droppedTargets;
        this.bumpers = bumpers;
        this.targets = targets;
    }

    @Override
    public String getTableName() {
        return this.name;
    }

    @Override
    public int getNumberOfDropTargets() {
        return this.dropTargets;
    }
    @Override
    public int getCurrentlyDroppedDropTargets() {
        return this.droppedTargets;
    }

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
        for (Target target : this.targets) {
            if (target instanceof DropTarget) { target.reset();}
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
        return true;
    }

}
