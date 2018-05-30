package logic.gameelements.target;

import visitor.Visitor;

/**
 * The class of a spot target.
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
    public int hitSeed(long seed) {
        setChanged();
        notifyObservers("triggerJackPotBonus");
        deactivate();
        clearChanged();
        setChanged();
        notifyObservers("hitSpotTarget");
        return getScore();
    }

    @Override
    public int hit() {
        return hitSeed(-1);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitSpotTarget(this);
    }
}
