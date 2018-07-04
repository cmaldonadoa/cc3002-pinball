package visitor;

import controller.Game;
import logic.gameelements.bumper.KickerBumper;

/**
 * Adds the score of a hit upgraded {@link KickerBumper} to the game.
 *
 * @author Cristobal Maldonado
 */
public class hitUpgradedKickerBumperVisitor implements Visitor {
    @Override
    public void visitGame(Game game) {
        int currentScore = game.getScore();
        KickerBumper bumper = new KickerBumper();
        bumper.upgrade();
        int score = bumper.getScore();
        game.setScore(currentScore + score);
    }
}
