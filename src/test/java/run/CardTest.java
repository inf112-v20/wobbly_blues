package run;

import classes.Card;
import classes.Map;
import classes.Robot;
import enums.Direction;
import interfaces.IRobot;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import testrunner.GdxTestRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class CardTest {

    static Map map;
    static List<IRobot> players;
    static IRobot robot;

    @BeforeClass
    public static void init(){
        map = new Map("testboard.tmx");
        players = map.placePlayers(1);
        robot = players.get(0);
    }

    @Before
    public void resetEnv(){
        robot.setDirection(Direction.UP);
    }

    @Test
    public void cardTurnRightEffectOnRobotDirection(){
        Card card = new Card(Card.CardType.TURNRIGHT);
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
    public void cardUTurnThenMoveForward(){
        Card uTurn = new Card(Card.CardType.UTURN);
        Card move = new Card(Card.CardType.MOVEONE);
        uTurn.setRobot(robot);
        move.setRobot(robot);

        int x = robot.getPosX();
        int y = robot.getPosY();

        uTurn.doAction(map);
        assertEquals(Direction.DOWN, robot.getDirection());

        move.doAction(map);
        assertEquals(x, robot.getPosX());
        assertEquals(y-1, robot.getPosY());
    }
}
