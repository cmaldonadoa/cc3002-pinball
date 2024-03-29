package logic.gameelements.bumper;

import logic.gameelements.Hittable;

/**
 * Interface that represents operations related to a bumper behavior.
 *
 * @author Juan-Pablo Silva
 * @see KickerBumper
 * @see PopBumper
 */
public interface Bumper extends Hittable {
    /**
     * Gets the remaining hits the bumper has to receive to upgrade.
     *
     * @return the remaining hits to upgrade the bumper
     */
    int remainingHitsToUpgrade();

    /**
     * Gets whether the bumper is currently upgraded or not.
     *
     * @return true if the bumper is upgraded, false otherwise
     */
    boolean isUpgraded();

    /**
     * Upgrades a bumper making {@link #isUpgraded()} return true.
     */
    void upgrade();

    /**
     * Downgrades a bumper making {@link #isUpgraded()} return false.
     */
    void downgrade();

    /**
     * Gets whether a target is a {@link KickerBumper} or not.
     *
     * @return true if the target is a DropTarget, false otherwise.
     */
    boolean isKickerBumper();

    /**
     * Updates the score that the bumper will give when hit.
     *
     * @param seed a seed to handle the chance of triggering {@link logic.bonus.ExtraBallBonus} when upgraded.
     *             If the seed is 0 it will trigger the extra ball bonus, otherwise it will be random.
     */
    void upgradeSeed(long seed);
}
