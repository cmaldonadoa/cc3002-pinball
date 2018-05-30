package logic.gameelements.target;

import visitor.Visitor;

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
    public int hitSeed(long seed) {
        double chance;
        if (seed != 0) { chance = new Random().nextDouble(); }
        else { chance = 0; }
        if (chance <= 0.30) {
            setChanged();
            notifyObservers("triggerExtraBallBonus");
        }
        deactivate();
        clearChanged();
        setChanged();
        notifyObservers("hitDropTarget");
        return getScore();
    }

    @Override
    public int hit() {
        return hitSeed(-1);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitDropTarget(this);
    }
}
