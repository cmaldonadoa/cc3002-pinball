package logic.bonus;

import controller.Game;

import java.util.Observable;

public class ExtraBallBonus extends AbstractBonus {
    public ExtraBallBonus() {
        super();
    }

    @Override
    public void trigger(Game game) {
        triggerOnce();
        /* Add extra ball to game */
    }
}
