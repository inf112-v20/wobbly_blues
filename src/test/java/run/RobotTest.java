package run;

import org.junit.Test;
import classes.*;

import static org.junit.Assert.assertEquals;

public class RobotTest {

    @Test
    public void SimpleRobotMovementTest() {
        Robot robot = new Robot(null);
        int posX = robot.getPosX();
        int posY = robot.getPosY();
        robot.setPos(posX+1,posY+1);
        assertEquals(robot.getPosX(), posX+1, 0);
        assertEquals(robot.getPosY(), posY+1, 0);
    }
}
