package logic.bonus;

import controller.Game;
import org.junit.*;
import static org.junit.Assert.*;

public class ExtraBallBonusTest {
    private ExtraBallBonus extraBallBonus;
    private Game game;

    @Before
    public void serUp() {
        extraBallBonus = new ExtraBallBonus();
        game = new Game();
    }

    @Test
    public void testNotNull() {
        assertNotNull(extraBallBonus);
    }

    @Test
    public void testTriggerScoreUpdate() {
        assertEquals(0, game.getScore());
        extraBallBonus.trigger(game);
        assertEquals(0, game.getScore());
    }

    @Test
    public void testBallAddedOnTrigger() {
        assertEquals(3, game.getBalls());
        extraBallBonus.trigger(game);
        assertEquals(4, game.getBalls());
    }
}
