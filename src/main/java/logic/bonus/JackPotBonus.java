package logic.bonus;

import controller.Game;

/**
 * The class of a jackpot bonus, which gives 100 000 points.
 *
 * @author Cristobal Maldonado
 */
public class JackPotBonus extends AbstractBonus {
    /**
     * The constructor of a jackpot bonus.
     */
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
