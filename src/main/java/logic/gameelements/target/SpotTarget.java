package logic.gameelements.target;

import visitor.hitSpotTargetVisitor;
import visitor.resetDropTargetsVisitor;
import visitor.triggerJackPotBonusVisitor;

/**
 * The class of a spot target, which triggers the {@link logic.bonus.JackPotBonus}.
 *
 * @author Cristobal Maldonado
 */
public class SpotTarget extends AbstractTarget {
    /**
     * The constructor of a spot target.
     */
    public SpotTarget() {
        super(0);
    }

    @Override
    /*
    Triggers the jackpot bonus, notifying it to the current game, then deactivates itself and notifies the current
    game that it has been hit.

    If the seed is 1, then it won't trigger the jackpot bonus.
     */
    public int hitSeed(long seed) {
        if (seed != 1) {
            setChanged();
            notifyObservers(new triggerJackPotBonusVisitor());
        }
        deactivate();
        setChanged();
        notifyObservers(new hitSpotTargetVisitor());
        return getScore();
    }

    @Override
    public boolean isDropTarget() {
        return false;
    }
}
