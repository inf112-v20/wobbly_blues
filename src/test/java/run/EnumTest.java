package run;

import static org.junit.Assert.assertEquals;

import enums.Direction;
import org.junit.Test;

public class EnumTest {

    @Test
    public void oppositeTest() {
        Direction dir = Direction.LEFT;
        assertEquals(Direction.RIGHT, dir.getOpposite());

    }
}
