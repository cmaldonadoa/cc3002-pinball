package visitor;

import controller.Game;

/**
 * Triggers the drop target bonus on a game.
 *
 * @author Cristobal Maldonado
 */
public class triggerDropTargetBonusVisitor implements Visitor{
    @Override
    public void visitGame(Game game) {
        game.getDropTargetBonus().trigger(game);
    }
}
