package visitor;

import controller.Game;

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
public interface Visitor {
    /**
     * Visits a {@link Game}.
     *
     * @param game the game to be visited.
     */
    void visitGame(Game game);
}
