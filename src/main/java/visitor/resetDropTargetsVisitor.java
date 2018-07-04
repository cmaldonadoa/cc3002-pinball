package visitor;

import controller.Game;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.gameelements.target.Target;
import logic.table.Table;

/**
 * Resets a {@link DropTarget}.
 *
 * @author Cristobal Maldonado
 */
public class resetDropTargetsVisitor implements Visitor {
    @Override
    public void visitGame(Game game) {
        Table table = game.getTable();
        for (Target target : table.getTargets()) {
            target.accept(this);
        }
    }

    public void visitDropTarget(DropTarget dropTarget) {
        dropTarget.reset();
    }

    public void visitSpotTarget(SpotTarget spotTarget) {}
}
