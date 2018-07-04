package visitor;

import controller.Game;

/**
 * Triggers the extra ball bonus on a game.
 *
 * @author Cristobal Maldonado
 */
public class triggerExtraBallBonusVisitor implements Visitor {

    @Override
    public void visitGame(Game game) {
        game.getExtraBallBonus().trigger(game);
    }
}
