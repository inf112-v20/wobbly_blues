package inf112.skeleton.app;

import org.junit.Test;
import Class.*;

import static org.junit.Assert.assertEquals;

public class RobotTest {

    @Test
    public void SimpleRobotMovementTest() {
        Robot robot = new Robot(null);
        float posX = robot.getPosX();
        float posY = robot.getPosY();
        robot.setPos(posX+1,posY+1);
        assertEquals((int)robot.getPosX(), (int)posX+1);
        assertEquals((int)robot.getPosY(), (int)posY+1);
    }
}
