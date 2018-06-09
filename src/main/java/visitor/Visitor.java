package visitor;

import controller.Game;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;

/**
 * A visitor class to implement the visitor design pattern.
 *
 * @author Cristobal Maldonado
 * @see hitDropTargetVisitor
 * @see hitSpotTargetVisitor
 * @see hitKickerBumperVisitor
 * @see hitPopBumperVisitor
 * @see hitUpgradedKickerBumperVisitor
 * @see hitUpgradedPopBumperVisitor
 * @see resetDropTargetsVisitor
 * @see triggerJackPotBonusVisitor
 * @see triggerDropTargetBonusVisitor
 * @see triggerExtraBallBonusVisitor
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
    public void visitSpotTarget(SpotTarget spotTarget){}

    /**
     * Visits a {@link Game}.
     *
     * @param game the game to be visited.
     */
    public void visitGame(Game game) {}
}
