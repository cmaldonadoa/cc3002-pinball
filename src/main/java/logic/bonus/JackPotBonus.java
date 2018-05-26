package logic.bonus;

import controller.Game;

public class JackPotBonus extends AbstractBonus {
    public JackPotBonus() {
        super();
    }

    @Override
    public void trigger(Game game) {
        triggerOnce();
        int currentScore = game.getScore();
        game.setScore(currentScore + 100000);
    }
}
