package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

/** Test the clorus class
 * @authr Hong Tang
 */
public class TestClorus {

    @Test
    public void testChoose() {
        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        c = new Clorus(1.2);
        HashMap<Direction, Occupant> withPlip = new HashMap<>();
        withPlip.put(Direction.TOP, new Plip(1.1));
        withPlip.put(Direction.BOTTOM, new Empty());
        withPlip.put(Direction.LEFT, new Empty());
        withPlip.put(Direction.RIGHT, new Empty());

        actual = c.chooseAction(withPlip);
        expected = new Action(Action.ActionType.ATTACK, Direction.TOP);

        assertEquals(expected, actual);

        c = new Clorus(1.2);
        HashMap<Direction, Occupant> replicate = new HashMap<>();
        replicate.put(Direction.TOP, new Empty());
        replicate.put(Direction.BOTTOM, new Empty());
        replicate.put(Direction.LEFT, new Empty());
        replicate.put(Direction.RIGHT, new Empty());

        actual = c.chooseAction(replicate);
        expected = new Action(Action.ActionType.REPLICATE, actual.x, actual.y);

        assertEquals(expected, actual);

        c = new Clorus(0.8);
        HashMap<Direction, Occupant> allEmpty = new HashMap<>();
        allEmpty.put(Direction.TOP, new Empty());
        allEmpty.put(Direction.BOTTOM, new Impassible());
        allEmpty.put(Direction.LEFT, new Impassible());
        allEmpty.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(allEmpty);
        expected = new Action(Action.ActionType.MOVE, Direction.TOP);

        assertEquals(expected, actual);
    }
}
