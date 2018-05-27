package logic.bonus;

import controller.Game;

/**
 * The class of an extra ball bonus, which adds an extra ball to the current {@link Game}.
 *
 * @author Cristobal Naldonado
 */
public class ExtraBallBonus extends AbstractBonus {
    /**
     * The constructor of an extra ball bonus.
     */
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
