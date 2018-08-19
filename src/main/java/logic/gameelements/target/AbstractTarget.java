package logic.gameelements.target;

import visitor.resetDropTargetsVisitor;

import java.util.Observable;
import java.util.Observer;

/**
 *  Abstract class that defines some common behaviours between targets. Targets are observed by {@link controller.Game}.
 *
 *  @author Cristobal Maldonado
 *  @see DropTarget
 *  @see SpotTarget
 */

public abstract class AbstractTarget extends Observable implements Target {
    private int score;
    private boolean active;

    /**
     * The constructor for a target.
     *
     * @param score the score this target gives when hit
     */
    public AbstractTarget(int score) {
        this.score = score;
        this.active = true;
    }

    /**
     * Makes the target inactive.
     */
    public void deactivate() { this.active = false; }

    @Override
    public abstract int hitSeed(long seed);

    @Override
    public int hit() {
        if (this.isActive()) {
            return this.hitSeed(-1);
        }
        return 0;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public boolean isActive() { return this.active == true; }

    @Override
    public void reset() { this.active = true; }

    @Override
    public void attachObserver(Observer o) { this.addObserver(o); }

    @Override
    public abstract boolean isDropTarget();
}
