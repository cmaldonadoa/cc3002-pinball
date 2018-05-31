package visitor;

import controller.Game;
import logic.gameelements.target.SpotTarget;

/**
 * Adds the score of a hit {@link SpotTarget} to the game.
 *
 * @author Cristobal Maldonado
 */
public class hitSpotTargetVisitor extends Visitor {
    @Override
    public void visitGame(Game game) {
        int currentScore = game.getScore();
        int score = new SpotTarget().getScore();
        game.setScore(currentScore + score);
    }
}
