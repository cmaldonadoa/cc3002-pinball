package logic.gameelements.target;

import java.util.Random;

public class DropTarget extends AbstractTarget{
    public DropTarget() {
        super(100);
    }

    @Override
    public int hit() {
        double chance = new Random().nextDouble();
        if (chance <= 0.30) { notifyObservers("triggerExtraBallBonus"); }
        deactivate();
        notifyObservers("droppedDropTarget");
        return getScore();
    }

    @Override
    public void getTargetType() {
        notifyObservers("DropTarget");
    }
}
