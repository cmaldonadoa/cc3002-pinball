package visitor;

import logic.gameelements.target.DropTarget;

/**
 * Resets a {@link DropTarget}.
 *
 * @author Cristobal Maldonado
 */
public class resetDropTargetsVisitor extends Visitor {
    @Override
    public void visitDropTarget(DropTarget dropTarget) {
        dropTarget.reset();
    }
}
