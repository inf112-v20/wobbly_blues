package run;

import com.badlogic.gdx.math.*;
import enums.*;
import org.junit.Test;
import classes.Robot;

import static org.junit.Assert.assertEquals;

public class RobotTest {

    @Test
    public void SimpleRobotMovementTest() {
        Robot robot = new Robot(new Vector2(1,1), robotNames.getById(0));
        int posX = robot.getPosX();
        int posY = robot.getPosY();
        robot.setPos(posX+1,posY+1);
        assertEquals(posX+1, robot.getPosX(), 0);
        assertEquals(posY+1, robot.getPosY(), 0);
    }
}
