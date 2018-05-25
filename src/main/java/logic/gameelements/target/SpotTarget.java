package logic.gameelements.target;

import controller.Game;

public class SpotTarget extends AbstractTarget {
    public SpotTarget(Game game) {
        super(0, game);
    }

    @Override
    public int hit() {
        notifyObservers(getGame()); // JackPotBonus
        deactivate();
        return getScore();
    }
}
