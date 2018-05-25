package logic.gameelements.target;

import controller.Game;
import java.util.Observable;

public abstract class AbstractTarget extends Observable implements Target {
    private int score;
    private boolean active;
    private Game game;

    public AbstractTarget(int score, Game game) {
        this.score = score;
        this.active = true;
        this.game = game;
    }

    public Game getGame() {
        return this.game;
    }

    public void deactivate() {
        this.active = false;
    }

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
