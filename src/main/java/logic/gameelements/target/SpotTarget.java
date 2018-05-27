package logic.gameelements.target;

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
    public int hit() {
        notifyObservers("triggerJackPotBonus");
        deactivate();
        notifyObservers("hitSpotTarget");
        return getScore();
    }

    @Override
    public void notifyType() {
        notifyObservers("SpotTarget");
    }
}
