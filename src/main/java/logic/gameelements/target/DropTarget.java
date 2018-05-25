package logic.gameelements.target;

import controller.Game;
import java.util.Random;

public class DropTarget extends AbstractTarget{
    public DropTarget(Game game) {
        super(100, game);
    }

    @Override
    public int hit() {
        double chance = new Random().nextDouble();
        if (chance <= 0.30) { notifyObservers(getGame()); } // ExtraBallBonus
        deactivate();
        return getScore();
    }
}
