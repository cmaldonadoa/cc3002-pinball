package logic.gameelements.target;

import java.util.Random;

/**
 * The class of a drop target.
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
    public int hit() {
        double chance = new Random().nextDouble();
        if (chance <= 0.30) { notifyObservers("triggerExtraBallBonus"); }
        deactivate();
        notifyObservers("hitDropTarget");
        return getScore();
    }

    @Override
    public void notifyType() {
        notifyObservers("DropTarget");
    }
}
