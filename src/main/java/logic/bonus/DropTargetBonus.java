package logic.bonus;

import controller.Game;

/**
 * The class of a drop target bonus, which gives 1 000 000 points and upgrades all {@link logic.gameelements.bumper.Bumper}
 * on the table.
 *
 * @author Cristobal Maldonado
 */
public class DropTargetBonus extends AbstractBonus {
    /**
     * The constructor of a drop target bonus.
     */
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
