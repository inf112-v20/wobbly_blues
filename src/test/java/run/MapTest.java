package run;

import classes.*;
import com.badlogic.gdx.math.Vector2;
import enums.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import testrunner.GdxTestRunner;

import java.util.List;

import static org.junit.Assert.*;

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
        List<Robot> players = map.getPlayerList();
         assertEquals(8, players.size());
    }

    @Test
    public void testTwoPlayerCollisions(){
        Robot robotLeft = map.getRobot(7,11);
        Robot robotRight = map.getRobot(8,11);
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
    public void testPlayerPushesMultipleRobots(){
        Robot robotLeft = map.getRobot(1,11);
        Robot robotMid = map.getRobot(4, 11);
        Robot robotRight = map.getRobot(8,11);
        assertNotNull(robotLeft);
        assertNotNull(robotMid);
        assertNotNull(robotRight);

        Card cardRight = new Card(Card.CardType.TURNRIGHT);
        Card cardMove = new Card(Card.CardType.MOVEONE);
        cardRight.setRobot(robotLeft);
        cardMove.setRobot(robotLeft);

        cardRight.doAction(map);
        cardMove.doAction(map);

        assertEquals(2, robotLeft.getPosX());
        assertEquals(5, robotMid.getPosX());
        assertEquals(9, robotRight.getPosX());
    }

    @Test
    public void testCanGo(){
        Robot robot = new Robot(new Vector2(5,7), RobotNames.getById(0));
        for (Direction dir : Direction.values()){
            assertFalse(map.canGo(robot.getPosX(), robot.getPosY(), dir));
        }

        robot.setPos(9, 5);
        for (Direction dir : Direction.values()){
            assertTrue(map.canGo(robot.getPosX(), robot.getPosY(), dir));
        }

        robot.setPos(7, 7);
        assertFalse(map.canGo(robot.getPosX(), robot.getPosY(), Direction.RIGHT));
        assertFalse(map.canGo(robot.getPosX(), robot.getPosY(), Direction.DOWN));
        assertTrue(map.canGo(robot.getPosX(), robot.getPosY(), Direction.UP));
        assertTrue(map.canGo(robot.getPosX(), robot.getPosY(), Direction.LEFT));
    }

}
