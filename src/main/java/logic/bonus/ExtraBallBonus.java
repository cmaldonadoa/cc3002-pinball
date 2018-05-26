package logic.bonus;

import controller.Game;

import java.util.Observable;

public class ExtraBallBonus extends AbstractBonus {
    public ExtraBallBonus() {
        super();
    }

    @Override
    public void trigger(Game game) {
        triggerOnce();
        int currentBalls = game.getBalls();
        game.setBalls(currentBalls + 1);
    }
}
