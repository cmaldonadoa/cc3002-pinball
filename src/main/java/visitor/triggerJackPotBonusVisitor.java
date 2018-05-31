package visitor;

import controller.Game;

/**
 * Triggers the jackpot bonus on a game.
 *
 * @author Cristobal Maldonado
 */
public class triggerJackPotBonusVisitor extends Visitor {
    @Override
    public void visitGame(Game game) {
        game.getJackPotBonus().trigger(game);
    }
}
