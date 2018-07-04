package visitor;

import controller.Game;
import logic.gameelements.bumper.PopBumper;

/**
 * Adds the score of a hit upgraded {@link PopBumper} to the game.
 *
 * @author Cristobal Maldonado
 */
public class hitUpgradedPopBumperVisitor implements Visitor {
    @Override
    public void visitGame(Game game) {
        int currentScore = game.getScore();
        PopBumper bumper = new PopBumper();
        bumper.upgrade();
        int score = bumper.getScore();
        game.setScore(currentScore + score);
    }
}
