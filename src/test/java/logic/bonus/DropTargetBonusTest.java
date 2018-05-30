package logic.bonus;

import controller.Game;
import org.junit.*;
import static org.junit.Assert.*;

public class DropTargetBonusTest {
    private DropTargetBonus dropTargetBonus;
    private Game game;

    @Before
    public void serUp() {
        dropTargetBonus = new DropTargetBonus();
        game = new Game();
    }

    @Test
    public void testNotNull() {
        assertNotNull(dropTargetBonus);
    }

    @Test
    public void testTimesTriggered() {
        assertEquals(0, dropTargetBonus.timesTriggered());
        dropTargetBonus.trigger(game);
        assertEquals(1, dropTargetBonus.timesTriggered());
        dropTargetBonus.trigger(game);
        assertEquals(2, dropTargetBonus.timesTriggered());
    }

    @Test
    public void testTriggerScoreUpdate() {
        assertEquals(0, game.getScore());
        dropTargetBonus.trigger(game);
        assertEquals(1000000, game.getScore());
        game.setScore(50134);
        dropTargetBonus.trigger(game);
        assertEquals(1050134, game.getScore());
        dropTargetBonus.trigger(game);
        assertEquals(2050134, game.getScore());
    }
}
