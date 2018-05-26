package logic.gameelements.bumper;

import java.util.Observable;
import java.util.Observer;

public abstract class AbstractBumper extends Observable implements Bumper, Observer {
    private int hitsToUpgrade;
    private int score;
    private boolean upgraded;

    public AbstractBumper(int hits, int score) {
        this.hitsToUpgrade = hits;
        this.score = score;
        this.upgraded = false;
    }

    public void setHitsToUpgrade(int hits) {
        this.hitsToUpgrade = hits;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setUpgraded(boolean upgraded) {
        this.upgraded = upgraded;
    }

    @Override
    public int hit(){
        this.hitsToUpgrade -= 1;
        if (this.hitsToUpgrade == 0) { this.upgrade(); }
        return this.score;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public int remainingHitsToUpgrade() {
        return this.hitsToUpgrade;
    }

    @Override
    public boolean isUpgraded() {
        return this.upgraded;
    }

    @Override
    public abstract void upgrade();

    @Override
    public abstract void downgrade();

    @Override
    public void update(Observable o, Object arg) {
        if (arg.equals("upgradeBumpers")) { this.upgrade(); }
    }
}
