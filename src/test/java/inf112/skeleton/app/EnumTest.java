package inf112.skeleton.app;

import static org.junit.Assert.assertEquals;

import Enums.Direction;
import org.junit.Test;

public class EnumTest {

    @Test
    public void oppositeTest() {
        Direction dir = Direction.LEFT;
        assertEquals(Direction.RIGHT, dir.getOpposite());
    }
}
