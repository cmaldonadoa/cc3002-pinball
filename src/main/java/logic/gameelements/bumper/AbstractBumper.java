package logic.gameelements.bumper;

import controller.Game;

import java.util.Observable;
import java.util.Observer;


/**
 * Abstract class that defines some common behaviour between bumpers. Bumpers are observed by {@link Game}.
 *
 * @author Cristobal Maldonado
 * @see KickerBumper
 * @see PopBumper
 */
public abstract class AbstractBumper extends Observable implements Bumper {
    private int hitsToUpgrade;
    private int score;
    private boolean upgraded;

    /**
     * The constructor of a bumper.
     *
     * @param hits the hits the bumper needs to be upgraded.
     * @param score the score the bumper gives when hit
     */
    public AbstractBumper(int hits, int score) {
        this.hitsToUpgrade = hits;
        this.score = score;
        this.upgraded = false;
    }

    /**
     * Sets the hits needed to upgrade the bumper.
     *
     * @param hits the number of hits needed to upgrade the bumper
     */
    public void setHitsToUpgrade(int hits) {
        this.hitsToUpgrade = hits;
    }

    /**
     * Sets the score the bumpers hives when hit.
     *
     * @param score the score the bumper gives when hit
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Sets the upgrade status of the bumper (true if its upgraded, false otherwise).
     *
     * @param upgraded the upgrade status of the bumper
     */
    public void setUpgraded(boolean upgraded) {
        this.upgraded = upgraded;
    }

    /**
     * Decreases by one the remaining hits to upgrade, and upgrades the bumper if its necessary.
     */
    public void decreaseHitsToUpgrade() {
        if (!this.isUpgraded()) {
            this.hitsToUpgrade -= 1;
            if (remainingHitsToUpgrade() == 0) {
                this.upgrade();
            }
        }
    }

    @Override
    public abstract void upgradeSeed(long seed);

    @Override
    /*
    Decreases the hits needed to upgrade, and upgrades the bumper if needed. Notifies the current {@link controller.Game}
    instance that a bumper has been hit, and if this bumper is upgraded or not.
    */
    public abstract int hit();

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
    public void upgrade() {
        if (!this.isUpgraded()) {
            this.upgradeSeed(-1);
        }
    }

    @Override
    public abstract void downgrade();

    @Override
    public void attachObserver(Observer o) { this.addObserver(o); }

    @Override
    public abstract boolean isKickerBumper();

}
