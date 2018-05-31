package visitor;

import controller.Game;
import logic.gameelements.bumper.KickerBumper;

/**
 * Adds the score of a hit {@link KickerBumper} to the game.
 *
 * @author Cristobal Maldonado
 */
public class hitKickerBumperVisitor extends Visitor {
    @Override
    public void visitGame(Game game) {
        int currentScore = game.getScore();
        int score = new KickerBumper().getScore();
        game.setScore(currentScore + score);
    }
}
