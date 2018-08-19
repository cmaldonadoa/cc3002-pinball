package logic.gameelements.target;

import logic.gameelements.Hittable;
import visitor.resetDropTargetsVisitor;

/**
 * Interface that represents operations related to a target behavior.
 *
 * @author Juan-Pablo Silva
 * @see SpotTarget
 * @see DropTarget
 */
public interface Target extends Hittable {
    /**
     * Gets whether the target is currently active or not.
     *
     * @return true if the target is active, false otherwise
     */
    boolean isActive();

    /**
     * Resets the state of a target making it active again.
     */
    void reset();

    /**
     * Defines that an object have been hit. Used for test only.
     *
     * @param seed a seed to handle the chance of triggering a bonus
     * @return the score the player obtained hitting the object
     * @see logic.bonus.Bonus
     */
    int hitSeed(long seed);

    /**
     * Gets whether a target is a {@link DropTarget} or not.
     *
     * @return true if the target is a DropTarget, false otherwise.
     */
    boolean isDropTarget();
}
