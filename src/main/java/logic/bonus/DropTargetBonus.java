package logic.bonus;

import controller.Game;

public class DropTargetBonus extends AbstractBonus {
    public DropTargetBonus() {
        super();
    }

    @Override
    public void trigger(Game game) {
        triggerOnce();
        int currentScore = game.getScore();
        game.setScore(currentScore + 1000000);
        game.getTable().upgradeAllBumpers();
    }
}
