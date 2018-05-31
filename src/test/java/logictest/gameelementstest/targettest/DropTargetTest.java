package logictest.gameelementstest.targettest;

import logic.gameelements.target.DropTarget;
import org.junit.*;
import static org.junit.Assert.*;

public class DropTargetTest {
    private DropTarget dropTarget;

    @Before
    public void setUp() {
        dropTarget = new DropTarget();
    }

    @Test
    public void testNotNull() {
        assertNotNull(dropTarget);
    }

    @Test
    public void testActive() {
        assertTrue(dropTarget.isActive());
    }

    @Test
    public void testDeactivate() {
        dropTarget.deactivate();
        assertFalse(dropTarget.isActive());
    }

    @Test
    public void testInactiveAfterHit() {
        dropTarget.hit();
        assertFalse(dropTarget.isActive());
    }

    @Test
    public void testReset() {
        dropTarget.hit();
        dropTarget.reset();
        assertTrue(dropTarget.isActive());
    }

    @Test
    public void testHitScore() {
        int score = dropTarget.hit();
        assertEquals(100, score);
    }

    @Test
    public void testHitWhenInactive() {
        dropTarget.hit();
        int score = dropTarget.hit();
        assertEquals(0, score);
    }
}
