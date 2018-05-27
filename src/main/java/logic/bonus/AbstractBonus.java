package logic.bonus;

import controller.Game;

/**
 * Abstract class that defines some common behaviour between bonuses.
 *
 * @author Cristobal Maldonado
 * @see DropTargetBonus
 * @see ExtraBallBonus
 * @see JackPotBonus
 */
public abstract class AbstractBonus implements Bonus {
    private int triggered;

    /**
     * The constructor of a bonus.
     */
    public AbstractBonus() {
        this.triggered = 0;
    }

    /**
     * Adds one to the triggered times counter.
     */
    public void triggerOnce() {
        this.triggered += 1;
    }

    @Override
    public int timesTriggered() {
        return this.triggered;
    }

    @Override
    public abstract void trigger(Game game);
}
