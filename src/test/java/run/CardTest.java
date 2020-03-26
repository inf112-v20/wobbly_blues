package run;

import classes.Card;
import classes.Map;
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
    public void cardMakesRobotMoveOutOfBounds(){
        //Base is in the uppermost tile
        Card card = new Card(Card.CardType.MOVEONE);
        card.setRobot(robot);


        int hp = robot.getHp();
        int x = robot.getPosX();
        int y = robot.getPosY();

        assertEquals(3, hp);

        //Robot should die and respawn, meaning no change to position
        card.doAction(map);
        assertEquals(x, robot.getPosX());
        assertEquals(y, robot.getPosY());
        assertEquals(robot.getBp_x(), robot.getPosX());
        assertEquals(robot.getBp_y(), robot.getPosY());
        assertEquals(hp - 1, robot.getHp());
    }

    @Test
    public void cardUTurnThenMoveTwoForward(){
        Card uTurn = new Card(Card.CardType.UTURN);
        Card moveTwo = new Card(Card.CardType.MOVETWO);
        uTurn.setRobot(robot);
        moveTwo.setRobot(robot);

        int x = robot.getPosX();
        int y = robot.getPosY();

        uTurn.doAction(map);
        assertEquals(Direction.DOWN, robot.getDirection());

        moveTwo.doAction(map);
        assertEquals(x, robot.getPosX());
        assertEquals(y-2, robot.getPosY());
    }
}
