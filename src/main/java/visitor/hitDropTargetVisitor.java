package visitor;

import controller.Game;
import logic.gameelements.target.DropTarget;
import logic.table.GameTable;

/**
 * Adds the score given by a hit {@link DropTarget} to the game. Also triggers the {@link logic.bonus.DropTargetBonus}
 * if the current table in the game has all of its drop targets dropped.
 *
 * @author Cristobal Maldonado
 */
public class hitDropTargetVisitor extends Visitor {
    @Override
    public void visitGame(Game game) {
        int currentScore = game.getScore();
        int score = new DropTarget().getScore();
        game.setScore(currentScore + score);

        GameTable table = (GameTable) game.getTable();
        int currentDroppedDropTargets = table.getCurrentlyDroppedDropTargets();
        table.setDroppedDropTargets(currentDroppedDropTargets + 1);
        if (table.getCurrentlyDroppedDropTargets() == table.getNumberOfDropTargets()) {
            new triggerDropTargetBonusVisitor().visitGame(game);
        }
    }
}
