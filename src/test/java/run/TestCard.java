package run;

import classes.Card;
import classes.Map;
import classes.Robot;
import com.badlogic.gdx.math.Vector2;
import enums.Direction;
import interfaces.ICard;
import interfaces.IRobot;
import org.junit.Test;
import org.junit.runner.RunWith;
import testrunner.GdxTestRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class TestCard {

    @Test
    public void testCard(){
        Map map = new Map("testboard.tmx");
        List<IRobot> players = map.placePlayers(1);
        IRobot robot = players.get(0);

        ICard card = new Card(Card.CardType.TURNRIGHT);
        card.setRobot(robot);

        assertEquals(Direction.UP, robot.getDirection());

        card.doAction(map);
        assertEquals(Direction.RIGHT, robot.getDirection());
    }
}
