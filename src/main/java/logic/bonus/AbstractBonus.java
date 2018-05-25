package logic.bonus;

import controller.Game;

import java.util.Observable;
import java.util.Observer;

public abstract class AbstractBonus implements Bonus, Observer {
    private int triggered;

    public AbstractBonus() {
        this.triggered = 0;
    }

    public void triggerOnce() {
        this.triggered += 1;
    }

    @Override
    public void update(Observable o, Object arg) {
        trigger((Game) arg);
    }

    @Override
    public int timesTriggered() {
        return this.triggered;
    }

    @Override
    public abstract void trigger(Game game);


}
