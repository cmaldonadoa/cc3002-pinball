package logic.gameelements.target;

import org.junit.*;
import static org.junit.Assert.*;

public class SpotTargetTest {
    private SpotTarget spotTarget;

    @Before
    public void setUp() {
        spotTarget = new SpotTarget();
    }

    @Test
    public void testNotNull() {
        assertNotNull(spotTarget);
    }

    @Test
    public void testActive() {
        assertTrue(spotTarget.isActive());
    }

    @Test
    public void testDeactivate() {
        spotTarget.deactivate();
        assertFalse(spotTarget.isActive());
    }

    @Test
    public void testInactiveAfterHit() {
        spotTarget.hit();
        assertFalse(spotTarget.isActive());
    }

    @Test
    public void testReset() {
        spotTarget.hit();
        spotTarget.reset();
        assertTrue(spotTarget.isActive());
    }

    @Test
    public void testHitScore() {
        int score = spotTarget.hit();
        assertEquals(0, score);
    }

    @Test
    public void testHitWhenInactive() {
        int score = spotTarget.hit();
        assertEquals(0, score);
    }
}
