package visitor;

import logic.gameelements.target.DropTarget;

/**
 * A visitor subclass to reset drop targets.
 *
 * @author Cristobal Maldonado
 * @see DropTarget
 */
public class resetDropTargetsVisitor extends Visitor {
    @Override
    public void visitDropTarget(DropTarget dropTarget) {
        dropTarget.reset();
    }
}
