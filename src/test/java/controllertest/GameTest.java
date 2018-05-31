package controllertest;

import controller.Game;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.gameelements.target.Target;
import logic.table.GameTable;
import logic.table.Table;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameTest {
    private Game notPlayableGame;
    private Game playableGame;

    @Before
    public void setUp() {
        notPlayableGame = new Game();
        playableGame = new Game();
        List<Bumper> bumpers = new ArrayList<>();
        List<Target> targets = new ArrayList<>();
        bumpers.add(new KickerBumper());
        bumpers.add(new KickerBumper());
        bumpers.add(new KickerBumper());
        bumpers.add(new PopBumper());
        bumpers.add(new PopBumper());
        bumpers.add(new PopBumper());
        targets.add(new DropTarget());
        targets.add(new DropTarget());
        targets.add(new DropTarget());
        targets.add(new SpotTarget());
        targets.add(new SpotTarget());
        targets.add(new SpotTarget());
        GameTable table = new GameTable("aTable", 3, bumpers, targets);
        playableGame.setTable(table);
    }

    @Test
    public void testNotNull() {
        assertNotNull(notPlayableGame);
        assertNotNull(playableGame);
    }

    @Test
    public void testPlayableTable() {
        assertTrue(playableGame.getTable().isPlayableTable());
        assertFalse(notPlayableGame.getTable().isPlayableTable());
    }

    @Test
    public void testNotTriggeredBonus() {
        assertEquals(0, playableGame.getDropTargetBonus().timesTriggered());
        assertEquals(0, playableGame.getExtraBallBonus().timesTriggered());
        assertEquals(0, playableGame.getJackPotBonus().timesTriggered());
    }

    @Test
    public void testCreatePlayableTables() {
        Table table1 = notPlayableGame.createTable("table1", 7, 0.4, 4,8);
        Table table2 = playableGame.createTable("table2", 3, 0.83, 0,0);
        assertTrue(table1.isPlayableTable());
        assertTrue(table2.isPlayableTable());
    }

    @Test
    public void testDropBall() {
        assertEquals(3, playableGame.getBalls());
        playableGame.dropBall();
        assertEquals(2, playableGame.getBalls());
        playableGame.dropBall();
        assertEquals(1, playableGame.getBalls());
    }

    @Test
    public void testGameOver() {
        assertFalse(playableGame.gameOver());
        playableGame.setBalls(0);
        assertTrue(playableGame.gameOver());
    }

    @Test
    public void testScoreUpdateOnHittingBumpers() {
        assertEquals(0, playableGame.getScore());
        for (Bumper bumper : playableGame.getTable().getBumpers()) {
            bumper.hit();
        }
        assertEquals(1800, playableGame.getScore());
    }

    @Test
    public void testScoreUpdateOnHittingUpgradedBumpers() {
        assertEquals(0, playableGame.getScore());
        for (Bumper bumper : playableGame.getTable().getBumpers()) {
            bumper.upgrade();
            bumper.hit();
        }
        assertEquals(3900, playableGame.getScore());
    }

    @Test
    public void testScoreUpdateOnHittingTargets() {
        assertEquals(0, playableGame.getScore());
        for (Target target : playableGame.getTable().getTargets()) {
            target.hitSeed(1);
        }
        assertEquals(1000300, playableGame.getScore());
        for (Target target : playableGame.getTable().getTargets()) {
            assertFalse(target.isActive());
            target.hit();
        }
        assertEquals(1000300, playableGame.getScore());
    }

    @Test
    public void testTriggerDropTargetBonus() {
        for (Target target : playableGame.getTable().getTargets()) {
            target.hit();
        }
        assertEquals(playableGame.getTable().getNumberOfDropTargets(), playableGame.getTable().getCurrentlyDroppedDropTargets());
        assertEquals(1, playableGame.getDropTargetBonus().timesTriggered());
        for (Bumper bumper : playableGame.getTable().getBumpers()) {
            assertTrue(bumper.isUpgraded());
        }
    }

    @Test
    public void testTriggerJackPotBonus() {
        for (Target target : playableGame.getTable().getTargets()) {
            target.hit();
        }
        assertEquals(3, playableGame.getJackPotBonus().timesTriggered());
    }

    @Test
    public void testTriggerExtraBallBonus() {
        List<Target> newTargets = new ArrayList<>();
        newTargets.add(new DropTarget());
        newTargets.add(new DropTarget());
        Table fixedTable = new GameTable("fixedTable", 2, playableGame.getTable().getBumpers(), newTargets);
        playableGame.setTable(fixedTable);

        for (Bumper bumper : playableGame.getTable().getBumpers()) {
            bumper.upgradeSeed(0);
        }
        assertEquals(6, playableGame.getExtraBallBonus().timesTriggered());
        for (Target target : playableGame.getTable().getTargets()) {
            DropTarget dropTarget = (DropTarget) target;
            dropTarget.hitSeed(0);
        }
        assertEquals(8, playableGame.getExtraBallBonus().timesTriggered());
    }
}
