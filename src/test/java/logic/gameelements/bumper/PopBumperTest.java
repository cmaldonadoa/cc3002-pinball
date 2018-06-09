package logic.gameelements.bumper;

import org.junit.*;
import static org.junit.Assert.*;

public class PopBumperTest {
    private PopBumper popBumper;

    @Before
    public void setUp() {
        popBumper = new PopBumper();
    }

    @Test
    public void testNotNull() {
        assertNotNull(popBumper);
    }

    @Test
    public void testHitsToUpgrade() {
        assertEquals(3, popBumper.remainingHitsToUpgrade());
        popBumper.hit();
        assertEquals(2, popBumper.remainingHitsToUpgrade());
        popBumper.hit();
        assertEquals(1, popBumper.remainingHitsToUpgrade());
        popBumper.hit();
        assertEquals(0, popBumper.remainingHitsToUpgrade());
    }

    @Test
    public void testHitScore() {
        int score = popBumper.hit();
        assertEquals(100, score);
        assertEquals(popBumper.getScore(), score);
    }

    @Test
    public void testHitScoreWhenUpgraded() {
        popBumper.upgrade();
        int score = popBumper.hit();
        assertEquals(300, score);
    }

    @Test
    public void testHitUpgrade() {
        assertFalse(popBumper.isUpgraded());
        popBumper.hit();
        assertFalse(popBumper.isUpgraded());
        popBumper.hit();
        assertFalse(popBumper.isUpgraded());
        popBumper.hit();
        assertTrue(popBumper.isUpgraded());
        popBumper.hit();
        assertTrue(popBumper.isUpgraded());
    }

    @Test
    public void testHitScoreUntilUpgrade() {
        int score = popBumper.hit();
        assertEquals(100, score);
        score = popBumper.hit();
        assertEquals(100, score);
        score = popBumper.hit();
        assertEquals(300, score);
        score = popBumper.hit();
        assertEquals(300, score);
    }

    @Test
    public void testDowngrade() {
        popBumper.upgrade();
        popBumper.downgrade();
        assertFalse(popBumper.isUpgraded());
    }

    @Test
    public void testHitScoreAfterDowngrade() {
        popBumper.upgrade();
        popBumper.downgrade();
        int score = popBumper.hit();
        assertEquals(100, score);
    }

    @Test
    public void testHitsToUpgradeAfterDowngrade() {
        popBumper.upgrade();
        popBumper.downgrade();
        assertEquals(3, popBumper.remainingHitsToUpgrade());
    }
}
