package logic.table;

import logic.gameelements.bumper.Bumper;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.gameelements.target.Target;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameTableTest {
    private GameTable table;

    @Before
    public void setUp() {
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
        table = new GameTable("aTable", 3, bumpers, targets);
    }

    @Test
    public void testNotNull() {
        assertNotNull(table);
    }

    @Test
    public void testTableName() {
        assertEquals("aTable", table.getTableName());
    }

    @Test
    public void testPlayableTable() {
        assertTrue(table.isPlayableTable());
    }

    @Test
    public void testUpgradeAllBumpers() {
        table.upgradeAllBumpers();
        for (Bumper bumper : table.getBumpers()) {
            assertTrue(bumper.isUpgraded());
        }
    }
}
