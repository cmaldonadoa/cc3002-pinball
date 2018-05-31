package logictest.gameelementstest.bumpertest;

import logic.gameelements.bumper.KickerBumper;
import org.junit.*;
import static org.junit.Assert.*;

public class KickerBumperTest {
    private KickerBumper kickerBumper;

    @Before
    public void setUp() {
        kickerBumper = new KickerBumper();
    }

    @Test
    public void testNotNull() {
        assertNotNull(kickerBumper);
    }

    @Test
    public void testHitsToUpgrade() {
        assertEquals(5, kickerBumper.remainingHitsToUpgrade());
        kickerBumper.hit();
        assertEquals(4, kickerBumper.remainingHitsToUpgrade());
        kickerBumper.hit();
        assertEquals(3, kickerBumper.remainingHitsToUpgrade());
        kickerBumper.hit();
        assertEquals(2, kickerBumper.remainingHitsToUpgrade());
    }

    @Test
    public void testHitScore() {
        int score = kickerBumper.hit();
        assertEquals(500, score);
        assertEquals(kickerBumper.getScore(), score);
    }

    @Test
    public void testHitScoreWhenUpgraded() {
        kickerBumper.upgrade();
        int score = kickerBumper.hit();
        assertEquals(1000, score);
    }

    @Test
    public void testHitUpgrade() {
        assertFalse(kickerBumper.isUpgraded());
        kickerBumper.hit();
        assertFalse(kickerBumper.isUpgraded());
        kickerBumper.hit();
        assertFalse(kickerBumper.isUpgraded());
        kickerBumper.hit();
        assertFalse(kickerBumper.isUpgraded());
        kickerBumper.hit();
        assertFalse(kickerBumper.isUpgraded());
        kickerBumper.hit();
        assertTrue(kickerBumper.isUpgraded());
        kickerBumper.hit();
        assertTrue(kickerBumper.isUpgraded());
    }

    @Test
    public void testHitScoreUntilUpgrade() {
        int score = kickerBumper.hit();
        assertEquals(500, score);
        score = kickerBumper.hit();
        assertEquals(500, score);
        score = kickerBumper.hit();
        assertEquals(500, score);
        score = kickerBumper.hit();
        assertEquals(500, score);
        score = kickerBumper.hit();
        assertEquals(1000, score);
        score = kickerBumper.hit();
        assertEquals(1000, score);
    }

    @Test
    public void testDowngrade() {
        kickerBumper.upgrade();
        kickerBumper.downgrade();
        assertFalse(kickerBumper.isUpgraded());
    }

    @Test
    public void testHitScoreAfterDowngrade() {
        kickerBumper.upgrade();
        kickerBumper.downgrade();
        int score = kickerBumper.hit();
        assertEquals(500, score);
    }

    @Test
    public void testHitsToUpgradeAfterDowngrade() {
        kickerBumper.upgrade();
        kickerBumper.downgrade();
        assertEquals(5, kickerBumper.remainingHitsToUpgrade());
    }
}
