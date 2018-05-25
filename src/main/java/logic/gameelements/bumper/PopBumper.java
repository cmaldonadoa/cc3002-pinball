package logic.gameelements.bumper;

import java.util.Random;

public class PopBumper extends AbstractBumper{
    public PopBumper() {
        super(3, 100);
    }

    @Override
    public void upgrade() {
        setUpgrade(true);
        setScore(300);
        double chance = new Random().nextDouble();
        if (chance <= 0.10) { notifyObservers("PopBumper"); } // ExtraBallBonus
    }

    @Override
    public void downgrade() {
        setUpgrade(false);
        setScore(100);
        setHitsToUpgrade(3);
    }
}
