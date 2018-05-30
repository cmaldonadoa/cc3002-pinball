package visitor;

import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;

/**
 * A visitor class to implement the visitor design pattern.
 *
 * @author Cristobal Maldonado
 */
public class Visitor {
    /**
     * Visits a {@link DropTarget}.
     *
     * @param dropTarget the drop target to be visited.
     */
    public void visitDropTarget(DropTarget dropTarget) {}

    /**
     * Visits a {@link SpotTarget}.
     *
     * @param spotTarget the spot target to be visited.
     */
    public void visitSpotTarget(SpotTarget spotTarget) {}
}
