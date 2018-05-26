package logic.gameelements.bumper;

import java.util.Random;

public class KickerBumper extends AbstractBumper {
    public KickerBumper() {
        super(5, 500);
    }

    @Override
    public void upgrade() {
        setUpgrade(true);
        setScore(1000);
        double chance = new Random().nextDouble();
        if (chance <= 0.10) { notifyObservers("triggerExtraBallBonus"); }
    }

    @Override
    public void downgrade() {
        setUpgrade(false);
        setScore(500);
        setHitsToUpgrade(5);
    }
}
