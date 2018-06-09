package logic.bonus;

import controller.Game;
import org.junit.*;
import static org.junit.Assert.*;

public class JackPotBonusTest {
    private JackPotBonus jackPotBonus;
    private Game game;

    @Before
    public void serUp() {
        jackPotBonus = new JackPotBonus();
        game = new Game();
    }

    @Test
    public void testNotNull() {
        assertNotNull(jackPotBonus);
    }

    @Test
    public void testTriggerScoreUpdate() {
        assertEquals(0, game.getScore());
        jackPotBonus.trigger(game);
        assertEquals(100000, game.getScore());
        game.setScore(50134);
        jackPotBonus.trigger(game);
        assertEquals(150134, game.getScore());
        jackPotBonus.trigger(game);
        assertEquals(250134, game.getScore());
    }
}
