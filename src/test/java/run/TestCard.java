package run;

import classes.Card;
import classes.Map;
import enums.Direction;
import interfaces.ICard;
import interfaces.IRobot;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import testrunner.GdxTestRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class TestCard {

    static Map map;
    static List<IRobot> players;
    static IRobot robot;

    @BeforeClass
    public static void init(){
        map = new Map("testboard.tmx");
        players = map.placePlayers(1);
        robot = players.get(0);
    }

    @Test
    public void testCardTurnRightEffectOnRobotDirection(){
        ICard card = new Card(Card.CardType.TURNRIGHT);
        card.setRobot(robot);

        assertEquals(Direction.UP, robot.getDirection());

        card.doAction(map);
        assertEquals(Direction.RIGHT, robot.getDirection());
        card.doAction(map);
        assertEquals(Direction.DOWN, robot.getDirection());
        card.doAction(map);
        assertEquals(Direction.LEFT, robot.getDirection());
        card.doAction(map);
        assertEquals(Direction.UP, robot.getDirection());
    }

    @Test
    public void testCardMakesRobotMoveOutOfBounds(){
        //Base is in the uppermost tile
        ICard card = new Card(Card.CardType.MOVEONE);
        card.setRobot(robot);

        assertEquals(Direction.UP, robot.getDirection());
        int x = robot.getPosX();
        int y = robot.getPosY();

        //Robot should die and respawn, meaning no change to position
        card.doAction(map);
        assertEquals(x, robot.getPosX());
        assertEquals(y, robot.getPosY());
    }
}
