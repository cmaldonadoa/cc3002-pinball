package visitor;

import controller.Game;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.Target;
import logic.table.GameTable;

/**
 * Resets all {@link DropTarget}s in the current table.
 *
 * @author Cristobal Maldonado
 */
public class resetDropTargetsVisitor implements Visitor {
    @Override
    public void visitGame(Game game) {
        GameTable table = (GameTable) game.getTable();
        for (Target target : table.getTargets()) {
            if (!target.isActive() && target.isDropTarget()) {
                target.reset();
                table.setDroppedDropTargets(table.getCurrentlyDroppedDropTargets() - 1);
            }
        }
    }
}
