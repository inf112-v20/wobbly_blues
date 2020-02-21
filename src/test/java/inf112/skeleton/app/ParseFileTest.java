package inf112.skeleton.app;

import cells.BlankCell;
import cells.FlagCell;
import cells.HoleCell;
import classes.Map;
import classes.ParseXML;
import classes.ReadXML;
import interfaces.ICell;
import interfaces.IMap;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ParseFileTest {

    @Test
    public void testParse(){
        Map map = new Map(5,5);
        ReadXML reader = new ReadXML("assets/example.xml");
        int[][][] parseArr = reader.readFile(5,5, 5);
        ParseXML parser = new ParseXML();
        parser.parse(parseArr, map);
        assertEquals(map.getCellsAtPos(0,0).size(), 2);
        assertTrue(map.getCell(0,0,0) instanceof BlankCell);
        assertTrue(map.getCell(0,0,1) instanceof FlagCell);
        assertTrue(map.getCell(2,2,1) instanceof HoleCell);
    }
}
