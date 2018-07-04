package visitor;

import controller.Game;

/**
 * Triggers the jackpot bonus on a game.
 *
 * @author Cristobal Maldonado
 */
public class triggerJackPotBonusVisitor implements Visitor {
    @Override
    public void visitGame(Game game) {
        game.getJackPotBonus().trigger(game);
    }
}
