package logic.gameelements.bumper;

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
    public void upgrade() {
        setUpgraded(true);
        setScore(1000);
        double chance = new Random().nextDouble();
        if (chance <= 0.10) { notifyObservers("triggerExtraBallBonus"); }
    }

    @Override
    public void downgrade() {
        setUpgraded(false);
        setScore(500);
        setHitsToUpgrade(5);
    }
}
