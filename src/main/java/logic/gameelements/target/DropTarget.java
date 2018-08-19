package logic.gameelements.target;

import visitor.hitDropTargetVisitor;
import visitor.resetDropTargetsVisitor;
import visitor.triggerExtraBallBonusVisitor;

import java.util.Random;

/**
 * The class of a drop target, which has a probability of triggering the {@link logic.bonus.ExtraBallBonus}.
 *
 * @author Cristobal Maldonado
 */
public class DropTarget extends AbstractTarget{
    /**
     * The constructor of a drop target.
     */
    public DropTarget() {
        super(100);
    }

    @Override
    /*
    Has a chance of triggering the extra ball bonus, notifying it to the current game if triggered, then deactivates
    itself and notifies the current game that it has been hit.

    If the seed is 1 it won't trigger the extra ball bonus.
    If the seed is 0 it will trigger the extra ball bonus.
     */
    public int hitSeed(long seed) {
        double chance;
        if (seed == 1) { chance = 1; }
        else if (seed == 0) { chance =  0; }
        else { chance = new Random().nextDouble(); }

        if (chance <= 0.30) {
            setChanged();
            notifyObservers(new triggerExtraBallBonusVisitor());
        }
        deactivate();
        setChanged();
        notifyObservers(new hitDropTargetVisitor());
        return getScore();
    }

    @Override
    public boolean isDropTarget() {
        return true;
    }
}
