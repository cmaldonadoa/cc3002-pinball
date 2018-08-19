package logic.gameelements.bumper;

import visitor.hitPopBumperVisitor;
import visitor.hitUpgradedPopBumperVisitor;
import visitor.triggerExtraBallBonusVisitor;

import java.util.Random;

/**
 * The class of a pop bumper.
 *
 * @author Cristobal Maldonado
 */
public class PopBumper extends AbstractBumper{
    /**
     * The constructor of a pop bumper.
     */
    public PopBumper() {
        super(3, 100);
    }

    @Override
    public int hit(){
        decreaseHitsToUpgrade();
        if (isUpgraded()) {
            setChanged();
            notifyObservers(new hitUpgradedPopBumperVisitor());
        }
        else {
            setChanged();
            notifyObservers(new hitPopBumperVisitor());
        }
        return getScore();
    }

    @Override
    public void upgradeSeed(long seed) {
        setUpgraded(true);
        setScore(300);
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
        setScore(100);
        setHitsToUpgrade(3);
    }

    @Override
    public boolean isKickerBumper() {
        return false;
    }
}
