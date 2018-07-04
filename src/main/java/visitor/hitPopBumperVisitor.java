package visitor;

import controller.Game;
import logic.gameelements.bumper.PopBumper;

/**
 * Adds the score of a hit {@link PopBumper} to the game.
 *
 * @author Cristobal Maldonado
 */
public class hitPopBumperVisitor implements Visitor {
    @Override
    public void visitGame(Game game) {
        int currentScore = game.getScore();
        int score = new PopBumper().getScore();
        game.setScore(currentScore + score);
    }
}
