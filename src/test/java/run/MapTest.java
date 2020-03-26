package run;

import classes.*;
import interfaces.IRobot;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import testrunner.GdxTestRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests for the map class and how robots interact with it
 */
@RunWith(GdxTestRunner.class)
public class MapTest {

    Map map;

    @Before
    public void resetEnv(){
        map = new Map("testboard.tmx");
        map.placePlayers(8);
    }

    @Test
    public void testPlayerList(){
        List<IRobot> players = map.getPlayerList();
        assertEquals(8, players.size());
    }

    @Test
    public void testTwoPlayerCollisions(){
        IRobot robotLeft = map.getRobot(7,11);
        IRobot robotRight = map.getRobot(8,11);
        assertNotNull(robotLeft);
        assertNotNull(robotRight);
        Card cardRight = new Card(Card.CardType.TURNRIGHT);
        Card cardMove = new Card(Card.CardType.MOVEONE);
        cardRight.setRobot(robotLeft);
        cardMove.setRobot(robotLeft);

        cardRight.doAction(map);
        cardMove.doAction(map);

        assertEquals(8, robotLeft.getPosX());
        assertEquals(9, robotRight.getPosX());
    }

    @Test
    public void testMultiplePlayerCollisions(){
        IRobot robotLeft = map.getRobot(1,11);
        IRobot robotRight = map.getRobot(8,11);
        assertNotNull(robotLeft);
        assertNotNull(robotRight);
        Card cardRight = new Card(Card.CardType.TURNRIGHT);
        Card cardMove = new Card(Card.CardType.MOVEONE);
        cardRight.setRobot(robotLeft);
        cardMove.setRobot(robotLeft);

        cardRight.doAction(map);
        cardMove.doAction(map);

        assertEquals(2, robotLeft.getPosX());
        assertEquals(9, robotRight.getPosX());
    }

    @Test
    public void testIsOutHoleFlag(){

    }


}
