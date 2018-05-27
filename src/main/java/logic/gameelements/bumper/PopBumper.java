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
    public void upgrade() {
        setUpgraded(true);
        setScore(300);
        double chance = new Random().nextDouble();
        if (chance <= 0.10) { notifyObservers("triggerExtraBallBonus"); }
    }

    @Override
    public void downgrade() {
        setUpgraded(false);
        setScore(100);
        setHitsToUpgrade(3);
    }
}
