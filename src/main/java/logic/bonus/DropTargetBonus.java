package logic.bonus;

import controller.Game;

public class DropTargetBonus extends AbstractBonus {
    public DropTargetBonus() {
        super();
    }

    @Override
    public void trigger(Game game) {
        triggerOnce();
        /* Add 1M points */
        /* Upgrade all bumpers */
    }
}
