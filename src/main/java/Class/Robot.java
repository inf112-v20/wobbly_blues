package Class;

import Enums.Direction;
import Interface.IHand;
import Interface.IRobot;

public class Robot implements IRobot {

    private int hp;
    private Direction dir;
    private IHand hand;

    public Robot() {
        hp = 10;
        dir = Direction.UP;
    }

    @Override
    public void takeDamage() {
        hp--;
    }

    @Override
    public void shootLaser() {

    }

    @Override
    public void input() {

    }

    @Override
    public boolean powerDown() {
        return false;
    }

    @Override
    public void createHand() {

    }

    @Override
    public Direction getDirection() {
        return dir;
    }
}
