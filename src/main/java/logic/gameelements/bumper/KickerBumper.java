package logic.gameelements.bumper;

import visitor.hitKickerBumperVisitor;
import visitor.hitUpgradedKickerBumperVisitor;
import visitor.triggerExtraBallBonusVisitor;

import java.util.Random;

/**
 * The class of a kicker bumper.
 *
 * @author Cristobal Maldonado
 */
public class KickerBumper extends AbstractBumper {
    /**
     * The constructor of a kicker bumper.
     */
    public KickerBumper() {
        super(5, 500);
    }

    @Override
    public int hit(){
        int hitsToUpgrade = remainingHitsToUpgrade();
        setHitsToUpgrade(hitsToUpgrade - 1);
        if (remainingHitsToUpgrade() == 0) { this.upgrade(); }
        if (isUpgraded()) {
            setChanged();
            notifyObservers(new hitUpgradedKickerBumperVisitor());
        }
        else {
            setChanged();
            notifyObservers(new hitKickerBumperVisitor());
        }
        return getScore();
    }

    @Override
    public void upgradeSeed(long seed) {
        setUpgraded(true);
        setScore(1000);
        double chance;
        if (seed != 0) { chance = new Random().nextDouble(); }
        else { chance = 0; }

        if (chance <= 0.10) {
            setChanged();
            notifyObservers(new triggerExtraBallBonusVisitor());
        }
    }

    @Override
    public void downgrade() {
        setUpgraded(false);
        setScore(500);
        setHitsToUpgrade(5);
    }
}
