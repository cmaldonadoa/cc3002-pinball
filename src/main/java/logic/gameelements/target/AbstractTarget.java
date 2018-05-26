package logic.gameelements.target;

import java.util.Observable;

public abstract class AbstractTarget extends Observable implements Target {
    private int score;
    private boolean active;

    public AbstractTarget(int score) {
        this.score = score;
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

    public abstract void getTargetType();

    @Override
    public abstract int hit();

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public boolean isActive() {
        return this.active;
    }

    @Override
    public void reset() {
        this.active = true;
    }
}
