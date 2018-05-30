package logic.gameelements.bumper;

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
    public void upgradeSeed(long seed) {
        setUpgraded(true);
        setScore(300);
        double chance;
        if (seed != 0) { chance = new Random().nextDouble(); }
        else { chance = 0; }
        if (chance <= 0.10) {
            setChanged();
            notifyObservers("triggerExtraBallBonus");
        }
    }

    @Override
    public void downgrade() {
        setUpgraded(false);
        setScore(100);
        setHitsToUpgrade(3);
        clearChanged();
    }
}
