package logic.bonus;

import controller.Game;

public class JackPotBonus extends AbstractBonus {
    public JackPotBonus() {
        super();
    }

    @Override
    public void trigger(Game game) {
        triggerOnce();
        /* Add 100k points */
    }
}
