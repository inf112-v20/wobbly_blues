package deprecated;

import deprecated.classes.ReadXML;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReadFileTest {

    @Test
    public void testRead() {
        ReadXML reader = new ReadXML("assets/example.xml");
        int[][][] result = reader.readFile(5,5, 4);
        assertEquals(result[0][0][0], 5);
        assertEquals(result[0][0][3], 0);
        assertEquals(result[2][2][1], 6);
    }
}
