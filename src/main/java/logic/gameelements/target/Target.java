package logic.gameelements.target;

import logic.gameelements.Hittable;
import visitor.Visitor;

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
     * Accepts a visitor to do something.
     *
     * @param visitor a visitor to be accepted
     */
    void accept(Visitor visitor);

    /**
     * Defines that an object have been hit.
     *
     * @param seed a seed to handle the chance of triggering a bonus
     * @return the score the player obtained hitting the object
     * @see logic.bonus.Bonus
     */
    int hitSeed(long seed);
}
