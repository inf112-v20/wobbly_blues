package deprecated;

import deprecated.classes.BlankCell;
import deprecated.classes.List3D;
import interfaces.ICell;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class List3DTest {

    @Test
    public void test3D(){
        List3D<ICell> list = new List3D<>(3,3);
        list.add(0,0, new BlankCell());
        assertTrue(list.get(0,0,0) instanceof BlankCell);
    }
}
